package com.umesh.route_optimization_service.graph.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraphResponse {

    private List<GraphNodeResponse> nodes;

    private List<GraphEdgeResponse> edges;
}