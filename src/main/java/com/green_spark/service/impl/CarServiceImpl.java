package com.green_spark.service.impl;

import com.green_spark.domain.Car;
import com.green_spark.repository.CarsRepository;
import com.green_spark.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarsRepository carsRepository;

    @Autowired
    public CarServiceImpl(final CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public ArrayList<Car> getCars() {
        return (ArrayList<Car>) carsRepository.findAll();
    }

    @Override
    public void saveCar(Car car) {
        carsRepository.save(car);
    }

}
