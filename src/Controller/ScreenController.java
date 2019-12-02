package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenController {

    public void closeStage(Stage stage){
        stage.close();
    }

    public void openScreen(String filename, String title) {
        Stage stage = new Stage();
        Scene scene = null;

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("/FrontEnd/" + filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setOnCloseRequest(e -> closeProgram() );
        stage.show();

    }


    public void closeProgram(){
        System.out.println("Closing Screen");
    }
}
