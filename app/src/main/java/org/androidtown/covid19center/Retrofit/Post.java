package org.androidtown.covid19center.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("x")
    private String x;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

}
