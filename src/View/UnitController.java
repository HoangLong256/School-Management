package View;

import Controller.CourseControl;
import Controller.UnitControl;
import Model.Staff;
import Model.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class UnitController implements Initializable {

    private ScreenController screenController =  new ScreenController();
    private UnitControl unitControl = UnitControl.getInstance();
    private CourseControl courseControl = CourseControl.getInstance();
    private Map<String, Unit> unitMap = unitControl.getUnitMap();
    private ObservableList<Unit> unitData ;
    private List<Unit> unitList;

    @FXML
    private TableView<Unit> unitTable;
    @FXML
    private TableColumn<Unit, String> codeColumn;
    @FXML
    private TableColumn<Unit, String> nameColumn;
    @FXML
    private TableColumn<Unit, String> semesterColumn;
    @FXML
    private TextField decodeField;
    @FXML
    private TextField deNameField;
    @FXML
    private TextArea exIDArea;
    @FXML
    private TextArea exNameArea;
    @FXML
    private TextArea exAddArea;
    @FXML
    private TextArea lecIDArea;
    @FXML
    private TextArea lecNameArea;
    @FXML
    private TextArea lecAddArea;
    @FXML
    private Button homeBtn;
    @FXML
    private Button deleteBtn;

    Alert alert = new Alert(Alert.AlertType.ERROR);
    Unit selectedUnit;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        unitMap = unitControl.loadData();
        deleteBtn.setDisable(true);


        unitTable.setRowFactory(tv -> {
            TableRow<Unit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty()) ) {
                    selectedUnit  = row.getItem();
                    deleteBtn.setDisable(false);
                    showUnitDetail(selectedUnit);
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



    public void loadUnit(ActionEvent actionEvent) {
        unitMap = unitControl.loadData();

        // Transform map to array list
        unitList = new ArrayList<Unit>(unitMap.values());
        // Add list of staff to the observableArrayList
        unitData =  FXCollections.observableArrayList(unitList);

        // Set column in array to present as different attributes of staff
        codeColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("semester"));

        codeColumn.setStyle( "-fx-alignment: CENTER;");
        nameColumn.setStyle( "-fx-alignment: CENTER;");
        semesterColumn.setStyle( "-fx-alignment: CENTER;");

        // Set the created list to the unit table
        unitTable.setItems(null);
        unitTable.setItems(this.unitData);
    }

    public void backHome(ActionEvent actionEvent) {
        unitControl.saveData();
        screenController.openScreen("home.fxml", "Home Page");
        screenController.closeStage((Stage) homeBtn.getScene().getWindow());
    }



    public void deleteUnit(ActionEvent actionEvent) {
        String code = selectedUnit.getCode();
        unitControl.deleteUnit(code);
        courseControl.removeUnit(code);
        unitControl.saveData();
        courseControl.saveData();
        unitTable.getItems().remove(unitTable.getSelectionModel().getSelectedItem());
        deleteBtn.setDisable(true);

    }

    public void showUnitDetail(Unit unit) {
        Staff examiner = unit.getExaminer();
        Staff lecturer = unit.getLecturer();
        decodeField.setText(unit.getCode());
        deNameField.setText(unit.getName());
        exIDArea.setText(String.valueOf(examiner.getSid()));
        exNameArea.setText(examiner.getName());
        exAddArea.setText(examiner.getAddress());
        lecIDArea.setText(String.valueOf(lecturer.getSid()));
        lecNameArea.setText(lecturer.getName());
        lecAddArea.setText(lecturer.getAddress());
    }




    public void addAction(ActionEvent actionEvent) {
        screenController.openScreen("addUnit.fxml", "Add Unit");

    }
}

