package com.umesh.route_optimization_service.controller;

import com.umesh.route_optimization_service.common.response.ApiResponse;
import com.umesh.route_optimization_service.dto.request.RoadSegmentRequest;
import com.umesh.route_optimization_service.dto.response.RoadSegmentResponse;
import com.umesh.route_optimization_service.service.RoadSegmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roads")
@RequiredArgsConstructor
public class RoadSegmentController {

    private final RoadSegmentService service;

    @PostMapping
    public ResponseEntity<ApiResponse<RoadSegmentResponse>> create(
            @Valid @RequestBody RoadSegmentRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<RoadSegmentResponse>builder()
                        .success(true)
                        .message("Road created successfully")
                        .data(service.createRoadSegment(request))
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoadSegmentResponse>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<RoadSegmentResponse>>builder()
                        .success(true)
                        .message("Roads fetched successfully")
                        .data(service.getAllRoadSegments())
                        .build());
    }
}