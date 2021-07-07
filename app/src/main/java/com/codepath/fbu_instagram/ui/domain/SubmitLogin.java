package com.codepath.fbu_instagram.ui.domain;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;

import com.codepath.fbu_instagram.UseCase;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SubmitLogin extends UseCase {
    private final String TAG = "SubmitLogin";
    private String username;
    private String password;
    private Context context;

    /*
        Inside the SubmitLogin use case,
        I perform the actual communication
        with the model (Parse API.)
     */

    public SubmitLogin(Context context, String username, String password) {
        this.username = username;
        this.password = password;
        this.context = context;
    }

    @Override
    public void executeUseCase() {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Error with login: "+e.getCode()+", "+e.getMessage());
                } else {
                    Log.i(TAG, "Success! Username: "+user.getUsername()+", ID "+user.getObjectId());
                }
            }
        });
    }
}
