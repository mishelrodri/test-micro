package com.mishelrodri.controller;

import com.mishelrodri.entities.User;
import com.mishelrodri.models.Bike;
import com.mishelrodri.models.Car;
import com.mishelrodri.service.UserService;
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

    @GetMapping("/cars/{userId}")
    public ResponseEntity<?> getCars(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<?> getBikes(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("/save-car")
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        if(userService.getUserById(car.getUserId())==null){
            return ResponseEntity.notFound().build();
        }
        Car carnew = userService.saveCar(car);
        return ResponseEntity.ok(carnew);
    }

    @PostMapping("/save-bike")
    public ResponseEntity<Bike> saveBike(@RequestBody Bike bike){
        if(userService.getUserById(bike.getUserId())==null){
            return ResponseEntity.notFound().build();
        }
        Bike bike1 = userService.saveBike(bike);
        return ResponseEntity.ok(bike1);
    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<?> getUserAndVeehicles(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserAndVeehicles(id));
    }

}
