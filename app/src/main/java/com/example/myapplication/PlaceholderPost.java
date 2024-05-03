package com.example.myapplication;

public class PlaceholderPost {
    private int userId;
    private int id;
    private String title;
    private String body;
    public int getUserId() {
        return userId;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
    public void setPostId(int userId) {
        this.userId = userId;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String Title) {
        this.title = Title;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
