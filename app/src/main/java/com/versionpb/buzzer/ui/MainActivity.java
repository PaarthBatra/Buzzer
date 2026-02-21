package com.versionpb.buzzer.ui;

import android.animation.ValueAnimator;
import android.animation.ArgbEvaluator;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
 * Focused on vibration feedback and high-precision debug timing.
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private TextView tvTimer;
    private Button btnBuzz;
    private Vibrator vibrator;

    private android.media.MediaPlayer mediaPlayer;
    private boolean isPrepared = false;

    // Animation colors
    private static final int COLOR_RED = 0xFFE53935;
    private static final int COLOR_ORANGE = 0xFFFF9800;
    private ValueAnimator colorAnimator;

    // Timer management for debugging hold duration
    private long startTime;
    private final android.os.Handler timerHandler = new android.os.Handler(android.os.Looper.getMainLooper());
    private final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            double seconds = millis / 1000.0;
            tvTimer.setText(String.format(java.util.Locale.US, "%.2fs", seconds));
            timerHandler.postDelayed(this, 10); // Update every 10ms for smoothness
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        tvTimer = findViewById(R.id.tvTimer);
        btnBuzz = findViewById(R.id.btnBuzz);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        // Pre-prepare sound for instant playback
        initMediaPlayer();

        // Use touch listener for continuous response while holding
        btnBuzz.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case android.view.MotionEvent.ACTION_DOWN:
                    startBuzzing();
                    return true;
                case android.view.MotionEvent.ACTION_UP:
                case android.view.MotionEvent.ACTION_CANCEL:
                    stopBuzzing();
                    return true;
            }
            return false;
        });
    }

    private void initMediaPlayer() {
        int resId = getResources().getIdentifier("buzzer_sound", "raw", getPackageName());
        if (resId == 0)
            return;

        try {
            mediaPlayer = new android.media.MediaPlayer();
            android.content.res.AssetFileDescriptor afd = getResources().openRawResourceFd(resId);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();

            mediaPlayer.setLooping(true); // Loops naturally every 3s
            mediaPlayer.setAudioAttributes(new android.media.AudioAttributes.Builder()
                    .setUsage(android.media.AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(android.media.AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build());

            mediaPlayer.prepare();
            isPrepared = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startBuzzing() {
        // 1. Start Sound
        if (isPrepared && mediaPlayer != null) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }

        // 2. Start Debug Timer
        startTime = System.currentTimeMillis();
        timerHandler.removeCallbacks(timerRunnable);
        timerHandler.post(timerRunnable);
        tvTimer.setVisibility(View.VISIBLE);

        // 3. Start Continuous Vibration
        if (vibrator != null && vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createWaveform(new long[] { 0, 1000 }, 0));
            } else {
                // noinspection deprecation
                vibrator.vibrate(new long[] { 0, 1000 }, 0);
            }
        }

        // 4. Color Animation (Red -> Orange)
        animateButtonColor(COLOR_RED, COLOR_ORANGE);

        tvStatus.setText(getString(R.string.buzzed));
        tvStatus.setVisibility(View.VISIBLE);
    }

    private void stopBuzzing() {
        // 1. Stop Sound
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }

        // 2. Stop Debug Timer
        timerHandler.removeCallbacks(timerRunnable);

        // 3. Stop Vibration
        if (vibrator != null) {
            vibrator.cancel();
        }

        // 4. Color Animation (Orange -> Red)
        animateButtonColor(COLOR_ORANGE, COLOR_RED);

        tvStatus.setVisibility(View.INVISIBLE);
        // Note: Timer remains visible with the final hold time for debug reference
    }

    private void animateButtonColor(int fromColor, int toColor) {
        if (colorAnimator != null && colorAnimator.isRunning()) {
            colorAnimator.cancel();
        }

        colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
        colorAnimator.setDuration(150); // Fast but smooth
        colorAnimator.addUpdateListener(animator -> {
            int animatedColor = (int) animator.getAnimatedValue();
            btnBuzz.setBackgroundTintList(ColorStateList.valueOf(animatedColor));
        });
        colorAnimator.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopBuzzing();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerHandler.removeCallbacks(timerRunnable);
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
