package com.umesh.route_optimization_service.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficUpdateMessage {

    private Long roadSegmentId;

    private String trafficLevel;

    private Double averageSpeed;

    private Long timestamp;

}