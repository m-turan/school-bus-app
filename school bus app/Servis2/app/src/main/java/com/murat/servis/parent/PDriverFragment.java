package com.murat.servis.parent;

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
import com.murat.servis.driver.DriverInfoResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PDriverFragment extends Fragment {
    private List<DriverInfoResponse> drivers;
    private DriverAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_p_driver, container, false);
        recyclerView = rootView.findViewById(R.id.driver_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.222:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<FetchDriverResponse> call = apiService.getDrivers("Gnal");
        call.enqueue(new Callback<FetchDriverResponse>() {
            @Override
            public void onResponse(Call<FetchDriverResponse> call, Response<FetchDriverResponse> response) {
                if (response.isSuccessful()) {
                    FetchDriverResponse fetchDriverResponse = response.body();
                    if (fetchDriverResponse != null) {
                        drivers = fetchDriverResponse.getDrivers();
                        if (drivers != null && !drivers.isEmpty()) {
                            adapter = new DriverAdapter(getActivity(), drivers);
                            recyclerView.setAdapter(adapter);
                            Toast.makeText(getActivity(), "Başarılı: ", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("Error", "No drivers found");
                            Toast.makeText(getActivity(), "Sürücü bulunamadı", Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<FetchDriverResponse> call, Throwable t) {
                Log.e("API_ERROR", "Network error", t);
                Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }


}
