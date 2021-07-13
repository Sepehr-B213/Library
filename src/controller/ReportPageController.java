package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportPageController extends MainController implements Initializable {

    @FXML
    private JFXDatePicker dateFLD;

    @FXML
    private JFXTextField bookFLD;

    @FXML
    private JFXTextField authorFLD;

    @FXML
    private VBox reportChildrenPNL;

    @FXML
    private JFXDatePicker dateFLD1;

    @FXML
    private JFXCheckBox reportCHB;

    @FXML
    private Button authorSearchBTN;

    @FXML
    private Button bookSearchBTN;

    @FXML
    private Button dateSearchBTN;

    @FXML
    private Button dateSearchBTN1;

    @FXML
    private Button extensionBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
