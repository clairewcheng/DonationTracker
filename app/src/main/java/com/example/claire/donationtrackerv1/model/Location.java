package com.example.claire.donationtrackerv1.model;
import android.support.annotation.NonNull;

public class Location {

    private String city;
    private String key;
    private String latitude;
    private String longitude;
    private String name;
    private String phone;
    private String state;
    private String streetAddress;
    private String type;
    private String website;
    private String zip;

    public Location(String city, String key, String latitude, String longitude,
                    String name, String phone, String state, String streetAddress,
                    String type, String website, String zip) {
        this.city = city;
        this.key = key;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.streetAddress = streetAddress;
        this.type = type;
        this.website = website;
        this.zip = zip;
    }

    public Location() {
        this("", "", "", "", "", "", "", "", "", "", "");
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }


    @Override
    @NonNull public String toString() {
        return "Name: " + name
                + "\nCity: " + city
                + "\nStreet Address: " + streetAddress
                + "\nState: " + state
                + "\nZip: " + zip
                + "\nLatitude: " + latitude
                + "\nLongitude: " + longitude
                + "\nType: " + type
                + "\nWebsite: " + website
                + "\nPhone: " + phone;
    }
}
