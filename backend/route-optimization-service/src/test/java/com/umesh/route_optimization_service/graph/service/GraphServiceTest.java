package com.umesh.route_optimization_service.graph.service;

import com.umesh.route_optimization_service.graph.algorithm.AlgorithmType;
import com.umesh.route_optimization_service.graph.algorithm.PathFindingAlgorithm;
import com.umesh.route_optimization_service.graph.factory.AlgorithmFactory;
import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.GraphNode;
import com.umesh.route_optimization_service.graph.model.PathResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GraphServiceTest {

    @Mock
    private GraphBuilder graphBuilder;

    @Mock
    private AlgorithmFactory algorithmFactory;

    @Mock
    private PathFindingAlgorithm algorithm;

    @InjectMocks
    private GraphService graphService;

    @Test
    void shouldReturnShortestPathUsingSelectedAlgorithm() {

        Graph graph = new Graph();

        GraphNode source = GraphNode.builder()
                .id(1L)
                .name("Source")
                .latitude(17.0)
                .longitude(78.0)
                .build();

        GraphNode destination = GraphNode.builder()
                .id(2L)
                .name("Destination")
                .latitude(17.1)
                .longitude(78.1)
                .build();

        graph.addNode(source);
        graph.addNode(destination);

        PathResult expected = PathResult.builder()
                .totalDistance(12.0)
                .totalTravelTime(15.0)
                .path(List.of(source, destination))
                .build();

        when(graphBuilder.buildGraph())
                .thenReturn(graph);

        when(algorithmFactory.getAlgorithm(AlgorithmType.DIJKSTRA))
                .thenReturn(algorithm);

        when(algorithm.findPath(
                graph,
                1L,
                2L))
                .thenReturn(expected);

        PathResult result = graphService.shortestPath(
                1L,
                2L,
                AlgorithmType.DIJKSTRA);

        assertNotNull(result);

        assertEquals(
                12.0,
                result.getTotalDistance());

        assertEquals(
                15.0,
                result.getTotalTravelTime());

        assertEquals(
                2,
                result.getPath().size());

        verify(graphBuilder).buildGraph();

        verify(algorithmFactory)
                .getAlgorithm(AlgorithmType.DIJKSTRA);

        verify(algorithm)
                .findPath(graph, 1L, 2L);

    }

}