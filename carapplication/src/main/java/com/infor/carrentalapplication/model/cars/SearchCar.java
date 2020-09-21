package com.infor.carrentalapplication.model.cars;

import com.infor.carrentalapplication.model.Dates;
import com.infor.carrentalapplication.utils.CarApplicationsConstants;
import com.infor.carrentalapplication.utils.IllegalArgException;

import java.time.LocalDateTime;


public class SearchCar extends Dates {

    private Double maximumPricePerHour;

    public SearchCar(LocalDateTime fromDate, LocalDateTime toDate, Double maximumPricePerHour) throws IllegalArgException {
        super(fromDate, toDate);
        if (maximumPricePerHour <= 0.0) {
            throw new IllegalArgException(CarApplicationsConstants.MAXIMUM_PRICE_GREATER_THAN_ZERO);
        }
        this.maximumPricePerHour = maximumPricePerHour;
    }

    private SearchCar() {

    }

    public Double getMaximumPricePerHour() {
        return maximumPricePerHour;
    }

    public void setMaximumPricePerHour(Double maximumPricePerHour) {
        this.maximumPricePerHour = maximumPricePerHour;
    }

    @Override
    public String toString() {
        return "SearchCar{" +
                "maximumPricePerHour=" + maximumPricePerHour +
                ", fromDate=" + getFromDate() +
                ", toDate=" + getToDate() +
                '}';
    }
}

