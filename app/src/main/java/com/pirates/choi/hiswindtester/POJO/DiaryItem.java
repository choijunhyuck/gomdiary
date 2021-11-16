package com.pirates.choi.hiswindtester.POJO;

public class DiaryItem {

    private String title;
    private String diary;
    private String date;
    private String picture;
    private int background;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDiary(String diary) {
        this.diary = diary;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public String getTitle() {
        return title;
    }

    public String getDiary() {
        return diary;
    }

    public String getDate() {
        return date;
    }

    public String getPicture() {
        return picture;
    }

    public int getBackground() {
        return background;
    }
}