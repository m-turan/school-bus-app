package com.murat.servis.driver;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



import androidx.fragment.app.Fragment;
import com.murat.servis.ApiService;
import com.murat.servis.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AboutFragment extends Fragment {

    private ApiService apiService;
    private TextView nameTextView;
    private TextView surnameTextView;
    private TextView mailTextView;
    private TextView phoneTextView;
    private TextView birthdayTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        nameTextView = rootView.findViewById(R.id.input_name);
        surnameTextView = rootView.findViewById(R.id.input_surname);
        mailTextView = rootView.findViewById(R.id.input_mail);
        phoneTextView = rootView.findViewById(R.id.input_phone);
        birthdayTextView = rootView.findViewById(R.id.input_birthday);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.222:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        String m=DriverLogin.driver.getDriver_mail();
        String p=DriverLogin.driver.getDriver_password();
        System.out.println(DriverLogin.driver.getDriver_mail());

        Call<DriverInfoResponse> call = apiService.getDriverInfo(""+DriverLogin.driver.getDriver_mail()+"",""+DriverLogin.driver.getDriver_password()+"");
        call.enqueue(new Callback<DriverInfoResponse>() {
            @Override
            public void onResponse(Call<DriverInfoResponse> call, Response<DriverInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                        DriverInfoResponse driverInfo = response.body();
                        nameTextView.setText(driverInfo.getDriverName());
                        surnameTextView.setText(driverInfo.getDriverSurname());
                        mailTextView.setText(driverInfo.getDriverEmail());
                        phoneTextView.setText(driverInfo.getDriverPhone());
                        birthdayTextView.setText(driverInfo.getDriverBirthday());
                    }
                else {
                    Log.e("API_ERROR", "Error: " + response.message());
                    Toast.makeText(getContext(), "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<DriverInfoResponse> call, Throwable t) {
                Log.e("API_ERROR", "Network error", t);
                Toast.makeText(getContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        return rootView;
    }


}
