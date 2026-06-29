package com.umesh.route_optimization_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteNodeResponse {

    private Long id;

    private String name;

    private Double latitude;

    private Double longitude;

}