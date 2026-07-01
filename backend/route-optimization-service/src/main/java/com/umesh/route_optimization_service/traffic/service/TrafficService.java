package com.umesh.route_optimization_service.traffic.service;

import com.umesh.route_optimization_service.traffic.dto.TrafficRequest;
import com.umesh.route_optimization_service.traffic.dto.TrafficResponse;
import org.springframework.cache.annotation.CacheEvict;

public interface TrafficService {

    TrafficResponse updateTraffic(TrafficRequest request);

}