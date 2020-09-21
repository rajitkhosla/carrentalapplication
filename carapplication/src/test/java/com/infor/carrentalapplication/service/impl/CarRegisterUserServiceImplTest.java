package com.infor.carrentalapplication.service.impl;

import com.infor.carrentalapplication.model.carregisteruser.CarRegisterUser;
import com.infor.carrentalapplication.repository.CarRegisterUserRepository;
import com.infor.carrentalapplication.service.CarRegisterUserService;
import com.infor.carrentalapplication.utils.IllegalArgException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class CarRegisterUserServiceImplTest {

    @Autowired
    private CarRegisterUserService carRegisterService;
    @MockBean
    private CarRegisterUserRepository carRegisterUserRepository;

    @TestConfiguration
    static class CarRentalUserContextConfiguration {

        @Bean
        public CarRegisterUserService carRegisterUserService() {
            return new CarRegisterUserServiceImpl();
        }

    }
    @BeforeEach()
    public void setUp() {
        CarRegisterUser carRegisterUser =  new CarRegisterUser("User98", "Jphn", "Doe", "pas12341223232115");
        Mockito.when(carRegisterUserRepository.getOne(carRegisterUser.getUserName()))
                .thenReturn(carRegisterUser);
        Mockito.when(carRegisterUserRepository.findById(carRegisterUser.getUserName()))
                .thenReturn(Optional.of(carRegisterUser));

    }
    @Test
    void testBasics() throws IllegalArgException {
        CarRegisterUser carRegisterUser = new CarRegisterUser("User99", "Jphn", "Doe", "pas12341223232115");
         carRegisterService.createCarRegisterUser(carRegisterUser);
    }
    @Test
    void testCarRegsterUserAlreadyPresent() throws IllegalArgException {
        CarRegisterUser carRegisterUser = new CarRegisterUser("User98", "Jphn", "Doe", "pas12341223232115");
        Assertions.assertThrows(IllegalArgException.class, () ->
                carRegisterService.createCarRegisterUser(carRegisterUser));
    }
}
