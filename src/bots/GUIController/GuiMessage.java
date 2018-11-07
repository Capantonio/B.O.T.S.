package bots.GUIController;

import java.util.Iterator;
import java.util.LinkedList;

import bots.Controller.MessageClass;
import bots.Model.NotificationModel;
import bots.Model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class GuiMessage {

	@FXML
	public AnchorPane container;
	
	public LinkedList<NotificationModel> ris;
	
	public MessageClass controller;
	
	public void setStart()
	{
		controller = new MessageClass();
	}
	
	public void ShowResult()
	{
		ris = controller.GetResult();
		Iterator <NotificationModel> it = ris.iterator();
		Integer count = 0;
        while(it.hasNext()) {
        	NotificationModel x = it.next();
        	ShowInfo (new Label(), 0, count * 30, 70, x.Data.toString());
			ShowInfo (new Label(), 70, count * 30, 350, x.Text);
        	count++;
        }
	}
	
	public void ShowInfo(Label obj, Integer x, Integer y, Integer width, String text)
	{
		obj = new Label();
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		container.getChildren().add(obj);
	}
}
