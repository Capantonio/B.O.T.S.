package bots.GUIController;

import bots.Controller.LoginClass;
import bots.Controller.MainStart;
import bots.DAO.MysqlConnection;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class GuiLogin {

	@FXML
	private TextField usernameText;
	@FXML
	private PasswordField passwordText;
	
	public LoginClass controller;
	
	public void setStart ()
	{
		controller = new LoginClass();
	}
	
	@FXML
    private void handleOk() 
	{
		if (controller.Login(usernameText.getText(), passwordText.getText()))
		{
			if (MainStart.ConnectedUser.Admin.equals("0"))
				MainStart.GUI.changeStageSearch();
			else
				MainStart.GUI.changeStageAdmin();
		}
	}
	
	@FXML
	private void handleNewUser()
	{
		MainStart.GUI.changeStageRegister();
	}
}
