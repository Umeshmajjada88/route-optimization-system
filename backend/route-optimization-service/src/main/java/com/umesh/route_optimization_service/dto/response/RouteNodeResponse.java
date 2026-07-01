package com.umesh.route_optimization_service.dto.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteNodeResponse implements Serializable{
    

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Double latitude;

    private Double longitude;

}