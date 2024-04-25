package com.example.quizingapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    public TextInputLayout textInputLayoutEmail;
    public TextInputLayout textInputLayoutPassword;
    public TextInputEditText editTextEmail;
    public TextInputEditText editTextPassword;
    public Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v -> loginUser());
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        // Password must be at least 8 characters, contain a lowercase letter, an uppercase letter, and a digit
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        return password.matches(passwordPattern);
    }

    public void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            textInputLayoutEmail.setError("Email is required");
            return;
        } else if (!isValidEmail(email)) {
            textInputLayoutEmail.setError("Invalid email format");
            return;
        } else {
            textInputLayoutEmail.setError(null);
        }

        if (password.isEmpty()) {
            textInputLayoutPassword.setError("Password is required");
            return;
        } else if (!isValidPassword(password)) {
            textInputLayoutPassword.setError("Invalid password format");
            return;
        } else {
            textInputLayoutPassword.setError(null);
        }

        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}