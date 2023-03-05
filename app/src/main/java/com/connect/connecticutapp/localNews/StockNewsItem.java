package com.connect.connecticutapp.localNews;

public class StockNewsItem {

    private String imgUrl;
    private String title;
    private String detailUrl;
    private String Content;
    private String author;
    public StockNewsItem() {
    }

    public StockNewsItem(String imgUrl, String title, String detailUrl, String content,String Author) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.detailUrl = detailUrl;
        this.Content = content;
        this.author=Author;

    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}

