package com.example.claire.donationtrackerv1.model;

import java.util.Arrays;
import java.util.List;

/**
 * User class defines a user Object
 */
public class User {
    /** allows us to assign a type to a user*/
    public static final List<String> userType = Arrays.asList("User",
            "Manager",
            "Admin",
            "Location Employee");

    /** saves the userType of the user*/

    private String _userType;

    /** saves the email of the user */

    private String _email;

    /** saves the password of the user */

    private String _password;


    /**
     * Getter method for email
     * @return email
     */
    public String getEmail() { return _email; }

    /**
     * Setter method for email
     * @param email  email
     */
    public void setEmail(String email) { _email = email; }

    /**
     * Getter method for password
     * @return password
     */
    public String getPassword() {return _password; }

    /**
     * Setter method for password
     * @param password  password
     */
    public void setPassword(String password) { _password = password; }

    /**
     * Getter method for User Type
     * @return User Type
     */
    public String getUserType() { return _userType; }

    /**
     * Setter method for User Type
     * @param userType  User Type
     */
    public void setUserType(String userType) { _userType = userType; }


    /** Makes a new user
     * @param userType creates the type of user
     * @param email the email of the user
     * @param password the password of the user
     */

    public User(String userType, String email, String password) {
        _userType = userType;
        _email = email;
        _password = password;
    }


    /**
     * User constructor
     * @param email email
     * @param password password
     */
    public User(String email, String password) {
        this("User", email, password);
    }

    /**
     * User builds and empty user with filler info
     */
    public User() {
        this ("User", "email@email.com", "password");
    }



}


