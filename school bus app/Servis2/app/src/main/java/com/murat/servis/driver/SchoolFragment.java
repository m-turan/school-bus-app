package com.murat.servis.driver;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.murat.servis.ApiService;
import com.murat.servis.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SchoolFragment extends Fragment {
    List<SchoolResponse> schools;
    ApiService apiService;
    private SchoolAdapter schoolAdapter;
    private List<SchoolResponse> school_list;
    RecyclerView recyclerView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_school);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.222:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        Call<FetcSchoolResponse> call = apiService.getDriverSchools(""+DriverLogin.driver.getDriver_mail()+"");
        call.enqueue(new Callback<FetcSchoolResponse>() {
            @Override
            public void onResponse(Call<FetcSchoolResponse> call, Response<FetcSchoolResponse> response) {
                if(response.isSuccessful()){
                    FetcSchoolResponse fetcSchoolResponse = response.body();
                    if(fetcSchoolResponse!=null){
                        schools=fetcSchoolResponse.getSchools();
                        if(schools!=null){
                            schoolAdapter = new SchoolAdapter(getActivity(), schools);
                            recyclerView.setAdapter(schoolAdapter);
                        }
                        else {
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
            public void onFailure(Call<FetcSchoolResponse> call, Throwable t) {
                Log.e("API_ERROR", "Network error", t);
                Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
        Button addButton = view.findViewById(R.id.btnAddSchool);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSchool(v); // addSchool metodunu çağır

            }
        });
    }
    public void addSchool(View v){
        View rootView = getView();
        if(rootView != null) {
            EditText s_name = rootView.findViewById(R.id.editTextSchoolName);
            String okul_adi = s_name.getText().toString().trim();
            Log.d("DEBUG", "Okul adı: " + okul_adi); // Okul adını log'a yazdır
            Call<Void> call = apiService.addSchool(""+DriverLogin.driver.getDriver_mail()+"",""+okul_adi+"");
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getActivity(),"Başarıyla Eklendi",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getActivity(),"hata",Toast.LENGTH_SHORT).show();
                        Log.e("HATA bu ","easda");
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getActivity(),"db hata",Toast.LENGTH_SHORT).show();
                    Log.e("HATA bu ","easda");
                }
            });
        } else {
            Log.e("Error", "Root view is null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_school,container,false);
    }


}