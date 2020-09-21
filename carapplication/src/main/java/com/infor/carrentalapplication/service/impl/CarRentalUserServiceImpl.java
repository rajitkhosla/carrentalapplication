package com.infor.carrentalapplication.service.impl;

import com.infor.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.infor.carrentalapplication.repository.CarRentalUserRepository;
import com.infor.carrentalapplication.service.CarRentalUserService;
import com.infor.carrentalapplication.utils.CarApplicationsConstants;
import com.infor.carrentalapplication.utils.IllegalArgException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CarRentalUserServiceImpl implements CarRentalUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarRentalUserServiceImpl.class);

    @Autowired
    CarRentalUserRepository carRentalUserRepository;

    @Override
    @Transactional
    public CarRentalUser createCarRentalUser(CarRentalUser newCarRentalUser) throws IllegalArgException {
        checkIfUserPresent(newCarRentalUser);
        return carRentalUserRepository.save(newCarRentalUser);
    }

    void checkIfUserPresent(CarRentalUser carRentalUser) throws IllegalArgException {
        Optional<CarRentalUser> searchedCar = carRentalUserRepository.findById(carRentalUser.getUserName());
        if (searchedCar.isPresent()) {
            LOGGER.info(CarApplicationsConstants.CAR_REGISTER_USER_ALREADY_PRESENT,carRentalUser);
            throw new IllegalArgException(CarApplicationsConstants.CAR_REGISTER_USER_ALREADY_PRESENT);
        }
    }
}
