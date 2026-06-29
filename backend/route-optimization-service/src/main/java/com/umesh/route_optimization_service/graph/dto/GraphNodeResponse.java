package com.umesh.route_optimization_service.graph.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraphNodeResponse {

    private Long id;

    private String name;

    private Double latitude;

    private Double longitude;
}