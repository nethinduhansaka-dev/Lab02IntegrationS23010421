package com.s23010421.vpnhansaka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnGetStarted;
    private TextView tvWelcome, tvsno, tvFooter;
    private ImageView ivAppLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        initializeViews();

        // Set up Get Started button click listener
        setupButtonListener();
    }

    private void initializeViews() {
        btnGetStarted = findViewById(R.id.btnGetStarted);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvsno = findViewById(R.id.tvsno);
        tvFooter = findViewById(R.id.tvFooter);
        ivAppLogo = findViewById(R.id.ivAppLogo);
    }

    private void setupButtonListener() {
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Login Activity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}