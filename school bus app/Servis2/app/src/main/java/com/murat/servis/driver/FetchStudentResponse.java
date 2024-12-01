package com.murat.servis.driver;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchStudentResponse {

    List<StudentResponse> students;
    String error;

    public FetchStudentResponse(List<StudentResponse> students, String error) {
        this.students = students;
        this.error = error;
    }

    public List<StudentResponse> getStudents() {
        return students;
    }

    public void setStudents(List<StudentResponse> students) {
        this.students = students;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
