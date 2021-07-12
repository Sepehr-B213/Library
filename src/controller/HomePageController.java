package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HomePageController {

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
    private JFXComboBox<?> homeSearchCMB;

    @FXML
    private JFXCheckBox homeCHB;

}
