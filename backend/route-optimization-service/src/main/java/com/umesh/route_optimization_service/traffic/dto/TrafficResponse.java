package com.umesh.route_optimization_service.traffic.dto;

import com.umesh.route_optimization_service.traffic.entity.TrafficLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrafficResponse {

    private Long id;

    private Long roadSegmentId;

    private TrafficLevel trafficLevel;

    private Double averageSpeed;

}