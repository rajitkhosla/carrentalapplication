package com.infor.carrentalapplication.model.carbooking;

import com.infor.carrentalapplication.model.Dates;
import com.infor.carrentalapplication.utils.IllegalArgException;

import java.time.LocalDateTime;

public class BookedCarsDateReport extends Dates {
    public BookedCarsDateReport(LocalDateTime fromDate, LocalDateTime toDate) throws IllegalArgException {
        super(fromDate, toDate);
    }

    private BookedCarsDateReport() {

    }
}


