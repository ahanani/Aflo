package com.example.aflo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        createButtons();
    }

    public void createButtons() {
        Button signupBtn = findViewById(R.id.signupSubmit);
        signupBtn.setOnClickListener(view -> {
            Intent signupAndRedirectToHome = new Intent(view.getContext(), MainMenu.class);
            startActivity(signupAndRedirectToHome);
        });
    }
}
