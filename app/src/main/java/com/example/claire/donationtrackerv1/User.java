package com.example.claire.donationtrackerv1;

import java.util.Arrays;
import java.util.List;

public class User {
    /** allows us to assign a type to a user*/
    public static List<String> userType = Arrays.asList("User", "Manager", "Admin", "Location Employee");

    /** saves the userType of the user*/

    private String _userType;

    /** saves the email of the user */

    private String _email;

    /** saves the password of the user */

    private String _password;

    /* **********************
     * Getters and setters
     */
    public String getEmail() { return _email; }
    public void setEmail(String email) { _email = email; }

    public String getPassword() {return _password; }
    public void setPassword(String password) { _password = password; }

    public String getUserType() { return _userType; }
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

    public User(String email, String password) {
        this("User", email, password);
    }

    public User() {
        this ("User", "email@email.com", "password");
    }



}


