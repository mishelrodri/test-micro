package com.mishelrodri.repository;

import com.mishelrodri.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Integer> {

    List<Bike> findByUserId(int id);
}
