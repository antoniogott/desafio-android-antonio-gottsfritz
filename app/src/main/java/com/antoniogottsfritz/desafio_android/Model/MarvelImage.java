package com.antoniogottsfritz.desafio_android.Model;

import java.util.Objects;

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

    public String getUrl(String variant) {
        String fullPath = Path;
        if (!Objects.equals(variant, "original")) {
            fullPath += "/" + variant ;
        }
        return fullPath + "." + Extension;
    }
}
