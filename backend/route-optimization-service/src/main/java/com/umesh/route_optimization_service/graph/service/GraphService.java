package com.umesh.route_optimization_service.graph.service;

import com.umesh.route_optimization_service.graph.algorithm.AlgorithmType;
import com.umesh.route_optimization_service.graph.algorithm.PathFindingAlgorithm;
import com.umesh.route_optimization_service.graph.dto.GraphResponse;
import com.umesh.route_optimization_service.graph.factory.AlgorithmFactory;
import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.PathResult;
import com.umesh.route_optimization_service.mapper.GraphMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraphService {

    private final GraphBuilder graphBuilder;

    private final AlgorithmFactory algorithmFactory;

    public GraphResponse getGraph() {

        Graph graph = graphBuilder.buildGraph();

        return GraphMapper.toResponse(graph);

    }

    public PathResult shortestPath(
            Long source,
            Long destination,
            AlgorithmType algorithmType) {

        Graph graph = graphBuilder.buildGraph();

        PathFindingAlgorithm algorithm = algorithmFactory.getAlgorithm(
                algorithmType);

        return algorithm.findPath(
                graph,
                source,
                destination);

    }

}