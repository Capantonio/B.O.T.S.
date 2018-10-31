package bots.GUIController;

import bots.Controller.PopupUserClass;
import bots.Model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GuiUser {

	@FXML
	public TextField TName,
			TSurname,
			TUsername,
			TEmail,
			TTranscriber,
			TAdmin,
			TRev,
			TDownload;
	
	public PopupUserClass controller;
	
	public void setStart (UserModel user)
	{
		
	}
}
