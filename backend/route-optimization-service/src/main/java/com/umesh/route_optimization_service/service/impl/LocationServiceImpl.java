package com.umesh.route_optimization_service.service.impl;

import com.umesh.route_optimization_service.dto.request.LocationRequest;
import com.umesh.route_optimization_service.dto.response.LocationResponse;
import com.umesh.route_optimization_service.entity.Location;
import com.umesh.route_optimization_service.exception.DuplicateResourceException;
import com.umesh.route_optimization_service.exception.ResourceNotFoundException;
import com.umesh.route_optimization_service.mapper.LocationMapper;
import com.umesh.route_optimization_service.repository.LocationRepository;
import com.umesh.route_optimization_service.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;

    @Override
    public LocationResponse createLocation(LocationRequest request) {

        if (repository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException("Location code already exists.");
        }

        Location location = LocationMapper.toEntity(request);

        return LocationMapper.toResponse(repository.save(location));
    }

    @Override
    public LocationResponse getLocation(Long id) {

        Location location = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found."));

        return LocationMapper.toResponse(location);
    }

    @Override
    public List<LocationResponse> getAllLocations() {

        return repository.findAll()
                .stream()
                .map(LocationMapper::toResponse)
                .toList();
    }

    @Override
    public LocationResponse updateLocation(Long id, LocationRequest request) {

        Location location = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found."));

        location.setCode(request.getCode());
        location.setName(request.getName());
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());
        location.setCity(request.getCity());
        location.setState(request.getState());
        location.setCountry(request.getCountry());
        location.setLocationType(request.getLocationType());

        return LocationMapper.toResponse(repository.save(location));
    }

    @Override
    public void deleteLocation(Long id) {

        Location location = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found."));

        repository.delete(location);
    }
}