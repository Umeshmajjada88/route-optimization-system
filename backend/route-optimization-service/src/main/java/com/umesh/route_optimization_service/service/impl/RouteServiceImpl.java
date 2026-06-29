package com.umesh.route_optimization_service.service.impl;

import com.umesh.route_optimization_service.dto.request.RouteRequest;
import com.umesh.route_optimization_service.dto.response.RouteResponse;
import com.umesh.route_optimization_service.graph.model.PathResult;
import com.umesh.route_optimization_service.graph.service.GraphService;
import com.umesh.route_optimization_service.mapper.RouteMapper;
import com.umesh.route_optimization_service.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final GraphService graphService;

    @Override
    public RouteResponse optimizeRoute(RouteRequest request) {

        PathResult result = graphService.shortestPath(
                request.getSourceLocationId(),
                request.getDestinationLocationId());

        return RouteMapper.toResponse(result);

    }

}