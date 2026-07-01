package com.umesh.route_optimization_service.graph.model;

import com.umesh.route_optimization_service.traffic.entity.TrafficLevel;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Data
@Builder
public class GraphEdge implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long sourceId;

    private Long destinationId;

    private Double distance;

    private Double travelTime;

    private TrafficLevel trafficLevel;

}