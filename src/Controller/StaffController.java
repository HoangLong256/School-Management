package Controller;

import Implementation.StaffImpl;
import Model.Staff;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import java.net.URL;
import java.util.*;

public class StaffController implements Initializable {

    static StaffImpl staffImpl = new StaffImpl();
    static Map<Integer, Staff> staffMap =  new HashMap<Integer, Staff>();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deserializeStaff();
        showAllStaff();
        loadStaff();
        System.out.println("Staff deserialize done");
    }

    public String addStaff(int id, String name, String address){
        Staff staff = new Staff();
        staff.setSid(id);
        staff.setName(name);
        staff.setAddress(address);
        staffImpl.addStaff(staff, staffMap);
        return "OK";
    }

    public void showAllStaff(){
        staffImpl.showAllStaff(staffMap);
    }

    public void loadStaff() {
        // Transform map to array list
        staffList = new ArrayList<Staff>(staffMap.values());
        // Add list of staff to the observableArrayList
        staffData =  FXCollections.observableArrayList(staffList);

        // Set column in array to present as different attributes of staff
        idColumn.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("sid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));
        addColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("address"));

        // Set the created list to the staff table
        staffTable.setItems(null);
        staffTable.setItems(this.staffData);
    }

    public Staff getStaffByID(Integer sid){
        return staffImpl.getStaffByID(sid, staffMap);
    }

    public void serializeStaff() {
        staffImpl.serializeStaff(staffMap, "staff.txt");
    }
    public Map<Integer, Staff> deserializeStaff() {
        staffMap =  staffImpl.deserializeStaff("staff.txt");
        return staffMap;
    }

    public Boolean searchStaffID(Integer sid) {
        return staffImpl.searchID(sid, staffMap);
    }
    public void backHome(ActionEvent actionEvent) {
        staffImpl.serializeStaff(staffMap, "staff.txt");
        screenController.openScreen("home.fxml", "Home Page");
        screenController.closeStage((Stage) homeBtn.getScene().getWindow());
    }
}


