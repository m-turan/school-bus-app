package com.murat.servis.driver;

public class StudentResponse {
    private String studentName;
    private String studentSurname;
    private String schoolName;
    private String  parentPhone;

    public StudentResponse(String studentName, String studentSurname, String schoolName, String parentPhone) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.schoolName = schoolName;
        this.parentPhone = parentPhone;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
