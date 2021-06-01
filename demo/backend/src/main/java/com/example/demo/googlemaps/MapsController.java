package com.example.demo.googlemaps;

import com.example.demo.googlemaps.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.UrlMapping.LOCATION;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(LOCATION)
public class MapsController {

    @Autowired
    MapsRepository mapsRepository;

    @PostMapping()
    public void addlocation(@RequestParam("lat") String lat, @RequestParam("lng") String lng) {
        Location l = new Location();
        l.setLat(lat);
        l.setLng(lng);
        Location save = mapsRepository.save(l);
    }

    @GetMapping()
    public Iterable<Location> getLocations() {
        return mapsRepository.findAll();}


}
