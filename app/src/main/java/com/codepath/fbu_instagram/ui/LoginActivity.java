package com.codepath.fbu_instagram.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.viewmodels.LoginViewModel;

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
        setContentView(R.layout.activity_login);
        viewModel = new LoginViewModel(this);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnNewUser = findViewById(R.id.btnNewUser);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginPress();
            }
        });

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewUserPress();
            }
        });
    }

    public void onLoginPress() {
        viewModel.onLoginButtonClick(username.getText().toString(), password.getText().toString());
    }

    public static void onNewUserPress() {
        //insert viewmodel ref here
    }
}