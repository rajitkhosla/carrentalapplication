package com.infor.carrentalapplication.service;

import com.infor.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.infor.carrentalapplication.utils.IllegalArgException;
import org.springframework.stereotype.Service;

@Service
public interface CarRentalUserService {
    CarRentalUser createCarRentalUser(CarRentalUser newCarRentalUser) throws IllegalArgException;
}

