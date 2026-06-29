package com.umesh.route_optimization_service.dto.response;

import com.umesh.route_optimization_service.entity.LocationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationResponse {

    private Long id;

    private String code;

    private String name;

    private Double latitude;

    private Double longitude;

    private String city;

    private String state;

    private String country;

    private LocationType locationType;

    private Boolean active;
}