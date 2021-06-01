package com.example.demo.googlemaps;

import com.example.demo.googlemaps.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapsRepository extends CrudRepository<Location, Long> {
}
