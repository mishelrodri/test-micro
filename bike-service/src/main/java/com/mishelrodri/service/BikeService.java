package com.mishelrodri.service;

import com.mishelrodri.entity.Bike;
import com.mishelrodri.repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BikeService {

    private final BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public List<Bike> getAllByIdUser(int id) {
        return bikeRepository.findByUserId(id);
    }

    public Bike getCarById(int id) {
        return bikeRepository.findById(id).orElse(null);
    }


    public Bike save(Bike car) {
        bikeRepository.save(car);
        return car;
    }
}
