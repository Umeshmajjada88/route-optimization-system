package com.umesh.route_optimization_service.traffic.service;

import com.umesh.route_optimization_service.traffic.dto.TrafficRequest;
import com.umesh.route_optimization_service.traffic.dto.TrafficResponse;

public interface TrafficService {

    TrafficResponse updateTraffic(TrafficRequest request);

}