package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {
    ScreenController screenController = new ScreenController();

    @FXML
    private Button courseBtn;

    public void courseOpen(ActionEvent actionEvent) {
         screenController.openScreen("course.fxml", "Course Page");
         screenController.closeStage((Stage) courseBtn.getScene().getWindow());
    }


    public void unitOpen(ActionEvent actionEvent) throws IOException {
        screenController.openScreen("unit.fxml", "Unit Page");
        screenController.closeStage((Stage) courseBtn.getScene().getWindow());
//        screenController.aa();
    }

    public void staffOpen(ActionEvent actionEvent) {
        screenController.openScreen("staff.fxml", "Staff Page");
        screenController.closeStage((Stage) courseBtn.getScene().getWindow());
    }


    public void exitCall(ActionEvent actionEvent) {
        System.exit(0);
    }

}
