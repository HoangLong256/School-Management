import Controller.ScreenController;
import Controller.StaffController;
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
        Parent root = FXMLLoader.load(getClass().getResource("View/home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        StaffController staffController = new StaffController();
        Map<Integer, Staff> staffMap = staffController.deserializeStaff();
        if(staffMap == null) {
            uploadStaffsInfo("c:/temp/staffs.txt");
            staffController.showAllStaff();
        }
        System.out.println("Waiting to deserialize");
        launch(args);
    }


    public static void uploadStaffsInfo(String path) throws IOException {
        StaffController staffController = new StaffController();
        File file = new File(path);

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int numberOfStaff = Integer.parseInt(br.readLine());

        for(int i = 0; i < numberOfStaff; i++) {
            String[] idName = br.readLine().split(",");
            String address = br.readLine();
            staffController.addStaff(Integer.parseInt(idName[0]), idName[1], address);
        }
        staffController.serializeStaff();

        br.close();
        fr.close();
    }

}
