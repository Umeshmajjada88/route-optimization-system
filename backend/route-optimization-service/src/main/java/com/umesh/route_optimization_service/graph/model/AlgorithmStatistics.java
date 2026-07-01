package com.umesh.route_optimization_service.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmStatistics {

    private String algorithm;

    private Integer visitedNodes;

    private Long executionTimeMs;

}