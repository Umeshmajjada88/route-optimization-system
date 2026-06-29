package com.umesh.route_optimization_service.graph.model;

import java.util.*;

public class Graph {

    private final Map<Long, GraphNode> nodes = new HashMap<>();

    private final Map<Long, List<GraphEdge>> adjacencyList = new HashMap<>();

    public void addNode(GraphNode node) {

        nodes.put(node.getId(), node);

        adjacencyList.putIfAbsent(node.getId(), new ArrayList<>());
    }

    public void addEdge(GraphEdge edge) {

        adjacencyList.computeIfAbsent(edge.getSourceId(),
                k -> new ArrayList<>()).add(edge);
    }

    public GraphNode getNode(Long id) {

        return nodes.get(id);
    }

    public List<GraphEdge> getNeighbors(Long nodeId) {

        return adjacencyList.getOrDefault(nodeId, Collections.emptyList());
    }

    public Collection<GraphNode> getNodes() {

        return nodes.values();
    }

    // ⭐ ADD THIS
    public Map<Long, List<GraphEdge>> getAdjacencyList() {

        return adjacencyList;
    }

}