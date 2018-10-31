package bots.GUIController;

import java.io.IOException;
import java.sql.SQLException;

import bots.Controller.AdminClass;
import bots.Controller.LoginClass;
import bots.Controller.PopupUserClass;
import bots.Controller.RegisterClass;
import bots.Controller.SearchClass;
import bots.Controller.TranscribeListClass;
import bots.Controller.ViewerClass;
import bots.Model.UserModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StageController {
	
	public Stage primaryStage, secondaryStage;
	public UserModel ConnectedUser;
	
	public StageController(Stage stage)
	{
		primaryStage = stage;
		primaryStage.setTitle("B.O.T.S.  Book On The Shelf");
		
		changeStageLogin();
	}
	
	public void changeStageLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/bots/View/LoginView.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(personOverview);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            GuiLogin controller = loader.getController();
            controller.setStart();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void changeStageRegister ()
	{
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/bots/View/RegisterView.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(personOverview);
        primaryStage.setScene (scene);
        primaryStage.centerOnScreen();
        
        GuiRegister controller = loader.getController();
        controller.setStart();
        
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
            loader.setLocation(getClass().getResource("/bots/View/UserModifyPopup.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(personOverview);
            secondaryStage.setScene(scene);
            secondaryStage.sizeToScene();
            secondaryStage.show();
            
            GuiUser controller = loader.getController();
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
            loader.setLocation(getClass().getResource("/bots./View/TranscribeListPopup.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(personOverview);
            secondaryStage.setScene(scene);
            secondaryStage.sizeToScene();
            secondaryStage.show();
            
            GuiTranscriber controller = loader.getController();
            controller.setStart(this, user);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void ClosePopup ()
	{
		secondaryStage.close();
	}
	
	public void changeStageAdmin ()
	{
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/bots/View/AdminView.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(personOverview);
        primaryStage.setScene (scene);
        primaryStage.centerOnScreen();
        
        GuiAdmin controller = loader.getController();
        controller.setStart(this);
        
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
		
	public void changeStageSearch ()
	{
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/bots/View/SearchView.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(personOverview);
        primaryStage.setScene (scene);
        primaryStage.centerOnScreen();
        
        GuiSearch controller = loader.getController();
        controller.setStart();
        
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void changeStageOpera (Integer opload, Integer Method, Integer page)
	{
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/bots/View/OperaView.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(personOverview);
        primaryStage.setScene (scene);
        primaryStage.centerOnScreen();
        
        GuiViewer controller = loader.getController();
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

}
