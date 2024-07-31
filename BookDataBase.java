
import java.util.*;

public class BookDataBase {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int nextId = 1;
        
        books.add(new Book(nextId++, "The Catcher in the Rye", "J.D. Salinger", "Little, Brown and Company", 299.99));
        books.add(new Book(nextId++, "To Kill a Mockingbird", "Harper Lee", "J.B. Lippincott & Co.", 199.99));
        books.add(new Book(nextId++, "1984", "George Orwell", "Secker & Warburg", 249.99));
        books.add(new Book(nextId++, "Pride and Prejudice", "Jane Austen", "T. Egerton", 159.99));
        
        ArrayList<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparingDouble(book -> book.price));
        
        System.out.println("Books Sorted by Price:");
        for (Book book : sortedBooks) {
            System.out.println(book);
        }
        
        System.out.println("\nEnter the Author Name:");
        String author = sc.nextLine();
        ArrayList<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.author.equalsIgnoreCase(author)) {
                authorBooks.add(book);
            }
        }
        System.out.println("Books by " + author + ":");
        for (Book book : authorBooks) {
            System.out.println(book);
        }
        
        System.out.println("\nEnter Price:");
        double price = sc.nextDouble();
        ArrayList<Book> expensiveBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.price > price) {
                expensiveBooks.add(book);
            }
        }
        System.out.println("Books with price greater than " + price + ":");
        for (Book book : expensiveBooks) {
            System.out.println(book);
        }
        
        sc.nextLine(); 
        
        System.out.println("\nEnter the Publisher Name:");
        String publisher = sc.nextLine();
        ArrayList<Book> publisherBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.publisher.equalsIgnoreCase(publisher)) {
                publisherBooks.add(book);
            }
        }
        System.out.println("Books by publisher " + publisher + ":");
        for (Book book : publisherBooks) {
            System.out.println(book);
        }
        
        sc.close();
    }
}

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
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publisher=" + publisher + ", price=" + price + "]";
    }
}
