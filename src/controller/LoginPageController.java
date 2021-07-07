package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Relevant;
import model.Librarian;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginPageController extends MainController implements Initializable {

    @FXML
    private JFXPasswordField passwordFLD;

    @FXML
    private JFXTextField usernameFLD;

    @FXML
    private JFXButton signupBTN;

    @FXML
    private JFXCheckBox checkBox;

    @FXML
    private JFXButton loginBTN;

    @FXML
    private Label errorLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginBTN.setOnAction(e -> checkInfo());

        checkBox.setOnAction(event -> loginBTN.setDisable(!checkBox.isSelected()));

        signupBTN.setOnAction(event -> {
            try {
                show(load("../view/SignupPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void checkInfo() {
        if(!usernameFLD.getText().isEmpty() && !passwordFLD.getText().isEmpty()) {
            if(checkAccountAdmin()) {
                close(loginBTN);
                try {
                    show(load("../view/AdminDashboardPage.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (checkAccountLibrarian()) {
                close(loginBTN);
                // open LibrarianMainPage
            }
        }
        else
            errorLBL.setText(Relevant.loginPageErrors[0]);
    }

    public boolean checkAccountAdmin () {
        if(convertInt(usernameFLD.getText()) == Relevant.admin.getId()) {
            if(passwordFLD.getText().equals(Relevant.admin.getPassword()))
                return true ;
            else
                errorLBL.setText(Relevant.loginPageErrors[2]);
        }
        return false ;
    }

    public  boolean checkAccountLibrarian () {
        int id = convertInt(usernameFLD.getText());

        if (id != 0) {
            Librarian librarian = Librarian.search(id);
            if (librarian == null) {
                errorLBL.setText(Relevant.loginPageErrors[1]);
                return false;
            }
            else if(passwordFLD.getText().equals(librarian.getPassword()))
                return true;
            else {
                errorLBL.setText(Relevant.loginPageErrors[2]);
                return false;
            }
        }
        else {
            return false;
        }
    }

    public int convertInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            errorLBL.setText(Relevant.loginPageErrors[1]);
            return 0 ;
        }
    }
}