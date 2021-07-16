package model;

import controller.DataBase;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Book {
    private int id;
    private String name;
    private String author;
    private int librarian_id;
    private boolean isAvailable;
    private Date borrowedDate;
    private boolean extended;

    public Book() {
        this(0, "", "", 0, true, null, false);
    }

    public Book(int id, String name, String author, int librarian_id, boolean isAvailable, Date borrowedDate, boolean extended) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.librarian_id = librarian_id;
        this.isAvailable = isAvailable;
        this.borrowedDate = borrowedDate;
        this.extended = extended;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLibrarian_id() {
        return librarian_id;
    }

    public void setLibrarian_id(int librarian_id) {
        this.librarian_id = librarian_id;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public boolean getExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public void add() {
        String query = String.format("insert into book(name,author) values ('%s' , '%s')"
                ,this.getName(),this.getAuthor());
        this.setId(DataBase.idReturnExecution(query));
    }

    public static ArrayList<Book> getBooks(String condition) {
        String query = "select * from Book" + condition;
        return DataBase.getBookList(query);
    }

    public static Book searchByName(String name) {
        String condition = String.format(" where name = '%s'", name);
        ArrayList<Book> bookList = getBooks(condition);
        if(bookList.isEmpty()) {
            return null;
        } else {
            return bookList.get(0);
        }
    }

    public static Book searchById(int id) {
        String condition = String.format(" where id = '%d'", id);
        ArrayList<Book> bookList = getBooks(condition);
        if(bookList.isEmpty()) {
            return null;
        } else {
            return bookList.get(0);
        }
    }

    public static ArrayList<Book> search(String author) {
        String condition = String.format(" where author = '%s'", author);
        return getBooks(condition);
    }

    public static ArrayList<Book> search(Date date1, Date date2) {
        String condition = String.format(" where  borrowedDate >= '%s' and borrowedDate <= '%s'", date1, date2);
        return getBooks(condition);
    }

    public static ArrayList<Book> search(Date date) {
        return search(date, date);
    }

    public static ArrayList<Book> search(int librarian_id) {
        String condition = String.format(" where librarian_id = %d", librarian_id);
        return getBooks(condition);
    }

    public static ArrayList<Book> search(Boolean isAvailable) {
        String condition = String.format(" where isAvailable = %b", isAvailable);
        return getBooks(condition);
    }

    public void update() {
        String query;
        if(librarian_id == 0) {
            query = String.format("update book set librarian_id = null, isAvailable = %b ,"+
                            "borrowedDate = %s , extended = %b where id = %d",
                    this.isAvailable, this.borrowedDate, this.extended, this.id);
        } else {
            query = String.format("update book set librarian_id = %d, isAvailable = %b ,"+
                            "borrowedDate = '%s' , extended = %b where id = %d",
                    this.librarian_id, this.isAvailable, this.borrowedDate, this.extended, this.id);
        }

        DataBase.execution(query);
    }

    public void lend(int librarian_id) {
        this.librarian_id = librarian_id;
        this.isAvailable = false;
        this.borrowedDate = Date.valueOf(LocalDateTime.now().toLocalDate());
        this.extended = false;
        this.update();
    }

    public void returnBook() {
        this.librarian_id = 0;
        this.isAvailable = true;
        this.borrowedDate = null;
        this.extended = false;
        this.update();
    }

    public boolean extend() {
        if(extended) {
            return false;
        } else {
            this.extended = true;
            this.update();
            return true;
        }
    }

    public static void delete(int id) {
        String query = String.format("delete from book where id = %d", id);
        DataBase.execution(query);
    }

    public String toString() {
        String str = String.format("id: %d, name: %s, author: %s, librarian_id: %d, isAvailable: %b" +
                        ", borrowedDate: %s, extended: %b",
                this.id, this.name, this.author, this.librarian_id, this.isAvailable, this.borrowedDate, this.extended);
        return str;
    }
}