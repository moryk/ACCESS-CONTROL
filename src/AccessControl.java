import java.util.ArrayList;
import java.util.Scanner;

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
//////////////////// https://stackoverflow.com/questions/31716511/how-to-ignore-special-characters-and-spaces-in-string
// : how to remove special characters in loginScreen method.
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class AccessControl {

  private static ArrayList<User> users; // An ArrayList of valid users.
  private User currentUser; // Who is currently logged in, if anyone?
  private static final String DEFAULT_PASSWORD = "changeme";
  // Default password given to
  // new users or when we reset a user's password.


  /**
   * AccessControl constructor which creates a new AccessControl object. The constructor also check to
   * see whether each class variable has been initialized and, if not, initializes them. Any
   * non-static field has been initialized here.
   * 
   * Author: Ajmain Naqib Email: naqib@wisc.edu
   * 
   */
  public AccessControl() {
    if (users == null) {
      users = new ArrayList<User>(); // initiates arraylists with user object
      users.add(new User("admin", "root", true)); // add admin user with root password to arraylist
    }
    currentUser = null;
  }

  /*
   * Launch an AccessControl instance
   */
  public static void main(String[] args) {
    AccessControl ac = new AccessControl();
    // If we have any persistent information to lead
    // this is where we load it.
    Scanner userIn = new Scanner(System.in);
    ac.loginScreen(userIn);
  }


  /**
   * This method checks if username corresponds to it's password matches up. The method returns true
   * if the username/password pair matches any user the users ArrayList and false otherwise.
   * 
   * @param username is a string from user input
   * @param password is a string from user input
   * @return a boolean depending on if username and password is corresponding
   */

  public static boolean isValidLogin(String username, String password) {

    int userExists = userExists(username); // checks if username exist, get index if does

    if (userExists > -1) { // user exists
      if (users.get(userExists).getUsername().equalsIgnoreCase(username) // is username matches
        && users.get(userExists).isValidLogin(password)) { // is password matches to corresponding user
        return true;
      }
    }
    return false;

  }

  /**
   * This method sets the variable value of currentUser to a null which logs out the current user
   */
  public void logout() {
    currentUser = null;
  }


  /**
   * This method modifies the password value of the currentUser by calling the setPassword method from
   * the User Class.
   * 
   * @param newPassword is the password being passed in by the user.
   */

  public void changePassword(String newPassword) {
    this.currentUser.setPassword(newPassword);
  } // Change the current user's password


  /**
   * This method creates a normal user with default password if the current user is an Admin.
   * 
   * @param username of the user that will be added
   * @return a boolean value if user was created or not
   */
  public boolean addUser(String username) {

    int userExists = userExists(username); // checks if username exist, get index if does

    if (isCurrentUserAdmin()) {

      if (userExists > -1) { // user exists
        return false;
      } else { // user doesn't exist, so creating one
        User newUser = new User(username);
        newUser.setPassword(DEFAULT_PASSWORD);
        newUser.setIsAdmin(false);

        users.add(newUser);
        return true;
      }
    }
    return false;

  }

  /**
   * This method creates a user with default password if the current user is an Admin with admin or
   * normal priviliges
   * 
   * @param username of the user that will be added
   * @param isAdmin is a boolean. True if user is admin, false if user is normal user.
   * @return a boolean value if user was created or not
   */
  public boolean addUser(String username, boolean isAdmin) {

    int userExists = userExists(username); // checks if username exist, get index if does

    if (isCurrentUserAdmin()) {
      if (userExists > -1) { // user exists
        return false;
      } else { // user doesn't exist, so creating one
        users.add(new User(username, DEFAULT_PASSWORD, isAdmin));
        return true;
      }
    }

    return false;
  }

  /**
   * This method removes a user if the current user is an admin and the user exists. The method
   * returns true if the user is removed and false otherwise.
   * 
   * @param username of the user that will be removed
   * @return a boolean value if user was removed
   */
  public boolean removeUser(String username) {

    int userExists = userExists(username); // checks if username exist, get index if does

    if (isCurrentUserAdmin()) {
      if (userExists > -1) { // user exists
        users.remove(userExists); // Remove a user with index userExists
        return true;
      } else { // user doesn't exist, so nothing to remove
        return false;
      }
    }

    return false;
  }

  /**
   * This method gives a user admin access if the current user is an admin and the user exists. The
   * method returns true if the user is set admin and false otherwise
   * 
   * @param username of the user that will be modified
   * @return a boolean value based on if the user is set admin
   */
  public boolean giveAdmin(String username) {
    int userExists = userExists(username);;// checks if username exist, get index if does;

    if (isCurrentUserAdmin()) {

      if (userExists > -1) { // user exists
        users.get(userExists).setIsAdmin(true);
        return true;
      } else { // user doesn't exist
        return false;
      }

    }
    return false;
  }

  /**
   * This method removes a users admin access if the current user is an admin and the user exists. The
   * method returns true if the user is set to normal user and false otherwise
   * 
   * @param username of the user that will be added
   * @return a boolean value based on if the user is set to normal user
   */
  public boolean takeAdmin(String username) {
    int userExists = userExists(username);;// checks if username exist, get index if does;

    if (isCurrentUserAdmin()) {

      if (userExists > -1) { // user exists
        users.get(userExists).setIsAdmin(false);
        return true;
      } else { // user doesn't exist
        return false;
      }

    }
    return false;
  }

  /**
   * This method resets a user's password to the default password if the current user is an admin and
   * the user exists. The method returns true if the password is changed and false otherwise
   * 
   * @param username of the user that will be added
   * @return a boolean value based on if the user's password was set to default
   */
  public boolean resetPassword(String username) {
    int userExists = userExists(username);;// checks if username exist, get index if does;

    if (isCurrentUserAdmin()) {

      if (userExists > -1) { // user exists
        users.get(userExists).setPassword(DEFAULT_PASSWORD);
        return true;
      } else { // user doesn't exist
        return false;
      }
    }
    return false;
  } // Reset a user's password


  /**
   * This method checks if the current user is admin
   * 
   * @return true if current user is an admin, false otherwise
   */

  private boolean isCurrentUserAdmin() {
    if (!(this.currentUser == null)) { // checks that currentUser is set to a variable
      if (this.currentUser.getIsAdmin()) // checks if currentUser is admin
        return true;
    } else {
      return false;
    }
    return false;
  }

  /**
   * This method checks if there are any user with the username passed in, in the users arraylist. If
   * user is found, returns the index of the user in the users arraylist. If user is not found,
   * returns -1
   * 
   * @param username of the user
   * @return index of the user in the users arraylist
   */
  private static int userExists(String username) {
    int userIndex = -1;
    boolean userExists = false;

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equalsIgnoreCase(username)) { // checks if user exists in users arraylist
        userExists = true;
        userIndex = i; // sets the index of the user
        break;
      }
    }

    if (!userExists) {
      userIndex = -1; // if user doesn't exist
    }
    return userIndex;
  }

  /**
   * This method is a driver method which calls the main driver if the proper authentications are
   * provided
   * 
   * @param userInputScanner scanner object
   */

  public void loginScreen(Scanner userInputScanner) {

    String inputUsername = "";
    String inputPassword = "";

    while (true) {
      System.out.println("Enter Username: ");
      inputUsername = userInputScanner.next().trim(); // removes whitespace
      inputUsername = inputUsername.replaceAll("[^a-zA-Z0-9]", ""); // removes special characters

      System.out.println("Enter Password: ");
      inputPassword = userInputScanner.next();

      if (isValidLogin(inputUsername, inputPassword)) {
        sessionScreen(inputUsername, userInputScanner);
      } else {
        System.out.println("ERROR: Invalid login. Please try again! ");
      }
    }


  }


  /**
   * This method is the main driver that calls different method based on the correct user input
   * 
   * @param username of the user
   * @param userInputScanner scanner object
   */

  public void sessionScreen(String username, Scanner userInputScanner) {

    String inputUser, command, param1, param2;
    String[] inputsUser;
    boolean inputBool = false;
    boolean methodResponse = true;
    int errorResponse = 0;

    int userExists = userExists(username);  // checks if username exist, get index if does;

    if (userExists > -1) { // user exists
      this.currentUser = users.get(userExists);
    }

    while (true) {
      System.out.println("========== System Console ==========");
      System.out.println("Welcome, " + this.currentUser.getUsername() + "!");
      System.out.println("");

      System.out.println("===== Commands =====");

      System.out.println("  Logout from current user:             logout");
      System.out.println("  Set new password:                     newpw [newpassword]");
      System.out.println("  Add new user:                         adduser [username]");
      System.out
        .println("  Add new user with admin privilegde:   adduser [username] [true or false]");
      System.out.println("  Remove user:                          rmuser [username]");
      System.out.println("  Reset password:                       resetpw [username]");

      System.out.println("");


      if (errorResponse != 0) {
        System.out.print("ERROR! ");
        switch (errorResponse) {
          case 1:
            System.out.println("Incorrect command. Try again!");
            break;

          case 2:
            System.out.println("Too many arguments. Try again!");
            break;

          case 3:
            System.out.println(
              "Could not complete actions. Verify that you are admin or the user exists!");
            break;

          default:
            System.out.println("Something is wrong.");
            break;
        }
        errorResponse = 0;

      }

      System.out.println("Enter command: ");

      if (userInputScanner.hasNextLine()) {
        inputUser = userInputScanner.nextLine();  // sanitizes scanner input if has \n
      }

      inputUser = userInputScanner.nextLine(); 

      inputsUser = inputUser.split("\\s+"); // splits by space

      if (inputsUser.length == 3) { // if 3 words are input

        command = inputsUser[0].toLowerCase();
        param1 = inputsUser[1]; // TODO: check if the username can be enforeced to be lowercase
        param2 = inputsUser[2].toLowerCase();


        if (param2.equalsIgnoreCase("true")) {
          inputBool = true;
        } else if (param2.equalsIgnoreCase("false")) {
          inputBool = false;
        }

        if (command.equalsIgnoreCase("adduser")) {
          methodResponse = addUser(param1, inputBool);
        } else {
          errorResponse = 1;
        }

      } else if (inputsUser.length == 2) { // if 2 words are input
        // TODO implement rest
        command = inputsUser[0].toLowerCase(); // command
        param1 = inputsUser[1]; // first parameter

        switch (command) { // calling correct parameter
          case "newpw":
            changePassword(param1);
            break;

          case "adduser":
            methodResponse = addUser(param1);
            break;

          case "rmuser":
            methodResponse = removeUser(param1);
            break;

          case "giveadmin":
            methodResponse = giveAdmin(param1);
            break;

          case "rmadmin":
            methodResponse = takeAdmin(param1);
            break;

          case "resetpw":
            methodResponse = resetPassword(param1);
            break;


          default:
            errorResponse = 1; // if incorrect command is entered
            break;
        }


      } else if (inputsUser.length == 1) { // if 3 words are input
        command = inputsUser[0].toLowerCase();

        if (command.equalsIgnoreCase("logout")) {
          logout();
          break;
        } else {
          errorResponse = 1; // if incorrect command is entered
        }
      } else {
        errorResponse = 2; // if too many arguments are input
      }

      if (!methodResponse) {
        errorResponse = 3; // Could not complete actions.
      }
    }
  }


  /**
   * This method checks to make sure that username put into the parameter is an Admin or that its
   * username value is not null. The method returns true if the username is able to be created with
   * admin access.
   * 
   * @param username of the user that will be added
   * @param isAdmin is a boolean value which notifies if something has Admin access
   * @return a boolean value (true/false) to see if username was created or not with Admin abilities
   */
  public void setCurrentUser(String username) {

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername() == username) {
        this.currentUser = users.get(i);
      }
    }

  } // A mutator you can use to write tests
    // without simulating user input


}

