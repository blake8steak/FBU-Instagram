package com.codepath.fbu_instagram.ui.domain;

import android.content.Context;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateUser {
    private final String TAG = "CreateUser";
    private Context context;
    private String name;
    private String username;
    private String password;

    public CreateUser(Context context, String name, String username, String password) {
        this.context = context;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public void createUser() {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        // Set custom properties
        user.put("name", name);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }

}
