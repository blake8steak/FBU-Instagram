package com.codepath.fbu_instagram.ui.domain;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.codepath.fbu_instagram.UseCase;
import com.codepath.fbu_instagram.ui.LoginActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateUser extends UseCase {
    private final String TAG = "CreateUser";
    private Context context;

    private String username;
    private String password;
    private String email;
    private String name;

    public CreateUser(Context context) {
        this.context = context;
    }

    public void setNewUserData(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public void executeUseCase() {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        // Set custom properties
        user.put("name", name);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(context, "User "+username+" created successfully.", Toast.LENGTH_SHORT);
                    Intent i = new Intent(context, LoginActivity.class);
                    context.startActivity(i);
                } else {
                    Log.e(TAG, "Failed to register new user. "+e.getCode()+", "+e.getMessage());
                }
            }
        });
    }
}
