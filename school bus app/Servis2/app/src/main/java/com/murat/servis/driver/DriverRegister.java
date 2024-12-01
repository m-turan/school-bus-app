package com.murat.servis.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.murat.servis.ApiService;
import com.murat.servis.MainActivity;
import com.murat.servis.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DriverRegister extends AppCompatActivity {
    EditText driver_name;
    EditText driver_surname;
    EditText driver_mail;
    EditText driver_password;
    EditText driver_phone;
    EditText birthday;
    EditText control_password;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_register);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.222:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        driver_name = findViewById(R.id.driver_name);
        driver_surname = findViewById(R.id.driver_surname);
        driver_mail = findViewById(R.id.register_email2);
        driver_phone = findViewById(R.id.driver_number);
        birthday = findViewById(R.id.driver_birthday);
        driver_password = findViewById(R.id.register_password2);
        control_password=findViewById(R.id.control_password2);
    }

    public void registered2(View view)  {


        DriverLogin.driver.setDriver_name(driver_name.getText().toString().trim());
        DriverLogin.driver.setDriver_surname(driver_surname.getText().toString().trim());
        DriverLogin.driver.setDriver_mail(driver_mail.getText().toString().trim());
        DriverLogin.driver.setDriver_phone(driver_phone.getText().toString().trim());
        DriverLogin.driver.setDriver_password(driver_password.getText().toString().trim());
        String c_password = control_password.getText().toString().trim();

        Call<Void> call = apiService.addDriver(DriverLogin.driver);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DriverRegister.this, "Driver added successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(DriverRegister.this, "Failed to add driver", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API_ERROR", "Network error", t);
                Toast.makeText(DriverRegister.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = new Intent(DriverRegister.this, DriverLogin.class);
        startActivity(intent);
    }
}