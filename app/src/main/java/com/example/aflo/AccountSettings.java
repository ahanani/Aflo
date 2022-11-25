package com.example.aflo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AccountSettings extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        mAuth = FirebaseAuth.getInstance();
    }

    public void home(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void logout(View view) {
        mAuth.signOut();
        Intent leaveApp = new Intent(this, Landing.class);
        startActivity(leaveApp);
    }
}
