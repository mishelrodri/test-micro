package com.mishelrodri.service;

import com.mishelrodri.entities.User;
import com.mishelrodri.feignClients.BikeFeingClient;
import com.mishelrodri.feignClients.CarFeignClient;
import com.mishelrodri.models.Bike;
import com.mishelrodri.models.Car;
import com.mishelrodri.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final CarFeignClient carFeignClient;
    private final BikeFeingClient bikeFeingClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }


    public User save(User user){
        userRepository.save(user);
        return user;
    }

    public List<Car> getCars(int id){
        List<Car> cars = restTemplate.getForObject("http://car-service/car/by-user/"+id, List.class);
        return cars;
    }

    public List<Bike> getBikes(int id){
        List<Bike> bikes = restTemplate.getForObject("http://bike-service/bike/by-user/"+id, List.class);
        return bikes;
    }

    public Car saveCar(Car car){
        Car carSave = carFeignClient.save(car);
        return carSave;
    }

    public Bike saveBike(Bike bike){
        Bike bikeSave = bikeFeingClient.save(bike);
        return bikeSave;
    }

    public Map<String, Object> getUserAndVeehicles(int userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);

        if (user == null){
            result.put("mensaje", "No existe el usuario");
            return result;
        }
        result.put("user", user);
        result.put("cars", carFeignClient.getAllByUser(userId));
        result.put("bike", bikeFeingClient.getAllByUser(userId));
        return result;
    }

}
