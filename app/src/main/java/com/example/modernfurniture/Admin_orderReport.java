package com.example.modernfurniture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Admin_orderReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_report);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.Anavigation);
        bottomNavigationView.setSelectedItemId(R.id.order);
        //navigation bar
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.addP:
                        startActivity(new Intent(getApplicationContext(),Add_products.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.order:

                        return true;

                    case R.id.log:
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ureport:
                        startActivity(new Intent(getApplicationContext(),Admin_userReport.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }


}