package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupPageController implements Initializable {

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

    }
}
