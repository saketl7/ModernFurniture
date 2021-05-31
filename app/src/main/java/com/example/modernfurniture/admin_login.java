package com.example.modernfurniture;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        Button btn = findViewById(R.id.btnlogin2);
        EditText email = findViewById(R.id.Aemail);
        EditText pass = findViewById(R.id.Apassword);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(email.getText().toString(), pass.getText().toString());
            }
        });
    }

    public void validate(String inputEmail, String password) {

        Log.d("Value", "is email " +inputEmail);
        Log.d("Value", "is password" +password);

        if (inputEmail.equals("admin")){
            if(password.equals("pass")){
            startActivity(new Intent(getApplicationContext(),Admin_userReport.class));
            Toast.makeText(admin_login.this, "You have successfully logged in", Toast.LENGTH_SHORT).show();
        }}
        else{
            Toast.makeText(admin_login.this,"Please enter correct credentials", Toast.LENGTH_SHORT).show();
        }
    }
}