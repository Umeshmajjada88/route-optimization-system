package com.umesh.route_optimization_service.graph.service;

import com.umesh.route_optimization_service.entity.Location;
import com.umesh.route_optimization_service.entity.LocationType;
import com.umesh.route_optimization_service.entity.RoadSegment;
import com.umesh.route_optimization_service.entity.RoadType;
import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.repository.LocationRepository;
import com.umesh.route_optimization_service.repository.RoadSegmentRepository;
import com.umesh.route_optimization_service.traffic.entity.TrafficInfo;
import com.umesh.route_optimization_service.traffic.entity.TrafficLevel;
import com.umesh.route_optimization_service.traffic.repository.TrafficInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GraphBuilderTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private RoadSegmentRepository roadRepository;

    @Mock
    private TrafficInfoRepository trafficRepository;

    @InjectMocks
    private GraphBuilder graphBuilder;

    private Location source;

    private Location destination;

    private RoadSegment road;

    @BeforeEach
    void setUp() {

        source = new Location();

        source.setId(1L);
        source.setName("Source");
        source.setLatitude(17.0);
        source.setLongitude(78.0);
        source.setLocationType(LocationType.WAREHOUSE);

        destination = new Location();

        destination.setId(2L);
        destination.setName("Destination");
        destination.setLatitude(17.1);
        destination.setLongitude(78.1);
        destination.setLocationType(LocationType.HUB);

        road = new RoadSegment();

        road.setId(100L);
        road.setSourceLocation(source);
        road.setDestinationLocation(destination);
        road.setDistance(10.0);
        road.setSpeedLimit(60);
        road.setRoadType(RoadType.HIGHWAY);
        road.setOneWay(false);

    }

    @Test
    void shouldBuildGraphWithNodesAndEdges() {

        TrafficInfo traffic = new TrafficInfo();

        traffic.setRoadSegment(road);
        traffic.setTrafficLevel(TrafficLevel.LOW);
        traffic.setAverageSpeed(60.0);

        when(locationRepository.findAll())
                .thenReturn(List.of(source, destination));

        when(roadRepository.findAll())
                .thenReturn(List.of(road));

        when(trafficRepository.findByRoadSegmentId(100L))
                .thenReturn(Optional.of(traffic));

        Graph graph = graphBuilder.buildGraph();

        assertEquals(
                2,
                graph.getNodes().size());

        assertEquals(
                1,
                graph.getNeighbors(1L).size());

        assertEquals(
                1,
                graph.getNeighbors(2L).size());

    }

}