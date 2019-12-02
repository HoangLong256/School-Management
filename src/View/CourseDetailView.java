package View;

import Controller.ScreenController;
import Model.Course;
import Model.Staff;
import Model.Unit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseDetailView implements Initializable {

    @FXML
    private TextField codeField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField diIDField;
    @FXML
    private TextField diNameField;
    @FXML
    private TextField deIDField;
    @FXML
    private TextField deNameField;
    @FXML
    private TextArea unitCodeArea;
    @FXML
    private TextArea unitDetailArea;
    @FXML
    private Button closeBtn;


    static Course selectedCourse;
    private ScreenController screenController = new ScreenController();

    /*
    Name:initialize
    Purpose: thing to do before loading screen
    Passed: url, resourceBundle
    Return: none
    Input: none
    Output: none
    Effect: load the detail of selected course
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Staff director = selectedCourse.getDirector();
        Staff deputy = selectedCourse.getDeputy();


        codeField.setText(selectedCourse.getCode());
        nameField.setText(selectedCourse.getName());
        diIDField.setText(String.valueOf(director.getSid()));
        diNameField.setText(director.getName());
        deIDField.setText(String.valueOf(deputy.getSid()));
        deNameField.setText(deputy.getName());
        unitCodeArea.setText("Code \n");
        unitDetailArea.setText("Name \n");

        ArrayList<Unit> unitList = selectedCourse.getUnitList();
        if(unitList != null) {
            for(int i = 0; i < unitList.size(); i++) {
                unitCodeArea.appendText("\n" + unitList.get(i).getCode());
                unitDetailArea.appendText("\n" + unitList.get(i).getName());
            }
        }


    }


    /*
    Name: closeDetailAction
    Purpose: close screen
    Passed: actionEvent
    Return: none
    Input: none
    Output: none
    Effect: close screen
     */
    public void closeDetailAction(ActionEvent actionEvent) {
        screenController.closeStage((Stage) closeBtn.getScene().getWindow());
    }

    /*
    Name: getSelectedCourse
    Purpose: get course from the courseView screen
    Passed: course - the course to display detail
    Return: none
    Input: none
    Output: none
    Effect: set the course to selected course
     */
    public void getSelectedCourse(Course course) {
        selectedCourse = course;
    }


}
