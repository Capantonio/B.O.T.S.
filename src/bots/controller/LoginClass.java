package bots.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import bots.controller.MainStart;

public class LoginClass {
	
	private MainStart start;
	
	public void setStart (MainStart startx)
	{
		start = startx;
	}
	
	@FXML
    private void handleOk() 
	{
		
	}
	
	@FXML
	private void handleNewUser()
	{
		start.changeStageRegister();
	}
	
}