package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SmartContractActivity extends AppCompatActivity {

    EditText totalAmountInput, membersInput, paidMemberInput;
    Button deployBtn, calculateBtn, payBtn, backBtn; // ✅ added backBtn

    TextView contractStatus, shareResult;

    ListView recordList;

    ArrayList<String> records = new ArrayList<>();
    ArrayAdapter<String> adapter;

    double totalAmount = 0;
    int membersCount = 0;
    double perShare = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_contract);

        // INIT UI
        totalAmountInput = findViewById(R.id.totalAmountInput);
        membersInput = findViewById(R.id.membersInput);
        paidMemberInput = findViewById(R.id.paidMemberInput);

        deployBtn = findViewById(R.id.deployBtn);
        calculateBtn = findViewById(R.id.calculateBtn);
        payBtn = findViewById(R.id.payBtn);
        backBtn = findViewById(R.id.backBtn); // ✅ NEW

        contractStatus = findViewById(R.id.contractStatus);
        shareResult = findViewById(R.id.shareResult);

        recordList = findViewById(R.id.recordList);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, records);
        recordList.setAdapter(adapter);

        // =========================
        // DEPLOY CONTRACT
        // =========================
        deployBtn.setOnClickListener(v -> {

            String amountStr = totalAmountInput.getText().toString();
            String memberStr = membersInput.getText().toString();

            if (amountStr.isEmpty() || memberStr.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            totalAmount = Double.parseDouble(amountStr);
            membersCount = Integer.parseInt(memberStr);

            contractStatus.setText("Smart Contract Deployed ✅");

            records.add("Contract deployed with amount: " + totalAmount);
            adapter.notifyDataSetChanged();
        });

        // =========================
        // CALCULATE SHARES
        // =========================
        calculateBtn.setOnClickListener(v -> {

            if (membersCount == 0) {
                Toast.makeText(this, "Deploy contract first", Toast.LENGTH_SHORT).show();
                return;
            }

            perShare = totalAmount / membersCount;

            shareResult.setText("Each share: " + perShare);

            records.add("Share calculated: " + perShare);
            adapter.notifyDataSetChanged();
        });

        // =========================
        // PAYMENT
        // =========================
        payBtn.setOnClickListener(v -> {

            String name = paidMemberInput.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(this, "Enter member name", Toast.LENGTH_SHORT).show();
                return;
            }

            records.add(name + " paid: " + perShare + " ETH");
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Payment recorded (immutable)", Toast.LENGTH_SHORT).show();
        });

        // =========================
        // BACK BUTTON
        // =========================
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(
                    SmartContractActivity.this,
                    HomeActivity2.class
            );
            startActivity(intent);
            finish();
        });
    }
}