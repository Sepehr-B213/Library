package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.User;

import java.io.IOException;

public class LibrarianPageController {

    @FXML
    private VBox userChildPNL;

    @FXML
    private JFXTextField userSearchFLD;

    @FXML
    private Button userSearchBTN;

    @FXML
    private JFXComboBox<?> userCMB;

    @FXML
    private Button userAddBTN;

    @FXML
    private Button userDeleteBTN;



}
