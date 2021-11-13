package com.example.geolocatorapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int REQUEST_CODE_ADD_LOCATION = 1;
    private int REQUEST_CODE_EDIT_LOCATION = 2;

    private LocationModel location;
    private LocationDatabase locationDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationDatabase = new LocationDatabase(MainActivity.this);

        EditText searchInput;
        ImageView addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), AddNewLocation.class), REQUEST_CODE_ADD_LOCATION);
            }
        });
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
    }
}