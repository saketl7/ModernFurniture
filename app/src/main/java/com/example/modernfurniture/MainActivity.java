package com.example.modernfurniture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private ArrayList<Integer> imagesPath = new ArrayList<Integer>();
    private ArrayList<String> namesPath = new ArrayList<>();
    private ArrayList<String> modelNames = new ArrayList<>();
    AnchorNode anchorNode;
    private Button btnRemove;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.camera);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.wishlist:
                        startActivity(new Intent(getApplicationContext(),wishlist.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.gallery:
                        startActivity(new Intent(getApplicationContext(),gallery.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(),cart.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),profile.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.camera:
                        return true;

                }
                return false;
            }
        });

        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        btnRemove = (Button)findViewById(R.id.remove);
        getImages();

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            Anchor anchor = hitResult.createAnchor();

            ModelRenderable.builder()
                    .setSource(this, Uri.parse(Common.model))
                    .build()
                    .thenAccept(modelRenderable -> addModelToScene(anchor,modelRenderable));

        });


        btnRemove.setOnClickListener(view -> removeAnchorNode(anchorNode));
    }

    private void getImages() {

        imagesPath.add(R.drawable.table);
        imagesPath.add(R.drawable.bookshelf);
        imagesPath.add(R.drawable.lamp);
        imagesPath.add(R.drawable.odltv);
        imagesPath.add(R.drawable.clothdryer);
        imagesPath.add(R.drawable.chair);
        namesPath.add("Table");
        namesPath.add("BookShelf");
        namesPath.add("Lamp");
        namesPath.add("Old Tv");
        namesPath.add("Cloth Dryer");
        namesPath.add("Chair");
        modelNames.add("table.sfb");
        modelNames.add("model.sfb");
        modelNames.add("lamp.sfb");
        modelNames.add("tv.sfb");
        modelNames.add("cloth.sfb");
        modelNames.add("chair.sfb");

        initaiteRecyclerview();
    }

    private void initaiteRecyclerview() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(this,namesPath,imagesPath,modelNames);
        recyclerView.setAdapter(adapter);

    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {

        anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
    }

    public void removeAnchorNode(AnchorNode nodeToremove) {
        if (nodeToremove != null) {
            arFragment.getArSceneView().getScene().removeChild(nodeToremove);
            Objects.requireNonNull(nodeToremove.getAnchor()).detach();
            nodeToremove.setParent(null);
            //nodeToremove = null;
        }
    }
}