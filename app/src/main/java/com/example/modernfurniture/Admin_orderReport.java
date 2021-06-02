package com.example.modernfurniture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Admin_orderReport extends AppCompatActivity {
    public RecyclerView mRecycleView;
    public RecyclerView.LayoutManager mManager;
    FirebaseAuth auth;
    private FirebaseFirestore db;
    ArrayList<getOrderData> list = new ArrayList<>();
    public OrderAdapter mAdapter = new OrderAdapter(list);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_report);

        mRecycleView = findViewById(R.id.recyclerViewO);
        mRecycleView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this);
        mAdapter = new OrderAdapter(list);
        mRecycleView.setLayoutManager(mManager);
        mRecycleView.setAdapter(mAdapter);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

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

        db.collection("Admin_Orders").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> ulist = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : ulist) {
                                getOrderData p = d.toObject(getOrderData.class);
                                list.add(p);
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }


}