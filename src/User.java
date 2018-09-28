//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AccessControl
// Files: AccessControl.java, AccessControlTest.java, User.java
// Course: CS300, Fall 2018
//
// Author: Ajmain Naqib
// Email: naqib@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:
// Partner Email:
// Partner Lecturer's Name:
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __ Write-up states that pair programming is allowed for this assignment.
// __ We have both read and understand the course Pair Programming Policy.
// __ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources:
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class User {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    String username; // The user's name
    String password; // The user's password
    Boolean isAdmin; // Whether or not the user has Admin powers
    
    public User(String username) {
        this.username = username;
    }
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    // Creates a new user with the given password and admin status
public boolean isValidLogin(String password) {
    if(password.compareTo(this.password) == 0) {
        return true;
    }
    else {
        return false;
    }
    
} // Report whether the password is correct


public String getUsername() {
    return this.username;
} // Return the user's name


public boolean getIsAdmin() {
    return this.isAdmin; 
} // Report whether the user is an admin


public void setPassword(String password) {
    this.password = password;
} // Set the new password


public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
} // Set the new admin status
    
}

