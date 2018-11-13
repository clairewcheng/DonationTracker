package com.example.claire.donationtrackerv1.model;
import android.support.annotation.NonNull;

/**
 * Location Model class defines a valid Location object
 */
public class Location {

    private final String city;
    //private String key;
    private final String latitude;
    private final String longitude;
    private final String name;
    private final String phone;
    private final String state;
    private final String streetAddress;
    private final String type;
    private final String website;
    private final String zip;

    /**
     * For creation of Location Object
     * @param city city name
     * @param latitude latitude
     * @param longitude longitude
     * @param name name of location
     * @param phone phone number
     * @param state State address eg CA, WI
     * @param streetAddress Street Address
     * @param type Location type
     * @param website website link
     * @param zip zip code
     */
    public Location(String city, String latitude, String longitude,
                    String name, String phone, String state, String streetAddress,
                    String type, String website, String zip) {
        this.city = city;
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

    /**
     * Location constructor makes empty method
     */
    public Location() {
        this("", "", "", "", "", "",
                "", "", "", "");
    }



    /**
     * Getter method for city
     * @return city
     */
    public String getCity() {
        return city;
    }


//    /**
//     * Setter method for city
//     * @param city city
//     */
//    public void setCity(String city) {
//        this.city = city;
//    }

//// --Commented out by Inspection START (11/13/2018 10:03 AM):
////    /**
////     * Getter method for key
////     * @return key
////     */
////    public String getKey() {
////        return key;
////    }
//// --Commented out by Inspection STOP (11/13/2018 10:03 AM)
//
//    /**
//     * Setter method for key
//     * @param key key
//     * */
//    public void setKey(String key) {
//        this.key = key;
//    }

    /**
     * Getter method for lat
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

//    /**
//     * Setter method for lat
//     * @param latitude latitude
//     */
//    public void setLatitude(String latitude) {
//        this.latitude = latitude;
//    }

    /**
     * Getter method for longitude
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

//    /**
//     * Setter method for longitude
//     * @param longitude longitude
//     */
//    public void setLongitude(String longitude) {
//        this.longitude = longitude;
//    }
//
    /**
     * Getter method for Name
     * @return Name
     */
    public String getName() {
        return name;
    }

//    /**
//     * Setter method for Name
//     * @param name name
//     */
//    public void setName(String name) {
//        this.name = name;
//    }

    /**
     * Getter method for phone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

//    /**
//     * Setter method for phone
//     * @param phone phone
//     */
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    /**
//     * Getter method for state
//     * @return state
//     */
//    public String getState() {
//        return state;
//    }

//    /**
//     * Setter method for state
//     * @param state state
//     */
//    public void setState(String state) {
//        this.state = state;
//    }

    /**
     * Getter method for street address
     * @return street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

//    /**
//     * Setter method for street address
//     * @param streetAddress streetAddress
//     */
//    public void setStreetAddress(String streetAddress) {
//        this.streetAddress = streetAddress;
//    }

    /**
     * Getter method for type
     * @return type
     */
    public String getType() {
        return type;
    }

//    /**
//     * Setter method for type
//     * @param type type
//     */
//    public void setType(String type) {
//        this.type = type;
//    }

    /**
     * Getter method for website
     * @return website
     */
    public String getWebsite() {
        return website;
    }

//    /**
//     * Setter method for website
//     * @param website website
//     */
//    public void setWebsite(String website) {
//        this.website = website;
//    }

    /**
     * Getter method for zip
     * @return zip
     */
    public String getZip() {
        return zip;
    }

//    /**
//     * Setter method for zip
//     * @param zip zip
//     */
//    public void setZip(String zip) {
//        this.zip = zip;
//    }


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
