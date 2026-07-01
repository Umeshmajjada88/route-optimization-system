package com.umesh.route_optimization_service.graph.algorithm;

import com.umesh.route_optimization_service.graph.model.AlgorithmStatistics;
import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.GraphEdge;
import com.umesh.route_optimization_service.graph.model.GraphNode;
import com.umesh.route_optimization_service.graph.model.PathResult;
import com.umesh.route_optimization_service.graph.util.PathReconstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@Component
@RequiredArgsConstructor
public class DijkstraAlgorithm implements PathFindingAlgorithm {

    private final PathReconstructor pathReconstructor;

    @Override
    public PathResult findPath(
            Graph graph,
            Long source,
            Long destination) {

        long startTime = System.currentTimeMillis();

        int visitedNodes = 0;

        Map<Long, Double> cost = new HashMap<>();

        Map<Long, Long> previous = new HashMap<>();

        PriorityQueue<NodeCost> priorityQueue = new PriorityQueue<>(
                Comparator.comparingDouble(NodeCost::cost));

        for (GraphNode node : graph.getNodes()) {

            cost.put(node.getId(), Double.MAX_VALUE);

        }

        cost.put(source, 0.0);

        priorityQueue.offer(new NodeCost(source, 0.0));

        while (!priorityQueue.isEmpty()) {

            NodeCost current = priorityQueue.poll();

            visitedNodes++;

            if (current.nodeId().equals(destination)) {
                break;
            }

            for (GraphEdge edge : graph.getNeighbors(current.nodeId())) {

                double newCost = cost.get(current.nodeId()) + edge.getTravelTime();

                if (newCost < cost.get(edge.getDestinationId())) {

                    cost.put(edge.getDestinationId(), newCost);

                    previous.put(
                            edge.getDestinationId(),
                            current.nodeId());

                    priorityQueue.offer(
                            new NodeCost(
                                    edge.getDestinationId(),
                                    newCost));

                }

            }

        }

        long endTime = System.currentTimeMillis();

        AlgorithmStatistics statistics = AlgorithmStatistics.builder()
                .algorithm("DIJKSTRA")
                .visitedNodes(visitedNodes)
                .executionTimeMs(endTime - startTime)
                .build();

        return pathReconstructor.buildResult(
                graph,
                previous,
                cost,
                destination,
                statistics);

    }

    private record NodeCost(
            Long nodeId,
            Double cost) {
    }

}