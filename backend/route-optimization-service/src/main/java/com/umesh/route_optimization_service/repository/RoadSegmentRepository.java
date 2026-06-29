package com.umesh.route_optimization_service.repository;

import com.umesh.route_optimization_service.entity.RoadSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadSegmentRepository extends JpaRepository<RoadSegment, Long> {
}