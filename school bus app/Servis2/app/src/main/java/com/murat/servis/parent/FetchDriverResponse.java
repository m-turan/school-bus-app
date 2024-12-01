package com.murat.servis.parent;

import com.murat.servis.driver.DriverInfoResponse;

import java.util.List;

public class FetchDriverResponse {
    List<DriverInfoResponse> drivers;
    String error;

    public FetchDriverResponse(List<DriverInfoResponse> drivers, String error) {
        this.drivers = drivers;
        this.error = error;
    }

    public List<DriverInfoResponse> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverInfoResponse> drivers) {
        this.drivers = drivers;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
