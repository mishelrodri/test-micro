package com.mishelrodri.service;

import com.mishelrodri.entities.Car;
import com.mishelrodri.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;


    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public List<Car> getAllByIdUser(int id) {
        return carRepository.findByUserId(id);
    }

    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }


    public Car save(Car car) {
        carRepository.save(car);
        return car;
    }
}
