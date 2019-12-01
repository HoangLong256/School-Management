package View;

import Controller.CourseControl;
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

public class AddCourse implements Initializable{
    private ScreenController screenController =  new ScreenController();
    private StaffControl staffControll =  StaffControl.getInstance() ;
    private CourseControl courseControl = CourseControl.getInstance();
    private Map<String, Course> courseMap = courseControl.getCourseMap();


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

    ToggleGroup programGroup = new ToggleGroup();
    ToggleGroup campusGroup = new ToggleGroup();
    Alert alert = new Alert(Alert.AlertType.ERROR);
    String courseID, name, campusLocation, programType, directorID, deputyID, unitCode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        courseMap = courseControl.loadData();
        underRadio.setToggleGroup(programGroup);
        postRadio.setToggleGroup(programGroup);
        saigonRadio.setToggleGroup(campusGroup);
        hanoiRadio.setToggleGroup(campusGroup);




    }


    public void closeAction(ActionEvent actionEvent) {
        screenController.closeStage((Stage) closeBtn.getScene().getWindow());

    }

    public void saveAction(ActionEvent actionEvent) {
        courseID = idText.getText().toUpperCase();
        name = nameText.getText();
        deputyID = deputyIDText.getText();
        directorID = directorIDText.getText();


        if(courseID.equals("") || name.equals("") || deputyID.equals("") ||directorID.equals(""))  {
            alert.setContentText("Missing required information");
            alert.show();
        }

        if(programGroup.getSelectedToggle() == null || campusGroup.getSelectedToggle() == null) {
            alert.setContentText("The program type need to be selected");
            alert.show();
        } else {
            RadioButton program = (RadioButton) programGroup.getSelectedToggle();
            programType = program.getText();
            RadioButton campus = (RadioButton) campusGroup.getSelectedToggle();
            campusLocation = campus.getText();
        }

//        if(!deputyID.matches("[0-9]*") || !directorID.matches("[0-9]*")) {
//            alert.setContentText("The ID of director and deputy only contain number");
//            alert.show();
//        }
//        else if(deputyID.equals(directorID)){
//            alert.setContentText("The director and deputy have to be different staff");
//            alert.show();
//        }
//        else if(deputyID.length() < 10 || directorID.length() < 10){
//            alert.setContentText("The staff ID have to contain at least 10 digits");
//            alert.show();
//        }
//        if(!staffController.searchStaffID(Integer.parseInt(deputyID)) || !staffController.searchStaffID(Integer.parseInt(directorID))) {
//            alert.setContentText("Cant found director or deputy");
//            alert.show();
//        }
//        Staff deputy = staffController.getStaffByID(Integer.parseInt(deputyID));
//        Staff director = staffController.getStaffByID(Integer.parseInt(directorID));
        Staff deputy = staffControll.getStaffByID(Integer.parseInt("1122334455"));
        if(staffControll.getStaffByID(Integer.parseInt("1122334455"))==null){
            System.out.println("Fail to assign");

        }
        Staff director = staffControll.getStaffByID(Integer.parseInt("1122443355"));
        Course course = new Course();
        course.setCode(courseID);
        course.setName(name);
        course.setLocation(campusLocation);
        course.setType(programType);
        course.setDirector(director);
        course.setDeputy(deputy);
        course.setUnitList(new ArrayList<>());
        if(!courseControl.addCourse(course)) {
            alert.setContentText("");
            alert.show();
        }
        courseControl.saveData();

//        screenController.closeStage((Stage) saveBtn.getScene().getWindow());
    }
}
