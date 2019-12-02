package View;

import Controller.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeView {
    ScreenController screenController = new ScreenController();

    @FXML
    private Button courseBtn;

    /*
    Name: courseOpen
    Purpose: open course screen
    Passed: action event
    Return: none
    Input: none
    Output: none
    Effect: open the course screen
     */
    public void courseOpen(ActionEvent actionEvent) {
         screenController.openScreen("course.fxml", "Course Page");
         screenController.closeStage((Stage) courseBtn.getScene().getWindow());
    }

    /*
    Name: unitOpen
    Purpose: open unit screen
    Passed: action event
    Return: none
    Input: none
    Output: none
    Effect: open the unit screen
     */
    public void unitOpen(ActionEvent actionEvent) throws IOException {
        screenController.openScreen("unit.fxml", "Unit Page");
        screenController.closeStage((Stage) courseBtn.getScene().getWindow());
    }

    /*
    Name: staffOpen
    Purpose: open staff screen
    Passed: action event
    Return: none
    Input: none
    Output: none
    Effect: open the staff screen
     */
    public void staffOpen(ActionEvent actionEvent) {
        screenController.openScreen("staff.fxml", "Staff Page");
        screenController.closeStage((Stage) courseBtn.getScene().getWindow());
    }


    /*
    Name: exitCall
    Purpose: close the program
    Passed: action event
    Return: none
    Input: none
    Output: none
    Effect: close the program
     */
    public void exitCall(ActionEvent actionEvent) {
        System.exit(0);
    }

}
