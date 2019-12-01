package View;

import View.enumerated.level;
import Implementation.UnitImpl;
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
    private StaffController staffController =  new StaffController();
//    private CourseController courseController = new CourseController();
    static Map<String, Unit> unitMap = new HashMap<>();
    static UnitImpl unitImpl = new UnitImpl();
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

//    public UnitController(StaffController staffController) {
//        this.staffController = staffController;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("/FrontEnd/unit.fxml"));
//        loader.setController(this);
//        try {
//            loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        unitMap = unitImpl.deserializeUnit("unit.txt");
        System.out.println("Unit deserialize done");

        deleteBtn.setDisable(true);


        unitTable.setRowFactory(tv -> {
            TableRow<Unit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty()) ) {
                    selectedUnit  = row.getItem();
//                    System.out.println("Single click on: "+rowData.getCode());
                    //   unitImpl.deleteCourse(rowData.getCode(), unitMap);
//                    unitTable.getItems().remove(unitTable.getSelectionModel().getSelectedItem());
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
        unitMap = unitImpl.deserializeUnit("unit.txt");

        // Transform map to array list
        unitList = new ArrayList<Unit>(unitMap.values());
        // Add list of staff to the observableArrayList
        unitData =  FXCollections.observableArrayList(unitList);

        // Set column in array to present as different attributes of staff
        codeColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("semesterEnum"));

        codeColumn.setStyle( "-fx-alignment: CENTER;");
        nameColumn.setStyle( "-fx-alignment: CENTER;");
        semesterColumn.setStyle( "-fx-alignment: CENTER;");

        // Set the created list to the unit table
        unitTable.setItems(null);
        unitTable.setItems(this.unitData);
    }

    public void backHome(ActionEvent actionEvent) {
        unitImpl.serializeUnit(unitMap, "unit.txt");
        screenController.openScreen("home.fxml", "Home Page");
        screenController.closeStage((Stage) homeBtn.getScene().getWindow());
    }

    public boolean searchUnit(String code) {
        return unitImpl.searchUnitCode(code, unitMap);
    }

    public Unit getUnit(String code) {
        return unitImpl.getUnitByCode(code, unitMap);
    }

    public void deleteUnit(ActionEvent actionEvent) {

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

