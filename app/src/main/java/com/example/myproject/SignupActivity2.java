package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.databinding.ActivitySignup2Binding;

public class SignupActivity2 extends AppCompatActivity {

    ActivitySignup2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        // BINDING
        binding = ActivitySignup2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // FULL SCREEN
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        // SIGNUP BUTTON CLICK
        binding.signupButton.setOnClickListener(view -> {

            String username = binding.nameEdit.getText().toString().trim();
            String email = binding.emailEdit.getText().toString().trim();
            String password = binding.passwordEdit.getText().toString().trim();
            String rePassword = binding.repasswordEdit.getText().toString().trim();

            // EMPTY FIELD CHECK
            if (username.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()
                    || rePassword.isEmpty()) {

                Toast.makeText(
                        SignupActivity2.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();
            }

            // PASSWORD MATCH CHECK
            else if (!password.equals(rePassword)) {

                Toast.makeText(
                        SignupActivity2.this,
                        "Passwords do not match ❌",
                        Toast.LENGTH_SHORT
                ).show();
            }

            else {

                // Database object
                Database db = new Database(getApplicationContext());

                // INSERT DATA
                db.register(username, email, password);

                // SUCCESS MESSAGE
                Toast.makeText(
                        SignupActivity2.this,
                        "Signup Successful ✅",
                        Toast.LENGTH_SHORT
                ).show();

                // CLEAR FIELDS
                binding.nameEdit.setText("");
                binding.emailEdit.setText("");
                binding.passwordEdit.setText("");
                binding.repasswordEdit.setText("");

                // GO TO LOGIN PAGE
                Intent intent = new Intent(
                        SignupActivity2.this,
                        LoginActivity2.class
                );

                startActivity(intent);

                finish();
            }
        });
    }
}