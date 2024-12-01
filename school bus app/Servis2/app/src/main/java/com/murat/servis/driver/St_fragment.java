package com.murat.servis.driver;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.murat.servis.ApiService;
import com.murat.servis.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class St_fragment extends Fragment {
    List<StudentResponse> students;
    ApiService apiService;
    private StudentAdapter adapter;
    private List<StudentResponse> studentList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_students);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.222:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        Call<FetchStudentResponse> call = apiService.getStudentInfo("" + DriverLogin.driver.getDriver_mail() + "");
        call.enqueue(new Callback<FetchStudentResponse>() {
            @Override
            public void onResponse(Call<FetchStudentResponse> call, Response<FetchStudentResponse> response) {
                if (response.isSuccessful()) {
                    FetchStudentResponse fetchStudentResponse = response.body();
                    if (fetchStudentResponse != null) {
                        students = fetchStudentResponse.getStudents();
                        if (students != null) {
                            adapter = new StudentAdapter(getActivity(), students);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.e("Error", "No students found");
                        }
                    } else {
                        Log.e("Error", "Response body is null");
                    }
                } else {
                    Toast.makeText(getActivity(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("Error", "Unsuccessful response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FetchStudentResponse> call, Throwable t) {
                Log.e("API_ERROR", "Network error", t);
                Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_st,container,false);
    }


}
