package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
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
    private JFXButton loginBTN;

    @FXML
    private Label errorLBL;

    @FXML
    private Button closeBTN;

    @FXML
    private Button minBTN;

    static Stage stage = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginBTN.setOnAction(e -> checkInfo());

        signupBTN.setOnAction(event -> {
            if(stage == null) {
                try {
                    stage = show(new Stage(), load("../view/SignupPage.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        closeBTN.setOnAction(event -> close(closeBTN));

        minBTN.setOnAction(event -> minimize(minBTN));
    }

    public void checkInfo() {
        if(!usernameFLD.getText().isEmpty() && !passwordFLD.getText().isEmpty()) {
            checkAccountAdmin();
        }
        else {
            clearText(usernameFLD, passwordFLD);
            errorLBL.setText(Relevant.loginPageErrors[0]);
        }
    }

    public void checkAccountAdmin () {
        if(convertInt(usernameFLD.getText()) == Relevant.admin.getId()) {
            if (passwordFLD.getText().equals(Relevant.admin.getPassword())) {
                stage = null;
                clearText(errorLBL, usernameFLD, passwordFLD);
                close(loginBTN);
                try {
                    stage = show(new Stage(), load("../view/AdminDashboardPage.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                clearText(passwordFLD);
                errorLBL.setText(Relevant.loginPageErrors[2]);
            }
        }
        else
            checkAccountLibrarian();
    }

    public void checkAccountLibrarian () {
        Librarian librarian = Librarian.search(convertInt(usernameFLD.getText()));
        if (librarian == null) {
            errorLBL.setText(Relevant.loginPageErrors[1]);
        }
        else if(passwordFLD.getText().equals(librarian.getPassword())) {
            stage = null;
            clearText(errorLBL, usernameFLD, passwordFLD);
            close(loginBTN);
            // open LibrarianMainPage
        }
        else {
            clearText(passwordFLD);
            errorLBL.setText(Relevant.loginPageErrors[2]);
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