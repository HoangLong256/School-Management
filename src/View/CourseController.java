package View;

import View.enumerated.location;
import Implementation.CourseImpl;
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

    private ScreenController screenController =  new ScreenController();
    private StaffController staffController =  new StaffController();
    private UnitController unitController = new UnitController();
    static Map<String, Course> courseMap = new HashMap<>();
    static CourseImpl courseImpl = new CourseImpl();
    private ObservableList<Course> courseData ;
    private List<Course> courseList;
//     Generate a constructor for the class CourseController
//    public CourseController(UnitController unitController, StaffController staffController) {
//        this.unitController = unitController;
//        this.staffController = staffController;
//    }


    @FXML
    private TextField codeField;
    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<location> locationComboBox;
    @FXML
    private RadioButton underRadio;
    @FXML
    private RadioButton postRadio;
    @FXML
    private TextField directorField;
    @FXML
    private TextField deputyField;
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
    private TextArea courseArea;
    @FXML
    private Button viewBtn;
    @FXML
    private TextArea unitArea;
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

    ToggleGroup toggleGroup = new ToggleGroup();
    Alert alert = new Alert(Alert.AlertType.ERROR);
    String code, name, campusLocation, programType, directorID, deputyID, unitCode;
    Course selectedCourse;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        courseMap = courseImpl.deserializeCourse("course.txt");
        System.out.println("Course deserialize done");

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



    /**
     * This method called when Add Course button pressed
     * Purpose: Add course to SST system
     * @return String - Contains "OK" or "Error"
     */
    public String addCourse(ActionEvent actionEvent) {
        return "Ok";
    }

    public void clearForm(ActionEvent actionEvent) {
        clear();
    }

    public void loadAction(ActionEvent actionEvent) {
        courseMap = courseImpl.deserializeCourse("course.txt");

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
        courseImpl.deleteCourse(selectedCourse.getCode(), courseMap);
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
        courseImpl.assignUnit(assignUnit, selectedCourse);
        return "OK";
    }

    public void viewDetail(ActionEvent actionEvent) {
        CourseDetailController detailController = new CourseDetailController();
        detailController.transferCourse(selectedCourse);
        screenController.openScreen("courseDetail.fxml", "Course Detail");
    }





    public void backHome(ActionEvent actionEvent) {
        courseImpl.serializeCourse(courseMap, "course.txt");
        screenController.openScreen("home.fxml", "Home Page");
        screenController.closeStage((Stage) homeBtn.getScene().getWindow());
    }



    public void clear() {
        codeField.setText("");
        nameField.setText("");
        underRadio.setSelected(false);
        postRadio.setSelected(false);
        directorField.setText("");
        deputyField.setText("");
    }


    public void addAction(ActionEvent actionEvent) {
        screenController.openScreen("addCourse.fxml", "Add Course");

    }
}
