package com.umesh.route_optimization_service.traffic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umesh.route_optimization_service.traffic.dto.TrafficRequest;
import com.umesh.route_optimization_service.traffic.dto.TrafficResponse;
import com.umesh.route_optimization_service.traffic.entity.TrafficLevel;
import com.umesh.route_optimization_service.traffic.service.TrafficService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrafficController.class)
class TrafficControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TrafficService trafficService;

    @Test
    void shouldUpdateTraffic() throws Exception {

        TrafficRequest request = new TrafficRequest();
        request.setRoadSegmentId(10L);
        request.setTrafficLevel(TrafficLevel.HIGH);
        request.setAverageSpeed(30.0);

        TrafficResponse response = TrafficResponse.builder()
                .id(1L)
                .roadSegmentId(10L)
                .trafficLevel(TrafficLevel.HIGH)
                .averageSpeed(30.0)
                .build();

        when(trafficService.updateTraffic(request))
                .thenReturn(response);

        mockMvc.perform(post("/api/traffic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message")
                        .value("Traffic updated successfully."))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.roadSegmentId").value(10))
                .andExpect(jsonPath("$.data.trafficLevel").value("HIGH"))
                .andExpect(jsonPath("$.data.averageSpeed").value(30.0));
    }

    @Test
    void shouldReturnBadRequestWhenRoadSegmentIdMissing() throws Exception {

        TrafficRequest request = new TrafficRequest();
        request.setTrafficLevel(TrafficLevel.HIGH);
        request.setAverageSpeed(30.0);

        mockMvc.perform(post("/api/traffic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}