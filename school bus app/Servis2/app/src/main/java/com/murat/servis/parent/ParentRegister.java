package com.murat.servis.parent;

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


public class ParentRegister extends AppCompatActivity {
    EditText p_name;
    EditText p_surname;
    EditText p_phone;
    EditText p_mail;
    EditText p_password;
    EditText p_birthday;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_register);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.222:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        p_name = findViewById(R.id.parent_name);
        p_surname = findViewById(R.id.parent_surname);
        p_phone = findViewById(R.id.parent_number);
        p_mail = findViewById(R.id.register_email);
        p_password = findViewById(R.id.register_password);
        p_birthday = findViewById(R.id.parent_birthday);


    }
    public void registered(View view){

        ParentLogin.parent.setParent_name(p_name.getText().toString().trim());
        ParentLogin.parent.setParent_surname(p_surname.getText().toString().trim());
        ParentLogin.parent.setParent_phone(p_phone.getText().toString().trim());
        ParentLogin.parent.setParent_mail(p_mail.getText().toString().trim());
        ParentLogin.parent.setParent_password(p_password.getText().toString().trim());
        ParentLogin.parent.setBirthday(p_birthday.getText().toString().trim());
        Call<Void> call = apiService.addParent(ParentLogin.parent);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ParentRegister.this, "parent added successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ParentRegister.this, "Failed to add parent", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API_ERROR", "Network error", t);
                Toast.makeText(ParentRegister.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });




        Intent intent = new Intent(ParentRegister.this, StudentInfo.class);
        startActivity(intent);
    }
}