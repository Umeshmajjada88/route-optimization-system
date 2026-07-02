package com.umesh.route_optimization_service.graph.algorithm;

import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.GraphEdge;
import com.umesh.route_optimization_service.graph.model.GraphNode;
import com.umesh.route_optimization_service.graph.model.PathResult;
import com.umesh.route_optimization_service.graph.util.PathReconstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraAlgorithmTest {

    private DijkstraAlgorithm algorithm;

    @BeforeEach
    void setUp() {

        algorithm = new DijkstraAlgorithm(
                new PathReconstructor());

    }

    @Test
    void shouldFindShortestPath() {

        Graph graph = new Graph();

        GraphNode a = GraphNode.builder()
                .id(1L)
                .name("A")
                .latitude(0.0)
                .longitude(0.0)
                .build();

        GraphNode b = GraphNode.builder()
                .id(2L)
                .name("B")
                .latitude(0.0)
                .longitude(1.0)
                .build();

        GraphNode c = GraphNode.builder()
                .id(3L)
                .name("C")
                .latitude(0.0)
                .longitude(2.0)
                .build();

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);

        graph.addEdge(
                GraphEdge.builder()
                        .sourceId(1L)
                        .destinationId(2L)
                        .distance(5.0)
                        .travelTime(5.0)
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
                        .distance(20.0)
                        .travelTime(20.0)
                        .build());

        PathResult result = algorithm.findPath(
                graph,
                1L,
                3L);

        assertNotNull(result);

        assertEquals(
                10.0,
                result.getTotalTravelTime());

        assertEquals(
                10.0,
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
                "DIJKSTRA",
                result.getStatistics().getAlgorithm());

    }

}