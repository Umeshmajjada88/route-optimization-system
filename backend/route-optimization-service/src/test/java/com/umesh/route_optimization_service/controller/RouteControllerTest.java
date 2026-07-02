package com.umesh.route_optimization_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umesh.route_optimization_service.dto.request.RouteRequest;
import com.umesh.route_optimization_service.dto.response.RouteResponse;
import com.umesh.route_optimization_service.graph.algorithm.AlgorithmType;
import com.umesh.route_optimization_service.service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RouteController.class)
class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldOptimizeRoute() throws Exception {

        RouteRequest request = new RouteRequest();

        request.setSourceId(1L);
        request.setDestinationId(5L);
        request.setAlgorithm(AlgorithmType.DIJKSTRA);

        RouteResponse response = RouteResponse.builder()
                .totalDistance(20.5)
                .totalTravelTime(25.0)
                .build();

        when(routeService.optimizeRoute(request))
                .thenReturn(response);

        mockMvc.perform(post("/api/routes/optimize")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.success").value(true))

                .andExpect(jsonPath("$.message")
                        .value("Route optimized successfully."))

                .andExpect(jsonPath("$.data.totalDistance")
                        .value(20.5))

                .andExpect(jsonPath("$.data.totalTravelTime")
                        .value(25.0));

    }

    @Test
    void shouldReturnBadRequestWhenSourceMissing() throws Exception {

        RouteRequest request = new RouteRequest();

        request.setDestinationId(5L);
        request.setAlgorithm(AlgorithmType.DIJKSTRA);

        mockMvc.perform(post("/api/routes/optimize")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isBadRequest());

    }

}