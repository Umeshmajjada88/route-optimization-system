package com.umesh.route_optimization_service.traffic.service.impl;

import com.umesh.route_optimization_service.entity.RoadSegment;
import com.umesh.route_optimization_service.exception.ResourceNotFoundException;
import com.umesh.route_optimization_service.repository.RoadSegmentRepository;
import com.umesh.route_optimization_service.traffic.dto.TrafficRequest;
import com.umesh.route_optimization_service.traffic.dto.TrafficResponse;
import com.umesh.route_optimization_service.traffic.entity.TrafficInfo;
import com.umesh.route_optimization_service.traffic.mapper.TrafficMapper;
import com.umesh.route_optimization_service.traffic.repository.TrafficInfoRepository;
import com.umesh.route_optimization_service.traffic.service.TrafficService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;

@Service
@RequiredArgsConstructor
public class TrafficServiceImpl implements TrafficService {

    private final TrafficInfoRepository trafficRepository;

    private final RoadSegmentRepository roadRepository;

    @Override
    @CacheEvict(value = "graphCache", allEntries = true)
    public TrafficResponse updateTraffic(TrafficRequest request) {

        RoadSegment road = roadRepository.findById(request.getRoadSegmentId())
                .orElseThrow(() ->
        new ResourceNotFoundException("Road segment not found"));

        TrafficInfo traffic = trafficRepository.findByRoadSegmentId(road.getId())
                .orElse(new TrafficInfo());

        traffic.setRoadSegment(road);

        traffic.setTrafficLevel(request.getTrafficLevel());

        traffic.setAverageSpeed(request.getAverageSpeed());

        trafficRepository.save(traffic);

        return TrafficMapper.toResponse(traffic);

    }

}