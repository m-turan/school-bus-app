package com.murat.servis.driver;

import com.google.gson.annotations.SerializedName;

public class DriverInfoResponse {

    private String driverName;

    private String driverSurname;

    private String driverBirthday;

    private String driverPhone;

    private String driverEmail;

    public DriverInfoResponse(String driverName, String driverSurname, String driverPhone, String driverEmail) {
        this.driverName = driverName;
        this.driverSurname = driverSurname;
        this.driverPhone = driverPhone;
        this.driverEmail = driverEmail;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverSurname() {
        return driverSurname;
    }

    public void setDriverSurname(String driverSurname) {
        this.driverSurname = driverSurname;
    }

    public String getDriverBirthday() {
        return driverBirthday;
    }

    public void setDriverBirthday(String driverBirthday) {
        this.driverBirthday = driverBirthday;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }
}
