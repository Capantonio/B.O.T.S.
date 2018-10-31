package bots.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import bots.DAO.*;
import bots.Model.*;
import bots.GUIController.*;

public class MainStart extends Application {

	public static StageController GUI;
	public static MysqlConnection mySql;
	public static UserModel ConnectedUser;
	
	@Override
	public void start(Stage primaryStage) {
		
		mySql = new MysqlConnection();
		GUI = new StageController(primaryStage);
	}
	
	public static void main(String[] args) {
		 System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		launch(args);
	}
}
