package com.infor.carrentalapplication.service;

import com.infor.carrentalapplication.model.cars.Car;
import com.infor.carrentalapplication.model.cars.CreateCarRequest;
import com.infor.carrentalapplication.model.cars.SearchCar;
import com.infor.carrentalapplication.utils.CarNotFoundException;
import com.infor.carrentalapplication.utils.IllegalArgException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    Car findCarById(String plateNumber) throws CarNotFoundException;

    Car registerCar(CreateCarRequest createCarRequest) throws IllegalArgException;

    Car registerAvailability(Car updatedCar) throws CarNotFoundException;

    List<Car> searchCars(SearchCar searchCar);
}


