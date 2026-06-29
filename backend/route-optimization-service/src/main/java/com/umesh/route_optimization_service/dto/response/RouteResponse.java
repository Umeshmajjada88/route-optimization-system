package com.umesh.route_optimization_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RouteResponse {

    private Double totalDistance;

    private Double totalTravelTime;

    private List<RouteNodeResponse> path;

}