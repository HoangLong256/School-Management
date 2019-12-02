package View;

import Controller.CourseControl;
import Controller.ScreenController;
import Controller.UnitControl;
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

public class UnitView implements Initializable {

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
    private Button homeBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button viewDetailBtn;

    Alert alert = new Alert(Alert.AlertType.ERROR);
    Unit selectedUnit;

    /*
    Name:initialize
    Purpose: thing to do before loading screen
    Passed: url, resourceBundle
    Return: none
    Input: none
    Output: none
    Effect: load the table view of unit, disable some buttons, get selected unit if click on table
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        unitMap = unitControl.loadData();
        deleteBtn.setDisable(true);
        viewDetailBtn.setDisable(true);

        unitTable.setRowFactory(tv -> {
            TableRow<Unit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty()) ) {
                    selectedUnit  = row.getItem();
                    viewDetailBtn.setDisable(false);
                    deleteBtn.setDisable(false);
                    showUnitDetail(selectedUnit);
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
        unitMap = unitControl.loadData();
        unitList = new ArrayList<Unit>(unitMap.values());
        unitData =  FXCollections.observableArrayList(unitList);
        codeColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("semester"));
        unitTable.setItems(null);
        unitTable.setItems(this.unitData);
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
    public void refresh(ActionEvent actionEvent) {
        loadTableView();
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
        unitControl.saveData();
        screenController.openScreen("home.fxml", "Home Page");
        screenController.closeStage((Stage) homeBtn.getScene().getWindow());
    }


    /*
    Name: deleteUnit
    Purpose: delete Unit
    Passed: action Event
    Return: none
    Input: none
    Output: none
    Effect: perform delete unit
     */
    public void deleteUnit(ActionEvent actionEvent) {
        String code = selectedUnit.getCode();
        courseControl.removeUnit(code);
        unitControl.deleteUnit(code);
        unitControl.saveData();
        courseControl.saveData();
        unitTable.getItems().remove(unitTable.getSelectionModel().getSelectedItem());
        deleteBtn.setDisable(true);
        viewDetailBtn.setDisable(true);
        decodeField.setText("");

    }

    /*
    Name: showUnitDetail
    Purpose: show selected unit code to textfield
    Passed: unit
    Return: none
    Input: none
    Output: none
    Effect: set textfield to unit code
     */
    public void showUnitDetail(Unit unit) {
        decodeField.setText(unit.getCode());
    }

    /*
    Name: viewDetailAction
    Purpose: view detail of unit
    Passed: action event
    Return: none
    Input: none
    Output: none
    Effect: open detail screen
     */
    public void viewDetailAction(ActionEvent actionEvent){
        UnitDetailView detailController = new UnitDetailView();
        detailController.getSelectedUnit(selectedUnit);
        screenController.openScreen("unitDetail.fxml", "Unit Detail");
    }

    /*
    Name: addAction
    Purpose: add unit to database
    Passed: action event
    Return: none
    Input: none
    Output: none
    Effect: open the add unit screen
     */
    public void addAction(ActionEvent actionEvent) {
        screenController.openScreen("addUnit.fxml", "Add Unit");

    }
}

