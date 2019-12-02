package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenController {

    /*
    Name: closeStage
    Purpose: close screen
    Passed: stage
    Return: none
    Input: none
    Output: none
    Effect: close screen
     */
    public void closeStage(Stage stage){
        stage.close();
    }

    /*
    Name: openScreen
    Purpose: open screen
    Passed: filename - screen to open
            title - title of screen
    Return: none
    Input: none
    Output: none
    Effect: open screen
     */
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

    /*
        Name: closeProgram
        Purpose: display message while closing screen
        Passed: none
        Return: none
        Input: none
        Output: message while closing
        Effect: display message
    */
    public void closeProgram(){
        System.out.println("Closing Screen");
    }
}
