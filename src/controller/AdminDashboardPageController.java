package controller;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardPageController extends MainController implements Initializable {

    @FXML
    private Button homeBTN;

    @FXML
    private Button usersBTN;

    @FXML
    private Button reportBTN;

    @FXML
    private Button menuBTN;

    @FXML
    private Button inboxBTN;

    @FXML
    private Button settingsBTN;

    @FXML
    private Button logoutBTN;

    @FXML
    private Pane settingPNL;

    @FXML
    private Pane reportPNL;

    @FXML
    private Pane userPNL;

    @FXML
    private Pane homePNL;

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
    private JFXToggleButton bookTBTN;

    @FXML
    private JFXToggleButton authorTBTN;

    @FXML
    private JFXToggleButton idTBTN;

    @FXML
    private Button minBTN;

    @FXML
    private Button closeBTN;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
