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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        createButtons();
    }

    public void createButtons() {
        Button signupBtn = findViewById(R.id.signupSubmit);
        signupBtn.setOnClickListener(view -> sendSignupForm());
    }

    public void sendSignupForm() {
        EditText email = findViewById(R.id.signupEmail);
        EditText password = findViewById(R.id.signupPassword);
        EditText confPassword = findViewById(R.id.signupConfirmPassword);
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String confPasswordString = confPassword.getText().toString();

        if (!validate(emailString, passwordString, confPasswordString)) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailString, passwordString)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("auth", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();

                    Intent loginAndRedirectToHome = new Intent(this, MainMenu.class);
                    startActivity(loginAndRedirectToHome);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("auth", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Signup.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            });

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
