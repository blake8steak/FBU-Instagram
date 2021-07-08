package com.codepath.fbu_instagram.ui.viewmodels;

import android.content.Context;

import com.codepath.fbu_instagram.ui.domain.SignOut;

public class ProfileViewModel {
    private final String TAG = "ProfileViewModel";
    private Context viewContext;
    private SignOut signOut;

    public ProfileViewModel(Context context) {
        this.viewContext = context;
        signOut = new SignOut(viewContext);
    }

    public void signOut() {
        signOut.executeUseCase();
    }


}
