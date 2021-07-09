package com.codepath.fbu_instagram.models;

import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcel;

import java.io.File;

@Parcel
public class PostParcel {
    public static String description;
    public static ParseFile image;
    public static ParseUser user;

    public PostParcel() {}

    public PostParcel(String description, ParseFile image, ParseUser user) {
        this.description = description;
        this.image = image;
        this.user = user;
    }

}
