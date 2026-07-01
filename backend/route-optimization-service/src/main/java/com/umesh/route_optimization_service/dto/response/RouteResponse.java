package com.umesh.route_optimization_service.dto.response;

import com.umesh.route_optimization_service.graph.model.AlgorithmStatistics;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RouteResponse {

    private String algorithm;

    private Double totalDistance;

    private Double totalTravelTime;

    private List<RouteNodeResponse> path;

    private AlgorithmStatistics statistics;

}