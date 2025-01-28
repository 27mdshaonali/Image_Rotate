package com.example.imagerotate;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView rotatingImage;
    Button pauseButton, playButton;
    ObjectAnimator rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rotatingImage = findViewById(R.id.rotatingImage);
        pauseButton = findViewById(R.id.pauseButton);
        playButton = findViewById(R.id.playButton);

        // Creating rotation animation
        rotate = ObjectAnimator.ofFloat(rotatingImage, "rotation", 0f, 360f);
        rotate.setDuration(2000); // Rotates once every 2 seconds
        rotate.setRepeatCount(ObjectAnimator.INFINITE); // Infinite loop
        rotate.setInterpolator(new LinearInterpolator()); // Smooth and constant speed

        // Initially set up to start the animation
        rotate.start();

        // Set up play button
        playButton.setOnClickListener(v -> {
            if (!rotate.isStarted() || rotate.isPaused()) {
                rotate.resume(); // Resume the animation without resetting the position
            }
        });

        // Set up pause button
        pauseButton.setOnClickListener(v -> {
            if (rotate.isRunning()) {
                rotate.pause(); // Pause the animation
            }
        });
    }
}
