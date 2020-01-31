package com.example.td3fragmentsgiraldo;

public class ImageFlickr {
    private String title;
    private String author;
    private String id;
    private String secret;
    private String server;
    private String url;
    private String farm;

    public ImageFlickr(String title,String author, String id, String secret, String server, String url, String farm){
        this.title= title;
        this.author=author;
        this.id=id;
        this.secret=secret;
        this.server=server;
        this.url=url;
        this.farm=farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }
}
