package com.umesh.route_optimization_service.dto.request;

import com.umesh.route_optimization_service.graph.algorithm.AlgorithmType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RouteRequest {

    @NotNull
    private Long sourceId;

    @NotNull
    private Long destinationId;

    @NotNull
    private AlgorithmType algorithm;

}