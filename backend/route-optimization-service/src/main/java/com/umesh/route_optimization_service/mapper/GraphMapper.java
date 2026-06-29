package com.umesh.route_optimization_service.mapper;

import com.umesh.route_optimization_service.graph.dto.GraphEdgeResponse;
import com.umesh.route_optimization_service.graph.dto.GraphNodeResponse;
import com.umesh.route_optimization_service.graph.dto.GraphResponse;
import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.GraphEdge;
import com.umesh.route_optimization_service.graph.model.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class GraphMapper {

    private GraphMapper() {
    }

    public static GraphResponse toResponse(Graph graph) {

        List<GraphNodeResponse> nodes = graph.getNodes()
                .stream()
                .map(GraphMapper::toNodeResponse)
                .toList();

        List<GraphEdgeResponse> edges = new ArrayList<>();

        graph.getAdjacencyList()
                .values()
                .forEach(edgeList -> edgeList.forEach(edge -> edges.add(toEdgeResponse(edge))));

        return GraphResponse.builder()
                .nodes(nodes)
                .edges(edges)
                .build();
    }

    private static GraphNodeResponse toNodeResponse(GraphNode node) {

        return GraphNodeResponse.builder()
                .id(node.getId())
                .name(node.getName())
                .latitude(node.getLatitude())
                .longitude(node.getLongitude())
                .build();
    }

    private static GraphEdgeResponse toEdgeResponse(GraphEdge edge) {

        return GraphEdgeResponse.builder()
                .sourceId(edge.getSourceId())
                .destinationId(edge.getDestinationId())
                .distance(edge.getDistance())
                .build();
    }
}