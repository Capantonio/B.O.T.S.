package bots.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import bots.controller.MainStart;

public class LoginClass {
	
	private MainStart start;
	
	@FXML
	private TextField usernameText;
	@FXML
	private TextField passwordText;
	
	public void setStart (MainStart startx)
	{
		start = startx;
	}
	
	@FXML
    private void handleOk() 
	{
		String Usernamex = usernameText.getText();
		String Passwordx = passwordText.getText();
		if (Usernamex.equals("Admin") && Passwordx.equals("Admin"));
			start.changeStageSearch();
	}
	
	@FXML
	private void handleNewUser()
	{
		
		start.changeStageRegister();
	}
	
}