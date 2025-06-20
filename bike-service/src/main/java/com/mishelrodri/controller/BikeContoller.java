package com.mishelrodri.controller;

import com.mishelrodri.entity.Bike;
import com.mishelrodri.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
@RequiredArgsConstructor
public class BikeContoller {

    private final BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> bikes = bikeService.getAll();
        if (bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id) {
        Bike bike = bikeService.getCarById(id);
        if (bike == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike biked) {
        Bike bikeNew = bikeService.save(biked);

        return ResponseEntity.ok(bikeNew);
    }


    @GetMapping("/by-user/{id}")
    public ResponseEntity<List<Bike>> getAllByUser(@PathVariable("id") int id) {
        List<Bike> bikes = bikeService.getAllByIdUser(id);
        return ResponseEntity.ok(bikes);
    }

}
