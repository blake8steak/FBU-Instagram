package com.codepath.fbu_instagram.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.viewmodels.SignupViewModel;

public class SignupActivity extends AppCompatActivity {
    private final String TAG = "SignupActivity";
    private SignupViewModel signupViewModel;
    private EditText etName;
    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnCreateAccount;
    private Button btnBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupViewModel = new SignupViewModel(this);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnBackToLogin = findViewById(R.id.btnBackToLogin);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupViewModel.createUser(etPassword, etConfirmPassword, etName.getText().toString(),
                        etEmail.getText().toString(), etUsername.getText().toString());
            }
        });

        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupViewModel.backToLogin();
            }
        });

    }
}