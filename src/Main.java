import com.sun.istack.internal.NotNull;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Relevant;


public class Main extends Application {

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/LoginPage.fxml"));

        loader.load();

        Relevant.loginStage = primaryStage;
        primaryStage.setScene(new Scene(loader.getRoot()));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
