package com.antoniogottsfritz.desafio_android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comic {
    @SerializedName("id")
    @Expose
    private int Id;

    @SerializedName("title")
    @Expose
    private String Title;

    @SerializedName("issueNumber")
    @Expose
    private float IssueNumber;

    @SerializedName("description")
    @Expose
    private String Description;

    @SerializedName("prices")
    @Expose
    private List<ComicPrice> Prices;

    @SerializedName("thumbnail")
    @Expose
    private MarvelImage Thumbnail;

    private float HighestPrice;

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

    public float getIssueNumber() {
        return IssueNumber;
    }

    public void setIssueNumber(float issueNumber) {
        IssueNumber = issueNumber;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<ComicPrice> getPrices() {
        return Prices;
    }

    public void setPrices(List<ComicPrice> prices) {
        Prices = prices;
    }

    public MarvelImage getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(MarvelImage thumbnail) {
        Thumbnail = thumbnail;
    }

    public float getHighestPrice() {
        return HighestPrice;
    }

    public void setHighestPrice(float highestPrice) {
        HighestPrice = highestPrice;
    }

    public static class ComicPrice {
        @SerializedName("type")
        @Expose
        private String Type;

        @SerializedName("price")
        @Expose
        private Float Price;

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public Float getPrice() {
            return Price;
        }

        public void setPrice(Float price) {
            Price = price;
        }
    }
}
