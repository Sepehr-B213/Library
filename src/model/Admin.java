package model;

import controller.DataBase;

import java.util.ArrayList;

public class Admin extends User{

    public Admin() {
        super();
    }

    public Admin(int id, String name, String password) {
        super(id, name, password);
    }

    public void add() {
        String query = String.format("insert into admin(password,name) values ('%s' , '%s')",
                this.getPassword(),this.getName());
        this.setId(DataBase.idReturnExecution(query));
    }

    public static ArrayList<Admin> getAdmins(String condition) {
        String query = "select * from admin" + condition;
        return DataBase.executeAdmin(query);
    }

    public static Admin search(int id) {
        String condition = String.format(" where id = %d", id);
        ArrayList<Admin> adminList = getAdmins(condition);
        if(adminList.isEmpty()) {
            return null;
        } else {
            return adminList.get(0);
        }
    }

    public static ArrayList<Admin> search(String name) {
        String condition = String.format(" where name = '%s'", name);
        return getAdmins(condition);
    }

    public void update() {
        String query = String.format("update admin set password = '%s', name = '%s' where id = %d",this.getPassword(), getName(), this.getId());
        DataBase.execution(query);
    }

    public void delete() {
        String query = String.format("delete from admin where id = %d", this.getId());
        DataBase.execution(query);
    }

    public String toString() {
        String str = String.format("id: %d, name: %s, password: %s",
                this.getId(), this.getName(), this.getPassword());
        return str;
    }
}