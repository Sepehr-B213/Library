package controller;

import model.Book;
import model.Librarian;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private static Connection connection;
    private static Statement statement;

    private DataBase() {
    }

    public static void builtConnection() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_DB",
                    "admin", "123456@s");
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void execution(String query) {
        builtConnection();
        try {
            statement.execute(query);
            closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int createLibrarian(Librarian librarian) throws SQLException {
        builtConnection();
        statement.execute
                (String.format("insert into user(password,firstName,lastName) values "
                        + "('%s' , '%s' , '%s' )", librarian.getPassword(), librarian.getName(), librarian.getLastName()));
        closeConnection();
        return 0;
    }

    public static int createBook(Book book) throws SQLException {
        builtConnection();
        statement.execute
                (String.format("insert into book(name,author) values ('%s' , '%s' )"
                        , book.getName(), book.getAuthor()));
        closeConnection();
        return 0;
    }
}