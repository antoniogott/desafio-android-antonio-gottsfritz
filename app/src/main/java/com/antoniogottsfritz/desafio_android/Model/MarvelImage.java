package com.antoniogottsfritz.desafio_android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class MarvelImage {
    @SerializedName("path")
    @Expose
    private String Path;

    @SerializedName("extension")
    @Expose
    private String Extension;

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getExtension() {
        return Extension;
    }

    public void setExtension(String extension) {
        Extension = extension;
    }

    public String getUrl(String variant) {
        String fullPath = Path;
        if (!Objects.equals(variant, "original")) {
            fullPath += "/" + variant ;
        }
        return fullPath + "." + Extension;
    }
}
