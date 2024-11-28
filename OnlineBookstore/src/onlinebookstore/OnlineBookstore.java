/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package onlinebookstore;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OnlineBookstore {

    private static HashMap<String, User> users = new HashMap<>();
    private static final String FILE_NAME = "users.txt"; // File to store user data
    private static final Book[] books = {
        new Book("Harry Potter", "J.K. Rowling", "Fantasy", 4.8, "A young wizard embarks on an adventure.", 15.99),
        new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 4.4, "A tragic story of a millionaire's obsession.", 10.99),
        new Book("1984", "George Orwell", "Dystopian", 4.7, "A totalitarian regime monitors everything.", 12.50),
        new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 4.6, "A hobbit's journey to reclaim a treasure.", 14.75),
        new Book("Moby Dick", "Herman Melville", "Classic", 3.9, "A captain's obsession with hunting a white whale.", 9.99),
        new Book("To Kill a Mockingbird", "Harper Lee", "Classic", 4.9, "A young girl's journey through moral awakenings in the South.", 11.50),
        new Book("The Catcher in the Rye", "J.D. Salinger", "Classic", 4.0, "A teenager's critique of the adult world.", 10.49),
        new Book("Pride and Prejudice", "Jane Austen", "Romance", 4.5, "A romantic novel about manners and misunderstandings.", 13.00),
        new Book("The Alchemist", "Paulo Coelho", "Philosophical", 4.2, "A shepherd's journey to fulfill his dreams.", 10.00),
        new Book("Dune", "Frank Herbert", "Science Fiction", 4.6, "A young man’s destiny in an intergalactic struggle.", 18.00),
        new Book("Brave New World", "Aldous Huxley", "Dystopian", 4.3, "A world where happiness is mandatory, but at what cost?", 14.20),
        new Book("The Road", "Cormac McCarthy", "Post-apocalyptic", 4.1, "A father and son's survival journey in a desolate world.", 12.99),
        new Book("A Game of Thrones", "George R.R. Martin", "Fantasy", 4.7, "Noble houses vie for the Iron Throne.", 16.00),
        new Book("The Kite Runner", "Khaled Hosseini", "Drama", 4.8, "A tale of friendship, betrayal, and redemption.", 12.99),
        new Book("The Da Vinci Code", "Dan Brown", "Thriller", 4.0, "A professor unravels a conspiracy hidden in works of art.", 14.99)
};


    private static ArrayList<Book> cart = new ArrayList<>(); // Cart to hold selected books

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
                    browseBooks(scanner);
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

    private static void browseBooks(Scanner scanner) {
    System.out.println("How would you like to browse books?");
    System.out.println("1. By Category (Genre)");
    System.out.println("2. By Author");
    System.out.println("3. By Rating");
    System.out.println("4. Alphabetically (By Title)");
    System.out.print("Please choose an option (1-4): ");
    int browseChoice = scanner.nextInt();
    scanner.nextLine(); // Consume the newline

    switch (browseChoice) {
        case 1:
            browseByCategory(scanner);
            break;
        case 2:
            browseByAuthor(scanner);
            break;
        case 3:
            browseByRating(scanner);
            break;
        case 4:
            browseAlphabetically();
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
    }
}

    private static void browseByCategory(Scanner scanner) {
        System.out.println("Enter the genre you want to browse (e.g., Fantasy, Classic, Dystopian): ");
        String category = scanner.nextLine();
        ArrayList<Book> foundBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                foundBooks.add(book);
            }
        }

        if (foundBooks.isEmpty()) {
            System.out.println("No books found in the " + category + " category.");
            promptSearchAgain(scanner, () -> browseByCategory(scanner), () -> storeMenu(scanner));
        } else {
            System.out.println("Books in the " + category + " category:");
            for (Book book : foundBooks) {
                System.out.println(book);
            }
            viewBookDetails(scanner);
        }
    }

    private static void browseByAuthor(Scanner scanner) {
        System.out.println("Enter the author's name: ");
        String author = scanner.nextLine();
        ArrayList<Book> foundBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                foundBooks.add(book);
            }
        }

        if (foundBooks.isEmpty()) {
            System.out.println("No books found by author " + author + ".");
            promptSearchAgain(scanner, () -> browseByAuthor(scanner), () -> storeMenu(scanner));
        } else {
            System.out.println("Books by " + author + ":");
            for (Book book : foundBooks) {
                System.out.println(book);
            }
            viewBookDetails(scanner);
        }
    }

    private static void browseByRating(Scanner scanner) {
        System.out.print("Enter the minimum rating (1 to 5): ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline
        ArrayList<Book> foundBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getRating() >= rating) {
                foundBooks.add(book);
            }
        }

        if (foundBooks.isEmpty()) {
            System.out.println("No books found with a rating of " + rating + " or higher.");
            promptSearchAgain(scanner, () -> browseByRating(scanner), () -> storeMenu(scanner));
        } else {
            System.out.println("Books with a rating of " + rating + " or higher:");
            for (Book book : foundBooks) {
                System.out.println(book);
            }
            viewBookDetails(scanner);
        }
    }

    private static void promptSearchAgain(Scanner scanner, Runnable searchMethod, Runnable menuMethod) {
        System.out.println("\nWould you like to search again?");
        System.out.println("1. Yes");
        System.out.println("2. No, go back to the user menu");
        System.out.print("Please choose an option (1-2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        switch (choice) {
            case 1:
                searchMethod.run(); // Call the provided search method
                break;
            case 2:
                menuMethod.run(); // Call the provided menu method
                break;
            default:
                System.out.println("Invalid choice. Returning to the user menu.");
                menuMethod.run(); // Default to returning to the menu
        }
    }

    private static void browseAlphabetically() {
       // Create a sorted list of books by title
       Book[] sortedBooks = books.clone();
       java.util.Arrays.sort(sortedBooks, (b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));

       System.out.println("Books sorted alphabetically by title:");
       for (Book book : sortedBooks) {
           System.out.println(book); // Uses the `toString()` method for display
       }

       // Prompt the user to view details or go back
       Scanner scanner = new Scanner(System.in);
       System.out.println("\nWould you like to view details of a specific book?");
       System.out.println("1. Yes");
       System.out.println("2. No, go back to User Menu");
       System.out.print("Please choose an option (1-2): ");
       int choice = scanner.nextInt();
       scanner.nextLine(); // Consume the newline

       switch (choice) {
           case 1:
               viewBookDetails(scanner); // Allow the user to view book details
               break;
           case 2:
               // Return to user menu or previous menu
               break;
           default:
               System.out.println("Invalid choice. Returning to the user menu.");
       }
   }
       

    private static void viewBookDetails(Scanner scanner) {
        System.out.print("Enter the title of the book to view details: ");
        String bookTitle = scanner.nextLine();

        // Find the book by title
        Book book = null;
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(bookTitle)) {
                book = b;
                break;
            }
        }

        if (book != null) {
            System.out.println("\nBook Details:");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Category: " + book.getCategory());
            System.out.println("Rating: " + book.getRating());
            System.out.println("Description: " + book.getDescription());
            
            // Options after viewing book details
            System.out.println("\n1. Go Back to User Menu");
            System.out.println("2. Add to Cart");
            System.out.println("3. Buy Now");
            System.out.print("Please choose an option (1-3): ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (option) {
                case 1:
                    storeMenu(scanner); // Go back to store menu
                    break;
                case 2:
                    addToCart(book); // Add to cart
                    break;
                case 3:
                    buyNow(book); // Proceed to buy
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } else {
            System.out.println("Book not found. Please try again.");
        }
    }

    private static void addToCart(Book book) {
        cart.add(book);
        System.out.println(book.getTitle() + " has been added to your cart.");
    }

   private static void buyNow(Book book) {
    System.out.println("You are about to buy: " + book.getTitle());
    System.out.println("Price: $20.00"); // Assuming a fixed price for simplicity

    Scanner scanner = new Scanner(System.in);
    String paymentMethod = selectPaymentMethod(scanner);

    if (paymentMethod != null) {
        System.out.println("Payment method selected: " + paymentMethod);
        System.out.println("Processing payment...");
        System.out.println("Thank you for your purchase of " + book.getTitle() + "! Your order is being processed.");
    } else {
        System.out.println("Payment canceled. Returning to store menu.");
    }
}

    private static void viewCart() {
    if (cart.isEmpty()) {
        System.out.println("Your cart is empty.");
    } else {
        System.out.println("Your Cart: ");
        double total = 0;
        for (Book book : cart) {
            System.out.println(book.getTitle() + " - $" + book.getPrice());
            total += book.getPrice();
        }
        System.out.println("Total: $" + total);
        
        System.out.println("\n1. Proceed to Checkout");
        System.out.println("2. Go Back to Store Menu");
        System.out.print("Please choose an option (1-2): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                checkout();
                break;
            case 2:
                storeMenu(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

    private static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Cannot proceed with checkout.");
        } else {
            System.out.println("Proceeding to checkout...");
            double total = 0;
            for (Book book : cart) {
                total += 20; // Assuming a fixed price for simplicity
                System.out.println(book.getTitle() + " - $20.00");
            }
            System.out.println("Total: $" + total);

            // Select payment method
            Scanner scanner = new Scanner(System.in);
            String paymentMethod = selectPaymentMethod(scanner);

            if (paymentMethod != null) {
                System.out.println("Payment method selected: " + paymentMethod);
                System.out.println("Processing payment of $" + total + "...");
                System.out.println("Thank you for your purchase! Your order is being processed.");
                cart.clear(); // Clear the cart after checkout
            } else {
                System.out.println("Payment canceled. Returning to cart.");
            }
        }
    }

    private static String selectPaymentMethod(Scanner scanner) {
        System.out.println("\nSelect a payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Cash on Delivery");
        System.out.println("4. Cancel");
        System.out.print("Please choose an option (1-4): ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        switch (paymentChoice) {
            case 1:
                System.out.print("Enter your credit card number: ");
                String cardNumber = scanner.nextLine();
                System.out.println("Credit card ending in " + cardNumber.substring(cardNumber.length() - 4) + " selected.");
                return "Credit Card";
            case 2:
                System.out.print("Enter your PayPal email: ");
                String paypalEmail = scanner.nextLine();
                System.out.println("PayPal account " + paypalEmail + " selected.");
                return "PayPal";
            case 3:
                System.out.println("Cash on Delivery selected.");
                return "Cash on Delivery";
            case 4:
                System.out.println("Payment process canceled.");
                return null;
            default:
                System.out.println("Invalid choice. Please try again.");
                return selectPaymentMethod(scanner);
        }
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

class Book {
    private String title;
    private String author;
    private String category;
    private double rating;
    private String description;
    private double price; // New attribute

    public Book(String title, String author, String category, double rating, String description, double price) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price; // Getter for price
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Rating: " + rating + ", Price: $" + price;
    }
}


