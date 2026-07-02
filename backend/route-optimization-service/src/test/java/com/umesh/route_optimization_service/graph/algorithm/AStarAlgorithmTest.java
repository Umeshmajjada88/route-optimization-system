package com.umesh.route_optimization_service.graph.algorithm;

import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.GraphEdge;
import com.umesh.route_optimization_service.graph.model.GraphNode;
import com.umesh.route_optimization_service.graph.model.PathResult;
import com.umesh.route_optimization_service.graph.util.HeuristicCalculator;
import com.umesh.route_optimization_service.graph.util.PathReconstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AStarAlgorithmTest {

    private AStarAlgorithm algorithm;

    @BeforeEach
    void setUp() {

        algorithm = new AStarAlgorithm(
                new HeuristicCalculator(),
                new PathReconstructor());

    }

    @Test
    void shouldFindOptimalPathUsingAStar() {

        Graph graph = new Graph();

        GraphNode a = GraphNode.builder()
                .id(1L)
                .name("A")
                .latitude(17.3850)
                .longitude(78.4867)
                .build();

        GraphNode b = GraphNode.builder()
                .id(2L)
                .name("B")
                .latitude(17.4000)
                .longitude(78.4900)
                .build();

        GraphNode c = GraphNode.builder()
                .id(3L)
                .name("C")
                .latitude(17.4200)
                .longitude(78.5000)
                .build();

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);

        graph.addEdge(
                GraphEdge.builder()
                        .sourceId(1L)
                        .destinationId(2L)
                        .distance(4.0)
                        .travelTime(4.0)
                        .build());

        graph.addEdge(
                GraphEdge.builder()
                        .sourceId(2L)
                        .destinationId(3L)
                        .distance(5.0)
                        .travelTime(5.0)
                        .build());

        graph.addEdge(
                GraphEdge.builder()
                        .sourceId(1L)
                        .destinationId(3L)
                        .distance(15.0)
                        .travelTime(15.0)
                        .build());

        PathResult result = algorithm.findPath(
                graph,
                1L,
                3L);

        assertNotNull(result);

        assertEquals(
                9.0,
                result.getTotalTravelTime());

        assertEquals(
                9.0,
                result.getTotalDistance());

        assertEquals(
                3,
                result.getPath().size());

        assertEquals(
                "A",
                result.getPath().get(0).getName());

        assertEquals(
                "B",
                result.getPath().get(1).getName());

        assertEquals(
                "C",
                result.getPath().get(2).getName());

        assertNotNull(result.getStatistics());

        assertEquals(
                "ASTAR",
                result.getStatistics().getAlgorithm());

    }

}