package com.umesh.route_optimization_service.traffic.mapper;

import com.umesh.route_optimization_service.traffic.dto.TrafficResponse;
import com.umesh.route_optimization_service.traffic.entity.TrafficInfo;

public class TrafficMapper {

    private TrafficMapper() {
    }

    public static TrafficResponse toResponse(TrafficInfo traffic) {

        return TrafficResponse.builder()
                .id(traffic.getId())
                .roadSegmentId(traffic.getRoadSegment().getId())
                .trafficLevel(traffic.getTrafficLevel())
                .averageSpeed(traffic.getAverageSpeed())
                .build();

    }

}