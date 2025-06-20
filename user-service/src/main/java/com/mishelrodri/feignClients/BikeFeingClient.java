package com.mishelrodri.feignClients;

import com.mishelrodri.models.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "nombre", url = "http://localhost:8003/bike")
public interface BikeFeingClient {

    @PostMapping
    Bike save(@RequestBody Bike bike);

    @GetMapping("/by-user/{id}")
    List<Bike> getAllByUser(@PathVariable("id") int id);

}
