package com.umesh.route_optimization_service.graph.factory;

import com.umesh.route_optimization_service.graph.algorithm.AStarAlgorithm;
import com.umesh.route_optimization_service.graph.algorithm.AlgorithmType;
import com.umesh.route_optimization_service.graph.algorithm.DijkstraAlgorithm;
import com.umesh.route_optimization_service.graph.algorithm.PathFindingAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlgorithmFactory {

    private final DijkstraAlgorithm dijkstraAlgorithm;

    private final AStarAlgorithm aStarAlgorithm;

    public PathFindingAlgorithm getAlgorithm(AlgorithmType type) {

        return switch (type) {

            case DIJKSTRA -> dijkstraAlgorithm;

            case ASTAR -> aStarAlgorithm;

        };

    }

}