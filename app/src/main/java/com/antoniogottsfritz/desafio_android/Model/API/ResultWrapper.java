package com.antoniogottsfritz.desafio_android.Model.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultWrapper<T> {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("copyright")
    @Expose
    private String copyright;

    @SerializedName("attributionText")
    @Expose
    private String attributionText;

    @SerializedName("attributionHTML")
    @Expose
    private String attributionHTML;

    @SerializedName("data")
    @Expose
    private ResultContainer<T> data;

    /*@SerializedName("etag")
    @Expose
    private String etag;*/

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public ResultContainer<T> getData() {
        return data;
    }

    public void setData(ResultContainer<T> data) {
        this.data = data;
    }

    /*public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }*/

}
