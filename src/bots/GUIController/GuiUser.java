package bots.GUIController;

import java.sql.SQLException;

import bots.Controller.MainStart;
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
		TName.setText(user.Name);
  	  	TSurname.setText(user.Surname);
  	  	TUsername.setText(user.Username);
  	  	TEmail.setText(user.Email);
	  	TTranscriber.setText(user.Transcriber);
	  	TAdmin.setText(user.Admin);
	 	TRev.setText(user.Revisioner);
	  	TDownload.setText(user.Download);
	  	
	  	controller = new PopupUserClass();
	  	controller.user = user;
	}
	
	@FXML
	private void handleApply()
	{
		controller.Apply(TName.getText(), TSurname.getText(), TUsername.getText(), TEmail.getText(), TTranscriber.getText(), TAdmin.getText(), TRev.getText(), TDownload.getText());
	  	MainStart.GUI.ClosePopup();
	}
	
	@FXML
	private void handleCancel()
	{
		MainStart.GUI.ClosePopup();
	}
}
