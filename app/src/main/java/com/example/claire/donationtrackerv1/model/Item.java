package com.example.claire.donationtrackerv1.model;

import java.util.Arrays;
import java.util.List;

public class Item {

    private String shortDesc;
    private String longDesc;
    private String value;
    private String timeStamp;
    private String date;
    public static List<String> _category = Arrays.asList("Clothing", "Hat", "Kitchen", "Electronics", "Household", "Other");
    private String comments;
    private String location;
    private String category;
    private String picture;

    public Item(String shortDesc, String longDesc, String value, String timeStamp, String date, String comments,
            String location, String category, String picture) {
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.value = value;
        this.timeStamp = timeStamp;
        this.date = date;
        this.comments = comments;
        this.location = location;
        this.category = category;
        this.picture = picture;
    }

    // constructor chaining
    public Item(String shortDesc, String longDesc, String value, String timeStamp, String date, String comments,
                String location, String category) {
        this(shortDesc, longDesc, value, timeStamp, date, comments, location, category, "No picture available");
    }

    public Item() {
        this("", "", "", "", "", "","", "", ""); }

    // to string method
    @Override
    public String toString() {
        if (comments == null && picture == null) {
            return "Name: " + shortDesc
                    + "\nTime Stamp: " + timeStamp
                    + "\nDate: " + date
                    + "\nDescription: " + longDesc
                    + "\nValue: " + value
                    + "\nCategory: " + category
                    + "\nLocation: " + location;
        }

        else if (comments == null) {
            return "Name: " + shortDesc
                    + "\nTime Stamp: " + timeStamp
                    + "\nDate: " + date
                    + "\nDescription: " + longDesc
                    + "\nValue: " + value
                    + "\nCategory: " + category
                    + "\nPicture: " + picture
                    + "\nLocation: " + location;
        }

        else if (picture == null) {
            return "Name: " + shortDesc
                    + "\nTime Stamp: " + timeStamp
                    + "\nDate: " + date
                    + "\nDescription: " + longDesc
                    + "\nValue: " + value
                    + "\nCategory: " + category
                    + "\nComments: " + comments
                    + "\nLocation: " + location;
        }
        else {
            return "Name: " + shortDesc
                    + "\nTime Stamp: " + timeStamp
                    + "\nDate: " + date
                    + "\nDescription: " + longDesc
                    + "\nValue: " + value
                    + "\nCategory: " + category
                    + "\nComments: " + comments
                    + "\nPicture: " + picture
                    + "\nLocation: " + location;
        }
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String desc) {
        shortDesc = desc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String desc) {
        longDesc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String time) {
        timeStamp = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}