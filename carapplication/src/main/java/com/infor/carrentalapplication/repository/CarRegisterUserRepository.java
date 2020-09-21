package com.infor.carrentalapplication.repository;

import com.infor.carrentalapplication.model.carregisteruser.CarRegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRegisterUserRepository extends JpaRepository<CarRegisterUser, String> {
}
