package com.umesh.route_optimization_service.graph.service;

import com.umesh.route_optimization_service.entity.Location;
import com.umesh.route_optimization_service.entity.RoadSegment;
import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.GraphEdge;
import com.umesh.route_optimization_service.graph.model.GraphNode;
import com.umesh.route_optimization_service.repository.LocationRepository;
import com.umesh.route_optimization_service.repository.RoadSegmentRepository;
import com.umesh.route_optimization_service.traffic.repository.TrafficInfoRepository;
import com.umesh.route_optimization_service.traffic.entity.TrafficInfo;
import com.umesh.route_optimization_service.traffic.entity.TrafficLevel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

@Service
@RequiredArgsConstructor
public class GraphBuilder {

    private final LocationRepository locationRepository;

    private final RoadSegmentRepository roadRepository;

    private final TrafficInfoRepository trafficRepository;

    @Cacheable("graphCache")
    public Graph buildGraph()  {

        Graph graph = new Graph();

        for (Location location : locationRepository.findAll()) {

            graph.addNode(
                    GraphNode.builder()
                            .id(location.getId())
                            .name(location.getName())
                            .latitude(location.getLatitude())
                            .longitude(location.getLongitude())
                            .build());

        }

        for (RoadSegment road : roadRepository.findAll()) {

            TrafficInfo traffic = trafficRepository
                    .findByRoadSegmentId(road.getId())
                    .orElse(null);

            double averageSpeed = traffic != null ? traffic.getAverageSpeed() : 60.0;

            TrafficLevel level = traffic != null ? traffic.getTrafficLevel() : TrafficLevel.LOW;

            double travelTime;

            if (level == TrafficLevel.BLOCKED || averageSpeed <= 0) {

                travelTime = Double.MAX_VALUE;

            } else {

                travelTime = (road.getDistance() / averageSpeed) * 60.0;

            }

            // Forward Edge
            graph.addEdge(
                    GraphEdge.builder()
                            .sourceId(road.getSourceLocation().getId())
                            .destinationId(road.getDestinationLocation().getId())
                            .distance(road.getDistance())
                            .travelTime(travelTime)
                            .trafficLevel(level)
                            .build());

            // Reverse Edge (for two-way roads)
            if (!road.getOneWay()) {

                graph.addEdge(
                        GraphEdge.builder()
                                .sourceId(road.getDestinationLocation().getId())
                                .destinationId(road.getSourceLocation().getId())
                                .distance(road.getDistance())
                                .travelTime(travelTime)
                                .trafficLevel(level)
                                .build());

            }

        }

        return graph;
    }

}