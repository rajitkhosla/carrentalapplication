package com.infor.carrentalapplication.service;

import com.infor.carrentalapplication.model.cars.Car;
import com.infor.carrentalapplication.model.carbooking.CarBooking;
import com.infor.carrentalapplication.model.carbooking.CarBookingRequest;
import com.infor.carrentalapplication.model.carbooking.BookedCarsDateReport;
import com.infor.carrentalapplication.model.carbooking.BookedCarsPerHourReportResult;
import com.infor.carrentalapplication.model.carbooking.BookedCarsReport;
import com.infor.carrentalapplication.utils.CarBookedAlreadyException;
import com.infor.carrentalapplication.utils.CarNotFoundException;
import com.infor.carrentalapplication.utils.IllegalArgException;
import com.infor.carrentalapplication.utils.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarBookingService {
    CarBooking createBooking(CarBookingRequest newCarBookingRequest) throws CarBookedAlreadyException, UserNotFoundException, CarNotFoundException;

    List<Car> searchBookedCars(BookedCarsReport bookedCarsReport);

    Double searchTotalPayment(BookedCarsDateReport bookedCarsReport);

    List<BookedCarsPerHourReportResult> searchBookedCarPerHour(BookedCarsDateReport bookedCarsReport) throws IllegalArgException;
}
