package com.mishelrodri.controller;

import com.mishelrodri.entities.User;
import com.mishelrodri.models.Bike;
import com.mishelrodri.models.Car;
import com.mishelrodri.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User userNew = userService.save(user);

        return ResponseEntity.ok(userNew);
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackGetCars")
    @GetMapping("/cars/{userId}")
    public ResponseEntity<?> getCars(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackGetBikes")
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<?> getBikes(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackSaveCar")
    @PostMapping("/save-car")
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        if(userService.getUserById(car.getUserId())==null){
            return ResponseEntity.notFound().build();
        }
        Car carnew = userService.saveCar(car);
        return ResponseEntity.ok(carnew);
    }

    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackSaveBikes")
    @PostMapping("/save-bike")
    public ResponseEntity<Bike> saveBike(@RequestBody Bike bike){
        if(userService.getUserById(bike.getUserId())==null){
            return ResponseEntity.notFound().build();
        }
        Bike bike1 = userService.saveBike(bike);
        return ResponseEntity.ok(bike1);
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAll")
    @GetMapping("/getAll/{id}")
    public ResponseEntity<?> getUserAndVeehicles(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserAndVeehicles(id));
    }

    public ResponseEntity<?> fallBackGetCars(@PathVariable("userId") int userId, RuntimeException e){
        return  ResponseEntity.ok("El usuario "+ userId + "tiene los coches en el taller");
    }

    public ResponseEntity<?> fallBackSaveCar(@RequestBody Car car, RuntimeException e){
        return ResponseEntity.ok("No podemos crear el carro");
    }

    public ResponseEntity<?> fallBackGetBikes(@PathVariable("userId") int userId, RuntimeException e){
        return  ResponseEntity.ok("El usuario "+ userId + "tiene las motos en el taller");
    }

    public ResponseEntity<?> fallBackSaveBikes(@RequestBody Bike bike, RuntimeException e){
        return ResponseEntity.ok("El usuario nop tiene dinero para motos");
    }

    public ResponseEntity<?> fallBackGetAll(@PathVariable int id, RuntimeException e){
        return ResponseEntity.ok("El usuario tiene los vehiculos en el taller");
    }

}
