/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package onlinebookstore;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class OnlineBookstore {

    private static HashMap<String, User> users = new HashMap<>();
    private static final String FILE_NAME = "users.txt"; // File to store user data

    public static void main(String[] args) {
        loadUsersFromFile(); // Load users from the file when the program starts

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
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
                System.out.println("Login successful! Welcome to the online bookstore.");
                loggedIn = true;
                storeMenu(scanner); // Redirect to the store menu
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }
    }

    private static void signup(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please try a different username.");
            return;
        }

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

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Please try again.");
            return;
        }

        User newUser = new User(username, name, contact, email, address, password);
        users.put(username, newUser);

        saveUserToFile(newUser); // Save the new user to the file
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
        System.out.println("Browsing books...");
    }

    private static void viewCart() {
        System.out.println("Your cart is currently empty.");
    }

    private static void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // Assuming CSV format
                String username = data[0];
                String name = data[1];
                String contact = data[2];
                String email = data[3];
                String address = data[4];
                String password = data[5];

                User user = new User(username, name, contact, email, address, password);
                users.put(username, user); // Add the user to the HashMap
            }
        } catch (FileNotFoundException e) {
            // File not found; no users to load
            System.out.println("No existing user data found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error reading user data: " + e.getMessage());
        }
    }

    private static void saveUserToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(user.toCSV() + "\n"); // Write the user data in CSV format
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }
}

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

    public String toCSV() {
        return username + "," + name + "," + contact + "," + email + "," + address + "," + password;
    }
}
