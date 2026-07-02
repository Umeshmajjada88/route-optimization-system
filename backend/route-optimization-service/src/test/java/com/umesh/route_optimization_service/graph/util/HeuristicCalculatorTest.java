package com.umesh.route_optimization_service.graph.util;

import com.umesh.route_optimization_service.graph.model.GraphNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeuristicCalculatorTest {

    private HeuristicCalculator heuristicCalculator;

    @BeforeEach
    void setUp() {

        heuristicCalculator = new HeuristicCalculator();

    }

    @Test
    void shouldReturnZeroForSameLocation() {

        GraphNode node = GraphNode.builder()
                .id(1L)
                .name("Hyderabad")
                .latitude(17.3850)
                .longitude(78.4867)
                .build();

        double result = heuristicCalculator.calculate(
                node,
                node);

        assertEquals(
                0.0,
                result,
                0.0001);

    }

    @Test
    void shouldReturnPositiveDistanceForDifferentLocations() {

        GraphNode source = GraphNode.builder()
                .id(1L)
                .name("Hyderabad")
                .latitude(17.3850)
                .longitude(78.4867)
                .build();

        GraphNode destination = GraphNode.builder()
                .id(2L)
                .name("Madhapur")
                .latitude(17.4483)
                .longitude(78.3915)
                .build();

        double result = heuristicCalculator.calculate(
                source,
                destination);

        assertTrue(result > 0);

    }

    @Test
    void shouldBeSymmetric() {

        GraphNode source = GraphNode.builder()
                .id(1L)
                .latitude(17.3850)
                .longitude(78.4867)
                .build();

        GraphNode destination = GraphNode.builder()
                .id(2L)
                .latitude(17.4483)
                .longitude(78.3915)
                .build();

        double forward = heuristicCalculator.calculate(
                source,
                destination);

        double reverse = heuristicCalculator.calculate(
                destination,
                source);

        assertEquals(
                forward,
                reverse,
                0.0001);

    }

}