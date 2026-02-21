package com.versionpb.buzzer.ui;

import android.os.Bundle;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.versionpb.buzzer.R;

/**
 * Single-screen buzzer app.
 * Tap the BUZZ button — that's it!
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private Button btnBuzz;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        btnBuzz = findViewById(R.id.btnBuzz);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        btnBuzz.setOnClickListener(v -> onBuzzPressed());
    }

    private void onBuzzPressed() {
        // Haptic feedback
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(300,
                        VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                // noinspection deprecation
                vibrator.vibrate(300);
            }
        }
        tvStatus.setText(getString(R.string.buzzed));
        tvStatus.setVisibility(View.VISIBLE);
    }
}
