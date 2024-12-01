package com.murat.servis.parent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.murat.servis.ApiService;
import com.murat.servis.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentInfo extends AppCompatActivity {

    EditText name;
    EditText surname;
    EditText schoolName;
    EditText age;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.222:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        name = findViewById(R.id.s_name);
        surname = findViewById(R.id.s_surname);
        schoolName = findViewById(R.id.sc_name);
        age = findViewById(R.id.s_age);



    }
    public void registerStudent(View view){
        Student student = new Student();
        String value = age.getText().toString().trim();
        int finalValue = Integer.parseInt(value);
        student.setStudent_name(name.getText().toString().trim());
        student.setStudent_surname(surname.getText().toString().trim());
        student.setSchool_name(schoolName.getText().toString().trim());
        student.setStudent_age(finalValue);
        Call<Void> call = apiService.addStudent(student);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(StudentInfo.this,"Student added succesfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(StudentInfo.this,"Student couldn't added", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Apı Error","Network error");
                Toast.makeText(StudentInfo.this,"network error", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = new Intent(StudentInfo.this, ParentLogin.class);
        startActivity(intent);



    }


}