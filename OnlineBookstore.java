/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package onlinebookstore;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class OnlineBookstore {

    // Storing user data with username as the key, and other details as values.
    private static HashMap<String, User> users = new HashMap<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("===== Online Bookstore =====");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.println("3. Exit");
            System.out.print("Please choose an option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    signup(scanner);
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting the bookstore. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
        scanner.close();
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            System.out.println("Login successful! Welcome to the online bookstore.");
            storeMenu(scanner); // Redirect to the store menu after login
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void signup(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please try a different username.");
            return;
        }

        // Gathering additional information for registration
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your contact number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter your home address: ");
        String address = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Confirm your password: ");
        String confirmPassword = scanner.nextLine();

        // Checking if the passwords match
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Please try again.");
            return;
        }

        // Creating a new user object and storing it in the users HashMap
        User newUser = new User(username, name, contact, email, address, password);
        users.put(username, newUser);

        System.out.println("Account successfully made!");
    }

    private static void storeMenu(Scanner scanner) {
        boolean storeRunning = true;

        while (storeRunning) {
            System.out.println("\n===== Store Menu =====");
            System.out.println("1. Browse Books");
            System.out.println("2. View Cart");
            System.out.println("3. Logout");
            System.out.print("Please choose an option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    browseBooks();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    storeRunning = false;
                    System.out.println("You have been logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static void browseBooks() {
        // For now, we'll just display a placeholder message for browsing books
        System.out.println("Browsing books...");
        // Later you can add more functionality here such as displaying a list of books from a collection
    }

    private static void viewCart() {
        // Placeholder message for viewing the cart
        System.out.println("Your cart is currently empty.");
        // You can later add functionality to manage the shopping cart
    }
}

// User class to store detailed user information
class User {
    private String username;
    private String name;
    private String contact;
    private String email;
    private String address;
    private String password;

    public User(String username, String name, String contact, String email, String address, String password) {
        this.username = username;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
