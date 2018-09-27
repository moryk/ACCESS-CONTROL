import java.util.ArrayList;
import java.util.Scanner;

public class AccessControl {

    private static ArrayList<User> users; // An ArrayList of valid users.
    private User currentUser; // Who is currently logged in, if anyone?
    private static final String DEFAULT_PASSWORD = "changeme";
    // Default password given to
    // new users or when we reset a user's password.


    // TODO: Your AccessControl constructor needs to make a new AccessControl object.
    // Any non-static field should be initialized here. Your constructor should also check whether
    // each class variable has been initialized and, if not, initialize them.
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


    // Your isValidLogin method should return true if the username/password pair matches any user in
    // your users ArrayList and false otherwise. This method should be static, since it only relies
    // on the static users ArrayList. This method should not have any side-effects. (For example, do
    // not set the currentUser here.) Its only job is to determine whether a username/password pair
    // is valid.
    public static boolean isValidLogin(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername() == username && users.get(i).isValidLogin(password)) {
                return true;
            }
        }
        return false;

    } // Report whether a
      // given username/password pair is a valid login

    public void logout() {
        currentUser = null;
    } // Log out the current user

    public void changePassword(String newPassword) {
        this.currentUser.setPassword(newPassword);
    } // Change the current user's password


    // ONLY ADMIN METHOD
    public boolean addUser(String username) {

        if (!(this.currentUser == null) || isCurrentUserAdmin()) {

            return true;
        }
        return false;
        // user.add(;
    } // Create a new user (non-admin)

    // ONLY ADMIN METHOD
    public boolean addUser(String username, boolean isAdmin) {
        if (isCurrentUserAdmin() || !(this.currentUser == null)) {

            // checks if user exists with same username
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername() == username) {
                    return false;
                }
            }



            return true;
        }

        return false;
    } // Create a new user
      // and specify their admin status

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

    // ONLY ADMIN METHOD
    public boolean giveAdmin(String username) {

        if (isCurrentUserAdmin() || !(this.currentUser == null)) {

            return true;
        }
        return false;

        // for (int i = 0; i < users.size(); i++) {
        // if (users.get(i).compareTo(username) == 0) {

        // }
        // }
    } // Give a user admin power

    // ONLY ADMIN METHOD
    public boolean takeAdmin(String username) {
        if (isCurrentUserAdmin() || !(this.currentUser == null)) {

            return true;
        }
        return false;
    } // Remove a user's admin power

    // ONLY ADMIN METHOD
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
//  Get a command from the user. Valid commands are:
//  logout
//  newpw [newpassword]
//  adduser [username]
//  adduser [username] [true or false]
//  rmuser [username]
//  giveadmin [username]
//  rmadmin [username]
//  resetpw [username]
//  Run the appropriate method


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
        
        while(true) {
            System.out.println("Welcome, " +this.currentUser.getUsername() + "!");
            
            inputUser = userInputScanner.nextLine(); // todo: a single “word” with no spaces or
                                                     // other special characters
            
            inputsUser = inputUser.split("\\s+");
            
            if(inputsUser.length == 3) {

                command = inputsUser[0].toLowerCase();
                param1 = inputsUser[1];         // TODO: check if the username can be enforeced to be lowercase
                param2 = inputsUser[2].toLowerCase();
                
                
                if(param2 == "true") {
                    inputBool = true;
                } else if(param2== "false") {
                    inputBool = false;
                }  
                
                if(command == "adduser") {
                    methodResponse = addUser(param1, inputBool );
                } else {
                    //TODO: implement incorrect command sequence
                }
                
            } else if (inputsUser.length == 2) {
              //TODO implement rest
                command = inputsUser[0].toLowerCase();
                param1 = inputsUser[1];         // check if the username can be enforeced to be lowercase
                
                switch (command) {
                    case "newpw":  
                        changePassword(param1);
                        break;
                        
                    case "adduser":  
                        methodResponse= addUser(param1);
                        break;
                        
                    case "rmuser":  
                        methodResponse= removeUser(param1);
                        break;
                        
                    case "giveadmin":  
                        methodResponse= giveAdmin(param1);
                        break;
                        
                    case "rmadmin":  
                        methodResponse= takeAdmin(param1);
                        break;
                        
                    case "resetpw":  
                        methodResponse= resetPassword(param1);
                        break;
                        
                        
                    default: // TODO: implement incorrect command sequence 
                             break;
                }
                
                
            } else if (inputsUser.length == 1) {
                command = inputsUser[0].toLowerCase();
                
                if(command == "logout") {
                    logout();
                    break;
                }
            } else {
                //TODO implement error saying too many input arguments
            }
               
        }
        

    }
    // Called when a user is logged in
    // Set the currentUser
    // Allows them to changePassword or logout
    // If they are an admin, gives access to admin methods


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

