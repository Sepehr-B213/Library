package model;

import java.sql.Date;

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
}