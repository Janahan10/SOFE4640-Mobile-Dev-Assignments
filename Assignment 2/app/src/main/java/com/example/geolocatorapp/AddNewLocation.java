package com.example.geolocatorapp;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AddNewLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_location);

        ImageView imageBack = findViewById(R.id.backButton);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                LocationModel locationModel = new LocationModel();
                intent.putExtra("note", locationModel);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        ImageView confirmButton = findViewById(R.id.doneButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText latitudeInput = (EditText) findViewById(R.id.latitudeInput);
                EditText longitudeInput = (EditText) findViewById(R.id.longitudeInput);

                double latitude = 0;
                double longitude = 0;
                String address = null;

                if (latitudeInput.getText().toString().isEmpty()) {
                    latitudeInput.setError("Latitude Value Required");
                } else {
                    latitude = Double.parseDouble(latitudeInput.getText().toString());
                }

                if (latitudeInput.getText().toString().isEmpty()) {
                    longitudeInput.setError("Longitude Value Required");
                } else {
                    longitude = Double.parseDouble(longitudeInput.getText().toString());
                }

                if (Geocoder.isPresent()) {
                    Geocoder geocoder =
                            new Geocoder(AddNewLocation.this, Locale.getDefault());

                    try {

                        List<Address> result =
                                geocoder.getFromLocation(latitude, longitude, 1);

                        for (Address ad : result) {
                            address = ad.getAddressLine(0);
                        }

                        LocationModel location = new LocationModel(-1, latitude, longitude, address);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}