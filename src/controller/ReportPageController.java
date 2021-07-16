package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportPageController extends MainController implements Initializable {

    @FXML
    private JFXDatePicker dateFLD;

    @FXML
    private JFXTextField bookFLD;

    @FXML
    private JFXTextField authorFLD;

    @FXML
    private VBox reportChildrenPNL;

    @FXML
    private JFXDatePicker dateFLD1;

    @FXML
    private JFXCheckBox reportCHB;

    @FXML
    private Button authorSearchBTN;

    @FXML
    private Button bookSearchBTN;

    @FXML
    private Button dateSearchBTN;

    @FXML
    private Button dateSearchBTN1;

    @FXML
    private Button extensionBTN;

    static int selectedRaw;

    static ArrayList<HBox> hBoxes = new ArrayList<>();

    static ArrayList<Book> books = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        books = Book.getBooks("");
        rawCreator();
        dateFLD1.setVisible(false);
        dateSearchBTN1.setVisible(false);

        bookSearchBTN.setOnAction(event -> {
            if(!bookFLD.getText().isEmpty()) {
                books.clear();
                books.add(Book.searchByName(bookFLD.getText()));
                rawCreator();
            }
        });

        authorSearchBTN.setOnAction(event -> {
            if(!authorFLD.getText().isEmpty()) {
                books = Book.search(authorFLD.getText());
                rawCreator();
            }
        });

        reportCHB.setOnAction(event -> {
            if(reportCHB.isSelected()) {
                dateFLD1.setVisible(true);
                dateSearchBTN1.setVisible(true);
            }
            else {
                dateFLD1.setVisible(false);
                dateSearchBTN1.setVisible(false);
            }
        });

        dateSearchBTN.setOnAction(event -> {
            if(reportCHB.isSelected()) {
                System.out.println(dateFLD.getValue());
                if(!(dateFLD.getValue() == null || dateFLD1.getValue() == null)) {
                    books = Book.search(Date.valueOf(dateFLD.getValue()), Date.valueOf(dateFLD1.getValue()));
                    rawCreator();
                }
            }
        });
    }

    public void rawCreator() {
        reportChildrenPNL.getChildren().removeAll(hBoxes);
        int i = 0;
        for(Book book : books) {
            try {
                hBoxes.add((HBox) load("../view/BookTRaw.fxml"));
                setRaw(hBoxes.get(i), book);
                reportChildrenPNL.getChildren().add(hBoxes.get(i));
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
}
