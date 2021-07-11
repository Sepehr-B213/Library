package model;

public class User {

    private int id ;
    private String name;
    private String password ;

    public User() {
        this(0, "", "");
    }

    public User(int id, String name, String password) {
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}