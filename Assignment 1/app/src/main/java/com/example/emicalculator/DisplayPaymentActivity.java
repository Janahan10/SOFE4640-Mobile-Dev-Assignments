package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_payment);

        String result = getIntent().getExtras().getString("Result");
        TextView totalPaymentText = (TextView) findViewById(R.id.totalPayment);

        totalPaymentText.setText("$ " + result);
    }
}