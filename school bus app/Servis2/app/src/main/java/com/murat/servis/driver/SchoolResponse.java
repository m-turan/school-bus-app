package com.murat.servis.driver;

public class SchoolResponse {
    private String schoolName;

    public SchoolResponse(String school_name) {
        this.schoolName = school_name;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
