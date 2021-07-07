package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ReportPageController {

    @FXML
    private JFXDatePicker dateFLD;

    @FXML
    private VBox reportchldrenPNL;

    @FXML
    private JFXButton datesearchBTN;

    @FXML
    private JFXTextField bookFLD;

    @FXML
    private JFXButton booksearchBTN;

    @FXML
    private JFXTextField authorFLD;

    @FXML
    private JFXButton searchauthorBTN;
}
