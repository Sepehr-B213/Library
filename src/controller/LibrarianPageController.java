package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Librarian;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LibrarianPageController extends MainController implements Initializable {

    @FXML
    private VBox userChildPNL;

    @FXML
    private JFXTextField userSearchFLD;

    @FXML
    private Button userSearchBTN;

    @FXML
    private JFXComboBox<String> userCMB;

    @FXML
    private Button userAddBTN;

    @FXML
    private Button userDeleteBTN;

    static Stage stage = null;

    static int selectedRaw = -1;

    static ArrayList<HBox> hBoxes = new ArrayList<>();

    static ArrayList<Librarian> librarians = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage = null;
        userCMB.getItems().addAll("نام و نام خانوادگی", "شماره عضویت");
        userCMB.setValue("نام و نام خانوادگی");
        librarians = Librarian.getLibrarians("");
        rawCreator();

        userSearchBTN.setOnAction(event -> {
            if(!userCMB.getValue().isEmpty()) {
                userChildPNL.getChildren().removeAll(hBoxes);
                librarians = searchLibrarian(userCMB.getValue());
                rawCreator();
            }
        });

        userDeleteBTN.setOnAction(event -> {
            if(selectedRaw >= 0) {
                int librarianId = getRaw(hBoxes.get(selectedRaw)).getId();
                //Librarian.delete(librarianId);
                librarians.remove(selectedRaw);
                userChildPNL.getChildren().removeAll(hBoxes);
                rawCreator();
                selectedRaw = -1;
            }
        });

        userAddBTN.setOnAction(event -> {
            if(stage == null) {
                try {
                    stage = show(new Stage(), load("../view/AddLibrarianPage.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void rawCreator() {
        int i = 0;
        for (Librarian librarian : librarians) {
            try {
                hBoxes.add((HBox) load("../view/LibrarianTRaw.fxml"));
                setRaw(hBoxes.get(i), librarian);
                userChildPNL.getChildren().add(hBoxes.get(i));
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        rawsSetEvent();
    }

    public void setRaw(HBox hBox, Librarian librarian) {
        ((Label)hBox.getChildren().get(1)).setText(librarian.getName());
        ((Label)hBox.getChildren().get(2)).setText(librarian.getLastName());
        ((Label)hBox.getChildren().get(3)).setText(librarian.getId()+"");
    }

    public Librarian getRaw(HBox hBox) {
        return Librarian.search(Integer.parseInt(((Label)hBox.getChildren().get(3)).getText()));
    }

    public void rawsSetEvent() {
        for(int i = 0; i < hBoxes.size(); i++)
            rawsSetEventHelper(i);
    }

    public void rawsSetEventHelper(int i) {
        hBoxes.get(i).setOnMouseEntered(event -> {
            hBoxes.get(i).setStyle("-fx-background-color : #0A0E3F");
        });

        hBoxes.get(i).setOnMouseExited(event -> {
            if(i != selectedRaw)
                hBoxes.get(i).setStyle("-fx-background-color : #02030A");
        });

        hBoxes.get(i).setOnMouseClicked(event -> {
            if(selectedRaw >= 0)
                hBoxes.get(selectedRaw).setStyle("-fx-background-color : #02030A");
            selectedRaw = i;
            hBoxes.get(i).setStyle("-fx-background-color : #0A0E3F");
        });
    }

    public ArrayList<Librarian> searchLibrarian(String combo) {
        ArrayList<Librarian> list = new ArrayList<>();
        switch(combo) {
            case "نام و نام خانوادگی" :
                String[] split = userSearchFLD.getText().split(" ");
                if (split.length == 2)
                    list = Librarian.search(split[0],split[1]);
                break;

            case "شماره عضویت" :
                int id = convertInt(userSearchFLD.getText());
                list.add(Librarian.search(id));
                break;

            default:
                break;
        }

        return list;
    }

    public int convertInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            return 0 ;
        }
    }
}
