package com.umesh.route_optimization_service.graph.algorithm;

import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.GraphEdge;
import com.umesh.route_optimization_service.graph.model.GraphNode;
import com.umesh.route_optimization_service.graph.model.PathResult;
import com.umesh.route_optimization_service.graph.util.PathReconstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraAlgorithmTest {

    private DijkstraAlgorithm dijkstraAlgorithm;

    private Graph graph;

    @BeforeEach
    void setUp() {

        dijkstraAlgorithm = new DijkstraAlgorithm(new PathReconstructor());

        graph = new Graph();

        GraphNode nodeA = GraphNode.builder()
                .id(1L)
                .name("A")
                .latitude(0.0)
                .longitude(0.0)
                .build();

        GraphNode nodeB = GraphNode.builder()
                .id(2L)
                .name("B")
                .latitude(0.0)
                .longitude(1.0)
                .build();

        GraphNode nodeC = GraphNode.builder()
                .id(3L)
                .name("C")
                .latitude(0.0)
                .longitude(2.0)
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
    void shouldFindShortestPath() {

        PathResult result = dijkstraAlgorithm.findPath(graph, 1L, 3L);

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

        PathResult result = dijkstraAlgorithm.findPath(graph, 1L, 3L);

        assertNotNull(result.getStatistics());

        assertEquals(
                "DIJKSTRA",
                result.getStatistics().getAlgorithm());

        assertTrue(
                result.getStatistics().getVisitedNodes() > 0);

        assertTrue(
                result.getStatistics().getExecutionTimeMs() >= 0);

    }

    @Test
    void shouldReturnSingleNodePathWhenSourceEqualsDestination() {

        PathResult result = dijkstraAlgorithm.findPath(graph, 1L, 1L);

        assertEquals(0.0, result.getTotalDistance());

        assertEquals(0.0, result.getTotalTravelTime());

        assertEquals(1, result.getPath().size());

        assertEquals(
                "A",
                result.getPath().get(0).getName());

    }

}