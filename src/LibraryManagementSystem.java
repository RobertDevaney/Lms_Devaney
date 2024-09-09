import java.util.*;
import java.io.*;

/**
 * Robert Devaney
 * CEN-3024C-15339
 * September 9, 2024
 * LibraryManagementSystem.java
 * This application manages a collection of books within a library. It allows the addition, removal, and display of books through a console interface.
 */

public class LibraryManagementSystem {
    private final Map<Integer, Book> books = new HashMap<>();

    /**
     * Method: main
     * The entry point of the program. Handles the user interaction for managing books.
     */

    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        // Prompting user for the file path initially
        System.out.print("Please enter the path to the books file: ");
        String filePath = scanner.nextLine();
        system.loadBooksFromFile(filePath); // Load books from user-specified file

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display All Books");
            System.out.println("2. Remove a Book by ID");
            System.out.println("3. Add a Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = system.safeNextInt(scanner);
            if (choice == -1) continue; // Error in input, skip to next iteration

            switch (choice) {
                case 1 -> system.displayBooks();
                case 2 -> {
                    system.removeBookById(scanner);
                    system.saveBooksToFile(filePath); // Save updates to file
                }
                case 3 -> {
                    system.addBook(scanner);
                    system.saveBooksToFile(filePath); // Save updates to file
                }
                case 4 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid option, please try again.");
            }
        } while (choice != 4);

        scanner.close();
        System.out.println("Program exited successfully.");
    }

    /**
     * Method: safeNextInt
     * Safely reads an integer from the scanner, handling any input mismatches.
     * Parameter scanner The scanner to read from.
     * Return int The integer read, or -1 if there was an error.
     */

    private int safeNextInt(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear buffer
            return -1;
        }
    }


    private void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books to display.");
        } else {
            books.values().forEach(System.out::println);
        }
    }
    /**
     * Method: addBook
     * Adds a new book to the library system.
     * Parameter to read book details from the user.
     */
    private void addBook(Scanner scanner) {
        System.out.print("Enter book ID: ");
        int id = safeNextInt(scanner);
        if (id == -1) return; // Error in input
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        if (books.containsKey(id)) {
            System.out.println("A book with this ID already exists.");
        } else {
            books.put(id, new Book(id, title, author));
            System.out.println("Book added successfully.");
        }
    }
    /**
     * Method: removeBookById
     * Removes a book from the library by its ID.
     * Parameter to read the book ID from the user.
     */

    private void removeBookById(Scanner scanner) {
        System.out.print("Enter the ID of the book to remove: ");
        int id = safeNextInt(scanner);
        if (id == -1) return; // Error in input
        if (books.containsKey(id)) {
            books.remove(id);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    /**
     * Method: loadBooksFromFile
     * Loads books from a specified file into the library system.
     * Parameter fileName The path to the file containing book data.
     */
    private void loadBooksFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    books.put(id, new Book(id, title, author));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing ID from file.");
        }
    }
    /**
     * Method: saveBooksToFile
     * Saves the current state of books in the library to a file.
     * Parameter fileName The path to the file where books will be saved.
     */

    private void saveBooksToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Book book : books.values()) {
                writer.println(book.getId() + "," + book.getTitle() + "," + book.getAuthor());
            }
        } catch (IOException e) {
            System.out.println("Failed to save books.");
        }
    }
}