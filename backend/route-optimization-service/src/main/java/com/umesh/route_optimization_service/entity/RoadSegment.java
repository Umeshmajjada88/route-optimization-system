package com.umesh.route_optimization_service.entity;

import com.umesh.route_optimization_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "road_segments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoadSegment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_location_id", nullable = false)
    private Location sourceLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_location_id", nullable = false)
    private Location destinationLocation;

    @Column(nullable = false)
    private Double distance;

    @Column(nullable = false)
    private Integer speedLimit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoadType roadType;

    @Builder.Default
    @Column(nullable = false)
    private Boolean oneWay = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;
}