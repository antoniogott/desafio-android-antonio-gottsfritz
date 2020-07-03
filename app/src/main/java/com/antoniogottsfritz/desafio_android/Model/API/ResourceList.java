package com.antoniogottsfritz.desafio_android.Model.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResourceList {
    @SerializedName("available")
    @Expose
    private int Available;

    @SerializedName("collectionURI")
    @Expose
    private String CollectionUri;

    @SerializedName("returned")
    @Expose
    private int Returned;

    public int getAvailable() {
        return Available;
    }

    public void setAvailable(int available) {
        Available = available;
    }

    public String getCollectionUri() {
        return CollectionUri;
    }

    public void setCollectionUri(String collectionUri) {
        CollectionUri = collectionUri;
    }

    public int getReturned() {
        return Returned;
    }

    public void setReturned(int returned) {
        Returned = returned;
    }
}