package com.antoniogottsfritz.desafio_android.Model;

import java.util.ArrayList;

public class Comic {
    private int Id;
    private String Title;
    private int IssueNumber;
    private String Description;
    private ArrayList<ComicPrice> Prices;
    private MarvelImage Thumbnail;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getIssueNumber() {
        return IssueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        IssueNumber = issueNumber;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public ArrayList<ComicPrice> getPrices() {
        return Prices;
    }

    public void setPrices(ArrayList<ComicPrice> prices) {
        Prices = prices;
    }

    public MarvelImage getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(MarvelImage thumbnail) {
        Thumbnail = thumbnail;
    }
}
