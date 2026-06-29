package com.umesh.route_optimization_service.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PathResult {

    private Double totalDistance;

    private Double totalTravelTime;

    private List<GraphNode> path;

}