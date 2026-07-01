package com.umesh.route_optimization_service.graph.util;

import com.umesh.route_optimization_service.graph.model.GraphNode;
import org.springframework.stereotype.Component;

@Component
public class HeuristicCalculator {


    public double calculate(GraphNode source, GraphNode destination) {

        double lat1 = Math.toRadians(source.getLatitude());
        double lon1 = Math.toRadians(source.getLongitude());

        double lat2 = Math.toRadians(destination.getLatitude());
        double lon2 = Math.toRadians(destination.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1)
                        * Math.cos(lat2)
                        * Math.sin(dLon / 2)
                        * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(
                Math.sqrt(a),
                Math.sqrt(1 - a));

        double distanceKm = 6371.0 * c;

        double averageSpeedKmPerHour = 60.0;

        double travelTimeMinutes = (distanceKm / averageSpeedKmPerHour) * 60.0;

        return travelTimeMinutes;

    }

}