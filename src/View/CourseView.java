package View;

import Controller.CourseControl;
import Controller.ScreenController;
import Controller.UnitControl;
import Model.Course;
import Model.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CourseView implements Initializable {
    private CourseControl courseControl = CourseControl.getInstance();
    private ScreenController screenController =  new ScreenController();
    private UnitControl unitControl = UnitControl.getInstance();
    private Map<Integer, Course> courseMap = courseControl.getCourseMap();
    private ObservableList<Course> courseData ;
    private List<Course> courseList;



    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableColumn<Course, String> codeColumn;
    @FXML
    private TableColumn<Course, String> nameColumn;
    @FXML
    private TableColumn<Course, String> locationColumn;
    @FXML
    private TableColumn<Course, String> programColumn;
    @FXML
    private Button viewBtn;
    @FXML
    private TextField unitField;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button assignBtn;
    @FXML
    private Button homeBtn;
    @FXML
    private Button addCourseBtn;

    Alert alertError = new Alert(Alert.AlertType.ERROR);
    Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    String  unitCode;
    Course selectedCourse;

    /*
    Name:initialize
    Purpose: thing to do before loading screen
    Passed: url, resourceBundle
    Return: none
    Input: none
    Output: none
    Effect: load the table view of course, disable some buttons, get selected course if click on table
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        courseMap = courseControl.loadData();

        assignBtn.setDisable(true);
        deleteBtn.setDisable(true);
        addCourseBtn.setDisable(false);
        viewBtn.setDisable(true);


        courseTable.setRowFactory(tv -> {
            TableRow<Course> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty()) ) {
                    selectedCourse = row.getItem();
                    assignBtn.setDisable(false);
                    deleteBtn.setDisable(false);
                    viewBtn.setDisable(false);

                }
            });
            return row ;
        });
        loadTableView();

    }
    /*
    Name: loadTableView
    Purpose: load data to table
    Passed: none
    Return: none
    Input: none
    Output: none
    Effect: load unit to table
     */
    public void loadTableView(){
        courseMap = courseControl.loadData();
        courseList = new ArrayList<Course>(courseMap.values());
        courseData =  FXCollections.observableArrayList(courseList);
        codeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("location"));
        programColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("type"));
        courseTable.setItems(null);
        courseTable.setItems(this.courseData);
    }



    /*
    Name: refresh
    Purpose: refresh the table
    Passed: action event
    Return: none
    Input: none
    Output: none
    Effect: refresh the table, load it again when it has new data
     */
    public void Refresh(ActionEvent actionEvent) {
        loadTableView();
    }

    /*
    Name: deleteCourse
    Purpose: delete selected course
    Passed: action Event
    Return: none
    Input: none
    Output: none
    Effect: perform delete course
     */
    public void deleteCourse(ActionEvent actionEvent) {
        courseControl.deleteCourse(selectedCourse.getcID());
        courseMap = courseControl.saveData();
        courseTable.getItems().remove(courseTable.getSelectionModel().getSelectedItem());
        deleteBtn.setDisable(true);
        assignBtn.setDisable(true);
        viewBtn.setDisable(true);
    }

    /*
    Name: assignUnit
    Purpose: assign unit to selected course
    Passed: action event
    Return: true if success, false if failed
    Input: none
    Output: alert if get error or success
    Effect: perform to assign unt
     */
    public Boolean assignUnit(ActionEvent actionEvent) {
        unitCode = unitField.getText().toUpperCase();
        ArrayList<Unit> unitList = selectedCourse.getUnitList();

        if(!unitControl.searchUnit(unitCode)) {
            alertError.setContentText("Assigned Unit Error: Invalid Unit");
            alertError.show();
            unitField.setText("");
            return Boolean.FALSE;
        }
        Unit assignUnit = unitControl.getUnit(unitCode);
        if(unitList != null && unitList.contains(assignUnit)) {
            alertError.setContentText("Assigned Unit Error: Already Assigned");
            alertError.show();
            return Boolean.FALSE;
        }
        if(!courseControl.assignUnit(assignUnit, selectedCourse)) {
            alertError.setContentText("This unit is not suitable for the program");
            alertError.show();
            return Boolean.FALSE;
        }
        alertInfo.setContentText("Unit Assigned");
        alertInfo.show();
        courseMap = courseControl.saveData();
        unitField.setText("");
        return Boolean.TRUE;
    }

    /*
    Name: viewDetail
    Purpose: view detail of course
    Passed: action event
    Return: none
    Input: none
    Output: none
    Effect: open detail screen
     */
    public void viewDetail(ActionEvent actionEvent) {
        CourseDetailView detailController = new CourseDetailView();
        detailController.getSelectedCourse(selectedCourse);
        screenController.openScreen("courseDetail.fxml", "Course Detail");
    }

    /*
    Name: mainMenu
    Purpose: back to main menu
    Passed: actionEvent
    Return: none
    Input: none
    Output: none
    Effect: back to main menu
     */
    public void mainMenu(ActionEvent actionEvent) {
        courseControl.saveData();
        screenController.openScreen("home.fxml", "Home Page");
        screenController.closeStage((Stage) homeBtn.getScene().getWindow());
    }

    /*
    Name: addAction
    Purpose: add course to database
    Passed: action event
    Return: none
    Input: none
    Output: none
    Effect: open the add course screen
     */
    public void addAction(ActionEvent actionEvent) {
        screenController.openScreen("addCourse.fxml", "Add Course");

    }
}
