public class AccessControlTest{
    /*
     * Testing main. Runs each test and prints which (if any) failed.
     */
    public static void main(String[] args) {
      int fails = 0;
      if (!testLogin1()) {
        System.out.println("testLogin1 [bad username] failed");
        fails++;
      }
      if (!testAddUser1()) {
        System.out.println("testLogin2 [good login] failed");
        fails++;
      }
      if (!testResetPassword()) {
          System.out.println("testResetPassword failed");
          fails++;
      }

      if (fails == 0)
        System.out.println("All tests passed!");
    }

    private static boolean testLogin3() {
        return false;
    }

    private static boolean testLogin2() {
        return false;
    }

    /*
     * This test tries to log in a user that doesn't exist
     * @return boolean test passed
     */
    public static boolean testLogin1() {
      AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users initialized
      String user = "SomeHowSomeWay";
      String password = "password";
      return !AccessControl.isValidLogin(user, password); // isValidLogin should return false
    }
    
    /*
     * Create a new AccessControl and do not log in an admin.
     * Verify that addUser(String username) returns false
     * and that the new user is not added.
     * @return boolean test passed
     */
    public static boolean testAddUser1() {
      AccessControl ac = new AccessControl();
      String user = "alexi";
      
      boolean addUserReport = ac.addUser(user);
      if (addUserReport)
        return false; // addUserReport should be false
      // Make sure user wasn't added anyway
      return !AccessControl.isValidLogin(user, "changeme");
    }
    
    /**
     * Goal is to reset a users password using a user with admin power
     * 
     * @return boolean test passed
     */
    public static boolean testResetPassword() {
        AccessControl ac = new AccessControl();
        ac.setCurrentUser("admin");
        ac.addUser("MoryK");
        ac.setCurrentUser("MoryK");
        ac.changePassword("password");
        ac.setCurrentUser("admin");
        if (ac.resetPassword("MoryK")) {
            return true;
        } else {
            return false;
        }

    }
    /**
     * Goal is to reset a users password using a user with admin power
     * 
     * @return boolean test passed
     */
    public static boolean testTakeAdmin() {
        AccessControl ac = new AccessControl();
        ac.setCurrentUser("admin");
        ac.addUser("MoryK", true);
        if (ac.takeAdmin("MoryK")) {
            return true;
        } else {
            return false;
        }

    }
}
