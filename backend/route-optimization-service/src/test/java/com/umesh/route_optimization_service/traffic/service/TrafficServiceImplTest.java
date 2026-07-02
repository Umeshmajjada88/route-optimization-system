package com.umesh.route_optimization_service.traffic.service;

import com.umesh.route_optimization_service.entity.Location;
import com.umesh.route_optimization_service.entity.RoadSegment;
import com.umesh.route_optimization_service.entity.RoadType;
import com.umesh.route_optimization_service.graph.service.GraphBuilder;
import com.umesh.route_optimization_service.repository.RoadSegmentRepository;
import com.umesh.route_optimization_service.traffic.dto.TrafficRequest;
import com.umesh.route_optimization_service.traffic.dto.TrafficResponse;
import com.umesh.route_optimization_service.traffic.entity.TrafficInfo;
import com.umesh.route_optimization_service.traffic.entity.TrafficLevel;
import com.umesh.route_optimization_service.traffic.repository.TrafficInfoRepository;
import com.umesh.route_optimization_service.traffic.service.impl.TrafficServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrafficServiceImplTest {

    @Mock
private TrafficInfoRepository trafficRepository;

@Mock
private RoadSegmentRepository roadRepository;

@Mock
private GraphBuilder graphBuilder;

@InjectMocks
private TrafficServiceImpl trafficService;

    @Test
    void shouldUpdateTrafficSuccessfully() {

        Location source = new Location();
        source.setId(1L);

        Location destination = new Location();
        destination.setId(2L);

        RoadSegment road = new RoadSegment();
        road.setId(100L);
        road.setSourceLocation(source);
        road.setDestinationLocation(destination);
        road.setDistance(10.0);
        road.setSpeedLimit(60);
        road.setRoadType(RoadType.HIGHWAY);

        TrafficInfo traffic = new TrafficInfo();
        traffic.setRoadSegment(road);

        TrafficRequest request = new TrafficRequest();
        request.setRoadSegmentId(100L);
        request.setTrafficLevel(TrafficLevel.HIGH);
        request.setAverageSpeed(25.0);

        when(roadRepository.findById(100L))
                .thenReturn(Optional.of(road));

        when(trafficRepository.findByRoadSegmentId(100L))
                .thenReturn(Optional.of(traffic));

        when(trafficRepository.save(any(TrafficInfo.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        doNothing().when(graphBuilder).evictGraphCache();

        TrafficResponse response = trafficService.updateTraffic(request);

        assertNotNull(response);

        assertEquals(
                TrafficLevel.HIGH,
                response.getTrafficLevel());

        assertEquals(
                25.0,
                response.getAverageSpeed());

        verify(trafficRepository)
                .save(any(TrafficInfo.class));

        verify(graphBuilder)
                .evictGraphCache();

    }

}