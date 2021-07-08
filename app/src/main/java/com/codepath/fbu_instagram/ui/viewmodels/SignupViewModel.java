package com.codepath.fbu_instagram.ui.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.fbu_instagram.ui.LoginActivity;
import com.codepath.fbu_instagram.ui.MainActivity;
import com.codepath.fbu_instagram.ui.domain.CreateUser;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupViewModel {
    private final String TAG = "SignupViewModel";
    private Context viewContext;
    private CreateUser createUser;

    public SignupViewModel(Context context) {
        this.viewContext = context;
        createUser = new CreateUser(context);
    }

    public void backToLogin() {
        Intent i = new Intent(viewContext, LoginActivity.class);
        viewContext.startActivity(i);
    }

    public void goToMain() {
        Intent i = new Intent(viewContext, MainActivity.class);
        viewContext.startActivity(i);
    }


    public void createUser(EditText password, EditText confirmPassword, String name, String email, String username) {
        if(!password.getText().toString().equals(confirmPassword.getText().toString())) {
            // passwords do not match
            password.setText("");
            confirmPassword.setText("");
            Toast.makeText(viewContext, "Passwords did not match.", Toast.LENGTH_SHORT);
        } else {
            createUser.setNewUserData(name, email, username, password.getText().toString());
            createUser.executeUseCase();
            //goToMain();
            Toast.makeText(viewContext, "User "+username+" created successfully.", Toast.LENGTH_SHORT);
        }
    }
}
