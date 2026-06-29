package com.umesh.route_optimization_service.mapper;

import com.umesh.route_optimization_service.dto.request.LocationRequest;
import com.umesh.route_optimization_service.dto.response.LocationResponse;
import com.umesh.route_optimization_service.entity.Location;

public class LocationMapper {

    private LocationMapper() {
    }

    public static Location toEntity(LocationRequest request) {

        return Location.builder()
                .code(request.getCode())
                .name(request.getName())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .locationType(request.getLocationType())
                .active(true)
                .build();
    }

    public static LocationResponse toResponse(Location location) {

        return LocationResponse.builder()
                .id(location.getId())
                .code(location.getCode())
                .name(location.getName())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .city(location.getCity())
                .state(location.getState())
                .country(location.getCountry())
                .locationType(location.getLocationType())
                .active(location.getActive())
                .build();
    }
}