package com.example.geolocatorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter locationAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ImageView imageBack = findViewById(R.id.backButton3);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                SearchResultActivity.super.onBackPressed();
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String searchQuery = intent.getStringExtra("query");
        List<LocationModel> locations = (ArrayList<LocationModel>) bundle.getSerializable("result");
        updateLocations(locations);
    }

    private void updateLocations(List<LocationModel> locations) {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        locationAdapter = new LocationAdapter(locations, SearchResultActivity.this);
        recyclerView.setAdapter(locationAdapter);
    }
}