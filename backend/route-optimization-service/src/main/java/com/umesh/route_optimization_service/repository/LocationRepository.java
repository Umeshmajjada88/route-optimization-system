package com.umesh.route_optimization_service.repository;

import com.umesh.route_optimization_service.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByCode(String code);

    boolean existsByCode(String code);

}