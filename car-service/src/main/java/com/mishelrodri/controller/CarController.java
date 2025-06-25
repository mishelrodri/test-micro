package com.mishelrodri.controller;


import com.mishelrodri.entities.Car;
import com.mishelrodri.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carService.getAll();
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car card,@RequestHeader Map<String, String> headers) {

        System.out.println("HEADERS EN CAR-SERVICE:");
        headers.forEach((k, v) -> System.out.println("🔴 "+ k + ": " + v));
        Car carNew = carService.save(card);

        return ResponseEntity.ok(carNew);
    }


    @GetMapping("/by-user/{id}")
    public ResponseEntity<List<Car>> getAllByUser(@PathVariable("id") int id) {
        List<Car> cars = carService.getAllByIdUser(id);
        return ResponseEntity.ok(cars);
    }
}
