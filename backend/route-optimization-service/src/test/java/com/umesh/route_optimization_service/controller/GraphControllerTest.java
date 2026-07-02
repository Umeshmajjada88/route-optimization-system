package com.umesh.route_optimization_service.controller;

import com.umesh.route_optimization_service.graph.algorithm.AlgorithmType;
import com.umesh.route_optimization_service.graph.model.PathResult;
import com.umesh.route_optimization_service.graph.service.GraphService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GraphController.class)
class GraphControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GraphService graphService;

    @Test
    void shouldReturnShortestPath() throws Exception {

        PathResult result = PathResult.builder()
                .totalDistance(12.5)
                .totalTravelTime(15.0)
                .build();

        when(graphService.shortestPath(
                eq(1L),
                eq(2L),
                eq(AlgorithmType.DIJKSTRA)))
                .thenReturn(result);

        mockMvc.perform(get("/api/graph/shortest-path")
                .param("source", "1")
                .param("destination", "2")
                .param("algorithm", "DIJKSTRA"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.totalDistance").value(12.5))

                .andExpect(jsonPath("$.totalTravelTime").value(15.0));

    }

}