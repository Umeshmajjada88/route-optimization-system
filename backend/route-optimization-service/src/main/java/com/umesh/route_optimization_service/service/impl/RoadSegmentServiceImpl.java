package com.umesh.route_optimization_service.service.impl;

import com.umesh.route_optimization_service.dto.request.RoadSegmentRequest;
import com.umesh.route_optimization_service.dto.response.RoadSegmentResponse;
import com.umesh.route_optimization_service.entity.Location;
import com.umesh.route_optimization_service.entity.RoadSegment;
import com.umesh.route_optimization_service.exception.ResourceNotFoundException;
import com.umesh.route_optimization_service.mapper.RoadSegmentMapper;
import com.umesh.route_optimization_service.repository.LocationRepository;
import com.umesh.route_optimization_service.repository.RoadSegmentRepository;
import com.umesh.route_optimization_service.service.RoadSegmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoadSegmentServiceImpl implements RoadSegmentService {

    private final RoadSegmentRepository roadRepository;
    private final LocationRepository locationRepository;

    @Override
    public RoadSegmentResponse createRoadSegment(RoadSegmentRequest request) {

        Location source = locationRepository.findById(request.getSourceLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Source location not found"));

        Location destination = locationRepository.findById(request.getDestinationLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Destination location not found"));

        if (source.getId().equals(destination.getId())) {
            throw new IllegalArgumentException("Source and destination cannot be the same.");
        }

        RoadSegment road = RoadSegmentMapper.toEntity(
                source,
                destination,
                request.getDistance(),
                request.getSpeedLimit(),
                request.getRoadType(),
                request.getOneWay());

        return RoadSegmentMapper.toResponse(roadRepository.save(road));
    }

    @Override
    public RoadSegmentResponse getRoadSegment(Long id) {

        return RoadSegmentMapper.toResponse(
                roadRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Road not found")));
    }

    @Override
    public List<RoadSegmentResponse> getAllRoadSegments() {

        return roadRepository.findAll()
                .stream()
                .map(RoadSegmentMapper::toResponse)
                .toList();
    }

    @Override
    public RoadSegmentResponse updateRoadSegment(Long id, RoadSegmentRequest request) {
        throw new UnsupportedOperationException("Will implement later");
    }

    @Override
    public void deleteRoadSegment(Long id) {

        RoadSegment road = roadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Road not found"));

        roadRepository.delete(road);
    }
}