package com.s23010421.vpnhansaka;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private TextView tvTemperatureValue, tvThresholdValue;
    private Button btnSimulate;
    private MediaPlayer mediaPlayer;

    // Use the last two digits of your student ID as threshold
    private static final float TEMPERATURE_THRESHOLD = 21.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        // Initialize UI components
        tvTemperatureValue = findViewById(R.id.tvTemperatureValue);
        tvThresholdValue = findViewById(R.id.tvThresholdValue);
        btnSimulate = findViewById(R.id.btnSimulate);

        // Set the threshold value text
        tvThresholdValue.setText(TEMPERATURE_THRESHOLD + "°C");

        // Initialize sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Check if temperature sensor is available
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        } else {
            // If no temperature sensor, show message
            tvTemperatureValue.setText("Temperature sensor not available");
        }

        // Set up audio for temperature threshold alert
        mediaPlayer = MediaPlayer.create(this, R.raw.alert_sound);

        // Set up simulation button for testing
        btnSimulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simulateTemperatureChange();
            }
        });
    }

    private void simulateTemperatureChange() {
        // Simulate a temperature reading above threshold for testing
        float simulatedTemperature = TEMPERATURE_THRESHOLD + 5.0f;
        tvTemperatureValue.setText(simulatedTemperature + "°C");

        // Play alert sound when temperature exceeds threshold
        if (simulatedTemperature > TEMPERATURE_THRESHOLD) {
            playAlertSound();
        }
    }

    private void playAlertSound() {
        if (mediaPlayer != null) {
            // Stop if already playing
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.prepareAsync();
            }

            // Play the alert sound
            mediaPlayer.start();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float temperature = event.values[0];
            tvTemperatureValue.setText(temperature + "°C");

            // Check if temperature exceeds threshold
            if (temperature > TEMPERATURE_THRESHOLD) {
                playAlertSound();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed for this example
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the sensor listener when the activity resumes
        if (temperatureSensor != null) {
            sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listener when the activity is paused
        sensorManager.unregisterListener(this);

        // Pause media player if it's playing
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release media player resources
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}