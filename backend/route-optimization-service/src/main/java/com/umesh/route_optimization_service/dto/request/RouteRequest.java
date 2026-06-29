package com.umesh.route_optimization_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RouteRequest {

    @NotNull
    private Long sourceLocationId;

    @NotNull
    private Long destinationLocationId;

}