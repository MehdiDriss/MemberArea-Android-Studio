package com.example.mymemberarea;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    TextView btn;
    
    private EditText inputUsername, inputPassword, inputEmail, inputConformPassword;
    Button btnRegister;

    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn = findViewById(R.id.alreadyHaveAccount);
        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConformPassword = findViewById(R.id.inputConformPassword);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(RegisterActivity.this);

        
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> checkCrededentials());

        btn.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }


    private void checkCrededentials() {
        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String conformPassword = inputConformPassword.getText().toString();

        if (username.isEmpty() || username.length() < 7) {
            showError(inputUsername, "Your username is not valid !");
        } else if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Email is not valid");
        } else if (password.isEmpty() || password.length() < 7) {
            showError(inputPassword, "Password must be 7 character");
        } else if (conformPassword.isEmpty() || !conformPassword.equals(password)) {
            showError(inputConformPassword, "Password not match");
        }
        else
        {
            mLoadingBar.setTitle("Registeration");
            mLoadingBar.setMessage("Please wait, while check your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this::onComplete);
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    private void onComplete(Task<AuthResult> task) {
        if (task.isSuccessful()) {
            Toast.makeText(RegisterActivity.this, "Successfully Registration", Toast.LENGTH_SHORT).show();

            mLoadingBar.dismiss();

            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(RegisterActivity.this, Objects.requireNonNull(task.getException()).toString(), Toast.LENGTH_SHORT).show();
        }
    }
}