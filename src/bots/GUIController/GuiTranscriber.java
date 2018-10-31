package bots.GUIController;

import bots.Controller.TranscribeListClass;
import bots.Model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class GuiTranscriber {

	@FXML
	public TextField Title;
	@FXML
	public TextField Page;
	@FXML
	public Button[] Add = new Button[100];
	
	@FXML
	public Label[] obj = new Label[100], obj2 = new Label[100];
	
	@FXML
	public AnchorPane Container;
	
	public TranscribeListClass controller;
	
	public void setStart (UserModel user)
	{
		
	}
}
