package bots.controller;

import java.sql.SQLException;

import bots.controller.model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TranscribeListClass {

	public MainStart start;
	public UserModel user;
	
	@FXML
	public TextField Title;
	@FXML
	public TextField Page;
	
	@FXML
	public Label[] obj = new Label[100], obj2 = new Label[100];
	
	@FXML
	public AnchorPane Container;
	
	public void setStart (MainStart startx, UserModel xuser)
	{
		start = startx;
		user = xuser;
		try {
			start.mySql.UserQuery.LoadTrasncribeList(xuser.ID, this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void handleAdd()
	{
		
	}
	
	public void ShowPermission (Integer page, String title, Integer x)
	{
		obj[x] = new Label();
		obj[x].setText(page.toString());
		obj[x].setLayoutX(180);
		obj[x].setLayoutY(30*x);
		obj[x].setPrefWidth(30);
		Container.getChildren().add(obj[x]);
		obj2[x] = new Label();
		obj2[x].setText(title);
		obj2[x].setLayoutX(0);
		obj2[x].setLayoutY(30*x);
		obj2[x].setPrefWidth(170);
		Container.getChildren().add(obj2[x]);
	}
}
