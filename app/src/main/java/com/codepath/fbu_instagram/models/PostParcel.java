package com.codepath.fbu_instagram.models;

import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcel;

import java.io.File;
import java.util.Date;

@Parcel
public class PostParcel {
    public String description;
    public ParseFile image;
    public ParseUser user;
    public Date postDate;

    public PostParcel() {}

    public PostParcel(String description, ParseFile image, ParseUser user, Date postDate) {
        this.description = description;
        this.image = image;
        this.user = user;
        this.postDate = postDate;
    }

}
