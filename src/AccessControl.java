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
        
        int userExists = userExists(username); //checks if username exisit
        
        if (userExists > -1) {   // user exists
            if (users.get(userExists).getUsername().equalsIgnoreCase(username) && users.get(userExists).isValidLogin(password)) {
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
    public boolean addUser(String username, boolean isAdmin) {
        
        int userExists = userExists(username); //checks if username exisit

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

    // ONLY ADMIN METHOD
    public boolean removeUser(String username) {

        int userExists = userExists(username); //checks if username exisit

        if (isCurrentUserAdmin()) {
            if (userExists > -1) {   // user exists
                users.remove(userExists);
                return true;
            } else { // user doesn't exist, so nothing to remove
                return false;
            } 
        }

        return false;
    } // Remove a user

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
    } // Give a user admin power

    // ONLY ADMIN METHOD
    public boolean takeAdmin(String username) {
int userExists =userExists(username); ;//checks if username exisit;
        
        if (isCurrentUserAdmin()) {
            
            if (userExists > -1) {   // user exists
                users.get(userExists).setIsAdmin(false);
                return true;
            } else { // user doesn't exist
                return false;
            } 

        }
        return false;
    } // Remove a user's admin power

    // ONLY ADMIN METHOD
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
            
            
            
            inputUser = userInputScanner.nextLine(); // todo: a single “word” with no spaces or
                                                     // other special characters
            
            inputsUser = inputUser.split("\\s+");
            
            if(inputsUser.length == 3) {

                command = inputsUser[0].toLowerCase();
                param1 = inputsUser[1];         // TODO: check if the username can be enforeced to be lowercase
                param2 = inputsUser[2].toLowerCase();
                
                
                if(param2.equalsIgnoreCase("true")) {
                    inputBool = true;
                } else if(param2.equalsIgnoreCase("false")) {
                    inputBool = false;
                }  
                
                if(command.equalsIgnoreCase("adduser") ) {
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
                
                if(command.equalsIgnoreCase("logout") ) {
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

