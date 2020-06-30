package com.antoniogottsfritz.desafio_android.Model;

public class Character {
    private int Id;
    private String Name;
    private String Description;
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

    public String getThumbnailUrl(String variant) {
        return Thumbnail.getUrl(variant);
    }

    public void setThumbnail(MarvelImage thumbnail) {
        Thumbnail = thumbnail;
    }
}
