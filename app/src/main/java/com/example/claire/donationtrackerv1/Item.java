package com.example.claire.donationtrackerv1;

public class Item {

    private String time_Stamp;
    private String name;
    private String description;
    private Integer value;
    private String category;
    private String comments;
    private String picture;
    private Location location;

    public Item(String time_Stamp, String name, String description, Integer value, String category,
                String comments, String picture, Location location) {
        time_Stamp = this.time_Stamp;
        name = this.name;
        description = this.description;
        value = this.value;
        category = this.category;
        comments = this.comments;
        picture = this.picture;
        location = this.location;
    }

    // constructor chaining
    public Item(String time_Stamp, String name, String description, Integer value, String category,
                String picture, Location location) {
        this(time_Stamp, name, description, value, category, "No comments entered" , picture, location);
    }

    public Item (String time_Stamp, String name, String description, Integer value, String category,  Location location) {
        this(time_Stamp, name, description, value, category, "No comments entered", "No picture entered", location );
    }

    // to string method
    @Override
    public String toString() {
        if (comments == null && picture == null) {
            return "Name: " + name
                    + "\nTime Stamp: " + time_Stamp
                    + "\nDescription: " + description
                    + "\nValue: " + value
                    + "\nCategory: " + category
                    + "\nLocation: " + location;
        }

        else if (comments == null) {
            return "Name: " + name
                    + "\nTime Stamp: " + time_Stamp
                    + "\nDescription: " + description
                    + "\nValue: " + value
                    + "\nCategory: " + category
                    + "\nPicture: " + picture
                    + "\nLocation: " + location;
        }

        else if (picture == null) {
            return "Name: " + name
                    + "\nTime Stamp: " + time_Stamp
                    + "\nDescription: " + description
                    + "\nValue: " + value
                    + "\nCategory: " + category
                    + "\nComments: " + picture
                    + "\nLocation: " + location;
        }
        else {
            return "Name: " + name
                    + "\nTime Stamp: " + time_Stamp
                    + "\nDescription: " + description
                    + "\nValue: " + value
                    + "\nCategory: " + category
                    + "\nComments: " + comments
                    + "\nPicture: " + picture
                    + "\nLocation: " + location;
        }
    }
}