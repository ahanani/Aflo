package com.example.aflo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            sendSignupForm();
        });
    }

    public void sendSignupForm() {
        EditText email = findViewById(R.id.signupEmail);
        EditText password = findViewById(R.id.signupPassword);
        EditText confPassword = findViewById(R.id.signupConfirmPassword);
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String confPasswordString = confPassword.getText().toString();

        if (validate(emailString, passwordString, confPasswordString)) {
            Intent loginAndRedirectToHome = new Intent(this, MainMenu.class);
            startActivity(loginAndRedirectToHome);
        }

    }

    public boolean validate(String email, String password, String confPassword) {
        if (email.isEmpty() || password.isEmpty() || confPassword.isEmpty()) {
            Toast msg = Toast.makeText(this, "Fields cannot be empty",
                    Toast.LENGTH_LONG);
            msg.show();
            return false;
        } else if (!password.equals(confPassword)) {
            Toast msg = Toast.makeText(this, "Confirm password must match password",
                    Toast.LENGTH_LONG);
            msg.show();
            return false;
        }

        return true;
    }
}
