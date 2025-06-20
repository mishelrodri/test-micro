package com.mishelrodri.feignClients;

import com.mishelrodri.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "noimporta", url = "http://localhost:8002/car")
public interface CarFeignClient {

    @PostMapping()
    Car save(@RequestBody Car card);

    @GetMapping("/by-user/{id}")
    List<Car> getAllByUser(@PathVariable("id") int id);
}
