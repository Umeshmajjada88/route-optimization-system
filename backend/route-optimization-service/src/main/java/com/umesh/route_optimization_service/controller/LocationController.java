package com.umesh.route_optimization_service.controller;

import com.umesh.route_optimization_service.common.response.ApiResponse;
import com.umesh.route_optimization_service.dto.request.LocationRequest;
import com.umesh.route_optimization_service.dto.response.LocationResponse;
import com.umesh.route_optimization_service.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService service;

    @PostMapping
    public ResponseEntity<ApiResponse<LocationResponse>> createLocation(
            @Valid @RequestBody LocationRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<LocationResponse>builder()
                        .success(true)
                        .message("Location created successfully.")
                        .data(service.createLocation(request))
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LocationResponse>> getLocation(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<LocationResponse>builder()
                        .success(true)
                        .message("Location fetched successfully.")
                        .data(service.getLocation(id))
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<LocationResponse>>> getAllLocations() {

        return ResponseEntity.ok(
                ApiResponse.<List<LocationResponse>>builder()
                        .success(true)
                        .message("Locations fetched successfully.")
                        .data(service.getAllLocations())
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LocationResponse>> updateLocation(
            @PathVariable Long id,
            @Valid @RequestBody LocationRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<LocationResponse>builder()
                        .success(true)
                        .message("Location updated successfully.")
                        .data(service.updateLocation(id, request))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLocation(@PathVariable Long id) {

        service.deleteLocation(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Location deleted successfully.")
                        .build());
    }
}