package View;

import Controller.CourseControl;
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

public class CourseController implements Initializable {
    private CourseControl courseControl = CourseControl.getInstance();
    private ScreenController screenController =  new ScreenController();
    private UnitController unitController = new UnitController();
    private Map<String, Course> courseMap = courseControl.getCourseMap();
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

    Alert alert = new Alert(Alert.AlertType.ERROR);
    String  unitCode;
    Course selectedCourse;
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
    }





    public void loadAction(ActionEvent actionEvent) {
        courseMap = courseControl.loadData();

        // Transform map to array list
        courseList = new ArrayList<Course>(courseMap.values());
        // Add list of staff to the observableArrayList
        courseData =  FXCollections.observableArrayList(courseList);

        // Set column in array to present as different attributes of staff
        codeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("location"));
        programColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("type"));
        
        // Set the created list to the staff table
        courseTable.setItems(null);
        courseTable.setItems(this.courseData);
    }

    public void deleteCourse(ActionEvent actionEvent) {
        courseControl.deleteCourse(selectedCourse.getCode());
        courseMap = courseControl.saveData();
        courseTable.getItems().remove(courseTable.getSelectionModel().getSelectedItem());
        deleteBtn.setDisable(true);
    }

    public String assignUnit(ActionEvent actionEvent) {
        unitCode = unitField.getText().toUpperCase();
        ArrayList<Unit> unitList = selectedCourse.getUnitList();

        if(!unitController.searchUnit(unitCode)) {
            alert.setContentText("Invalid unit code");
            alert.show();
            return "Error";
        }
        Unit assignUnit = unitController.getUnit(unitCode);
        if(unitList != null && unitList.contains(assignUnit)) {
            alert.setContentText("This unit already assigned to this course");
            alert.show();
            return "Error";
        }
        courseControl.assignUnit(assignUnit, selectedCourse);
        courseMap = courseControl.saveData();
        unitField.setText("");
        return "OK";
    }

    public void viewDetail(ActionEvent actionEvent) {
        CourseDetailController detailController = new CourseDetailController();
        detailController.transferCourse(selectedCourse);
        screenController.openScreen("courseDetail.fxml", "Course Detail");
    }





    public void backHome(ActionEvent actionEvent) {
        courseControl.saveData();
        screenController.openScreen("home.fxml", "Home Page");
        screenController.closeStage((Stage) homeBtn.getScene().getWindow());
    }





    public void addAction(ActionEvent actionEvent) {
        screenController.openScreen("addCourse.fxml", "Add Course");

    }
}
