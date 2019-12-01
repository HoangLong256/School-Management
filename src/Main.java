import Controller.CourseControl;
import Controller.StaffControl;
import Controller.UnitControl;
import View.ScreenController;
import View.StaffController;
import Model.Staff;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.Map;

public class Main extends Application {
    ScreenController screenController = new ScreenController();
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FrontEnd/home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        StaffControl staffControl = StaffControl.getInstance();
        CourseControl courseControl = CourseControl.getInstance();
        UnitControl unitControl = UnitControl.getInstance();

        if(staffControl.getStaffMap().isEmpty()) {
            uploadStaffsInfo("src/FileData/staffs.txt");
            staffControl.showAllStaff();
        }else{
            System.out.println("Not empty");
        }
        System.out.println("Waiting to deserialize");
        staffControl.loadData();
        unitControl.loadData();
        courseControl.loadData();
        launch(args);
    }


    public static void uploadStaffsInfo(String path) throws IOException {
        StaffControl staffControl = StaffControl.getInstance();
        File file = new File(path);

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int numberOfStaff = Integer.parseInt(br.readLine());

        for(int i = 0; i < numberOfStaff; i++) {
            String[] idName = br.readLine().split(",");
            String address = br.readLine();
            staffControl.addStaff(Integer.parseInt(idName[0]), idName[1], address);
        }
        staffControl.saveData();

        br.close();
        fr.close();
    }

}
