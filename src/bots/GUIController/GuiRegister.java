package bots.GUIController;

import java.sql.SQLException;

import bots.Controller.MainStart;
import bots.Controller.RegisterClass;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GuiRegister {

	@FXML
	 private MainStart start;
	 @FXML
	 private TextField nametext;
	 @FXML
	 private TextField surnametext;
	 @FXML
	 private TextField emailtext;
	 @FXML
	 private TextField passwordtext;
	 @FXML
	 private TextField usernametext;
	 
	 private RegisterClass controller;
	 
	 public void setStart()
	 {
		 controller = new RegisterClass();
	 }
	 
	 @FXML
	 private void handleCancel ()
	 {
		 MainStart.GUI.changeStageLogin();
	 }
	 
	 @FXML
	 private void handleSubmit () throws SQLException
	 {
		 String Username = usernametext.getText();
		 String Password = passwordtext.getText();
		 String Name = nametext.getText();
		 String Surname = surnametext.getText();
		 String Email = emailtext.getText();
		 
		 MainStart.GUI.changeStageSearch();
	 }
}
