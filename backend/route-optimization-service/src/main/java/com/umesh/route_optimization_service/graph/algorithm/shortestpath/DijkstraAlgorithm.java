package com.umesh.route_optimization_service.graph.algorithm.shortestpath;

import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.GraphEdge;
import com.umesh.route_optimization_service.graph.model.GraphNode;
import com.umesh.route_optimization_service.graph.model.PathResult;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DijkstraAlgorithm {

    public PathResult findShortestPath(Graph graph,
            Long source,
            Long destination) {

        Map<Long, Double> cost = new HashMap<>();
        Map<Long, Long> previous = new HashMap<>();

        PriorityQueue<NodeDistance> priorityQueue = new PriorityQueue<>(
                Comparator.comparingDouble(NodeDistance::distance));

        for (var node : graph.getNodes()) {
            cost.put(node.getId(), Double.MAX_VALUE);
        }

        cost.put(source, 0.0);

        priorityQueue.offer(new NodeDistance(source, 0.0));

        while (!priorityQueue.isEmpty()) {

            NodeDistance current = priorityQueue.poll();

            if (current.nodeId().equals(destination)) {
                break;
            }

            for (GraphEdge edge : graph.getNeighbors(current.nodeId())) {

                double newCost = cost.get(current.nodeId()) +
                        edge.getTravelTime();

                if (newCost < cost.get(edge.getDestinationId())) {

                    cost.put(edge.getDestinationId(), newCost);

                    previous.put(edge.getDestinationId(), current.nodeId());

                    priorityQueue.offer(new NodeDistance(
                            edge.getDestinationId(),
                            newCost));
                }

            }
        }

        List<GraphNode> path = new ArrayList<>();

        Long current = destination;

        while (current != null) {

            path.add(graph.getNode(current));

            current = previous.get(current);

        }

        Collections.reverse(path);

        double totalDistance = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {

            Long sourceId = path.get(i).getId();
            Long destinationId = path.get(i + 1).getId();

            GraphEdge edge = graph.getNeighbors(sourceId)
                    .stream()
                    .filter(e -> e.getDestinationId().equals(destinationId))
                    .findFirst()
                    .orElseThrow();

            totalDistance += edge.getDistance();

        }

        return PathResult.builder()
                .totalDistance(totalDistance)
                .totalTravelTime(cost.get(destination))
                .path(path)
                .build();
    }

    private record NodeDistance(
            Long nodeId,
            Double distance) {
    }
}