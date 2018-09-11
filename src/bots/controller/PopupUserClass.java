package bots.controller;

import java.sql.SQLException;

import bots.controller.model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PopupUserClass {
	
	public MainStart start;
	
	public UserModel user;
	
	@FXML
	TextField TName,
			TSurname,
			TUsername,
			TEmail,
			TTranscriber,
			TAdmin,
			TRev,
			TDownload;
	
	public void setStart (MainStart startx, UserModel xuser)
	{
		start = startx;
		user = xuser;

		TName.setText(user.Name);
  	  	TSurname.setText(user.Surname);
  	  	TUsername.setText(user.Username);
  	  	TEmail.setText(user.Email);
	  	TTranscriber.setText(user.Transcriber);
	  	TAdmin.setText(user.Admin);
	 	TRev.setText(user.Revisioner);
	  	TDownload.setText(user.Download);
	}
	
	@FXML
	private void handleApply()
	{
		user.Name = TName.getText();
  	  	user.Surname = TSurname.getText();
  	  	user.Username = TUsername.getText();
  	  	user.Email = TEmail.getText();
	  	user.Transcriber = TTranscriber.getText();
	  	user.Admin = TAdmin.getText();
	 	user.Revisioner = TRev.getText();
	  	user.Download = TDownload.getText();
	  		try {
			start.mySql.UserQuery.ChangeUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	start.ClosePopup();
	}
	
	@FXML
	private void handleCancel()
	{
		start.ClosePopup();
	}
	
	@FXML
	private void handleActive()
	{
	}

}
