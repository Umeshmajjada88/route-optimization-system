package com.umesh.route_optimization_service.controller;

import com.umesh.route_optimization_service.common.response.ApiResponse;
import com.umesh.route_optimization_service.dto.request.RouteRequest;
import com.umesh.route_optimization_service.dto.response.RouteResponse;
import com.umesh.route_optimization_service.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/optimize")
    public ResponseEntity<ApiResponse<RouteResponse>> optimizeRoute(
            @Valid @RequestBody RouteRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<RouteResponse>builder()
                        .success(true)
                        .message("Route optimized successfully.")
                        .data(routeService.optimizeRoute(request))
                        .build());

    }

}