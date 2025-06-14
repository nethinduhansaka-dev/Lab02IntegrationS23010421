package com.s23010421.vpnhansaka;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private EditText etStudentID, etUsername, etPassword;
    private Button btnLogin;

    // Database Helper instance
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database helper - FIX: Use the same variable name
        databaseHelper = new DatabaseHelper(this);

        // Initialize UI components
        etStudentID = findViewById(R.id.etStudentID);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);


        // Set up login button click listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataAndNavigate();
            }
        });
    }

    private void saveDataAndNavigate() {
        String studentId = etStudentID.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate input
        if (studentId.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save data to database - Now this will work correctly
        boolean isDataSaved = databaseHelper.insertData(studentId, username, password);

        // Display Toast message to confirm status
        if (isDataSaved) {
            Toast.makeText(this, "Data saved to database successfully!", Toast.LENGTH_LONG).show();

            // Navigate to Maps Activity after successful data save
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed to save data to database", Toast.LENGTH_SHORT).show();
        }
    }
}





