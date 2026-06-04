package com.example.myproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.databinding.ActivityLogin2Binding;

public class LoginActivity2 extends AppCompatActivity {

    ActivityLogin2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        binding.button.setOnClickListener(view -> {

            // Get user input
            String email = binding.editTextText2
                    .getText()
                    .toString()
                    .trim();

            String password = binding.PasswordEdit
                    .getText()
                    .toString()
                    .trim();

            // Database object
            Database db = new Database(getApplicationContext());

            // Check empty fields
            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                        LoginActivity2.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                // Login check
                if (db.login(email, password) == 1) {

                    Toast.makeText(
                            LoginActivity2.this,
                            "Login Successful ✅",
                            Toast.LENGTH_SHORT
                    ).show();

                    // Save email using SharedPreferences
                    SharedPreferences sharedPreferences =
                            getSharedPreferences(
                                    "shared_prefs",
                                    Context.MODE_PRIVATE
                            );

                    SharedPreferences.Editor editor =
                            sharedPreferences.edit();

                    editor.putString("email", email);
                    editor.apply();

                    // Open Home Activity
                    Intent intent = new Intent(
                            LoginActivity2.this,
                            HomeActivity2.class
                    );

                    startActivity(intent);

                    finish();

                } else {

                    Toast.makeText(
                            LoginActivity2.this,
                            "Invalid email or password ❌",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}