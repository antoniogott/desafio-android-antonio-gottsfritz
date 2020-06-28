package com.antoniogottsfritz.desafio_android.Model;

public class MarvelImage {
    private String Path;
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

    public String getUrl() {
        return Path + "/standard_medium." + Extension;
    }

    public String getUrl(String variant) {
        return Path + "/" + variant + "." + Extension;
    }
}
