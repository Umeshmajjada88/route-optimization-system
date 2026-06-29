package com.umesh.route_optimization_service.service;

import com.umesh.route_optimization_service.dto.request.LocationRequest;
import com.umesh.route_optimization_service.dto.response.LocationResponse;

import java.util.List;

public interface LocationService {

    LocationResponse createLocation(LocationRequest request);

    LocationResponse getLocation(Long id);

    List<LocationResponse> getAllLocations();

    LocationResponse updateLocation(Long id, LocationRequest request);

    void deleteLocation(Long id);
}