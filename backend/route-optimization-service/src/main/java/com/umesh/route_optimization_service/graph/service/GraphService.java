package com.umesh.route_optimization_service.graph.service;

import com.umesh.route_optimization_service.graph.algorithm.shortestpath.DijkstraAlgorithm;
import com.umesh.route_optimization_service.graph.dto.GraphResponse;
import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.PathResult;
import com.umesh.route_optimization_service.mapper.GraphMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraphService {

    private final GraphBuilder graphBuilder;

    private final DijkstraAlgorithm dijkstraAlgorithm;

    public GraphResponse getGraph() {

        Graph graph = graphBuilder.buildGraph();

        return GraphMapper.toResponse(graph);

    }

    public PathResult shortestPath(Long source,
            Long destination) {

        Graph graph = graphBuilder.buildGraph();

        return dijkstraAlgorithm.findShortestPath(
                graph,
                source,
                destination);

    }
}