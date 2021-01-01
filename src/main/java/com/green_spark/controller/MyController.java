package com.green_spark.controller;

import com.green_spark.domain.Car;
import com.green_spark.service.CarService;
import com.green_spark.service.impl.CarServiceImpl;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MyController {

    private final SparkSession spark = SparkSession
            .builder()
            .master("local")
            .appName("Any name")
            .getOrCreate();

    private int speed = 240;

    private final CarService carService;

    @Autowired
    public MyController(final CarServiceImpl carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String rrt1() {
        return "Hello World !!!";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String rrt2() {
        speed++;
        final Car car = new Car();
        car.setCarName("BMW " + speed);
        car.setCarSpeed(speed);
        carService.saveCar(car);
        return "Auto was saved !";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ArrayList<Car> rrt3() {
        return (ArrayList<Car>) carService.getCars();
    }

    @RequestMapping(value = "/spark-show", method = RequestMethod.GET)
    public ArrayList<Car> rrt4() {


        Dataset<Row> jdbcDF = spark.read()
                .format("jdbc")
                .option("driver", "org.postgresql.Driver")
                .option("url", "jdbc:postgresql://localhost:5432/any_name_db")
                .option("dbtable", "cars")
                .option("user", "tokkpasha")
                .option("password", "tokkpasha")
                .load();

        jdbcDF.show();
        jdbcDF.printSchema();

        jdbcDF.createOrReplaceTempView("cars");
        Dataset<Row> sql = spark.sql("select * from cars");

        sql.show();

        return null;
    }

}
