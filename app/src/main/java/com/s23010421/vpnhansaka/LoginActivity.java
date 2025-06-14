package com.s23010421.vpnhansaka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etStudentID, etUsername, etPassword;
    private Button btnLogin, btnBack;
    private TextView tvLoginTitle;

    // Database Helper instance (if you want to connect to database)
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Initialize UI components
        initializeViews();

        // Set up button listeners
        setupButtonListeners();
    }

    private void initializeViews() {
        etStudentID = findViewById(R.id.etStudentID);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnBack = findViewById(R.id.btnBack);
        tvLoginTitle = findViewById(R.id.tvLoginTitle);
    }

    private void setupButtonListeners() {
        // Login Button Click Listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        // Back Button Click Listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to Get Started screen
                finish();
            }
        });
    }

    private void performLogin() {
        String studentId = etStudentID.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate input fields
        if (studentId.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save data to database
        boolean isDataSaved = databaseHelper.insertData(studentId, username, password);

        if (isDataSaved) {
            Toast.makeText(this, "Data saved to database successfully!", Toast.LENGTH_LONG).show();

            // Navigate to Maps Activity (or next screen)
            Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("studentId", studentId);
            startActivity(intent);

            // Optional: Close this activity
            finish();
        } else {
            Toast.makeText(this, "Failed to save data to database", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etStudentID.setText("");
        etUsername.setText("");
        etPassword.setText("");
    }
}
