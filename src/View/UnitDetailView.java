package View;


import Controller.CourseControl;
import Controller.ScreenController;
import Model.Unit;
import Model.Course;
import Model.Staff;
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

public class UnitDetailView implements Initializable{

    static Unit selectedUnit;
    private ScreenController screenController = new ScreenController();
    private CourseControl courseControl = CourseControl.getInstance();

    @FXML
    private Button closeBtn;

    @FXML
    private TextField examinerIDText;

    @FXML
    private TextField examinerNameText;

    @FXML
    private TextField lecturerIDText;

    @FXML
    private TextField lecturerNameText;

    @FXML
    private TextField codeField;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea courseCodeArea;

    @FXML
    private TextArea courseNameArea;

    /*
    Name: closeDetailAction
    Purpose: close screen
    Passed: actionEvent
    Return: none
    Input: none
    Output: none
    Effect: close screen
     */
    public void closeDetailAction(ActionEvent event) {
        screenController.closeStage((Stage) closeBtn.getScene().getWindow());

    }

    /*
    Name: getSelectedUnit
    Purpose: get unit from the unitView screen
    Passed: unit - the unit to display detail
    Return: none
    Input: none
    Output: none
    Effect: set the unit to selected unit
     */
    public void getSelectedUnit(Unit unit) {
        selectedUnit = unit;
    }

    /*
    Name:initialize
    Purpose: thing to do before loading screen
    Passed: url, resourceBundle
    Return: none
    Input: none
    Output: none
    Effect: load the detail of selected unit
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Staff examiner = selectedUnit.getExaminer();
        Staff lecture = selectedUnit.getLecturer();
        courseCodeArea.setText("Code \n");
        courseNameArea.setText("Name \n");
        codeField.setText(selectedUnit.getCode());
        nameField.setText(selectedUnit.getName());
        examinerIDText.setText(String.valueOf(examiner.getSid()));
        examinerNameText.setText(examiner.getName());
        lecturerIDText.setText(String.valueOf(lecture.getSid()));
        lecturerNameText.setText(lecture.getName());
        ArrayList<Course> courseList = courseControl.getCoursesHaveUnit(selectedUnit.getCode());
        if(courseList != null){
            for(int i = 0; i < courseList.size(); i++){
                courseCodeArea.appendText("\n" + courseList.get(i).getCode());
                courseNameArea.appendText("\n" + courseList.get(i).getName());
            }
        }

    }

}
