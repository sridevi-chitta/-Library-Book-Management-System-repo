import java.util.ArrayList;
import java.util.Scanner;

// Model class for Book
class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;

    public Book(String id, String title, String author, String genre, String availabilityStatus) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailabilityStatus(String status) {
        this.availabilityStatus = status;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Availability: " + availabilityStatus;
    }
}

// Library management system
public class LibraryManagementSystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID or Title");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewAllBooks();
                case 3 -> searchBook();
                case 4 -> updateBook();
                case 5 -> deleteBook();
                case 6 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        if (getBookById(id) != null) {
            System.out.println("Book ID must be unique. Try again.");
            return;
        }
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        if (author.isEmpty()) {
            System.out.println("Author cannot be empty.");
            return;
        }
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Availability Status (Available/Checked Out): ");
        String status = scanner.nextLine();
        if (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Checked Out")) {
            System.out.println("Invalid availability status. Use 'Available' or 'Checked Out'.");
            return;
        }

        books.add(new Book(id, title, author, genre, status));
        System.out.println("Book added successfully!");
    }

    private static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\nBook List:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void searchBook() {
        System.out.print("Enter Book ID or Title to search: ");
        String query = scanner.nextLine();
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(query) || book.getTitle().equalsIgnoreCase(query)) {
                System.out.println("Book Found: " + book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        String id = scanner.nextLine();
        Book book = getBookById(id);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        System.out.print("Enter new Title (leave blank to keep current): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) book.setTitle(title);

        System.out.print("Enter new Author (leave blank to keep current): ");
        String author = scanner.nextLine();
        if (!author.isEmpty()) book.setAuthor(author);

        System.out.print("Enter new Genre (leave blank to keep current): ");
        String genre = scanner.nextLine();
        if (!genre.isEmpty()) book.setGenre(genre);

        System.out.print("Enter new Availability Status (Available/Checked Out): ");
        String status = scanner.nextLine();
        if (status.equalsIgnoreCase("Available") || status.equalsIgnoreCase("Checked Out")) {
            book.setAvailabilityStatus(status);
        } else {
            System.out.println("Invalid status. Keeping current status.");
        }
        System.out.println("Book details updated successfully!");
    }

    private static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String id = scanner.nextLine();
        Book book = getBookById(id);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        books.remove(book);
        System.out.println("Book deleted successfully!");
    }

    private static Book getBookById(String id) {
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id)) {
                return book;
            }
        }
        return null;
    }
}
