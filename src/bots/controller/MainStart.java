package bots.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Stage;

public class MainStart extends Application {

	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("B.O.T.S.  Book On The Shelf");

        initRootLayout();
	}
	
	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainStart.class.getResource("view/Loginview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(personOverview);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            LoginClass controller = loader.getController();
            controller.setStart(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void changeStageRegister ()
	{
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainStart.class.getResource("view/RegisterView.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(personOverview);
        primaryStage.setScene (scene);
        
        RegisterClass controller = loader.getController();
        controller.setStart(this);
        
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void changeStageLogin ()
	{
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainStart.class.getResource("view/LoginView.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(personOverview);
        primaryStage.setScene (scene);
        
        LoginClass controller = loader.getController();
        controller.setStart(this);
        
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
