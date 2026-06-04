package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity2 extends AppCompatActivity {

    CardView createGroupCard;
    CardView smartContractCard;
    CardView penaltyCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home2);

        // Initialize Cards
        createGroupCard = findViewById(R.id.createGroupCard);
        smartContractCard = findViewById(R.id.smartContractCard);
        penaltyCard = findViewById(R.id.penaltyCard);

        // Group Creation Card Click
        createGroupCard.setOnClickListener(view -> {

            Toast.makeText(
                    HomeActivity2.this,
                    "Opening Group Management...",
                    Toast.LENGTH_SHORT
            ).show();

            Intent intent = new Intent(
                    HomeActivity2.this,
                    GroupManagementActivity.class
            );

            startActivity(intent);
        });

        // Smart Contract Card Click
        smartContractCard.setOnClickListener(view -> {

            Toast.makeText(
                    HomeActivity2.this,
                    "Opening Smart Contract...",
                    Toast.LENGTH_SHORT
            ).show();

            Intent intent = new Intent(
                    HomeActivity2.this,
                    SmartContractActivity.class
            );

            startActivity(intent);
        });

        // Penalty Card Click
        penaltyCard.setOnClickListener(view -> {

            Toast.makeText(
                    HomeActivity2.this,
                    "Opening Penalty System...",
                    Toast.LENGTH_SHORT
            ).show();

            Intent intent = new Intent(
                    HomeActivity2.this,
                    PenaltyActivity.class
            );

            startActivity(intent);
        });
    }
}