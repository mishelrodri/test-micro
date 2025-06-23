package com.mishelrodri.feignClients;

import com.mishelrodri.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
/* 🔴 Como ahora usamos Eureka SI IMPORTA EL NOMBRE
Debe ser el nombre configurado para el microservicio y podemos quitar la url */
@FeignClient(name = "car-service", path = "/car")
public interface CarFeignClient {

    @PostMapping()
    Car save(@RequestBody Car card);

    @GetMapping("/by-user/{id}")
    List<Car> getAllByUser(@PathVariable("id") int id);
}
