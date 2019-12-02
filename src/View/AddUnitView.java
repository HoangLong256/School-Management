package View;

import Controller.ScreenController;
import Controller.StaffControl;
import Controller.UnitControl;
import View.enumerated.yearEnum;
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

public class AddUnitView implements Initializable {
    private StaffControl staffControll = StaffControl.getInstance();
    private UnitControl unitControl = UnitControl.getInstance();
    private Map<String, Unit> unitMap = unitControl.getUnitMap();
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
    private ComboBox<yearEnum> yearComboBox;

    @FXML
    private ComboBox<semesterEnum> semesterComboBox;

    @FXML
    private TextField shText;

    @FXML
    private TextField digitText;



    String unitCode, shorthand ,digit, name, semester, examinerID, lecturerID;
    int year;
    Alert alertError = new Alert(Alert.AlertType.ERROR);
    Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

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
    public void initialize(URL location, ResourceBundle resources) {
        unitMap = unitControl.loadData();
        this.yearComboBox.setItems(FXCollections.observableArrayList(yearEnum.values()));
        this.semesterComboBox.setItems(FXCollections.observableArrayList(semesterEnum.values()));

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
    Purpose: save unit to database
    Passed: action event
    Return: true if success, false if failed
    Input: none
    Output: alert
    Effect: check validation then add unit to database
     */
    public Boolean saveAction(ActionEvent actionEvent) {
        name = nameText.getText();
        examinerID = examinerIDText.getText();
        lecturerID = lecturerIDText.getText();
        shorthand = shText.getText();
        digit = digitText.getText();

        if(shorthand.equals("") || digit.equals("") || name.equals("") || examinerID.equals("")
                ||lecturerID.equals("") || yearComboBox.getSelectionModel().isEmpty()
                || semesterComboBox.getSelectionModel().isEmpty())  {
            alertError.setContentText("Missing required information");
            alertError.show();
            return Boolean.FALSE;
        }

        semester = semesterComboBox.getSelectionModel().getSelectedItem().toString();
        year = yearComboBox.getSelectionModel().getSelectedIndex() + 1;
        unitCode = shorthand.toUpperCase() + year + digit;
        String unitCodeError = unitCodeValidator(shorthand,digit,unitCode);
        if(unitCodeError != null){
            alertError.setContentText(unitCodeError);
            alertError.show();
            return Boolean.FALSE;
        }

        String staffError = staffValidator(examinerID, lecturerID);
        if(staffError != null){
            alertError.setContentText(staffError);
            alertError.show();
            return Boolean.FALSE;
        }


        Staff examiner = staffControll.getStaffByID(Integer.parseInt(examinerID));
        Staff lecturer = staffControll.getStaffByID(Integer.parseInt(lecturerID));
//        Staff examiner = staffControll.getStaffByID(Integer.parseInt("1122334455"));
//        Staff lecturer = staffControll.getStaffByID(Integer.parseInt("1122443355"));

        Unit unit = new Unit();
        unit.setCode(unitCode);
        unit.setName(name);
        unit.setSemester(semester);
        unit.setExaminer(examiner);
        unit.setLecturer(lecturer);
        unit.setYear(year);

        if(!unitControl.addUnit(unit)) {
            alertError.setContentText("Failed to add Unit");
            alertError.show();
            return Boolean.FALSE;
        }
        alertInfo.setContentText("Unit Added");
        alertInfo.show();
        clear();
        unitControl.saveData();
//        screenController.closeStage((Stage) saveBtn.getScene().getWindow());
        return Boolean.TRUE;
    }


    /*
    Name: idValidator
    Purpose: validate the id
    Passed: sh - unit shorthand
            number - unit id digit
            unitCode - unit code
    Return: String if error
    Input: none
    Output: none
    Effect: check unit code and return result
     */
    public String unitCodeValidator(String sh,String number, String unitCode){
        if(sh.length() != 4){
            return "Unit Error: Shorthand must be 4 letters";
        }
        if(number.length() != 3){
            return "Unit Error: ID must be 3 digit number";
        }
        if(unitControl.searchUnit(unitCode)){
            return "Unit Error: Invalid Unit Code";
        }
        try{
            Integer.parseInt(number);
            return null;
        }catch (NumberFormatException e){
            return "Unit Error: Number format";
        }catch (IndexOutOfBoundsException e){
            return "Index Exception";
        }
    }

    /*
    Name: staffValidator
    Purpose: validate staff
    Passed: examinerID - staffID
            lecturerID - staffID
    Return: String if error
    Input: none
    Output: none
    Effect: validate the staff if
     */
    public String staffValidator (String examinerID, String lecturerID){
        try {
            Integer.parseInt(examinerID);
            Integer.parseInt(lecturerID);
            if(staffControll.getStaffByID(Integer.parseInt(examinerID))==null
                    ||staffControll.getStaffByID(Integer.parseInt(lecturerID)) == null){
                return "Staff Error: Invalid ID";
            }
            return null;
        } catch (NumberFormatException e) {
            return "Staff Error: Number Format";
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
        nameText.setText("");
        lecturerIDText.setText("");
        examinerIDText.setText("");
        shText.setText("");
        digitText.setText("");

    }


}
