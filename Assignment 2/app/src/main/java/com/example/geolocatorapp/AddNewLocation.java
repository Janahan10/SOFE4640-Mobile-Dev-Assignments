package com.example.geolocatorapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText latitudeField = (EditText) findViewById(R.id.latitudeInput);
                EditText longitudeField = (EditText) findViewById(R.id.longitudeInput);
                double latitude;
                double longitude;

                if (latitudeField.getText().toString() == "") {
                    latitudeField.setError("Latitude field is empty!");
                } else {
                    latitude = Double.parseDouble(latitudeField.getText().toString());
                }
                if (longitudeField.getText().toString() == "") {
                    longitudeField.setError("Longitude field is empty!");
                } else {
                    longitude = Double.parseDouble(longitudeField.getText().toString());
                }



            }
        });
    }
}