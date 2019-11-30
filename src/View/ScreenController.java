package View;

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
//    public void aa() throws IOException {
//        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("unit.fxml"));
//        loader.setController(this);
//        loader.load();
//    }

    public void closeProgram(){
        System.out.println("Screen closed");
//        System.exit(1);
    }
}
