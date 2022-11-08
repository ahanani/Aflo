package com.example.aflo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createButtons();
    }

    public void createButtons() {
        Button loginBtn = findViewById(R.id.loginSubmit);
        loginBtn.setOnClickListener(view -> {
            sendLoginForm();
        });
    }

    public void sendLoginForm() {
        EditText email = findViewById(R.id.loginEmail);
        EditText password = findViewById(R.id.loginPassword);
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

        if (validate(emailString, passwordString)) {
            Intent loginAndRedirectToHome = new Intent(this, MainMenu.class);
            startActivity(loginAndRedirectToHome);
        }

    }

    public boolean validate(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast msg = Toast.makeText(this, "Email and password cannot be empty",
                    Toast.LENGTH_LONG);
            msg.show();
            return false;
        }

        return true;
    }
}
