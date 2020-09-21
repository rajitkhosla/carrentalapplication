package com.infor.carrentalapplication.service.impl;

import com.infor.carrentalapplication.model.carregisteruser.CarRegisterUser;
import com.infor.carrentalapplication.model.cars.Car;
import com.infor.carrentalapplication.model.cars.CreateCarRequest;
import com.infor.carrentalapplication.model.cars.SearchCar;
import com.infor.carrentalapplication.repository.CarRegisterUserRepository;
import com.infor.carrentalapplication.repository.CarRepository;
import com.infor.carrentalapplication.service.CarService;
import com.infor.carrentalapplication.utils.CarApplicationsConstants;
import com.infor.carrentalapplication.utils.CarNotFoundException;
import com.infor.carrentalapplication.utils.IllegalArgException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarRegisterUserServiceImpl.class);
    @Autowired
    CarRepository carRepository;
    @Autowired
    CarRegisterUserRepository carRegisterUserRepository;

    @Override
    @Transactional(readOnly = true)
    public Car findCarById(String plateNumber) throws CarNotFoundException {
        return carRepository.findById(plateNumber)
                .orElseThrow(() -> {
                    LOGGER.info(CarApplicationsConstants.CAR_WITH_NUMBER_PLATE + plateNumber);
                    return new CarNotFoundException(plateNumber);
                });

    }

    @Override
    @Transactional
    public Car registerCar(CreateCarRequest createCarRequest) throws IllegalArgException {
        checkIfCarPresent(createCarRequest.getCarNumberPlate());
        CarRegisterUser carRegisterUser = checkIfRegisterUserNotPresent(createCarRequest.getCarRegisterUser());
        Car newCar = new Car(createCarRequest.getCarNumberPlate(), carRegisterUser);
        return carRepository.save(newCar);
    }

    private void checkIfCarPresent(String numberPlate) throws IllegalArgException {
        Optional<Car> searchedCar = carRepository.findById(numberPlate);
        if (searchedCar.isPresent()) {
            LOGGER.info(CarApplicationsConstants.CAR_ALREADY_REGISTERED, numberPlate);
            throw new IllegalArgException(CarApplicationsConstants.CAR_ALREADY_REGISTERED);
        }

    }

    private CarRegisterUser checkIfRegisterUserNotPresent(String registerUser) throws IllegalArgException {
        Optional<CarRegisterUser> searchedRegisterdUser = carRegisterUserRepository.findById(registerUser);
        if (!searchedRegisterdUser.isPresent()) {
            LOGGER.info(CarApplicationsConstants.REGISTER_USER_NOT_PRESENT, registerUser);
            throw new IllegalArgException(CarApplicationsConstants.REGISTER_USER_NOT_PRESENT);
        }
        return searchedRegisterdUser.get();

    }

    @Override
    @Transactional
    public Car registerAvailability(Car updatedCar) throws CarNotFoundException {
        return carRepository.findById(updatedCar.getNumberplate())
                .map(car -> {
                    car.setPricePerHour(updatedCar.getPricePerHour());
                    car.setFromDate(updatedCar.getFromDate());
                    car.setToDate(updatedCar.getToDate());
                    return carRepository.save(car);
                })
                .orElseThrow(() -> {
                    LOGGER.info(CarApplicationsConstants.CAR_ALREADY_REGISTERED + updatedCar);
                    return new CarNotFoundException(updatedCar.getNumberplate());
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> searchCars(SearchCar searchCar) {
        return carRepository.findAllWithDatesAndMaximumPrice(searchCar.getFromDate(), searchCar.getToDate(), searchCar.getMaximumPricePerHour());

    }
}


