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

public class DriverLogin extends AppCompatActivity {
    static Driver driver = new Driver();
    EditText driver_mail;
    EditText driver_password;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_login);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.222:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        driver_mail = findViewById(R.id.driver_login_mail);
        driver_password = findViewById(R.id.driver_login_password);

        apiService = retrofit.create(ApiService.class);

    }
    public void driverRegister(View view){

        Intent intent = new Intent(DriverLogin.this, DriverRegister.class);
        startActivity(intent);
    }
    public void driverLogged(View view){

        DriverLogin.driver.setDriver_mail(driver_mail.getText().toString().trim());
        DriverLogin.driver.setDriver_password(driver_password.getText().toString());

        Call<Void> call = apiService.driverLogin(DriverLogin.driver);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(DriverLogin.this,"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DriverLogin.this, DriverHomePage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(DriverLogin.this,"Kullanıcı adı veya şifre yanlış",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API_ERROR", "Network error", t);
                Toast.makeText(DriverLogin.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}