package com.codepath.fbu_instagram.ui.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.codepath.fbu_instagram.ui.SignupActivity;
import com.codepath.fbu_instagram.ui.domain.SubmitLogin;

public class LoginViewModel {
    private final String TAG = "LoginViewModel";
    private Context viewContext;

    /*
        Inside the ViewModel, I create instances
        of use cases and pass over any necessary data
        retrieved from the LoginActivity.
     */

    public LoginViewModel(Context context) {
        this.viewContext = context;
    }

    public void onLoginButtonClick(String username, String password) {
        SubmitLogin submitLoginUseCase = new SubmitLogin(viewContext, username, password);
        submitLoginUseCase.executeUseCase();
    }

    public void onNewUserButtonClick() {
        Intent i = new Intent(viewContext, SignupActivity.class);
        viewContext.startActivity(i);
    }
}
