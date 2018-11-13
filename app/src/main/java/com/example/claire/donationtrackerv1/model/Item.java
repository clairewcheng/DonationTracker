package com.example.claire.donationtrackerv1.model;

import java.util.Arrays;
import java.util.List;
import android.support.annotation.NonNull;

/**
 * Item defines what a valid donation item is and has methods for creating objects of type Item.
 */
public class Item {

    private String shortDesc;
    private String longDesc;
    private String value;
    private String timeStamp;
    private String date;
    public static final List<String> _category = Arrays.asList("Clothing",
            "Hat", "Kitchen", "Electronics", "Household", "Other");
    private String comments;
    private String location;
    private String category;
    private String picture;

    /**
     * Method for the creation of an item.
     * @param shortDesc Short Description of the item it's "title"
     * @param longDesc Long Description of the item. Details.
     * @param value The estimated cost fo the item.
     * @param timeStamp When the item was donated.
     * @param date The day the item was donated.
     * @param comments Optional comments on the item.
     * @param location Location the item is stored at.
     * @param category Selected category the item belongs to.
     * @param picture Option picture of the item. String.
     */
    public Item(String shortDesc, String longDesc, String value, String timeStamp, String date,
                String comments, String location, String category, String picture) {
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

    /**
     * Method for the creation of an item.
     * @param shortDesc Short Description of the item it's "title"
     * @param longDesc Long Description of the item. Details.
     * @param value The estimated cost fo the item.
     * @param timeStamp When the item was donated.
     * @param date The day the item was donated.
     * @param comments Optional comments on the item.
     * @param location Location the item is stored at.
     * @param category Selected category the item belongs to.
     */
    // constructor chaining
    public Item(String shortDesc, String longDesc, String value, String timeStamp, String date,
                String comments, String location, String category) {
        this(shortDesc, longDesc, value, timeStamp, date, comments, location, category,
                "No picture available");
    }

    /**
     * Method for the creation of an empty Item
     */
    public Item() {
        this("",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""); }

    // to string method
    @Override
    @NonNull public String toString() {
        if ((comments == null) && (picture == null)) {
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

    /**
     * Getter method for Short Description
     * @return shortDescription
     */
    public String getShortDesc() {
        return shortDesc;
    }

    /**
     * Setter method for Short Description
     * @param desc shortDescription
     */
    public void setShortDesc(String desc) {
        shortDesc = desc;
    }

    /**
     * Getter method for Long Description
     * @return long Description
     */
    public String getLongDesc() {
        return longDesc;
    }

    /**
     * Setter method for long Description
     * @param desc longDescription
     */
    public void setLongDesc(String desc) {
        longDesc = desc;
    }

    /**
     * Getter method for Value
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter method for Value
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Getter method for Time Stamp
     * @return time stamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Setter method for Time
     * @param time time
     */
    public void setTimeStamp(String time) {
        timeStamp = time;
    }

    /**
     * Getter method for date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter method for date
     * @param date date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter method for comments
     * @return comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Setter method for comments
     * @param comments comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Getter method for location
     * @return location string
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter method for location
     * @param location location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter method for location
     * @return location string
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter method for category
     * @param category category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter method for picture
     * @return picture picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Setter method for picture
     * @param picture picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }
}