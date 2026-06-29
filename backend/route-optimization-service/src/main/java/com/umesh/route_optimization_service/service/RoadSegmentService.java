package com.umesh.route_optimization_service.service;

import com.umesh.route_optimization_service.dto.request.RoadSegmentRequest;
import com.umesh.route_optimization_service.dto.response.RoadSegmentResponse;

import java.util.List;

public interface RoadSegmentService {

    RoadSegmentResponse createRoadSegment(RoadSegmentRequest request);

    RoadSegmentResponse getRoadSegment(Long id);

    List<RoadSegmentResponse> getAllRoadSegments();

    RoadSegmentResponse updateRoadSegment(Long id, RoadSegmentRequest request);

    void deleteRoadSegment(Long id);
}