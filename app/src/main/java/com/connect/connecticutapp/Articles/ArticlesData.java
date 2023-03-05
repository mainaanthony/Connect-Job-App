package com.connect.connecticutapp.Articles;

public class ArticlesData {




    private String articleTitle;
    private String articleDescription;
    private String articleContent;
    private String writerEmail;
    private String writerName;
    private String writerContact;
    private String writerDesc;
    private String writerWebsite;
    private String timestamp;


    public ArticlesData() {
    }

    public String getWriterEmail() {
        return writerEmail;
    }

    public void setWriterEmail(String writerEmail) {
        this.writerEmail = writerEmail;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getWriterContact() {
        return writerContact;
    }

    public void setWriterContact(String writerContact) {
        this.writerContact = writerContact;
    }

    public String getWriterDesc() {
        return writerDesc;
    }

    public void setWriterDesc(String writerDesc) {
        this.writerDesc = writerDesc;
    }

    public String getWriterWebsite() {
        return writerWebsite;
    }

    public void setWriterWebsite(String writerWebsite) {
        this.writerWebsite = writerWebsite;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public ArticlesData(String articleTitle, String articleDescription, String articleContent, String writerEmail, String writerName, String writerContact, String writerDesc, String writerWebsite, String timestamp) {
        this.articleTitle = articleTitle;
        this.articleDescription = articleDescription;
        this.articleContent = articleContent;
        this.writerEmail = writerEmail;
        this.writerName = writerName;
        this.writerContact = writerContact;
        this.writerDesc = writerDesc;
        this.writerWebsite = writerWebsite;
        this.timestamp = timestamp;



    }
}
