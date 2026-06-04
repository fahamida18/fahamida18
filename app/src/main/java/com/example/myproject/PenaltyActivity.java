package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PenaltyActivity extends AppCompatActivity {

    EditText amountInput, deadlineInput, paidDateInput;
    Button checkBtn, backBtn;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalty);

        // INIT UI
        amountInput = findViewById(R.id.amountInput);
        deadlineInput = findViewById(R.id.deadlineInput);
        paidDateInput = findViewById(R.id.paidDateInput);

        checkBtn = findViewById(R.id.checkBtn);
        backBtn = findViewById(R.id.backBtn);
        resultText = findViewById(R.id.resultText);

        // =========================
        // CHECK PENALTY BUTTON
        // =========================
        checkBtn.setOnClickListener(v -> {

            String amountStr = amountInput.getText().toString().trim();
            String deadlineStr = deadlineInput.getText().toString().trim();
            String paidStr = paidDateInput.getText().toString().trim();

            if (amountStr.isEmpty() || deadlineStr.isEmpty() || paidStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount = Double.parseDouble(amountStr);
            int deadline = Integer.parseInt(deadlineStr);
            int paidDay = Integer.parseInt(paidStr);

            // =========================
            // PENALTY LOGIC
            // =========================
            if (paidDay <= deadline) {

                resultText.setText("✅ ON TIME PAYMENT\nNo Penalty Applied");
                resultText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));

            } else {

                int lateDays = paidDay - deadline;
                double penalty = lateDays * (amount * 0.02); // 2% per day
                double total = amount + penalty;

                resultText.setText(
                        "❌ LATE PAYMENT\n" +
                                "Late Days: " + lateDays + "\n" +
                                "Penalty: " + penalty + "\n" +
                                "Total Payable: " + total
                );

                resultText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }
        });

        // =========================
        // BACK BUTTON
        // =========================
        backBtn.setOnClickListener(v -> {

            Intent intent = new Intent(
                    PenaltyActivity.this,
                    HomeActivity2.class
            );

            startActivity(intent);
            finish();
        });
    }
}