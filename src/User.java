
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

