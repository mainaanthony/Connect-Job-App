package com.connect.connecticutapp.Articles;

public class articleReviewsData {


    private String fullName;
    private String commentPost;
    private String contact;
    private String rating;
    private String timestamp;

    public   articleReviewsData(){

    }


    public articleReviewsData(String fullName, String commentPost, String contact, String rating, String timestamp) {
        this.fullName = fullName;
        this.commentPost = commentPost;
        this.contact = contact;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCommentPost() {
        return commentPost;
    }

    public void setCommentPost(String commentPost) {
        this.commentPost = commentPost;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
