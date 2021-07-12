
/*
        dar vaaghe in safhe yek mini safhe baraye afzoodan ketaab ast, ke Admin va ketaabdaar hardo mitavanand
        ketaab jadid az tarigh in page be dataBase mysql ezaafe konand ( albate ketab baayad vizhegi haaye mored nazar
        mesl ekraari naboodan naam ra daara baashad ) .
        dar soorat ijad yaaa adam ijaad ketab payam marboote be karbar namyesh daade mishavad .
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Book;
import model.Relevant;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class AddBookPageController extends MainController implements Initializable {
    @FXML
    private JFXTextField bookNameFLD;

    @FXML
    private JFXTextField bookAuthorFLD;

    @FXML
    private JFXButton acceptBTN;

    @FXML
    private JFXButton exitBTN;

    @FXML
    private Label errorLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        acceptBTN.setOnAction(event -> addBook());
        exitBTN.setOnAction(event -> {
            HomePageController.stage = null;
            close(exitBTN);
        });
    }

    public void addBook() {
        if (bookNameFLD.getText().isEmpty() || bookAuthorFLD.getText().isEmpty()) {
            errorLBL.setText(Relevant.addBookPageErrors[0]);
        }
        else {
            Book book = Book.searchByName(bookNameFLD.getText());
            if(book == null) {
                book = new Book();
                book.setName(bookNameFLD.getText());
                book.setAuthor(bookAuthorFLD.getText());
                book.add();
                errorLBL.setText(Relevant.addBookPageErrors[1]);
                clearText(bookNameFLD, bookAuthorFLD);
            }
            else {
                errorLBL.setText(Relevant.addBookPageErrors[2]);
            }
        }
    }
}
