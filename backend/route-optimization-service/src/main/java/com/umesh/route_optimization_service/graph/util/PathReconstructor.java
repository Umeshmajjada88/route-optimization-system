package com.umesh.route_optimization_service.graph.util;

import com.umesh.route_optimization_service.graph.model.AlgorithmStatistics;
import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.GraphEdge;
import com.umesh.route_optimization_service.graph.model.GraphNode;
import com.umesh.route_optimization_service.graph.model.PathResult;
import org.springframework.stereotype.Component;
import com.umesh.route_optimization_service.exception.RouteNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class PathReconstructor {

    public PathResult buildResult(
        Graph graph,
        Map<Long, Long> previous,
        Map<Long, Double> cost,
        Long destination,
        AlgorithmStatistics statistics) {

            if (!cost.containsKey(destination)
                || cost.get(destination) == null
                || cost.get(destination).equals(Double.MAX_VALUE)) {

            throw new RouteNotFoundException(
                    "No route exists between the selected locations.");

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
                    .orElseThrow(() -> new RuntimeException("Edge not found"));

            totalDistance += edge.getDistance();

        }

        return PathResult.builder()
                .totalDistance(totalDistance)
                .totalTravelTime(cost.get(destination))
                .path(path)
                .statistics(statistics)
                .build();

    }

}