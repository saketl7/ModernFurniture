package com.example.modernfurniture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.UUID;

public class DetailsPage extends AppCompatActivity {
    ImageView back;
    ImageView Img;
    TextView name,price,type,quantity;
    Button addtoCart, wishlist, view;
    ImageView add,remove;
    int totalQuntity = 1;

    private FirebaseFirestore db;
    FirebaseAuth auth;
    Products products = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object obj = getIntent().getSerializableExtra("details");

        if(obj instanceof Products){
            products = (Products) obj;
        }

        view = findViewById(R.id.D360);
        Img = findViewById(R.id.Dimage);
        quantity = findViewById(R.id.Dquantity);
        name = findViewById(R.id.Dnamefill);
        price = findViewById(R.id.Dpricefill);
        type = findViewById(R.id.Dtypefill);
        addtoCart = findViewById(R.id.Dcart);
        wishlist = findViewById(R.id.Dwishlist);
        add = findViewById(R.id.Dplus);
        remove = findViewById(R.id.Dminus);

        //new products
        if(products != null){
            Picasso.get().load(products.getImageUrl()).into(Img);
            name.setText(products.getName());
            price.setText(String.valueOf(products.getPrice()));
            type.setText(products.getType());
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuntity<10){
                    totalQuntity++;
                    quantity.setText(String.valueOf(totalQuntity));
                }

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuntity>1){
                    totalQuntity--;
                    quantity.setText(String.valueOf(totalQuntity));
                }

            }
        });

        back = findViewById(R.id.Dback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),gallery.class));
            }
        });
        
        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtoCart();
            }
        });

        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtoWishlist();
            }
        });
    }

    private void addtoWishlist() {
        String price2 = price.getText().toString();
        String id = UUID.randomUUID().toString();
        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("name",name.getText().toString());
        cartMap.put("type", products.getType());
        cartMap.put("image", products.getImageUrl());
        cartMap.put("price", Double.parseDouble(price2));
        cartMap.put("id", id);

        db.collection("Wishlist").document(auth.getCurrentUser().getUid())
                .collection("users").document(id)
                .set(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(DetailsPage.this,"Added to Wishlist",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addtoCart() {

        String image2 = products.getImageUrl();
        String name2 = name.getText().toString();
        String price2 = price.getText().toString();
        String quantity2 = quantity.getText().toString();
        String id = UUID.randomUUID().toString();
        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("id", id);
        cartMap.put("name", name2);
        cartMap.put("price", Double.parseDouble(price2));
        cartMap.put("quantity", Double.parseDouble(quantity2));
        cartMap.put("image", image2);

        db.collection("Cart").document(auth.getCurrentUser().getUid())
                .collection("users").document(id)
                .set(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(DetailsPage.this,"Added to Cart",Toast.LENGTH_SHORT).show();
            }
        });

    }

}