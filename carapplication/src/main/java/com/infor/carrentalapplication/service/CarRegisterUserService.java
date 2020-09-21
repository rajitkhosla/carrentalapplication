package com.infor.carrentalapplication.service;

import com.infor.carrentalapplication.model.carregisteruser.CarRegisterUser;
import com.infor.carrentalapplication.utils.IllegalArgException;
import org.springframework.stereotype.Service;

@Service
public interface CarRegisterUserService {
    CarRegisterUser createCarRegisterUser(CarRegisterUser newCarRegisterUser) throws IllegalArgException;
}
