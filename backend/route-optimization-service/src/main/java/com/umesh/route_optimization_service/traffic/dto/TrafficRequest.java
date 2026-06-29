package com.umesh.route_optimization_service.traffic.dto;

import com.umesh.route_optimization_service.traffic.entity.TrafficLevel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrafficRequest {

    @NotNull
    private Long roadSegmentId;

    @NotNull
    private TrafficLevel trafficLevel;

    @NotNull
    private Double averageSpeed;

}