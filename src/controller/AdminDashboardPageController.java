package controller;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Book;
import model.Relevant;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminDashboardPageController extends MainController implements Initializable {

    @FXML
    private VBox menuBar;

    @FXML
    private Button homeBTN;

    @FXML
    private Button usersBTN;

    @FXML
    private Button reportBTN;

    @FXML
    private Button menuBTN;

    @FXML
    private Button inboxBTN;

    @FXML
    private Button settingsBTN;

    @FXML
    private Button logoutBTN;

    @FXML
    private Pane settingPNL;

    @FXML
    private Pane reportPNL;

    @FXML
    private Pane userPNL;

    @FXML
    private Pane homePNL;

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
    private JFXToggleButton bookTBTN;

    @FXML
    private JFXToggleButton authorTBTN;

    @FXML
    private JFXToggleButton idTBTN;

    @FXML
    private Button minBTN;

    @FXML
    private Button closeBTN;

    static int selectedRaw;

    static ArrayList<HBox> hBoxes = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user();

        rawCreator(Book.getBooks(""));

        homeBTN.setOnAction(event -> {
            reportPNL.getChildren().removeAll();
            homePNL.toFront();
        });

        usersBTN.setOnAction(event -> userPNL.toFront());

        reportBTN.setOnAction(event -> {
            try {
                reportPNL.getChildren().add(load("../view/ReportPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            reportPNL.toFront();
        });

        settingsBTN.setOnAction(event -> settingPNL.toFront());

        logoutBTN.setOnAction(event -> {
            Relevant.loginStage.show();
            close(logoutBTN);
        });

        deleteBookBTN.setOnAction(event -> {
            if(selectedRaw >= 0) {
                int bookId = getRaw((HBox) homeChildrenPNL.getChildren().get(selectedRaw)).getId();
                Book.delete(bookId);
                homeChildrenPNL.getChildren().removeAll(hBoxes);
                rawCreator(Book.getBooks(""));
                selectedRaw = -1;
            }
        });

        bookTBTN.setOnAction(event -> {
            selectToggles(bookTBTN, authorTBTN, idTBTN);
        });

        authorTBTN.setOnAction(event -> {
            selectToggles(authorTBTN, bookTBTN, idTBTN);
        });

        idTBTN.setOnAction(event -> {
            selectToggles(idTBTN, bookTBTN, authorTBTN);
        });

        closeBTN.setOnAction(event -> close(closeBTN));

        minBTN.setOnAction(event -> minimize(minBTN));
    }

    public void user() {
        homePNL.getChildren().remove(deleteBookBTN);
        menuBar.getChildren().removeAll(usersBTN, reportBTN);
    }

    public void rawCreator(ArrayList<Book> books) {
        ArrayList<HBox> hBoxes1 = new ArrayList<>();
        int i = 0;
        for(Book book : books) {
            try {
                hBoxes1.add((HBox) load("../view/BookTRaw.fxml"));
                setRaw(hBoxes1.get(i), books.get(i));
                homeChildrenPNL.getChildren().add(hBoxes1.get(i));
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        hBoxes = hBoxes1;
        rawsSetEvent();
    }

    public void setRaw(HBox hBox, Book book) {
        ((Label)hBox.getChildren().get(1)).setText(book.getName());
        ((Label)hBox.getChildren().get(2)).setText(book.getAuthor());
        ((Label)hBox.getChildren().get(3)).setText(book.getId()+"");
        ((Button)hBox.getChildren().get(4)).setText(book.getIsAvailable()+"");
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

    public void selectToggles(JFXToggleButton toggleButton1, JFXToggleButton toggleButton2, JFXToggleButton toggleButton3) {
        if(toggleButton1.isSelected()) {
            homeSearchBTN.setDisable(false);
            toggleButton2.setSelected(false);
            toggleButton3.setSelected(false);
        }
        else
            homeSearchBTN.setDisable(true);
    }
}
