package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import model.Librarian;
import model.Relevant;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupPageController extends MainController implements Initializable {

    @FXML
    private JFXTextField nameFLD;

    @FXML
    private JFXTextField lastnameFLD;

    @FXML
    private JFXPasswordField passwordFLD;

    @FXML
    private JFXPasswordField confirmFLD;

    @FXML
    private JFXButton exitBTN;

    @FXML
    private JFXButton signupBTN;

    @FXML
    private JFXCheckBox checkBox;

    @FXML
    private JFXButton termsBTN;

    @FXML
    private Label errorLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkBox.setOnAction(event -> signupBTN.setDisable(!checkBox.isSelected()));

        signupBTN.setOnAction(event -> signingUp());

        exitBTN.setOnAction(event -> {
            LoginPageController.stage = null;
            close(exitBTN);
        });

        termsBTN.setOnAction(event -> {});
    }

    public void signingUp() {
        if(checkPassword()) {
            Librarian librarian = new Librarian(0,nameFLD.getText(),passwordFLD.getText(),lastnameFLD.getText()) ;
            librarian.add();
            errorLBL.setTextFill(Paint.valueOf("#4be928"));
            errorLBL.setText(String.format(Relevant.signUpPageErrors[1], librarian.getId()));
        }
        else {
            errorLBL.setTextFill(Paint.valueOf("#ff3700"));
            errorLBL.setText(Relevant.signUpPageErrors[0]);
        }
    }

    public boolean checkPassword(){
        if(passwordFLD.getText().equals(confirmFLD.getText()))
            return true ;
        else
            return false ;
    }

    public void openTerms() {

    }
}
