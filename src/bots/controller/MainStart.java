package bots.controller;

import java.io.IOException;
import java.sql.SQLException;

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
	private Stage secondaryStage;
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
	
	public void PopupUser(UserModel user) {
        try {
        	secondaryStage = new Stage();
        	secondaryStage.centerOnScreen();
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainStart.class.getResource("view/UserModifyPopup.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(personOverview);
            secondaryStage.setScene(scene);
            secondaryStage.sizeToScene();
            secondaryStage.show();
            
            PopupUserClass controller = loader.getController();
            controller.setStart(this, user);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void PopupTrsc(UserModel user) {
        try {
        	secondaryStage = new Stage();
        	secondaryStage.centerOnScreen();
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainStart.class.getResource("view/TranscribeListPopup.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(personOverview);
            secondaryStage.setScene(scene);
            secondaryStage.sizeToScene();
            secondaryStage.show();
            
            TranscribeListClass controller = loader.getController();
            controller.setStart(this, user);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void ClosePopup ()
	{
		secondaryStage.close();
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
        primaryStage.centerOnScreen();
        
        RegisterClass controller = loader.getController();
        controller.setStart(this);
        
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void changeStageAdmin ()
	{
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainStart.class.getResource("view/AdminView.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(personOverview);
        primaryStage.setScene (scene);
        primaryStage.centerOnScreen();
        
        AdminClass controller = loader.getController();
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
        primaryStage.centerOnScreen();
        
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
        primaryStage.centerOnScreen();
        
        SearchClass controller = loader.getController();
        controller.setStart(this);
        
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void changeStageOpera (Integer opload, Integer Method, Integer page)
	{
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainStart.class.getResource("view/OperaView.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(personOverview);
        primaryStage.setScene (scene);
        primaryStage.centerOnScreen();
        
        ViewerClass controller = loader.getController();
        try {
			controller.setStart(this, opload, Method, page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	public static void main(String[] args) {
		 System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		launch(args);
	}
}
