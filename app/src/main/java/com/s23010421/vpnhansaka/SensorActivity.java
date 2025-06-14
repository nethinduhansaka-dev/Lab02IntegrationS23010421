package com.s23010421.vpnhansaka;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "TemperatureSensor";

    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private TextView tvTemperatureValue, tvThresholdValue, tvTemperatureStatus, tvSensorInfo;
    private Button btnSimulate;
    private MediaPlayer mediaPlayer;

    // Use the last two digits of your student ID as threshold (S23010421 = 21)
    private static final float TEMPERATURE_THRESHOLD = 21.0f;
    private boolean isAudioPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        initializeViews();
        initializeSensor();
        initializeMediaPlayer();
        setupSimulationButton();
    }

    private void initializeViews() {
        tvTemperatureValue = findViewById(R.id.tvTemperatureValue);
        tvThresholdValue = findViewById(R.id.tvThresholdValue);
        tvTemperatureStatus = findViewById(R.id.tvTemperatureStatus);
        tvSensorInfo = findViewById(R.id.tvSensorInfo);
        btnSimulate = findViewById(R.id.btnSimulate);

        tvThresholdValue.setText(TEMPERATURE_THRESHOLD + "°C");
    }

    private void initializeSensor() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if (temperatureSensor != null) {
            tvSensorInfo.setText("Hardware temperature sensor detected");
            tvTemperatureStatus.setText("Sensor Active");
            tvTemperatureStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            Log.d(TAG, "Temperature sensor found: " + temperatureSensor.getName());
        } else {
            tvSensorInfo.setText("No temperature sensor available - Use simulation");
            tvTemperatureStatus.setText("Simulation Mode");
            tvTemperatureStatus.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
            Log.d(TAG, "No temperature sensor available");
        }
    }

    private void initializeMediaPlayer() {
        try {
            mediaPlayer = MediaPlayer.create(this, android.provider.Settings.System.DEFAULT_NOTIFICATION_URI);
            if (mediaPlayer == null) {
                Log.e(TAG, "Failed to initialize MediaPlayer");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error initializing MediaPlayer: " + e.getMessage());
        }
    }

    private void setupSimulationButton() {
        btnSimulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simulateTemperature();
            }
        });
    }

    private void simulateTemperature() {
        // Generate random temperature values for testing
        float[] testTemperatures = {15.0f, 18.0f, 20.0f, 22.0f, 25.0f, 28.0f, 30.0f};
        int randomIndex = (int) (Math.random() * testTemperatures.length);
        float simulatedTemp = testTemperatures[randomIndex];

        updateTemperatureDisplay(simulatedTemp);
        Toast.makeText(this, "Simulated: " + simulatedTemp + "°C", Toast.LENGTH_SHORT).show();
    }

    private void updateTemperatureDisplay(float temperature) {
        tvTemperatureValue.setText(String.format("%.1f°C", temperature));

        if (temperature > TEMPERATURE_THRESHOLD) {
            tvTemperatureStatus.setText("ALERT: Above Threshold");
            tvTemperatureStatus.setTextColor(getResources().getColor(android.R.color.holo_red_dark));

            if (!isAudioPlaying) {
                playAlertSound();
            }
        } else {
            String status = temperatureSensor != null ? "Sensor Active" : "Simulation Mode";
            tvTemperatureStatus.setText(status + " - Normal");
            tvTemperatureStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            isAudioPlaying = false;
        }
    }

    private void playAlertSound() {
        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.prepare();
                }

                mediaPlayer.start();
                isAudioPlaying = true;
                Toast.makeText(this, "Temperature Alert - Playing audio", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Log.e(TAG, "Error playing alert sound: " + e.getMessage());
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float temperature = event.values[0];
            updateTemperatureDisplay(temperature);
            Log.d(TAG, "Hardware sensor reading: " + temperature + "°C");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle accuracy changes if needed
        Log.d(TAG, "Sensor accuracy changed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (temperatureSensor != null) {
            sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "Temperature sensor listener registered");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isAudioPlaying = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
