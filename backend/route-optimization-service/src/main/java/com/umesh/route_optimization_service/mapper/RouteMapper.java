package com.umesh.route_optimization_service.mapper;

import com.umesh.route_optimization_service.dto.response.RouteNodeResponse;
import com.umesh.route_optimization_service.dto.response.RouteResponse;
import com.umesh.route_optimization_service.graph.model.GraphNode;
import com.umesh.route_optimization_service.graph.model.PathResult;

import java.util.List;

public class RouteMapper {

    private RouteMapper() {
    }

    public static RouteResponse toResponse(PathResult pathResult) {

        List<RouteNodeResponse> nodes = pathResult.getPath()
                .stream()
                .map(RouteMapper::toNodeResponse)
                .toList();

        double travelTime = pathResult.getTotalTravelTime();

        return RouteResponse.builder()
                .algorithm(pathResult.getStatistics().getAlgorithm())
                .totalDistance(pathResult.getTotalDistance())
                .totalTravelTime(travelTime)
                .statistics(pathResult.getStatistics())
                .path(nodes)
                .build();

    }

    private static RouteNodeResponse toNodeResponse(GraphNode node) {

        return RouteNodeResponse.builder()
                .id(node.getId())
                .name(node.getName())
                .latitude(node.getLatitude())
                .longitude(node.getLongitude())
                .build();

    }

}