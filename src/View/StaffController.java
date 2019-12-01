package View;

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

public class StaffController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staffControl.loadData();
        loadStaff();
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


    public void backHome(ActionEvent actionEvent) {
        screenController.openScreen("home.fxml", "Home Page");
        screenController.closeStage((Stage) homeBtn.getScene().getWindow());
    }
}


