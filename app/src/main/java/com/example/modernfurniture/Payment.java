package com.example.modernfurniture;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.cardform.view.CardForm;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Payment extends AppCompatActivity {

    CardForm cardForm;
    Button buy;
    AlertDialog.Builder alertBuilder;
    TextView btn;
    ImageView back;
    EditText address, name2;
    FirebaseAuth auth;
    private FirebaseFirestore db;
    getCartData cart = new getCartData();
    String method2 = "cash on delivery";
    String method1 = "Online paid";
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        back = findViewById(R.id.Pback);
        name2 = findViewById(R.id.Pname);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),cart.class));
            }
        });
        cardForm = findViewById(R.id.card_form);
        buy = findViewById(R.id.paybtn);
        btn = findViewById(R.id.option1);
        address = findViewById(R.id.Paddress);

        //cash on delivery database
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name2.getText().toString().equals("") || address.getText().toString().equals("")){
                    Toast.makeText(Payment.this, "Enter All details", Toast.LENGTH_LONG).show();
                }
                else {
                    addOrder2();
                    startActivity(new Intent(getApplicationContext(), SuccessPay.class));
                }
            }
        });


        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(Payment.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name2.getText().toString().equals("") || address.getText().toString().equals("")) {
                    Toast.makeText(Payment.this, "Enter All details", Toast.LENGTH_LONG).show();
                } else {
                    if (cardForm.isValid()) {
                        alertBuilder = new AlertDialog.Builder(Payment.this);
                        alertBuilder.setTitle("Confirm before purchase");
                        alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                                "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                                "Card CVV: " + cardForm.getCvv() + "\n" +
                                "Postal code: " + cardForm.getPostalCode() + "\n" +
                                "Phone number: " + cardForm.getMobileNumber());
                        alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                //Toast.makeText(Payment.this, "Thank you for purchase", Toast.LENGTH_LONG).show();
                                addOrder1();
                                startActivity(new Intent(getApplicationContext(), SuccessPay.class));

                            }
                        });
                        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.show();

                    } else {
                        Toast.makeText(Payment.this, "Please complete the form", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void addOrder1(){
        String saveCurrentTime, saveCurrentDate;
        Calendar calforDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy");
        saveCurrentDate = currentDate.format(calforDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime = currentTime.format(calforDate.getTime());
        String id = UUID.randomUUID().toString();

        final HashMap<String, Object> cartMap2 = new HashMap<>();
        cartMap2.put("date", saveCurrentDate);
        cartMap2.put("time", saveCurrentTime);
        cartMap2.put("method", method1);
        cartMap2.put("orderId", id);
        cartMap2.put("address", address.getText().toString());
        cartMap2.put("userName", name2.getText().toString());
        db.collection("Admin_Orders").document(id)
                .set(cartMap2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //Toast.makeText(DetailsPage.this,"Added to Cart",Toast.LENGTH_SHORT).show();
            }
        });


        db.collection("Cart").document(auth.getCurrentUser().getUid())
                .collection("users").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> clist = queryDocumentSnapshots.getDocuments();

                            for(DocumentSnapshot d : clist){
                                final HashMap<String, Object> cartMap = new HashMap<>();
                                cartMap.put("date", saveCurrentDate);
                                cartMap.put("time", saveCurrentTime);
                                cartMap.put("method", method1);
                                cartMap.put("orderId", id);
                                cartMap.put("address", address.getText().toString());
                                cartMap.put("userName", name2.getText().toString());
                                db.collection("Orders").document(auth.getCurrentUser().getUid())
                                        .collection("users").document(id)
                                        .set(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        //Toast.makeText(DetailsPage.this,"Added to Cart",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                getCartData p = d.toObject(getCartData.class);
                                CollectionReference dbOrder = db.collection("Orders").document(auth.getCurrentUser().getUid()).collection("users").document(id).collection("Ordered_products");
                                dbOrder.add(p);

                                getCartData p2 = d.toObject(getCartData.class);
                                CollectionReference dbOrder2 = db.collection("Admin_Orders").document(id).collection("Ordered_products");
                                dbOrder2.add(p2);

                            }
                        }
                    }
                });

    };

    public void addOrder2(){
        String saveCurrentTime, saveCurrentDate;
        Calendar calforDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy");
        saveCurrentDate = currentDate.format(calforDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime = currentTime.format(calforDate.getTime());
        String id = UUID.randomUUID().toString();

        final HashMap<String, Object> cartMap2 = new HashMap<>();
        cartMap2.put("date", saveCurrentDate);
        cartMap2.put("time", saveCurrentTime);
        cartMap2.put("method", method2);
        cartMap2.put("orderId", id);
        cartMap2.put("address", address.getText().toString());
        cartMap2.put("userName", name2.getText().toString());
        db.collection("Admin_Orders").document(id)
                .set(cartMap2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //Toast.makeText(DetailsPage.this,"Added to Cart",Toast.LENGTH_SHORT).show();
            }
        });

        db.collection("Cart").document(auth.getCurrentUser().getUid())
                .collection("users").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> clist = queryDocumentSnapshots.getDocuments();

                            for(DocumentSnapshot d : clist){
                                final HashMap<String, Object> cartMap = new HashMap<>();
                                cartMap.put("date", saveCurrentDate);
                                cartMap.put("time", saveCurrentTime);
                                cartMap.put("method", method2);
                                cartMap.put("orderId", id);
                                cartMap.put("address", address.getText().toString());
                                cartMap.put("userName", name2.getText().toString());
                                db.collection("Orders").document(auth.getCurrentUser().getUid())
                                        .collection("users").document(id)
                                        .set(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        //Toast.makeText(DetailsPage.this,"Added to Cart",Toast.LENGTH_SHORT).show();
                                    }
                                });

                                getCartData p = d.toObject(getCartData.class);
                                CollectionReference dbOrder = db.collection("Orders").document(auth.getCurrentUser().getUid()).collection("users").document(id).collection("Ordered_products");
                                dbOrder.add(p);

                                getCartData p2 = d.toObject(getCartData.class);
                                CollectionReference dbOrder2 = db.collection("Admin_Orders").document(id).collection("Ordered_products");
                                dbOrder2.add(p2);

                            }
                        }
                    }
                });

    };
}