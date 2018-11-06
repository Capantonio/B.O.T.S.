package bots.GUIController;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import bots.Controller.MainStart;
import bots.Controller.TranscribeListClass;
import bots.Model.OperaModel;
import bots.Model.PageModel;
import bots.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	public AnchorPane Container;
	
	public TranscribeListClass controller;
	
	public void setStart (UserModel xuser)
	{
		controller = new TranscribeListClass();
		controller.setStart(xuser);
		Refresh();
	}
	
	@FXML
	public void handleAdd() throws SQLException
	{
		controller.Add(Title.getText(), Page.getText());
		Refresh();
	}
	
	public void Refresh ()
	{
		LinkedList<String[]> ret;
		ret = controller.Refresh();
		Iterator <String[]> it = ret.iterator();
		Integer count = 0;
        while(it.hasNext()) {
        	String[] x = it.next();
        	ShowPermission(new Label(), new Label(),new Button(), Integer.parseInt(x[0]), x[1], count, Integer.parseInt(x[2]), this);
        	count++;
        }
	}
	
	public void ShowPermission (Label l1, Label l2, Button b1, Integer page, String title, Integer x, Integer id, GuiTranscriber up)
	{
		l1 = new Label();
		l1.setText(page.toString());
		l1.setLayoutX(180);
		l1.setLayoutY(30*x);
		l1.setPrefWidth(30);
		Container.getChildren().add(l1);
		l2 = new Label();
		l2.setText(title);
		l2.setLayoutX(0);
		l2.setLayoutY(30*x);
		l2.setPrefWidth(170);
		Container.getChildren().add(l2);
		b1 = new Button();
		b1.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e)
		    {
		    	try {
					MainStart.mySql.UserQuery.RemoveTrscList(controller.user.ID, id);
					up.Refresh();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		b1.setText("Remove");
		b1.setMinWidth(40);
		b1.setLayoutX(210);
		b1.setLayoutY(x*30);
		Container.getChildren().add(b1);
	}
}
