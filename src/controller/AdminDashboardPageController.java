package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Admin;
import model.Librarian;
import model.Relevant;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardPageController extends MainController implements Initializable {

    @FXML
    private VBox menuBar;

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
    private Button profileBTN;

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
    private Pane profilePNL;

    @FXML
    private Button minBTN;

    @FXML
    private Button closeBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        access();

        try {
            homePNL.getChildren().add(load("../view/HomePage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        homeBTN.setOnAction(event -> {
            homePNL.getChildren().removeAll();
            try {
                homePNL.getChildren().add(load("../view/HomePage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            homePNL.toFront();
        });

        usersBTN.setOnAction(event -> {
            userPNL.getChildren().removeAll();
            try {
                userPNL.getChildren().add(load("../view/LibrarianPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            userPNL.toFront();
        });

        reportBTN.setOnAction(event -> {
            reportPNL.getChildren().removeAll();
            try {
                reportPNL.getChildren().add(load("../view/ReportPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            reportPNL.toFront();
        });

        settingsBTN.setOnAction(event -> {
            settingPNL.getChildren().removeAll();
            try {
                settingPNL.getChildren().add(load("../view/SettingPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            settingPNL.toFront();
        });

        profileBTN.setOnAction(event -> {
            profilePNL.getChildren().removeAll();
            try {
                profilePNL.getChildren().add(load("../view/LibrarianProfilePage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            profilePNL.toFront();
        });

        logoutBTN.setOnAction(event -> {
            Relevant.loginStage.show();
            close(logoutBTN);
        });

        closeBTN.setOnAction(event -> close(closeBTN));

        minBTN.setOnAction(event -> minimize(minBTN));
    }

    public void access() {
        if(Relevant.user instanceof Librarian)
            menuBar.getChildren().removeAll(usersBTN, reportBTN);
        else if(Relevant.user instanceof Admin)
            menuBar.getChildren().removeAll(profileBTN);
    }
}
