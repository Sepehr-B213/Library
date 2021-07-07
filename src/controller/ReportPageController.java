package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportPageController extends MainController implements Initializable {
    @FXML
    private JFXDatePicker dateFLD;

    @FXML
    private VBox reportChildrenPNL;

    @FXML
    private JFXButton dateSearchBTN;

    @FXML
    private JFXTextField bookFLD;

    @FXML
    private JFXButton bookSearchBTN;

    @FXML
    private JFXTextField authorFLD;

    @FXML
    private JFXButton searchAuthorBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
