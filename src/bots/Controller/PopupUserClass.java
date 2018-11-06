package bots.Controller;

import java.sql.SQLException;

import bots.Model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PopupUserClass {
	
	public UserModel user;
	
	public void Apply(String TName, String TSurname, String TUsername, String TEmail, String TTranscriber, String TAdmin, String TRev, String TDownload)
	{
		user.Name = TName;
  	  	user.Surname = TSurname;
  	  	user.Username = TUsername;
  	  	user.Email = TEmail;
	  	user.Transcriber = TTranscriber;
	  	user.Admin = TAdmin;
	 	user.Revisioner = TRev;
	  	user.Download = TDownload;
		try {
			MainStart.mySql.UserQuery.ChangeUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
