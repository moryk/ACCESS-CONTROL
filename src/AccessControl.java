import java.util.ArrayList;
import java.util.Scanner;

public class AccessControl {

    private static ArrayList<User> users; // An ArrayList of valid users.
    private User currentUser; // Who is currently logged in, if anyone?
    private static final String DEFAULT_PASSWORD = "changeme"; // created variable that can't be
                                                               // changed
    // Default password given to
    // new users or when we reset a user's password.

    /**
     * AccessControl constructor which creates a new AccessControl object. The constructor also
     * check to see whether each class variable has been initialized and, if not, initialize them.
     * Any non-static field has been initialized here.
     */

    public AccessControl() {

        if (users == null) {
            users = new ArrayList<User>();

            // User admin = new User("admin", "root", true);

            users.add(new User("admin", "root", true));


        }

        currentUser = null;

    } // A no-parameter constructor

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
     * This method checks to make sure that username and password matches up. The method returns
     * true if the username/password pair matches any user in your users ArrayList and false
     * otherwise. Its only job is to determine whether a username/password pair is valid.
     * 
     * @param username is the new transaction group being added (perfect size).
     * @param password is the collection that newTransactions is being added to (oversize).
     * @return a boolean value (true/false) to see if username and password matches up
     */

    public static boolean isValidLogin(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername() == username && users.get(i).isValidLogin(password)) {
                // grabs a username from the users arraylist and checks that the slected username is
                // the same as the parameter for username. The same is done for the password
                // parameter by
                // calling the isValidLogin method
                return true;
            }
        }
        return false;

    } // Report whether a
      // given username/password pair is a valid login

    /**
     * This method sets the variable value of currentUser equal to a null value when called. The
     * method also returns no value, it's only job is to modify the currentUser
     */
    public void logout() {
        currentUser = null; // sets current user to null
    } // Log out the current user

    /**
     * This method modifies the password value of the currentUser by calling the setPassword method
     * from the User Class.
     * 
     * @param newPassword is the new password value being held by currentUser. The method also
     *        returns no value, it's only job is to modify the currentUser's password value.
     */
    public void changePassword(String newPassword) {
        this.currentUser.setPassword(newPassword);
    } // Change the current user's password

    /**
     * This method checks to make sure that username put into the parameter is an Addmin or that its
     * username value is not null. The method returns true if the username is able to be created
     * without admin access.
     * 
     * @param username is the new transaction group being added (perfect size).
     * @return a boolean value (true/false) to see if username was created or not without Admin
     *         abilities
     */
    // ONLY ADMIN METHOD
    public boolean addUser(String username) {

        if (!(this.currentUser == null) || isCurrentUserAdmin()) {

            return true;
        }
        return false;
        // user.add(;
    } // Create a new user (non-admin)

    // ONLY ADMIN METHOD
    /**
     * This method checks to make sure that username put into the parameter is an Addmin or that its
     * username value is not null. The method returns true if the username is able to be created
     * with admin access.
     * @param username is the new transaction group being added (perfect size).
     * @param isAdmin is a boolean value which notifies if something has Admin access 
     * @return a boolean value (true/false) to see if username was created or not with Admin
     *         abilities
     */
    public boolean addUser(String username, boolean isAdmin) {
        if (isCurrentUserAdmin() || !(this.currentUser == null)) {

            // checks if user exists with same username
            for (int i = 0; i < users.size(); i++) { // checks the entire user ArrayList
                if (users.get(i).getUsername() == username) { // compares both username values
                    return false;
                }
            }



            return true;
        }

        return false;
    } // Create a new user
      // and specify their admin status
    /**
     * This method checks to make sure that username put into the parameter is an Addmin or that its
     * username value is not null. The method returns true if the username is able to be created
     * with admin access.
     * @param username is the new transaction group being added (perfect size).
     * @param isAdmin is a boolean value which notifies if something has Admin access 
     * @return a boolean value (true/false) to see if username was created or not with Admin
     *         abilities
     */
    // ONLY ADMIN METHOD
    public boolean removeUser(String username) {

        boolean userExists = false;

        if (isCurrentUserAdmin() || !(this.currentUser == null)) {

            // checks if user doesn't with same username
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername() == username) {
                    userExists = true;
                }
            }

            if (!userExists) {                           
                return false;
            }
            return true;
        }

        for (int i = 0; i < users.size(); i++) {
            // if (users.get(i).compareTo(username) == 0) {
            // users.remove(i);
            // }
        }
        return false;
    } // Remove a user
    /**
     * This method checks to make sure that username put into the parameter is an Addmin or that its
     * username value is not null. The method returns true if the username is able to be created
     * with admin access.
     * @param username is the new transaction group being added (perfect size).
     * @param isAdmin is a boolean value which notifies if something has Admin access 
     * @return a boolean value (true/false) to see if username was created or not with Admin
     *         abilities
     */
    // ONLY ADMIN METHOD
    public boolean giveAdmin(String username) {

        if (isCurrentUserAdmin() || !(this.currentUser == null)) {

            return true;
        }
        return false;

    
    } // Give a user admin power
    /**
     * This method checks to make sure that username put into the parameter is an Addmin or that its
     * username value is not null. The method returns true if the username is able to be created
     * with admin access.
     * @param username is the new transaction group being added (perfect size).
     * @param isAdmin is a boolean value which notifies if something has Admin access 
     * @return a boolean value (true/false) to see if username was created or not with Admin
     *         abilities
     */
    // ONLY ADMIN METHOD
    public boolean takeAdmin(String username) {
        if (isCurrentUserAdmin() || !(this.currentUser == null)) {
            //checks if isCurrentUserAdmin() methods is true and currentUser isn't null
            return true;
        }
        return false;
    } // Remove a user's admin power

    // ONLY ADMIN METHOD
    /**
     * This method checks to make sure that username put into the parameter is an Addmin or that its
     * username value is not null. The method returns true if the username is able to be created
     * with admin access.
     * @param username is the new transaction group being added (perfect size).
     * @param isAdmin is a boolean value which notifies if something has Admin access 
     * @return a boolean value (true/false) to see if username was created or not with Admin
     *         abilities
     */
    public boolean resetPassword(String username) {
        if (isCurrentUserAdmin() || !(this.currentUser == null)) {

            return true;
        }
        return false;
    } // Reset a user's password


    // is the current user admin?

    private boolean isCurrentUserAdmin() {
        if (!(this.currentUser == null)) {
            if (isCurrentUserAdmin())
                return true;
        } else {
            return false;
        }
        return false;
    }

    /**
     * This method checks to make sure that username put into the parameter is an Addmin or that its
     * username value is not null. The method returns true if the username is able to be created
     * with admin access.
     * @param username is the new transaction group being added (perfect size).
     * @param isAdmin is a boolean value which notifies if something has Admin access 
     * @return a boolean value (true/false) to see if username was created or not with Admin
     *         abilities
     */

    public void loginScreen(Scanner userInputScanner) {

        String inputUsername = "";
        String inputPassword = "";

        while (true) {
            System.out.println("Enter Username: ");
            inputUsername = userInputScanner.next(); // todo: a single “word” with no spaces or
                                                     // other special characters
            System.out.println("Enter Password: ");
            inputPassword = userInputScanner.next();

            if (isValidLogin(inputUsername, inputPassword)) {
                sessionScreen(inputUsername, userInputScanner);
            } else {
                System.out.println("ERROR: Invalid login. Please try again! ");
            }
        }


    } // Main driver loop.
      // Prompt the user for login information
      // calls the isValidLogin method
      // If the login is valid, call the sessionScreen method


    // The sessionScreen method also consists mostly of a single while loop. Unlike loginScreen,
    // sessionScreen has a little setup to do before you begin looping: set the currentUser to the
    // user object matching the username parameter.
    // Get a command from the user. Valid commands are:
    // logout
    // newpw [newpassword]
    // adduser [username]
    // adduser [username] [true or false]
    // rmuser [username]
    // giveadmin [username]
    // rmadmin [username]
    // resetpw [username]
    // Run the appropriate method
    /**
     * This method checks to make sure that username put into the parameter is an Addmin or that its
     * username value is not null. The method returns true if the username is able to be created
     * with admin access.
     * @param username is the new transaction group being added (perfect size).
     * @param isAdmin is a boolean value which notifies if something has Admin access 
     * @return a boolean value (true/false) to see if username was created or not with Admin
     *         abilities
     */

    public void sessionScreen(String username, Scanner userInputScanner) {

        String inputUser, command, param1, param2;
        String[] inputsUser;
        boolean inputBool = false;
        boolean methodResponse = false;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername() == username) {
                this.currentUser = users.get(i);
            }
        }

        while (true) {
            System.out.println("Welcome, " + this.currentUser.getUsername() + "!");

            inputUser = userInputScanner.nextLine(); // todo: a single “word” with no spaces or
                                                     // other special characters

            inputsUser = inputUser.split("\\s+");

            if (inputsUser.length == 3) {

                command = inputsUser[0].toLowerCase();
                param1 = inputsUser[1]; // TODO: check if the username can be enforeced to be
                                        // lowercase
                param2 = inputsUser[2].toLowerCase();


                if (param2 == "true") {
                    inputBool = true;
                } else if (param2 == "false") {
                    inputBool = false;
                }

                if (command == "adduser") {
                    methodResponse = addUser(param1, inputBool);
                } else {
                    // TODO: implement incorrect command sequence
                }

            } else if (inputsUser.length == 2) {
                // TODO implement rest
                command = inputsUser[0].toLowerCase();
                param1 = inputsUser[1]; // check if the username can be enforeced to be lowercase

                switch (command) {
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


                    default: // TODO: implement incorrect command sequence
                        break;
                }


            } else if (inputsUser.length == 1) {
                command = inputsUser[0].toLowerCase();

                if (command == "logout") {
                    logout();
                    break;
                }
            } else {
                // TODO implement error saying too many input arguments
            }

        }


    }
    // Called when a user is logged in
    // Set the currentUser
    // Allows them to changePassword or logout
    // If they are an admin, gives access to admin methods

    /**
     * This method checks to make sure that username put into the parameter is an Addmin or that its
     * username value is not null. The method returns true if the username is able to be created
     * with admin access.
     * @param username is the new transaction group being added (perfect size).
     * @param isAdmin is a boolean value which notifies if something has Admin access 
     * @return a boolean value (true/false) to see if username was created or not with Admin
     *         abilities
     */
    // The setCurrentUser method should not be used by any other method in AccessControl, but may be
    // very useful for writing test methods. You must implement it even if you do not use it in any
    // test methods. (We may use it when testing your code.)
    public void setCurrentUser(String username) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername() == username) {
                this.currentUser = users.get(i);
            }
        }

    } // A mutator you can use to write tests
      // without simulating user input


}

