package com.example.qa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;

import com.example.qa.R;

/**
 * The activity that displays the onboarding screens to introduce the user to the app.
 *
 */
public class OnboardingActivity extends AppCompatActivity {

    /**
     * Displays the onboarding screens to introduce the user to the app.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("OnboardingActivity", "Activity entered");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splashscreen);

        // Delay the onboarding screens to show off the splash screen
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            showOnboarding();
        }, 2000);

    }

    /**
     * Shows the main activity.
     */
    private void showMain() {
        Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Shows the onboarding screens.
     */
    private void showOnboarding() {
        // Display the first onboarding screen
        setContentView(R.layout.activity_introduction_one);

        // Initialize buttons and views
        ImageView next = findViewById(R.id.next);
        TextView skip = findViewById(R.id.skip);

        // The next button displays the second onboarding screen
        next.setOnClickListener(v -> {
            // Display the second onboarding screen
            setContentView(R.layout.activity_introduction_two);

            // Initialize buttons and views
            ImageView next2 = findViewById(R.id.next2);
            TextView skip2 = findViewById(R.id.skip2);

            // The next button displays the third onboarding screen
            next2.setOnClickListener(v2 -> {
                // Display the third onboarding screen
                setContentView(R.layout.activity_introduction_three);

                // Initialize the button
                ImageView next3 = findViewById(R.id.next3);

                // The next button displays the main activity
                next3.setOnClickListener(v3 -> {
                    showMain();
                });
            });

            // The skip button displays the main activity
            skip2.setOnClickListener(v2 -> {
                showMain();
            });
        });

        // The skip button displays the main activity
        skip.setOnClickListener(v -> {
            showMain();
        });
    }

}
