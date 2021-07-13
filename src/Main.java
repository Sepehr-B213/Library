import com.sun.istack.internal.NotNull;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Relevant;


public class Main extends Application {
    private double x, y;

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/LoginPage.fxml"));
        loader.load();

        Relevant.loginStage = primaryStage;
        primaryStage.setScene(new Scene(loader.getRoot()));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        ((Parent)loader.getRoot()).setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        ((Parent)loader.getRoot()).setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
