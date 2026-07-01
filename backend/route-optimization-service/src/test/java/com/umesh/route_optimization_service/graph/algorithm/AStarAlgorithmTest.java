package com.umesh.route_optimization_service.graph.algorithm;

import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.GraphEdge;
import com.umesh.route_optimization_service.graph.model.GraphNode;
import com.umesh.route_optimization_service.graph.model.PathResult;
import com.umesh.route_optimization_service.graph.util.HeuristicCalculator;
import com.umesh.route_optimization_service.graph.util.PathReconstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AStarAlgorithmTest {

    private AStarAlgorithm aStarAlgorithm;

    private Graph graph;

    @BeforeEach
    void setUp() {

        aStarAlgorithm = new AStarAlgorithm(
                new HeuristicCalculator(),
                new PathReconstructor());

        graph = new Graph();

        GraphNode nodeA = GraphNode.builder()
                .id(1L)
                .name("A")
                .latitude(17.3850)
                .longitude(78.4867)
                .build();

        GraphNode nodeB = GraphNode.builder()
                .id(2L)
                .name("B")
                .latitude(17.3900)
                .longitude(78.4900)
                .build();

        GraphNode nodeC = GraphNode.builder()
                .id(3L)
                .name("C")
                .latitude(17.4000)
                .longitude(78.5000)
                .build();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);

        graph.addEdge(
                GraphEdge.builder()
                        .sourceId(1L)
                        .destinationId(2L)
                        .distance(10.0)
                        .travelTime(10.0)
                        .build());

        graph.addEdge(
                GraphEdge.builder()
                        .sourceId(2L)
                        .destinationId(3L)
                        .distance(10.0)
                        .travelTime(10.0)
                        .build());

        graph.addEdge(
                GraphEdge.builder()
                        .sourceId(1L)
                        .destinationId(3L)
                        .distance(30.0)
                        .travelTime(30.0)
                        .build());

    }

    @Test
    void shouldFindOptimalPath() {

        PathResult result = aStarAlgorithm.findPath(
                graph,
                1L,
                3L);

        assertNotNull(result);

        assertEquals(20.0, result.getTotalDistance());

        assertEquals(20.0, result.getTotalTravelTime());

        List<GraphNode> path = result.getPath();

        assertEquals(3, path.size());

        assertEquals("A", path.get(0).getName());

        assertEquals("B", path.get(1).getName());

        assertEquals("C", path.get(2).getName());

    }

    @Test
    void shouldReturnAlgorithmStatistics() {

        PathResult result = aStarAlgorithm.findPath(
                graph,
                1L,
                3L);

        assertNotNull(result.getStatistics());

        assertEquals(
                "ASTAR",
                result.getStatistics().getAlgorithm());

        assertTrue(
                result.getStatistics().getVisitedNodes() > 0);

        assertTrue(
                result.getStatistics().getExecutionTimeMs() >= 0);

    }

    @Test
    void shouldReturnSingleNodePathWhenSourceEqualsDestination() {

        PathResult result = aStarAlgorithm.findPath(
                graph,
                1L,
                1L);

        assertEquals(0.0, result.getTotalDistance());

        assertEquals(0.0, result.getTotalTravelTime());

        assertEquals(1, result.getPath().size());

        assertEquals(
                "A",
                result.getPath().get(0).getName());

    }

}