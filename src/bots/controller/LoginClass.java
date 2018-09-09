package bots.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

import bots.controller.MainStart;
import bots.controller.model.UserModel;

public class LoginClass {
	
	private MainStart start;
	
	@FXML
	private TextField usernameText;
	@FXML
	private PasswordField passwordText;
	
	public void setStart (MainStart startx)
	{
		start = startx;
	}
	
	@FXML
    private void handleOk() 
	{
		String Usernamex = usernameText.getText();
		String Passwordx = passwordText.getText();
		try {
			start.ConnectedUser = start.mySql.UserQuery.LoginUser(Usernamex, Passwordx);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (start.ConnectedUser == null)
		{
			//Report an error on login
		}
		else
		{
			if (start.ConnectedUser.GetAdmin().equals("0"))
				start.changeStageSearch();
			else
				start.changeStageAdmin();
		}
	}
	
	
	@FXML
	private void handleNewUser()
	{
		start.changeStageRegister();
	}
	
}