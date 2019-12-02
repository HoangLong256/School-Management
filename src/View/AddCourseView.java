package View;

import Controller.CourseControl;
import Controller.ScreenController;
import Controller.StaffControl;
import Model.Course;
import Model.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.*;

public class AddCourseView implements Initializable{
    private ScreenController screenController =  new ScreenController();
    private StaffControl staffControll =  StaffControl.getInstance() ;
    private CourseControl courseControl = CourseControl.getInstance();
    private Map<Integer, Course> courseMap = courseControl.getCourseMap();


    @FXML
    private Button closeBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField idText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField directorIDText;

    @FXML
    private TextField deputyIDText;

    @FXML
    private RadioButton underRadio;

    @FXML
    private RadioButton postRadio;

    @FXML
    private RadioButton saigonRadio;

    @FXML
    private RadioButton hanoiRadio;

    @FXML
    private TextField shText;

    ToggleGroup programGroup = new ToggleGroup();
    ToggleGroup campusGroup = new ToggleGroup();
    Alert alertError = new Alert(Alert.AlertType.ERROR);
    Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    String courseSh, courseID, name, campusLocation, programType, directorID, deputyID, courseCode;

    /*
    Name:initialize
    Purpose: thing to do before loading screen
    Passed: url, resourceBundle
    Return: none
    Input: none
    Output: none
    Effect: set up screen and toggle group
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        courseMap = courseControl.loadData();
        underRadio.setToggleGroup(programGroup);
        postRadio.setToggleGroup(programGroup);
        saigonRadio.setToggleGroup(campusGroup);
        hanoiRadio.setToggleGroup(campusGroup);
    }


    /*
    Name: closeAction
    Purpose: close screen
    Passed: actionEvent
    Return: none
    Input: none
    Output: none
    Effect: close screen
     */
    public void closeAction(ActionEvent actionEvent) {
        screenController.closeStage((Stage) closeBtn.getScene().getWindow());

    }

    /*
    Name: saveAction
    Purpose: save course to database
    Passed: action event
    Return: true if success, false if failed
    Input: none
    Output: alert
    Effect: check validation then add course to database
     */
    public Boolean saveAction(ActionEvent actionEvent) {
        courseID = idText.getText();
        courseSh = shText.getText().toUpperCase();
        name = nameText.getText();
        deputyID = deputyIDText.getText();
        directorID = directorIDText.getText();

        if(courseID.equals("")
                ||courseSh.equals("")
                || name.equals("")
                || deputyID.equals("")
                ||directorID.equals("")
                ||programGroup.getSelectedToggle() == null
                ||campusGroup.getSelectedToggle() == null)  {
            alertError.setContentText("Missing Information");
            alertError.show();
            clear();
            return Boolean.FALSE;
        }

        RadioButton program = (RadioButton) programGroup.getSelectedToggle();
        programType = program.getText();
        RadioButton campus = (RadioButton) campusGroup.getSelectedToggle();
        campusLocation = campus.getText();

        String idError = idValidator(courseID,courseSh);
        if(idError != null){
            alertError.setContentText(idError);
            alertError.show();
            clear();
            return Boolean.FALSE;
        }
        String staffError = staffValidator(deputyID,directorID);
        if(staffError != null){
            alertError.setContentText(staffError);
            alertError.show();
            clear();
            return Boolean.FALSE;
        }


        courseCode = courseSh + courseID;
        Staff deputy = staffControll.getStaffByID(Integer.parseInt("1122334455"));
        Staff director = staffControll.getStaffByID(Integer.parseInt("1122443355"));
        Course course = new Course();
        course.setcID(Integer.parseInt(courseID));
        course.setCode(courseCode);
        course.setName(name);
        course.setLocation(campusLocation);
        course.setType(programType);
        course.setDirector(director);
        course.setDeputy(deputy);
        course.setUnitList(new ArrayList<>());

        if(!courseControl.addCourse(course)) {
            alertError.setContentText("");
            alertError.show();
            clear();
            return Boolean.FALSE;
        }
        alertInfo.setContentText("Adding Successfully");
        alertInfo.show();
        courseControl.saveData();
        clear();
        return Boolean.TRUE;
//        screenController.closeStage((Stage) saveBtn.getScene().getWindow());
    }


    /*
    Name: staffValidator
    Purpose: validate staff
    Passed: deputyID - staffID
            directorID - staffID
    Return: String if error
    Input: none
    Output: none
    Effect: validate the staff if
     */
    public String staffValidator (String deputyID, String directorID){
        if(deputyID.equals(directorID)){
            return "Staff Error: DeputyID and DirectoryID must be difference";
        }

        try {
            Integer.parseInt(deputyID);
            Integer.parseInt(directorID);
            if(staffControll.getStaffByID(Integer.parseInt(deputyID))==null
                    ||staffControll.getStaffByID(Integer.parseInt(directorID)) == null){
                return "Staff Error: Invalid ID";
            }
            return null;
        } catch (NumberFormatException e) {
            return "Staff Error: Number Format";
        }
    }


    /*
    Name: idValidator
    Purpose: validate the id
    Passed: courseID - course ID
            courseSh -shorthand
    Return: String if error
    Input: none
    Output: none
    Effect: check id and return result
     */
    public String idValidator(String courseID, String courseSh){
        try{
            Integer.parseInt(courseID);
            if(courseID.length() != 4){
                return "ID Error: ID must be 4 digits";
            }
            if(courseSh.matches("[0-9]*")){
                return "ID Error: Shorthand must be non digit characters";
            }
            if(courseSh.length() != 2){
                return "ID Error: Shorthand must be 2 letter";
            }
            if(courseControl.getCourseByID(courseID) != null){
                return "ID Error: ID is already used";
            }
            return null;
        }catch (NumberFormatException e){
            return "ID Error: Number Format";
        }catch (IndexOutOfBoundsException e){
            return "ID Error: Invalid ID";
        }
    }

    /*
    Name: clear
    Purpose: clear the textfield
    Passed: none
    Return: none
    Input: none
    Output: none
    Effect: clear the form
     */
    public void clear(){
        shText.setText("");
        idText.setText("");
        nameText.setText("");
        deputyIDText.setText("");
        directorIDText.setText("");
    }
}
