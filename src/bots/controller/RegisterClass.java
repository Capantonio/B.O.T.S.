package bots.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import bots.controller.MainStart;

public class RegisterClass {
	
	private MainStart start;
	
	public void setStart (MainStart startx)
	{
		start = startx;
	}
	
	@FXML
	private void handleCancel ()
	{
		start.changeStageLogin();
	}
	
	@FXML
	private void handleSubmit ()
	{
		
		start.changeStageLogin();
	}

}
