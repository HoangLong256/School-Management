package View;

import Controller.ScreenController;
import Controller.StaffControl;
import Model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class StaffView implements Initializable {

    private StaffControl staffControl = StaffControl.getInstance();
    private Map<Integer, Staff> staffMap =  staffControl.getStaffMap();
    private ObservableList<Staff> staffData;
    private List<Staff> staffList;
    ScreenController screenController = new ScreenController();


    @FXML
    private TableView<Staff> staffTable;
    @FXML
    private TableColumn<Staff, Integer> idColumn;
    @FXML
    private TableColumn<Staff, String> nameColumn;
    @FXML
    private TableColumn<Staff, String> addColumn;
    @FXML
    private Button homeBtn;

    /*
    Name:initialize
    Purpose: thing to do before loading screen
    Passed: url, resourceBundle
    Return: none
    Input: none
    Output: none
    Effect: load the table view of staff
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staffControl.loadData();
        loadStaff();
    }




    /*
    Name: loadStaff
    Purpose: load data to table
    Passed: none
    Return: none
    Input: none
    Output: none
    Effect: load staff to table
     */
    public void loadStaff() {
        staffList = new ArrayList<Staff>(staffMap.values());
        staffData =  FXCollections.observableArrayList(staffList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("sid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));
        addColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("address"));
        staffTable.setItems(null);
        staffTable.setItems(this.staffData);
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
        screenController.openScreen("home.fxml", "Home Page");
        screenController.closeStage((Stage) homeBtn.getScene().getWindow());
    }
}


