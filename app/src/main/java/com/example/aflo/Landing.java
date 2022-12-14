package com.example.aflo;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Landing extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        mAuth = FirebaseAuth.getInstance();

        createButtons();

        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX - width);
            }
        });
        animator.start();

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent surpassLogin = new Intent(Landing.this, MainMenu.class);
            startActivity(surpassLogin);

            Log.d("auth", "Already Signed in -- " + currentUser.getEmail());
        }
    }

    public void createButtons() {
        Button loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(view -> {
            Intent goToLogin = new Intent(view.getContext(), Login.class);
            startActivity(goToLogin);
        });

        Button signupBtn = findViewById(R.id.signup);
        signupBtn.setOnClickListener(view -> {
            Intent goToSignup = new Intent(view.getContext(), Signup.class);
            startActivity(goToSignup);
        });

        Button guestBtn = findViewById(R.id.guest);
        guestBtn.setOnClickListener(view -> mAuth.signInAnonymously()
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("auth", "signInAnonymously:success");
                    Intent guestLogin = new Intent(Landing.this,
                            MainMenu.class);
                    startActivity(guestLogin);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("auth", "signInAnonymously:failure",
                            task.getException());
                    Toast.makeText(Landing.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }));
    }
}
