package bots.controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Stage;

import bots.controller.DAO.*;
import bots.controller.model.*;

public class MainStart extends Application {

	private Stage primaryStage;
	public MysqlConnection mySql;
	public UserModel ConnectedUser;
	
	@Override
	public void start(Stage primaryStage) {
		
		mySql = new MysqlConnection();
		
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
	
	public void changeStageSearch ()
	{
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainStart.class.getResource("view/SearchView.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(personOverview);
        primaryStage.setScene (scene);
        
        SearchClass controller = loader.getController();
        controller.setStart(this);
        
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void changeStageOpera (Integer opload)
	{
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainStart.class.getResource("view/OperaView.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(personOverview);
        primaryStage.setScene (scene);
        
        OperaClass controller = loader.getController();
        controller.setStart(this, opload);
        
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
