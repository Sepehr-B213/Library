package model;

import controller.DataBase;

import java.util.ArrayList;

public class Admin extends User{ // use id,name and password from User class

    public Admin() {
        super();
    } // 1st constructor

    public Admin(int id, String name, String password) {
        super(id, name, password);
    } // 2nd constructor

    public void add() { // this method add an Admin to mysql
        String query = String.format("insert into admin(password,name) values ('%s' , '%s')",
                this.getPassword(),this.getName());
        this.setId(DataBase.idReturnExecution(query)); // return the id of Admin in mysql for example : 9815
        //and set the id of object with that .
    }

    public static ArrayList<Admin> getAdmins(String condition) { // this method return an arrays of Admins
        // which have an specific property and it defined in the argument
        String query = "select * from admin" + condition;
        return DataBase.getAdminList(query);
    }

    public static Admin search(int id) { // search in mysql and find the target by id
        String condition = String.format(" where id = %d", id);
        ArrayList<Admin> adminList = getAdmins(condition); // an array of Admin where id = x .
        if(adminList.isEmpty()) {
            return null;
        } else {
            return adminList.get(0); // if Admin not found in the mysql return null , otherwise returns 1st in array
        }
    }

    public static ArrayList<Admin> search(String name) { // search in mysql and find the target by name
        String condition = String.format(" where name = '%s'", name); // an array of Admin where name = "Something" .
        return getAdmins(condition);
    }

    public void update() {
        String query = String.format("update admin set password = '%s', name = '%s' where id = %d",this.getPassword(), getName(), this.getId());
        DataBase.execution(query); // we can use it to update fields in mysql , here we can update
        // everything(id,name,pass) .
    }

    public void delete() {
        String query = String.format("delete from admin where id = %d", this.getId());
        DataBase.execution(query); // we can delete Admin by passing mysql an id .
    }

    public String toString() {
        String str = String.format("id: %d, name: %s, password: %s",
                this.getId(), this.getName(), this.getPassword());
        return str; // this method shows properties of Admin like : id = 4323 , name = Ali , Pass = jdbjs
    }
}