package com.example.modernfurniture;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class admin_login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btn;
    EditText emailAddress, password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        btn = findViewById(R.id.btnlogin2);
        emailAddress = findViewById(R.id.email);
        password1 = findViewById(R.id.password);



        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailAddress.getText().toString().trim();
                String Pass = password1.getText().toString().trim();

                if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(Pass)){
                    mAuth.signInWithEmailAndPassword(email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(admin_login.this,Add_products.class));
                                finish();
                            }else {
                                Toast.makeText(admin_login.this,"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(admin_login.this,"You forget to put some data",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}