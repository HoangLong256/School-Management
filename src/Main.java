import Controller.CourseControl;
import Controller.StaffControl;
import Controller.UnitControl;
import Service.PreDeterminedService;
import Controller.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

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
        PreDeterminedService preDeterminedService = new PreDeterminedService();

        if(!preDeterminedService.checkEmptyData()) {
            preDeterminedService.readDataFromFile();
            System.out.println("Pre Determined Data");
            staffControl.displayStaffs();
        }
        System.out.println("Starting ... ");
        staffControl.loadData();
        unitControl.loadData();
        courseControl.loadData();
        launch(args);
    }




}
