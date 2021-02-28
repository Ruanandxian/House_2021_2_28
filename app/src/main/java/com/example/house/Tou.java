package com.example.house;

import android.app.Application;
import android.net.Uri;

public class Tou extends Application {
    private Uri uri = null;
    private String path = null;

    @Override
    public String toString() {
        return "Uri{" +
                "uri='" + uri + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
