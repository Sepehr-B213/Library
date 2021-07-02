package model;


public class Librarian extends Admin {
    private String lastName;

    public Librarian() {
        this(0, "", "", "");
    }

    public Librarian(int id, String name, String password, String lastName) {
        super(id, name, password);
        this.lastName = lastName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    }