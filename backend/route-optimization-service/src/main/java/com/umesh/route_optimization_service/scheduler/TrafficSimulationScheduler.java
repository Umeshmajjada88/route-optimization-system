package com.umesh.route_optimization_service.scheduler;

import com.umesh.route_optimization_service.entity.RoadSegment;
import com.umesh.route_optimization_service.repository.RoadSegmentRepository;
import com.umesh.route_optimization_service.traffic.entity.TrafficInfo;
import com.umesh.route_optimization_service.traffic.entity.TrafficLevel;
import com.umesh.route_optimization_service.traffic.repository.TrafficInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.umesh.route_optimization_service.websocket.TrafficUpdateMessage;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class TrafficSimulationScheduler {

    private final RoadSegmentRepository roadRepository;

    private final TrafficInfoRepository trafficRepository;

    private final SimpMessagingTemplate messagingTemplate;

    private final Random random = new Random();

    @Scheduled(fixedRate = 15000)
    @CacheEvict(value = "graphCache", allEntries = true)
    public void simulateTraffic() {

        List<RoadSegment> roads = roadRepository.findAll();

        if (roads.isEmpty()) {
            return;
        }

        for (RoadSegment road : roads) {

            TrafficInfo traffic = trafficRepository
                    .findByRoadSegmentId(road.getId())
                    .orElseGet(() -> {

                        TrafficInfo info = new TrafficInfo();
                        info.setRoadSegment(road);
                        return info;

                    });

            TrafficLevel level = randomTrafficLevel();

            traffic.setTrafficLevel(level);

            traffic.setAverageSpeed(randomSpeed(level));

            trafficRepository.save(traffic);

            TrafficUpdateMessage message = TrafficUpdateMessage.builder()
                    .roadSegmentId(road.getId())
                    .trafficLevel(level.name())
                    .averageSpeed(traffic.getAverageSpeed())
                    .timestamp(System.currentTimeMillis())
                    .build();

            messagingTemplate.convertAndSend(
                    "/topic/traffic",
                    message);

            log.info(
                    "Traffic Updated -> Road: {}, Level: {}, Speed: {} km/h",
                    road.getId(),
                    level,
                    traffic.getAverageSpeed());

        }

    }

    private TrafficLevel randomTrafficLevel() {

        int value = random.nextInt(100);

        if (value < 55) {
            return TrafficLevel.LOW;
        }

        if (value < 80) {
            return TrafficLevel.MEDIUM;
        }

        if (value < 97) {
            return TrafficLevel.HIGH;
        }

        return TrafficLevel.BLOCKED;

    }

    private double randomSpeed(TrafficLevel level) {

        return switch (level) {

            case LOW -> 60 + random.nextInt(21);

            case MEDIUM -> 35 + random.nextInt(20);

            case HIGH -> 10 + random.nextInt(25);

            case BLOCKED -> 0;

        };

    }

}