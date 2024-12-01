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

public class ParentLogin extends AppCompatActivity {
    static Parent parent = new Parent();
    EditText parent_mail;
    EditText parent_password;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_screen);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.222:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        parent_mail = findViewById(R.id.parent_mail);
        parent_password = findViewById(R.id.parent_password);
        apiService = retrofit.create(ApiService.class);
    }
    public void parentRegister(View view){
        Intent intent = new Intent(ParentLogin.this, ParentRegister.class);
        startActivity(intent);
    }

    public void logged(View view){

        ParentLogin.parent.setParent_mail(parent_mail.getText().toString().trim());
        ParentLogin.parent.setParent_password(parent_password.getText().toString());

        Call<Void> call = apiService.parentLogin(ParentLogin.parent);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ParentLogin.this,"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ParentLogin.this,ParentHomePage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ParentLogin.this,"Kullanıcı adı veya şifre yanlış",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API_ERROR", "Network error", t);
                Toast.makeText(ParentLogin.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}