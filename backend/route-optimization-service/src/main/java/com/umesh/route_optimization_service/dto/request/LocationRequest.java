package com.umesh.route_optimization_service.dto.request;

import com.umesh.route_optimization_service.entity.LocationType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocationRequest {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotNull
    @Min(-90)
    @Max(90)
    private Double latitude;

    @NotNull
    @Min(-180)
    @Max(180)
    private Double longitude;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotNull
    private LocationType locationType;
}