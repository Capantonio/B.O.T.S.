package bots.controller;

import java.sql.SQLException;

import bots.controller.model.PageModel;
import bots.controller.model.UserModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	public Button[] Add = new Button[100];
	
	@FXML
	public Label[] obj = new Label[100], obj2 = new Label[100];
	public Integer[] idPage = new Integer[100];
	
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
	
	@FXML
	public void handleAdd() throws SQLException
	{
		PageModel x = start.mySql.PageQuery.GetPageFromOpera(start.mySql.OperaQuery.GetIdOpera(Title.getText()), Integer.parseInt(Page.getText()));
		if (!(x.equals(null)))
			start.mySql.UserQuery.AddTrscList(user.ID, x.PageID);
	}
	
	public void ShowPermission (Integer page, String title, Integer x, Integer id)
	{
		idPage[x] = id;
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
		Add[x] = new Button();
		Add[x].setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e)
		    {
		    	try {
					start.mySql.UserQuery.RemoveTrscList(user.ID, idPage[x]);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		Add[x].setText("Remove");
		Add[x].setMinWidth(40);
		Add[x].setLayoutX(210);
		Add[x].setLayoutY(x*30);
		Container.getChildren().add(Add[x]);
	}
}
