package com.pirates.choi.hiswindtester.diary;

public class DiaryAdapterItem {

    private String title;
    private String diary;
    private String date;
    private String picture;
    private int background;

    public DiaryAdapterItem(String title, String diary, String date, String picture, int background) {

        super();

        this.title = title;
        this.diary = diary;
        this.date = date;
        this.picture = picture;
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