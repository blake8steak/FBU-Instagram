package com.codepath.fbu_instagram.ui.domain;

import android.content.Context;
import android.content.Intent;

import com.codepath.fbu_instagram.UseCase;
import com.codepath.fbu_instagram.ui.LoginActivity;
import com.parse.ParseUser;

public class SignOut extends UseCase {
    private Context context;

    public SignOut(Context context) {
        this.context = context;
    }


    @Override
    public void executeUseCase() {
        logOutUser();
    }

    public void logOutUser() {
        ParseUser.logOut();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
    }

}
