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
<<<<<<< HEAD
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername() == username && users.get(i).isValidLogin(password)) {
                // grabs a username from the users arraylist and checks that the slected username is
                // the same as the parameter for username. The same is done for the password
                // parameter by
                // calling the isValidLogin method
=======
        
        int userExists = userExists(username); //checks if username exisit
        
        if (userExists > -1) {   // user exists
            if (users.get(userExists).getUsername().equalsIgnoreCase(username) && users.get(userExists).isValidLogin(password)) {
>>>>>>> e6bd0f15ebd719cb7e7b08ad3cb2649644811822
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

        int userExists = userExists(username); //checks if username exisit

        if (isCurrentUserAdmin()) {

            if (userExists > -1) {   // user exists
                return false;
            } else {    // user doesn't exist, so creating one
                users.add(new User(username));
                return true;
            } 
        }
        return false;
        
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
<<<<<<< HEAD
        if (isCurrentUserAdmin() || !(this.currentUser == null)) {

            // checks if user exists with same username
            for (int i = 0; i < users.size(); i++) { // checks the entire user ArrayList
                if (users.get(i).getUsername() == username) { // compares both username values
                    return false;
                }
            }


=======
        
        int userExists = userExists(username); //checks if username exisit
>>>>>>> e6bd0f15ebd719cb7e7b08ad3cb2649644811822

        if (isCurrentUserAdmin()) {
            if (userExists > -1) {   // user exists
                return false;
            } else { // user doesn't exist, so creating one
                users.add(new User(username, DEFAULT_PASSWORD, isAdmin));
                return true;
            } 
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

        int userExists = userExists(username); //checks if username exisit

<<<<<<< HEAD
            if (!userExists) {                           
                return false;
            }
            return true;
=======
        if (isCurrentUserAdmin()) {
            if (userExists > -1) {   // user exists
                users.remove(userExists);
                return true;
            } else { // user doesn't exist, so nothing to remove
                return false;
            } 
>>>>>>> e6bd0f15ebd719cb7e7b08ad3cb2649644811822
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
        int userExists =userExists(username); ;//checks if username exisit;
        
        if (isCurrentUserAdmin()) {
            
            if (userExists > -1) {   // user exists
                users.get(userExists).setIsAdmin(true);
                return true;
            } else { // user doesn't exist
                return false;
            } 

        }
        return false;
<<<<<<< HEAD

    
=======
>>>>>>> e6bd0f15ebd719cb7e7b08ad3cb2649644811822
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
<<<<<<< HEAD
        if (isCurrentUserAdmin() || !(this.currentUser == null)) {
            //checks if isCurrentUserAdmin() methods is true and currentUser isn't null
            return true;
=======
int userExists =userExists(username); ;//checks if username exisit;
        
        if (isCurrentUserAdmin()) {
            
            if (userExists > -1) {   // user exists
                users.get(userExists).setIsAdmin(false);
                return true;
            } else { // user doesn't exist
                return false;
            } 

>>>>>>> e6bd0f15ebd719cb7e7b08ad3cb2649644811822
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
        int userExists =userExists(username); ;//checks if username exisit;
        
        if (isCurrentUserAdmin()) {
            
            if (userExists > -1) {   // user exists
                users.get(userExists).setPassword(DEFAULT_PASSWORD);
                return true;
            } else { // user doesn't exist
                return false;
            }
        }
        return false;
    } // Reset a user's password


    // is the current user admin?

    private boolean isCurrentUserAdmin() {
        if (!(this.currentUser == null)) {
            if (this.currentUser.getIsAdmin())
                return true;
        } else {
            return false;
        }
        return false;
    }
    
    //does user exists? if so, where. If no user is found return -1
    private static int userExists(String username) {
        int userIndex=-1;
        boolean userExists =false;
        
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                userExists = true;
                userIndex = i;
                break;
            }
        }
        
        if(!userExists) {
            userIndex = -1;
        }
        return userIndex;
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

<<<<<<< HEAD
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

=======
            String inputUser, command, param1, param2;
            String[] inputsUser;
            boolean inputBool = false;
            boolean methodResponse = false;
            
            int userExists =userExists(username); ;//checks if username exisit;
            
                
                if (userExists > -1) {   // user exists
                    this.currentUser = users.get(userExists);           
                }
                

        
        while(true) {
            System.out.println("========== System Console ==========");
            System.out.println("Welcome, " +this.currentUser.getUsername() + "!");
            
            System.out.println("===== Commands =====");
            
          //  logout
        //  newpw [newpassword]
        //  adduser [username]
        //  adduser [username] [true or false]
        //  rmuser [username]
        //  giveadmin [username]
        //  rmadmin [username]
        //  resetpw [username]
            System.out.println("Logout: logout");
            System.out.println("Set new password: newpw [newpassword]");
            System.out.println("Add new user: adduser [username]");
            System.out.println("Add new user with admin privilegde: adduser [username] [true or false]");
            System.out.println("Remove user: rmuser [username]");
            System.out.println("Reset password: resetpw [username]");
           
            System.out.println("");
            
            System.out.println("Enter command: ");
            
            
            
>>>>>>> e6bd0f15ebd719cb7e7b08ad3cb2649644811822
            inputUser = userInputScanner.nextLine(); // todo: a single “word” with no spaces or
                                                     // other special characters

            inputsUser = inputUser.split("\\s+");

            if (inputsUser.length == 3) {

                command = inputsUser[0].toLowerCase();
                param1 = inputsUser[1]; // TODO: check if the username can be enforeced to be
                                        // lowercase
                param2 = inputsUser[2].toLowerCase();
<<<<<<< HEAD


                if (param2 == "true") {
                    inputBool = true;
                } else if (param2 == "false") {
                    inputBool = false;
                }

                if (command == "adduser") {
                    methodResponse = addUser(param1, inputBool);
=======
                
                
                if(param2.equalsIgnoreCase("true")) {
                    inputBool = true;
                } else if(param2.equalsIgnoreCase("false")) {
                    inputBool = false;
                }  
                
                if(command.equalsIgnoreCase("adduser") ) {
                    methodResponse = addUser(param1, inputBool );
>>>>>>> e6bd0f15ebd719cb7e7b08ad3cb2649644811822
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
<<<<<<< HEAD

                if (command == "logout") {
=======
                
                if(command.equalsIgnoreCase("logout") ) {
>>>>>>> e6bd0f15ebd719cb7e7b08ad3cb2649644811822
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

