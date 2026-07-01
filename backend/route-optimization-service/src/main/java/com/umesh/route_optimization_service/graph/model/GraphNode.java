package com.umesh.route_optimization_service.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraphNode implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Double latitude;

    private Double longitude;

}