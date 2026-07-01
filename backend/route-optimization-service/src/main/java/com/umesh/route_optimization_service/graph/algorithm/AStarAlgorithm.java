package com.umesh.route_optimization_service.graph.algorithm;

import com.umesh.route_optimization_service.graph.model.AlgorithmStatistics;
import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.GraphEdge;
import com.umesh.route_optimization_service.graph.model.GraphNode;
import com.umesh.route_optimization_service.graph.model.PathResult;
import com.umesh.route_optimization_service.graph.util.HeuristicCalculator;
import com.umesh.route_optimization_service.graph.util.PathReconstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@Component
@RequiredArgsConstructor
public class AStarAlgorithm implements PathFindingAlgorithm {

    private final HeuristicCalculator heuristicCalculator;

    private final PathReconstructor pathReconstructor;

    @Override
    public PathResult findPath(
            Graph graph,
            Long source,
            Long destination) {

        long startTime = System.currentTimeMillis();

        int visitedNodes = 0;

        Map<Long, Double> gScore = new HashMap<>();

        Map<Long, Double> fScore = new HashMap<>();

        Map<Long, Long> previous = new HashMap<>();

        PriorityQueue<NodeScore> openSet = new PriorityQueue<>(
                Comparator.comparingDouble(NodeScore::score));

        GraphNode destinationNode = graph.getNode(destination);

        for (GraphNode node : graph.getNodes()) {

            gScore.put(node.getId(), Double.MAX_VALUE);

            fScore.put(node.getId(), Double.MAX_VALUE);

        }

        gScore.put(source, 0.0);

        fScore.put(
                source,
                heuristicCalculator.calculate(
                        graph.getNode(source),
                        destinationNode));

        openSet.offer(
                new NodeScore(
                        source,
                        fScore.get(source)));

        while (!openSet.isEmpty()) {

            NodeScore current = openSet.poll();

            visitedNodes++;

            if (current.nodeId().equals(destination)) {
                break;
            }

            for (GraphEdge edge : graph.getNeighbors(current.nodeId())) {

                double tentativeGScore = gScore.get(current.nodeId())
                        + edge.getTravelTime();

                if (tentativeGScore < gScore.get(edge.getDestinationId())) {

                    previous.put(
                            edge.getDestinationId(),
                            current.nodeId());

                    gScore.put(
                            edge.getDestinationId(),
                            tentativeGScore);

                    double estimatedCost = tentativeGScore
                            + heuristicCalculator.calculate(
                                    graph.getNode(edge.getDestinationId()),
                                    destinationNode);

                    fScore.put(
                            edge.getDestinationId(),
                            estimatedCost);

                    openSet.offer(
                            new NodeScore(
                                    edge.getDestinationId(),
                                    estimatedCost));

                }

            }

        }

        long endTime = System.currentTimeMillis();

        AlgorithmStatistics statistics = AlgorithmStatistics.builder()
                .algorithm("ASTAR")
                .visitedNodes(visitedNodes)
                .executionTimeMs(endTime - startTime)
                .build();

        return pathReconstructor.buildResult(
                graph,
                previous,
                gScore,
                destination,
                statistics);

    }

    private record NodeScore(
            Long nodeId,
            Double score) {
    }

}