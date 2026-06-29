package com.umesh.route_optimization_service.traffic.repository;

import com.umesh.route_optimization_service.traffic.entity.TrafficInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrafficInfoRepository extends JpaRepository<TrafficInfo, Long> {

    Optional<TrafficInfo> findByRoadSegmentId(Long roadSegmentId);

}