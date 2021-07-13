package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import model.Librarian;
import model.Relevant;

import java.net.URL;
import java.util.ResourceBundle;

public class AddLibrarianPageController extends MainController implements Initializable {

    @FXML
    private Label errorLBL;

    @FXML
    private JFXButton librarianAcceptBTN;

    @FXML
    private JFXButton exitBTN;

    @FXML
    private JFXTextField librarianNameFLD;

    @FXML
    private JFXTextField librarianLastnameFLD;

    @FXML
    private JFXTextField librarianPassFLD;

    @FXML
    private JFXTextField librarianRepeatPassFLD;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        librarianAcceptBTN.setOnAction(event -> librarianAccept());

        exitBTN.setOnAction(event -> {
            LibrarianPageController.stage = null;
            close(exitBTN);
        });
    }

    public void librarianAccept() {
        if(librarianNameFLD.getText().isEmpty() || librarianLastnameFLD.getText().isEmpty() || librarianPassFLD.getText().isEmpty() || librarianRepeatPassFLD.getText().isEmpty()){
            errorLBL.setText(Relevant.AddLibrarianPageErrors[0]);
        }
        else {
            if (checkPassword()) {
                Librarian librarian = new Librarian(0, librarianNameFLD.getText(), librarianPassFLD.getText(), librarianLastnameFLD.getText());
                librarian.add();
                errorLBL.setText(String.format(Relevant.AddLibrarianPageErrors[2], librarian.getId()));
                clearText(librarianNameFLD, librarianLastnameFLD, librarianPassFLD, librarianRepeatPassFLD);
            } else {
                errorLBL.setText(Relevant.AddLibrarianPageErrors[1]);
            }
        }

    }

    public boolean checkPassword(){
        return librarianPassFLD.getText().equals(librarianRepeatPassFLD.getText());
    }
}
