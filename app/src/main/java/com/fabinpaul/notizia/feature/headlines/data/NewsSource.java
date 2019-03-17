package com.fabinpaul.notizia.feature.headlines.data;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "source")
public class NewsSource {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "source_id")
    private int sourceID;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @Ignore
    @SerializedName("id")
    private String id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSourceID() {
        return sourceID;
    }

    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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