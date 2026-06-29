package com.umesh.route_optimization_service.service;

import com.umesh.route_optimization_service.dto.request.RouteRequest;
import com.umesh.route_optimization_service.dto.response.RouteResponse;

public interface RouteService {

    RouteResponse optimizeRoute(RouteRequest request);

}