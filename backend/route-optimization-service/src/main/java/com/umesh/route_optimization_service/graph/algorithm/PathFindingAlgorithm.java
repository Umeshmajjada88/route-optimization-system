package com.umesh.route_optimization_service.graph.algorithm;

import com.umesh.route_optimization_service.graph.model.Graph;
import com.umesh.route_optimization_service.graph.model.PathResult;

public interface PathFindingAlgorithm {

    PathResult findPath(
            Graph graph,
            Long source,
            Long destination);

}