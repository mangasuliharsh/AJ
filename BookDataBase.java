
import java.util.*;


class Book {
    int id;
    String title;
    String author;
    String publisher;
    double price;

    public Book(int id, String title, String author, String publisher, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book [ID=" + id + ", Title=" + title + ", Author=" + author + ", Publisher=" + publisher + ", Price=" + price + "]";
    }
}

public class BookDatabaseApp {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int nextId = 1;

        
        books.add(new Book(nextId++, "The Catcher in the Rye", "J.D. Salinger", "Little, Brown and Company", 299.99));
        books.add(new Book(nextId++, "To Kill a Mockingbird", "Harper Lee", "J.B. Lippincott & Co.", 199.99));
        books.add(new Book(nextId++, "1984", "George Orwell", "Secker & Warburg", 249.99));
        books.add(new Book(nextId++, "Pride and Prejudice", "Jane Austen", "T. Egerton", 159.99));

        
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparingDouble(book -> book.price));
        System.out.println("Books sorted by price:");
        for (Book book : sortedBooks) {
            System.out.println(book);
        }

        
        System.out.print("\nEnter author name to search: ");
        String author = scanner.nextLine();
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.author.equalsIgnoreCase(author)) {
                authorBooks.add(book);
            }
        }
        System.out.println("Books by " + author + ":");
        for (Book book : authorBooks) {
            System.out.println(book);
        }

        
        System.out.print("\nEnter price to filter books: ");
        double price = scanner.nextDouble();
        List<Book> expensiveBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.price > price) {
                expensiveBooks.add(book);
            }
        }
        System.out.println("Books with price greater than " + price + ":");
        for (Book book : expensiveBooks) {
            System.out.println(book);
        }

        scanner.close();
    }
}

