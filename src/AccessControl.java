import java.util.ArrayList;

public class AccessControl {
    ArrayList<User> users = new ArrayList<User>(); // An ArrayList of valid users
    String currentUser; // Who is currently logged in, if anyone?
    String defaultPassword; // Default password given to new users or when
    // we reset a user's password.

    public AccessControl() {

    } // A no-parameter constructor

    public static void main(String[] args) {
        AccessControl NewControl = new AccessControl();
    } // A STATIC method that creates a new AccessControl object
      // An implementation for this method is provided below

   public boolean isValidLogin(String username, String password) {
       for(int i = 0; i < users.size(); i++) {
           if (users.get(i).getUsername() == username &&  )
       }
      
    } // Report whether a
      // given username/password pair is a valid login

    public void logout() {
        currentUser = null;
    } // Log out the current user

    public String changePassword(String newPassword) {
        return newPassword;
    } // Change the current user's password

    public void addUser(String username) {
        user.add(; 
    } // Create a new user (non-admin)

    public addUser(String username, boolean isAdmin) {
        
    } // Create a new user
      // and specify their admin status

    public void removeUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).compareTo(username) == 0) {
                users.remove(i);
            }
        }
    } // Remove a user

    public void giveAdmin(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).compareTo(username) == 0) {

            }
        }
    } // Give a user admin power

    takeAdmin(String username) {
        
    } // Remove a user's admin power

    resetPassword(String username) {
        
    } // Reset a user's password
}
