package com.infor.carrentalapplication.model.carbooking;

import com.infor.carrentalapplication.model.Dates;
import com.infor.carrentalapplication.utils.CarApplicationsConstants;
import com.infor.carrentalapplication.utils.IllegalArgException;

import java.time.LocalDateTime;


public class BookedCarsPerHourReportResult extends Dates {
    private int noOfCarBooked;

    public BookedCarsPerHourReportResult(LocalDateTime fromDate, LocalDateTime toDate, int noOfCarBooked) throws IllegalArgException {
        super(fromDate, toDate);
        if (noOfCarBooked < 0) {
            throw new IllegalArgException(CarApplicationsConstants.NO_OF_BOOKED_CAR_GREATER_THAN_ZERO);
        }
        this.noOfCarBooked = noOfCarBooked;

    }

    public int getNoOfCarBooked() {
        return noOfCarBooked;
    }

    public void setNoOfCarBooked(int noOfCarBooked) {
        this.noOfCarBooked = noOfCarBooked;
    }

    @Override
    public String toString() {
        return "BookedCarsPerHourReportResult{" +
                "fromDate=" + getFromDate() +
                ", toDate=" + getToDate() +
                ", noOfCarBooked=" + noOfCarBooked +
                '}';
    }
}

