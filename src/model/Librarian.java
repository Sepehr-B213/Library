package model;

import controller.DataBase;

import java.util.ArrayList;


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



    public void add() {
        String query = String.format("insert into librarian(password,name,lastName) values ('%s' , '%s' , '%s')",
                this.getPassword(),this.getName(),this.getLastName());
        DataBase.execution(query);
    }

    public static ArrayList<Librarian> getLibrarians(String condition) {
        String query = "select * from librarian";
        return DataBase.executeLibrarian(query);
    }

    public static Librarian search(int id) {
        String condition = String.format(" where id = %d", id);
        ArrayList<Librarian> librarianList = getLibrarians(condition);
        if(librarianList.isEmpty()) {
            return null;
        } else {
            return librarianList.get(0);
        }
    }

    public static ArrayList<Librarian> search(String name, String lastName) {
        String condition = String.format(" where name = '%s' and lastname = '%s'", name, lastName);
        return getLibrarians(condition);
    }

    public void update() {
        String query = String.format("update librarian set password = '%s' where id = %d",this.getPassword(), this.getId());
        DataBase.execution(query);
    }

    public String toString() {
        String str = String.format("id: %d, name: %s, lastname: %s, password: %s",
                this.getId(), this.getName(), this.getLastName(), this.getPassword());
        return str;
    }
}