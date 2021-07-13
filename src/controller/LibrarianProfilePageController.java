package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Book;
import model.Librarian;
import model.Relevant;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LibrarianProfilePageController extends MainController implements Initializable {

    @FXML
    private VBox librarianReportChPNL;

    @FXML
    private Label librarianNameLBL;

    @FXML
    private Label librarianIdLBL;

    @FXML
    private Button returnBookBTN;

    static Librarian librarian;

    static int selectedRaw = -1;

    static ArrayList<HBox> hBoxes = new ArrayList<>();

    static ArrayList<Book> books = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        access();
        books = Book.search(librarian.getId());
        librarianNameLBL.setText(librarian.getName() + " " +librarian.getLastName());
        librarianIdLBL.setText(librarian.getId()+"");
        rawCreator();

        returnBookBTN.setOnAction(event -> {
            books.get(selectedRaw).returnBook();
        });
    }

    public void rawCreator() {
        int i = 0;
        for(Book book : books) {
            try {
                hBoxes.add((HBox) load("../view/BookTRaw.fxml"));
                setRaw(hBoxes.get(i), book);
                librarianReportChPNL.getChildren().add(hBoxes.get(i));
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
        ((Label)hBox.getChildren().get(4)).setText(book.getBorrowedDate()+"");
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

    public int convertInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            return 0 ;
        }
    }

    public void access() {
        if (Relevant.user instanceof Librarian) {
            librarian = (Librarian) Relevant.user;
        }
        else {
            librarian = Relevant.librarian;
            returnBookBTN.setVisible(false);
        }
    }
}
