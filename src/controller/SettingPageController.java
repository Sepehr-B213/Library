
/*
        in safhe baraaye taghir password & naam kaarbar tarahi shode ast ke agar yek seri shoroot
        (ke dar commnet mthod haaye marboote zekr shode) ra dara bood password ya naam sabt shode dar dataBase mysql ra
        avaz konad .
 */

package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Admin;
import model.Librarian;
import model.Relevant;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingPageController implements Initializable {

    @FXML
    private TextField passwordChangeFLD;

    @FXML
    private TextField confirmPasswordFLD;

    @FXML
    private Button changePasswordBTN;

    @FXML
    private TextField nameChangeFLD;

    @FXML
    private Button changeNameBTN;

    @FXML
    private Label changePassLBL;

    @FXML
    private Label changeNameLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changePasswordBTN.setOnAction(event -> changePassword());
        changeNameBTN.setOnAction(event -> changeName());
    }
    public void changePassword() {
        if(checkPassword()) {
            if (checkNotEmpty()) {
                if (Relevant.user instanceof Admin) {
                    Admin admin = (Admin) Relevant.user;
                    admin.setPassword(passwordChangeFLD.getText());
                    admin.update();
                } else {
                    Librarian librarian = (Librarian) Relevant.user;
                    librarian.setPassword(passwordChangeFLD.getText());
                    librarian.update();
                }
            } else
                changePassLBL.setText(Relevant.signUpPageErrors[0]);
        }
        else
            changePassLBL.setText(Relevant.signUpPageErrors[1]);
    }

    public void changeName() {
        if(!nameChangeFLD.getText().isEmpty()) {
            if (Relevant.user instanceof Admin) {
                Admin admin = (Admin) Relevant.user;
                admin.setName(nameChangeFLD.getText());
                admin.update();
            } else {
                Librarian librarian = (Librarian) Relevant.user;
                librarian.setName(nameChangeFLD.getText());
                librarian.update();
            }
        }
        else
            changePassLBL.setText(Relevant.signUpPageErrors[0]);
    }

    public boolean checkNotEmpty(){
        return !(passwordChangeFLD.getText().isEmpty() || confirmPasswordFLD.getText().isEmpty());
    }

    public boolean checkPassword(){
        return passwordChangeFLD.getText().equals(confirmPasswordFLD.getText());
    }

}

