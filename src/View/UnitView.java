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
    }




    public void loadUnit(ActionEvent actionEvent) {
        unitMap = unitControl.loadData();

        unitList = new ArrayList<Unit>(unitMap.values());
        unitData =  FXCollections.observableArrayList(unitList);
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
        courseControl.removeUnit(code);
        unitControl.deleteUnit(code);
        unitControl.saveData();
        courseControl.saveData();
        unitTable.getItems().remove(unitTable.getSelectionModel().getSelectedItem());
        deleteBtn.setDisable(true);
        viewDetailBtn.setDisable(true);
        decodeField.setText("");

    }

    public void showUnitDetail(Unit unit) {

        decodeField.setText(unit.getCode());

    }


    public void viewDetailAction(ActionEvent actionEvent){
        UnitDetailView detailController = new UnitDetailView();
        detailController.getSelectedUnit(selectedUnit);
        screenController.openScreen("unitDetail.fxml", "Unit Detail");
    }

    public void addAction(ActionEvent actionEvent) {
        screenController.openScreen("addUnit.fxml", "Add Unit");

    }
}

