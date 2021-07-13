/*
        dar in kelas vizhegi haye moshtarak az jomle id,name & password va hamintor method haye setter , getter
        baraye in vizhegi ha tarif shode ast ke kelas haye Admin & Librariaan az in super class ers bari darand.

 */

package model;

public class User {

    private int id ;
    private String name;
    private String password ;

    public User() {
        this(0, "", "");
    } // 1st constructor

    public User(int id, String name, String password) { // 2nd constructor
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
    }

    // setter and getter methods :
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