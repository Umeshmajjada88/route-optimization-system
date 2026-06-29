package com.umesh.route_optimization_service.dto.request;

import com.umesh.route_optimization_service.entity.RoadType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoadSegmentRequest {

    @NotNull
    private Long sourceLocationId;

    @NotNull
    private Long destinationLocationId;

    @NotNull
    private Double distance;

    @NotNull
    private Integer speedLimit;

    @NotNull
    private RoadType roadType;

    private Boolean oneWay;
}