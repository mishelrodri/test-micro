package com.mishelrodri.feignClients;

import com.mishelrodri.models.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
// 🔴 Lo del path no es obligatorio, pero lo puse para no tener que estarlo poniendo en TODASS las peticiones
@FeignClient(name = "bike-service", path = "/bike")
public interface BikeFeingClient {

    @PostMapping
    Bike save(@RequestBody Bike bike);

    @GetMapping("/by-user/{id}")
    List<Bike> getAllByUser(@PathVariable("id") int id);

}
