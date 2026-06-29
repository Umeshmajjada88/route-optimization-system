package com.umesh.route_optimization_service.mapper;

import com.umesh.route_optimization_service.dto.response.RoadSegmentResponse;
import com.umesh.route_optimization_service.entity.Location;
import com.umesh.route_optimization_service.entity.RoadSegment;

public class RoadSegmentMapper {

    private RoadSegmentMapper() {
    }

    public static RoadSegment toEntity(
            Location source,
            Location destination,
            Double distance,
            Integer speedLimit,
            com.umesh.route_optimization_service.entity.RoadType roadType,
            Boolean oneWay) {

        return RoadSegment.builder()
                .sourceLocation(source)
                .destinationLocation(destination)
                .distance(distance)
                .speedLimit(speedLimit)
                .roadType(roadType)
                .oneWay(oneWay)
                .active(true)
                .build();
    }

    public static RoadSegmentResponse toResponse(RoadSegment road) {

        return RoadSegmentResponse.builder()
                .id(road.getId())
                .sourceLocationId(road.getSourceLocation().getId())
                .destinationLocationId(road.getDestinationLocation().getId())
                .distance(road.getDistance())
                .speedLimit(road.getSpeedLimit())
                .roadType(road.getRoadType())
                .oneWay(road.getOneWay())
                .active(road.getActive())
                .build();
    }
}