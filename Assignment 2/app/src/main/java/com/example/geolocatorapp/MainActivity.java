package com.example.geolocatorapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int REQUEST_CODE_ADD_LOCATION = 1;
    private int REQUEST_CODE_EDIT_LOCATION = 2;

    private LocationModel location;
    private LocationDatabase locationDatabase;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter locationAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationDatabase = new LocationDatabase(MainActivity.this);

        System.out.println(locationDatabase.getAllLocations().toString());

        updateLocationView();

        EditText searchInput;
        ImageView addButton = findViewById(R.id.addButton);

        EditText searchField = findViewById(R.id.searchField);
        ImageView searchButton = findViewById(R.id.searchIcon);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchField.getText().toString();
                List<LocationModel> resultLocations = locationDatabase.searchLocations(query);

                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("result", (Serializable) resultLocations);
                intent.putExtra("bundle", bundle);
                intent.putExtra("query", query);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), AddNewLocation.class), REQUEST_CODE_ADD_LOCATION);
            }
        });
    }

    private void updateLocationView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        locationAdapter = new LocationAdapter(locationDatabase.getAllLocations(), MainActivity.this);
        recyclerView.setAdapter(locationAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_LOCATION) {
            if (resultCode == Activity.RESULT_OK) {
                location = (LocationModel) data.getExtras().getSerializable("location");
            }
        }
        System.out.println("LOCATION ----> " + location.toString());

        if (location.getAddress() != null && location.getId() != -500) {
            System.out.println(locationDatabase.addLocation(location));
        }

        updateLocationView();
    }
}