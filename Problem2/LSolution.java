import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LSolution {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        // initialise lists and seed
        Lib l = new Lib();

        Scanner sc = new Scanner(System.in);

        while (true) {

            // if(mainChoice.isEmpty())
            //     continue;    
            System.out.println("Plese Select an operation:");
            System.out.println("1. Add book to libary");
            System.out.println("2. Remove book in library");
            System.out.println("3. ListBooks in Library");
            System.out.println("4. Add Students ");
            System.out.println("5. Take book from library for student");
            System.out.println("6. Retuern book from library for student");
            String mainChoice = sc.nextLine();

            switch (mainChoice) {
                case "1":
                    System.out.println("Enter Book Name:");
                    String bname = sc.nextLine();
                    System.out.println("Enter Book gener:");
                    String gener = sc.nextLine();
                    System.out.println("Enter Book price:");
                    double price = sc.nextDouble();
                    System.out.println("Enter qty:");
                    int qty = sc.nextInt();
                    sc.nextLine();
                    l.addBook(bname, gener, price, qty);
                    break;
                case "2":
                    System.out.println("Enter book id:");
                    int rmBookId = sc.nextInt();
                    System.out.println("Enter qty:");
                    qty = sc.nextInt();
                    sc.nextLine();
                    l.rmBook(rmBookId, qty);
                    break;
                case "3":
                    l.displayBooks();
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                default:
                    System.out.println("Invalid Main Menu choice, please try again.");
                    break;
            }
        }
    }
}

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Book {
    int id;
    String name;
    String gener;
    double price;
    int qty;
       
    Book(int id, String name, String gener, double price, int qty) {
        this.id = id;
        this.name = name;
        this.gener = gener;
        this.price = price;
        this.qty = qty;

    }
}

class IssuedBook {
    Date issueDate;
    Date expectedReturnDate;
    Date actualReturnDate;
    Student issuedTo;
    Book book;

    IssuedBook(Date issueDate, Date expectedReturnDate, Date actualReturnDate, Student issuedTo, Book book) {
        this.issueDate = issueDate;
        this.expectedReturnDate = expectedReturnDate;
        this.actualReturnDate = actualReturnDate;
        this.actualReturnDate = actualReturnDate;
        this.issuedTo = issuedTo;
        this.book = book;
    }

}

class StudentRec {
    Student std;
    ArrayList<IssuedBook> issuedBooks;

    StudentRec(ArrayList<IssuedBook> issuedBooks, Student std) {
        this.issuedBooks = issuedBooks;
        this.std = std;
    }
}

class Lib {
    ArrayList<Book> books;
    ArrayList<StudentRec> members;
    final static int MAX_BOOKS = 200;
    final static int MAX_MEMBERS = 20;

    Lib() {
        this.books = new ArrayList<Book>();
        this.members = new ArrayList<StudentRec>();
    }
    

    void addBook(String name, String gener, double price, int qty) {
        Book book = getBook(name);
        int totalBooks = getTotalBooksInLib();

        if (totalBooks + qty > MAX_BOOKS) {
            System.out.println("Max Book quatity reached.Can add " + (MAX_BOOKS - totalBooks));
            return;
        }
        if (book == null && totalBooks + qty < MAX_BOOKS) {
            book = new Book(Util.getRandomId(), name, gener, price, qty);
            books.add(book);
            return;
        }
        book.qty += qty;
    }

    void rmBook(int id, int qty) {
        Book book = getBook(id);

        if (book == null) {
            System.out.println("Book with id " + id + " not found");
            return;
        }
        
        if (book.qty < qty) {
            System.out.println("Invalid quatity. Only  " +book.qty+" books available.");
            return;
        }

        if (book.qty == qty) {
            books.remove(book);
            return;
        }
        book.qty -= qty;

    }    
    
    void displayBooks() {
        for (Book book : books) {
            System.out.println("" +book.id+"");    
            System.out.println("" +book.name+"");                
            System.out.println("" +book.gener+"");    
            System.out.println("" +book.price+"");        
            System.out.println("" +book.qty+""); 
            System.out.println("----------------"); 

        }
    }

    private Book getBook(String name) {
        for (Book book : books) {
            if (book.name == name)
                return book;
        }
        return null;
    }

    private Book getBook(int id) {
        for (Book book : books) {
            if (book.id == id)
                return book;
        }
        return null;
    }

    private int getTotalBooksInLib() {
        int totalBooks = 0;
        for (Book b : books) {
            totalBooks += b.qty;
        }
        return totalBooks;
    }
}

class Util {
    static int getRandomId() {            
        return (int)(Math.random() * 1000);
    }
}