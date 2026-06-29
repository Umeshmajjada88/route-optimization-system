package com.umesh.route_optimization_service.traffic.controller;

import com.umesh.route_optimization_service.common.response.ApiResponse;
import com.umesh.route_optimization_service.traffic.dto.TrafficRequest;
import com.umesh.route_optimization_service.traffic.dto.TrafficResponse;
import com.umesh.route_optimization_service.traffic.service.TrafficService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/traffic")
@RequiredArgsConstructor
public class TrafficController {

    private final TrafficService trafficService;

    @PostMapping
    public ResponseEntity<ApiResponse<TrafficResponse>> updateTraffic(
            @Valid @RequestBody TrafficRequest request) {

        return ResponseEntity.ok(

                ApiResponse.<TrafficResponse>builder()
                        .success(true)
                        .message("Traffic updated successfully.")
                        .data(trafficService.updateTraffic(request))
                        .build()

        );

    }

}