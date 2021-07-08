package com.codepath.fbu_instagram.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.viewmodels.LoginViewModel;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel viewModel;
    private EditText username;
    private EditText password;
    private Button btnLogin;
    private Button btnNewUser;

    /*
        Inside the activity, I handle UI events and
        pass any necessary data over to the LoginViewModel
        so additional logic can be executed.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ParseUser.getCurrentUser() != null) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            setContentView(R.layout.activity_login);
            viewModel = new LoginViewModel(this);
            username = findViewById(R.id.etName);
            password = findViewById(R.id.etConfirmPassword);
            btnLogin = findViewById(R.id.btnCreateAccount);
            btnNewUser = findViewById(R.id.btnBackToLogin);

            btnLogin.setOnClickListener(v -> onLoginPress());

            btnNewUser.setOnClickListener(v -> onNewUserPress());
        }
    }

    public void onLoginPress() {
        viewModel.onLoginButtonClick(username.getText().toString(), password.getText().toString());
    }

    public void onNewUserPress() {
        viewModel.onNewUserButtonClick();
    }
}