package com.antoniogottsfritz.desafio_android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MarvelCharacter implements Serializable {
    @SerializedName("id")
    @Expose
    private int Id;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("description")
    @Expose
    private String Description;

    @SerializedName("thumbnail")
    @Expose
    private MarvelImage Thumbnail;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public MarvelImage getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(MarvelImage thumbnail) {
        Thumbnail = thumbnail;
    }
}
