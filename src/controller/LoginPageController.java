package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;



public class LoginPageController {

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
    private Label ErrorLBL;


}
