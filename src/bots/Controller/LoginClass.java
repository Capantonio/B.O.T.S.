package bots.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.sql.SQLException;

import bots.Controller.MainStart;
import bots.GUIController.StageController;

public class LoginClass {
	
    public Boolean Login(String xuser, String xpsw) 
	{
		String Usernamex = xuser;
		String Passwordx = xpsw;
		try {
			MainStart.ConnectedUser = MainStart.mySql.UserQuery.LoginUser(Usernamex, Passwordx);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (MainStart.ConnectedUser == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	
	@FXML
	private void NewUser()
	{
		MainStart.GUI.changeStageRegister();
	}
	
}