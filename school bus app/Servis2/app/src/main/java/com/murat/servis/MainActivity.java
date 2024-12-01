package com.murat.servis;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.murat.servis.driver.Driver;
import com.murat.servis.driver.DriverLogin;
import com.murat.servis.parent.Parent;
import com.murat.servis.parent.ParentLogin;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void parentScreen(View view){
        Intent intent = new Intent(MainActivity.this, ParentLogin.class);
        startActivity(intent);
    }
    public void driverScreen(View view){
        Intent intent = new Intent(MainActivity.this, DriverLogin.class);
        startActivity(intent);
    }


}