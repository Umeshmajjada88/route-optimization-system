package com.umesh.route_optimization_service.graph.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraphEdgeResponse {

    private Long sourceId;

    private Long destinationId;

    private Double distance;
}