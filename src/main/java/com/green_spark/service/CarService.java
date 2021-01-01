package com.green_spark.service;

import com.green_spark.domain.Car;

import java.util.ArrayList;
import java.util.List;

public interface CarService {

    ArrayList<Car> getCars();
    void saveCar(final Car car);

}
