package com.example.modernfurniture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_login extends AppCompatActivity {

    private EditText email, pass;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        btn = findViewById(R.id.btnlogin2);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(email.getText().toString(), pass.getText().toString());
            }

            public void validate(String inputEmail, String inputUsername) {
                if((inputEmail.equals("admin@gmail.com"))&&(inputUsername.equals("12345"))){
                    Intent intent = new Intent(admin_login.this, Add_products.class);
                    startActivity(intent);
                    Toast.makeText(admin_login.this, "You have successfully logged in", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(admin_login.this,"Please enter correct credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}