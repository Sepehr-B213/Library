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

    public static int idReturnExecution(String query) {
        builtConnection();
        try {
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            closeConnection();
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public static ArrayList<Book> executeBook(String query) {
        builtConnection();
        ArrayList<Book> bookList = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setLibrarian_id(resultSet.getInt("librarian_id"));
                book.setIsAvailable(resultSet.getBoolean("isAvailable"));
                book.setBorrowedDate(resultSet.getDate("borrowedDate"));
                book.setExtended(resultSet.getBoolean("extended"));
                bookList.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
        return bookList;
    }

    public static ArrayList<Librarian> executeLibrarian(String query) {
        builtConnection();
        ArrayList<Librarian> librarianList = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Librarian librarian = new Librarian();
                librarian.setId(resultSet.getInt("id"));
                librarian.setPassword(resultSet.getString("password"));
                librarian.setName(resultSet.getString("name"));
                librarian.setLastName(resultSet.getString("lastname"));
                librarianList.add(librarian);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
        return librarianList;
    }
}