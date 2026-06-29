package com.umesh.route_optimization_service.traffic.entity;

import com.umesh.route_optimization_service.common.entity.BaseEntity;
import com.umesh.route_optimization_service.entity.RoadSegment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "traffic_info")
public class TrafficInfo extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "road_segment_id", nullable = false, unique = true)
    private RoadSegment roadSegment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrafficLevel trafficLevel;

    @Column(nullable = false)
    private Double averageSpeed;

}