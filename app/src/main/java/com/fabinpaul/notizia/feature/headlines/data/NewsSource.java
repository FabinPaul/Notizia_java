package com.fabinpaul.notizia.feature.headlines.data;

import com.google.gson.annotations.SerializedName;

public class NewsSource {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private Object id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "NewsSource{" +
                        "name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}