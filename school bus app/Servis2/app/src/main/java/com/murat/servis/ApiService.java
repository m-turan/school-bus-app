package com.murat.servis;

import com.murat.servis.driver.Driver;
import com.murat.servis.driver.DriverInfoResponse;
import com.murat.servis.driver.FetcSchoolResponse;
import com.murat.servis.driver.FetchStudentResponse;
import com.murat.servis.driver.StudentResponse;
import com.murat.servis.parent.FetchDriverResponse;
import com.murat.servis.parent.Parent;
import com.murat.servis.parent.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/addDriver")
    Call<Void>addDriver(@Body Driver driver);
    @POST("/addParent")
    Call<Void>addParent(@Body Parent parent);
    @POST("/addStudent")
    Call<Void>addStudent(@Body Student student);

    @POST("/driverLogin")
    Call<Void>driverLogin(@Body Driver driver);
    @POST("/parentLogin")
    Call<Void>parentLogin(@Body Parent parent);
    @GET("/getDriverInfo")
    Call<DriverInfoResponse> getDriverInfo(@Query("driver_mail") String driverMail, @Query("driver_password") String driverPassword);
    @GET("/getStudentInfo")
    Call<FetchStudentResponse> getStudentInfo(@Query("driver_mail") String driverMail);
    @GET("getDriverSchools")
    Call<FetcSchoolResponse> getDriverSchools(@Query("driver_mail") String driverMail);
    @POST("/addSchool")
    Call<Void>addSchool(@Query("driver_mail") String driverMail, @Query("driver_school_name") String school_name);
    @GET("/getDrivers")
    Call<FetchDriverResponse> getDrivers(@Query("school_name") String school_name);

}
