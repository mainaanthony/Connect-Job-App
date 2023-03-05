package com.connect.connecticutapp.model;

public class Data {
            private String jobTitle;
            private String jobContact;
            private String jobSalary;
            private String jobSkills;
            private String jobDescription;
            private String jobCertificates;            //jobCertificates,jobLocation,jobType,jobWebsite
            private String jobLocation;
            private String jobType;
            private String jobWebsite;
            private String companyName;
            private String timestamp;
            private String date;
            private int clicks;

    public Data() {
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getJobContact() {
        return jobContact;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public String getJobSkills() {
        return jobSkills;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobContact(String jobContact) {
        this.jobContact = jobContact;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public void setJobSkills(String jobSkills) {
        this.jobSkills = jobSkills;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobCertificates() {
        return jobCertificates;
    }

    public void setJobCertificates(String jobCertificates) {
        this.jobCertificates = jobCertificates;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobWebsite() {
        return jobWebsite;
    }

    public void setJobWebsite(String jobWebsite) {
        this.jobWebsite = jobWebsite;
    }

    public Data(String jobTitle, String jobContact, String jobSalary, String jobSkills, String jobDescription, String jobCertificates, String jobLocation, String jobType, String jobWebsite,String timestamp, String companyName,  String date, int clicks) {
        this.jobTitle = jobTitle;
        this.jobContact = jobContact;
        this.jobSalary = jobSalary;
        this.jobSkills = jobSkills;
        this.jobDescription = jobDescription;
        this.jobCertificates = jobCertificates;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
        this.jobWebsite = jobWebsite;
        this.companyName = companyName;
        this.timestamp = timestamp;
        this.date = date;
        this.clicks = clicks;





    }
}