package com.example.modernfurniture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Admin_userReport extends AppCompatActivity {

    public RecyclerView mRecycleView;
    public RecyclerView.LayoutManager mManager;
    private FirebaseFirestore db;
    EditText search;
    CharSequence searchC = "";
    ArrayList<getUserData> list = new ArrayList<>();
    ArrayList<getUserData> filteredList = new ArrayList<>();
    public UserReportAdapter mAdapter = new UserReportAdapter(filteredList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_report);

        search = findViewById(R.id.Usearch);

        mRecycleView = findViewById(R.id.recyclerView2);
        mRecycleView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this);
        mAdapter = new UserReportAdapter(list);
        mRecycleView.setLayoutManager(mManager);
        mRecycleView.setAdapter(mAdapter);

        db = FirebaseFirestore.getInstance();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.Anavigation);
        bottomNavigationView.setSelectedItemId(R.id.ureport);
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
                        startActivity(new Intent(getApplicationContext(),Admin_orderReport.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.log:
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ureport:
                        return true;

                }
                return false;
            }
        });


        db.collection("users").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> ulist = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : ulist) {
                                getUserData p = d.toObject(getUserData.class);
                                list.add(p);
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });

        //search
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mAdapter.getFilter().filter(s);
                searchC = s;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

}


}
