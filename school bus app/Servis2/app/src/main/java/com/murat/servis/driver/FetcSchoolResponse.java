package com.murat.servis.driver;

import java.util.List;

public class FetcSchoolResponse {
    List<SchoolResponse> schools;
    String error;

    public FetcSchoolResponse(List<SchoolResponse> schools, String error) {
        this.schools = schools;
        this.error = error;
    }

    public List<SchoolResponse> getSchools() {
        return schools;
    }

    public void setSchools(List<SchoolResponse> schools) {
        this.schools = schools;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
