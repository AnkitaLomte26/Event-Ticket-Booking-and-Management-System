import java.util.HashMap;
import java.util.Scanner;


public class AuthenticationSystem {
    private HashMap<String , User> users = new HashMap<>();
    User loggedInUser = null;

    public void signUp(Scanner scanner) {
        
    
        System.out.println("\n===== Sign Up =====");
    
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
    
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

    
        if (users.containsKey(email)) {
            System.out.println("Email is already registered. Try logging in.");
            return;
        }
    
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
    
        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine();
    
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Signup failed.");
            return;
        }
    
        System.out.print("Enter Phone Number: ");
        String ph_no = scanner.nextLine().trim(); // Remove unnecessary nextLine()

        if (!ph_no.matches("\\d{10}")) {
            System.out.println("Invalid phone number. It should be 10 digits.");
            return;
        }
    
        User newUser = new User(username, email, password, ph_no);
        users.put(email, newUser);
    
        System.out.println("Sign Up successful! You can now log in.");
    }
    

    public User getLoggedInUser() {
        return loggedInUser;
        
    }


    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        
    }


    public void login(Scanner scanner){
       
        System.out.println("\n =====Login=====");

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if(users.containsKey(email)){
            User user = users.get(email);
            if(user.password.equals(password)){
                setLoggedInUser(user);
                System.out.println("Login successful! Welcome," + getLoggedInUser().username + "!");
            }
            else{
                System.out.println("Incorrect password.Try again.");
            }
        }
            else{
                System.out.println("Email not registered.Please sign up.");
            }
    }

    public void forgotPassword(Scanner scanner) {
        
        System.out.println("==== Forgot Password ====");
    
        System.out.println("Enter your registered email:");
        String email = scanner.nextLine();
    
        // Check if email exists
        if (!users.containsKey(email)) {
            System.out.println("No account found with this email.");
            return;
        }
    
        User user = users.get(email);
    
        System.out.println("Enter your registered phone number:");
        String ph_no = scanner.nextLine().trim();
    
        // Correct string comparison
        if (!user.ph_no.equals(ph_no)) {
            System.out.println("Phone number does not match our records. Cannot reset password.");
            return;
        }
    
        System.out.print("Enter your new password: ");
        String newPassword = scanner.nextLine();
    
        System.out.print("Confirm your new password: ");
        String confirmPassword = scanner.nextLine();
    
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Password reset failed.");
            return;
        }
    
        // Update password
        user.password = newPassword;
        System.out.println("Password reset successful! You can now log in with your new password.");
    }
    
    
            
        public void logout(){
                if(getLoggedInUser()!=null){
                    System.out.println("Logged out successfully.Goodbye,"+getLoggedInUser().username+"!");
                    setLoggedInUser(null);
                }
                else{
                    System.out.println("No user is currently logged in.");
                }
            }
        public boolean isLoggedIn(){
            return getLoggedInUser() != null;
        }
    }


