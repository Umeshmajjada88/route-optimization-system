package com.umesh.route_optimization_service.controller;

import com.umesh.route_optimization_service.graph.algorithm.AlgorithmType;
import com.umesh.route_optimization_service.graph.dto.GraphResponse;
import com.umesh.route_optimization_service.graph.model.PathResult;
import com.umesh.route_optimization_service.graph.service.GraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/graph")
@RequiredArgsConstructor
public class GraphController {

    private final GraphService graphService;

    @GetMapping
    public GraphResponse getGraph() {

        return graphService.getGraph();

    }

    @GetMapping("/shortest-path")
    public PathResult shortestPath(
            @RequestParam Long source,
            @RequestParam Long destination,
            @RequestParam(defaultValue = "DIJKSTRA") AlgorithmType algorithm) {

        return graphService.shortestPath(
                source,
                destination,
                algorithm);

    }

}