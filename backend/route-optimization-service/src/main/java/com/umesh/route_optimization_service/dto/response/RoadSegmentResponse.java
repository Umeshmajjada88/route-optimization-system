package com.umesh.route_optimization_service.dto.response;

import com.umesh.route_optimization_service.entity.RoadType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoadSegmentResponse {

    private Long id;

    private Long sourceLocationId;

    private Long destinationLocationId;

    private Double distance;

    private Integer speedLimit;

    private RoadType roadType;

    private Boolean oneWay;

    private Boolean active;
}