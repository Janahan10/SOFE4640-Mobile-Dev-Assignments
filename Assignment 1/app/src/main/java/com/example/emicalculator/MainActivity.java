package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.lang.Math.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method called to perform calculation
    public void calculatePayment(View view) {
        Intent intent = new Intent(MainActivity.this, DisplayPaymentActivity.class);
        EditText principleText = (EditText) findViewById(R.id.principleAmountField);
        EditText interestRateText = (EditText) findViewById(R.id.interestRateField);
        EditText paymentMonthsText = (EditText) findViewById(R.id.paymentFreqField);

        int principleAmount = Integer.parseInt(principleText.getText().toString());
        double interestRate = Double.parseDouble(interestRateText.getText().toString());
        int numMonths = Integer.parseInt(paymentMonthsText.getText().toString());

        double totalAmount = principleAmount * ((interestRate * Math.pow(1 + interestRate, numMonths))
                / (Math.pow(1 + interestRate, numMonths) - 1));

        double result = Math.floor(totalAmount * 100) / 100;
        String resultText = Double.toString(result);

        intent.putExtra("Result", resultText);
        startActivity(intent);
        finish();
    }
}