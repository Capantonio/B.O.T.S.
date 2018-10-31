package bots.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.SQLException;

import bots.Controller.MainStart;

public class RegisterClass {
	
	private void Register (String Username, String Email, String Name, String Password, String Surname) throws SQLException
	{
		if(MainStart.mySql.UserQuery.ExistUser(Username, Email))
			MainStart.mySql.UserQuery.RegisterUser(Name, Password, Email, Surname, Username);
	}
}