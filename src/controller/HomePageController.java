package controller;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import model.Librarian;
import model.Relevant;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomePageController extends MainController implements Initializable {

    @FXML
    private VBox homeChildrenPNL;

    @FXML
    private Button deleteBookBTN;

    @FXML
    private Button addBookBTN;

    @FXML
    private JFXTextField homeSearchFLD;

    @FXML
    private Button homeSearchBTN;

    @FXML
    private JFXComboBox<String> homeSearchCMB;

    @FXML
    private JFXCheckBox homeCHB;

    @FXML
    private Button borrowBookBTN;

    static Stage stage = null;

    static int selectedRaw = -1;

    static ArrayList<HBox> hBoxes = new ArrayList<>();

    static ArrayList<Book> books = new ArrayList<>();

    static ArrayList<Book> isAvailableBooks = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        access();

        homeSearchCMB.getItems().addAll("نام کتاب", "شماره کتاب", "نویسنده");
        homeSearchCMB.setValue("نام کتاب");

        books = Book.getBooks("");
        setIsAvailableBooks();
        rawCreator(books);

        homeSearchBTN.setOnAction(event -> {
            if(!homeSearchCMB.getValue().isEmpty()) {
                homeCHB.setSelected(false);
                homeChildrenPNL.getChildren().removeAll(hBoxes);
                books = searchBook(homeSearchCMB.getValue());
                setIsAvailableBooks();
                rawCreator(books);
            }
        });

        deleteBookBTN.setOnAction(event -> {
            if(selectedRaw >= 0) {
                int bookId = getRaw(hBoxes.get(selectedRaw)).getId();
                deleteBookFromArrayLists();
                Book.delete(bookId);
                selectedRaw = -1;

                homeChildrenPNL.getChildren().removeAll(hBoxes);
                if (homeCHB.isSelected()) {
                    if (!isAvailableBooks.isEmpty())
                        rawCreator(isAvailableBooks);
                }
                else {
                    if (!books.isEmpty())
                        rawCreator(books);
                }
            }
        });

        addBookBTN.setOnAction(event -> {
            if(stage == null) {
                try {
                    stage = show(new Stage(), load("../view/AddBookPage.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        homeCHB.setOnAction(event -> {
            homeChildrenPNL.getChildren().removeAll(hBoxes);
            if (homeCHB.isSelected()) {
                if (!isAvailableBooks.isEmpty())
                    rawCreator(isAvailableBooks);
            }
            else {
                if (!books.isEmpty())
                    rawCreator(books);
            }
        });

        borrowBookBTN.setOnAction(event -> {
            if(selectedRaw >= 0) {
                Book book = getRaw(hBoxes.get(selectedRaw));
                if(book.getIsAvailable())
                    book.lend(Relevant.user.getId());

                homeChildrenPNL.getChildren().removeAll(hBoxes);
                hBoxes.clear();
                if (homeCHB.isSelected()) {
                    isAvailableBooks.get(selectedRaw).lend(Relevant.user.getId());
                    isAvailableBooks.remove(selectedRaw);
                    selectedRaw = -1;
                    if (!isAvailableBooks.isEmpty())
                        rawCreator(isAvailableBooks);
                }
                else {
                    books.get(selectedRaw).lend(Relevant.user.getId());
                    setIsAvailableBooks();
                    selectedRaw = -1;
                    rawCreator(books);
                }
            }
        });


    }

    public void rawCreator(ArrayList<Book> bookArrayList) {
        hBoxes.clear();
        int i = 0;
        for(Book book : bookArrayList) {
            try {
                hBoxes.add((HBox) load("../view/BookTRaw.fxml"));
                setRaw(hBoxes.get(i), book);
                homeChildrenPNL.getChildren().add(hBoxes.get(i));
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        rawsSetEvent();
    }

    public void setRaw(HBox hBox, Book book) {
        ((Label)hBox.getChildren().get(1)).setText(book.getName());
        ((Label)hBox.getChildren().get(2)).setText(book.getAuthor());
        ((Label)hBox.getChildren().get(3)).setText(book.getId()+"");
        ((Label)hBox.getChildren().get(4)).setText(book.getIsAvailable() ? "دردسترس" : "امانت شده");
    }

    public Book getRaw(HBox hBox) {
        return Book.searchByName(((Label)hBox.getChildren().get(1)).getText());
    }

    public void rawsSetEvent() {
        for(int i = 0; i < hBoxes.size(); i++)
            rawsSetEventHelper(i);
    }

    public void rawsSetEventHelper(int i) {
        hBoxes.get(i).setOnMouseEntered(event -> {
            hBoxes.get(i).setStyle("-fx-background-color : #0A0E3F");
        });

        hBoxes.get(i).setOnMouseExited(event -> {
            if(i != selectedRaw)
                hBoxes.get(i).setStyle("-fx-background-color : #02030A");
        });

        hBoxes.get(i).setOnMouseClicked(event -> {
            if(selectedRaw >= 0)
                hBoxes.get(selectedRaw).setStyle("-fx-background-color : #02030A");
            selectedRaw = i;
            hBoxes.get(i).setStyle("-fx-background-color : #0A0E3F");
        });
    }

    public ArrayList<Book> searchBook(String combo) {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        switch(combo) {
            case "نام کتاب" :
                bookArrayList.add(Book.searchByName(homeSearchFLD.getText()));
                break;

            case "شماره کتاب" :
                int id = convertInt(homeSearchFLD.getText());
                bookArrayList.add(Book.searchById(id));
                break;

            case "نویسنده" :
                bookArrayList = Book.search(homeSearchFLD.getText());
                break;

            default:
                break;
        }
        return bookArrayList;
    }

    public int convertInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            return 0 ;
        }
    }

    public void setIsAvailableBooks() {
        isAvailableBooks.clear();
        if(books.size() > 0) {
            for (Book book : books) {
                if (book.getIsAvailable())
                    isAvailableBooks.add(book);
            }
        }
    }

    public void deleteBookFromArrayLists() {
        if(homeCHB.isSelected()) {
            books.remove(isAvailableBooks.get(selectedRaw));
            isAvailableBooks.remove(selectedRaw);
        }
        else {
            if(!isAvailableBooks.isEmpty())
                isAvailableBooks.remove(books.get(selectedRaw));
            books.remove(selectedRaw);
        }
    }

    public void refresh() {
        stage = null;
        selectedRaw = -1;
        books = Book.getBooks("");
        setIsAvailableBooks();
        rawCreator(books);
    }

    public void access() {
        if(Relevant.user instanceof Librarian)
            deleteBookBTN.setVisible(false);
        else
            borrowBookBTN.setVisible(false);
    }
}
