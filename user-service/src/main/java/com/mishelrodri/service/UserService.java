package com.mishelrodri.service;

import com.mishelrodri.entities.User;
import com.mishelrodri.models.Bike;
import com.mishelrodri.models.Car;
import com.mishelrodri.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

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
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/by-user/"+id, List.class);
        return cars;
    }

    public List<Bike> getBikes(int id){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/by-user/"+id, List.class);
        return bikes;
    }

}
