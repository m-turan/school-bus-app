package com.murat.servis.parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.murat.servis.R;
import com.murat.servis.driver.AboutFragment;
import com.murat.servis.driver.HomeFragment;
import com.murat.servis.driver.SchoolFragment;
import com.murat.servis.driver.SettingsFragment;
import com.murat.servis.driver.St_fragment;

public class ParentHomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
     DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home_page);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout2);
        NavigationView navigationView = findViewById(R.id.nav_vin2);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new PSearchFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_about2);
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_about2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new PAboutFragment()).commit();
        } else if (itemId == R.id.p_driver) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new PDriverFragment()).commit();
        } else if (itemId == R.id.p_driver_search) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new PSearchFragment()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}