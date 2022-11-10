package com.example.aflo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        createButtons();
    }

    public void createButtons() {
        Button loginBtn = findViewById(R.id.loginSubmit);
        loginBtn.setOnClickListener(view -> {
            sendLoginForm();
        });

        Button forgotPasswordBtn = findViewById(R.id.forgotPassword);
        forgotPasswordBtn.setOnClickListener(view -> {
            sendResetPasswordEmail();
        });
    }

    public void sendLoginForm() {
        EditText email = findViewById(R.id.loginEmail);
        EditText password = findViewById(R.id.loginPassword);
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

        if (!validate(emailString, passwordString)) {
            return;
        }

        mAuth.signInWithEmailAndPassword(emailString, passwordString)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("auth", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();

                    Intent loginAndRedirectToHome = new Intent(
                            Login.this, MainMenu.class);
                    startActivity(loginAndRedirectToHome);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("auth", "signInWithEmail:failure", task.getException());
                    Toast.makeText(Login.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            });
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

    public void sendResetPasswordEmail() {
        EditText email = findViewById(R.id.loginEmail);
        String emailString = email.getText().toString().trim();

        mAuth.sendPasswordResetEmail(emailString)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("auth", "Email sent.");
                    }
                });
    }
}
