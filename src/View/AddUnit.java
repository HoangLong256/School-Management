package View;

import Controller.StaffControl;
import Controller.UnitControl;
import View.enumerated.level;
import View.enumerated.semesterEnum;
import Model.Staff;
import Model.Unit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AddUnit implements Initializable {
    private StaffControl staffControll = StaffControl.getInstance();
    private UnitControl unitControl = UnitControl.getInstance();
    private Map<String, Unit> unitMap = unitControl.getUnitMap();
//    private UnitService unitImpl = new UnitService();
    private ScreenController screenController =  new ScreenController();

    @FXML
    private Button saveBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private TextField nameText;

    @FXML
    private TextField lecturerIDText;

    @FXML
    private TextField examinerIDText;

    @FXML
    private ComboBox<level> yearComboBox;

    @FXML
    private ComboBox<semesterEnum> semesterComboBox;

    @FXML
    private TextField shField;

    @FXML
    private TextField numField;



    String code, sh, year,number, name, semester, examinerID, lecID;
    Alert alert = new Alert(Alert.AlertType.ERROR);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        unitMap = unitControl.loadData();
        this.yearComboBox.setItems(FXCollections.observableArrayList(level.values()));
        this.semesterComboBox.setItems(FXCollections.observableArrayList(semesterEnum.values()));

    }




    public void closeAction(ActionEvent actionEvent) {
        screenController.closeStage((Stage) closeBtn.getScene().getWindow());

    }

    public Boolean saveAction(ActionEvent actionEvent) {
        name = nameText.getText();
        examinerID = examinerIDText.getText();
        lecID = lecturerIDText.getText();
        sh = shField.getText();
        number = numField.getText();

        if(sh.equals("") || number.equals("") || name.equals("") || examinerID.equals("") ||lecID.equals("") || yearComboBox.getSelectionModel().isEmpty() )  {
            alert.setContentText("Missing required information");
            alert.show();
            return Boolean.FALSE;
        }

        if(sh.length() > 4 || number.length() > 3) {
            alert.setContentText("Invalid length of short hand or number");
            alert.show();
        }

        if(semesterComboBox.getSelectionModel().isEmpty()) {
            alert.setContentText("The semesterEnum need to be chosen");
            alert.show();
            return Boolean.FALSE;
        } else {
            semester = semesterComboBox.getSelectionModel().getSelectedItem().toString();
        }

//        if(!examinerID.matches("[0-9]*") || !lecID.matches("[0-9]*")) {
//            alert.setContentText("The ID of director and deputy only contain number");
//            alert.show();
//            return Boolean.FALSE;
//        }
//        else if(examinerID.length() < 10 || lecID.length() < 10){
//            alert.setContentText("The staff ID have to contain at least 10 digits");
//            alert.show();
//            return Boolean.FALSE;
//        }
//        if(!staffController.searchStaffID(Integer.parseInt(examinerID)) || !staffController.searchStaffID(Integer.parseInt(lecID))) {
//            alert.setContentText("Cant found examiner or lecturer");
//            alert.show();
//            return Boolean.FALSE;
//        }
//        Staff examiner = staffController.getStaffByID(Integer.parseInt(examinerID));
//        Staff lecturer = staffController.getStaffByID(Integer.parseInt(lecID));
        code = sh.toUpperCase() + (yearComboBox.getSelectionModel().getSelectedIndex()+1) + number;
        Staff examiner = staffControll.getStaffByID(Integer.parseInt("1122334455"));
        Staff lecturer = staffControll.getStaffByID(Integer.parseInt("1122443355"));
        if(staffControll.getStaffByID(Integer.parseInt("1122443355")) == null){
            System.out.println("Failed to assign");
        }
        Unit unit = new Unit();
        unit.setCode(code);
        unit.setName(name);
        unit.setSemester(semester);
        unit.setExaminer(examiner);
        unit.setLecturer(lecturer);

        if(!unitControl.addUnit(unit)) {
            alert.setContentText("");
            alert.show();
            return Boolean.FALSE;
        }
        unitControl.saveData();
        screenController.closeStage((Stage) saveBtn.getScene().getWindow());
        return Boolean.TRUE;
    }


}
