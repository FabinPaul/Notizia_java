package com.fabinpaul.notizia.feature.headlines.data;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "article", indices = {@Index(value = {"news_source", "title", "id"})}, foreignKeys = @ForeignKey(entity = NewsSource.class,
        parentColumns = "source_id", childColumns = "news_source", onDelete = ForeignKey.CASCADE))
public class ArticlesItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "published_at")
    @SerializedName("publishedAt")
    private String publishedAt;

    @ColumnInfo(name = "author")
    @SerializedName("author")
    private String author;

    @ColumnInfo(name = "url_to_image")
    @SerializedName("urlToImage")
    private String urlToImage;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;

    @Embedded
    @SerializedName("source")
    private NewsSource newsSource;

    @ColumnInfo(name = "news_source")
    private long newsSourceID;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    private String url;

    @ColumnInfo(name = "content")
    @SerializedName("content")
    private String content;

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setNewsSource(NewsSource newsSource) {
        this.newsSource = newsSource;
    }

    public NewsSource getNewsSource() {
        return newsSource;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNewsSourceID() {
        return newsSourceID;
    }

    public void setNewsSourceID(long newsSourceID) {
        this.newsSourceID = newsSourceID;
    }

    @Override
    public String toString() {
        return
                "ArticlesItem{" +
                        "publishedAt = '" + publishedAt + '\'' +
                        ",author = '" + author + '\'' +
                        ",urlToImage = '" + urlToImage + '\'' +
                        ",description = '" + description + '\'' +
                        ",newsSource = '" + newsSource + '\'' +
                        ",title = '" + title + '\'' +
                        ",url = '" + url + '\'' +
                        ",content = '" + content + '\'' +
                        "}";
    }
}