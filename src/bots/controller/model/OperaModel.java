package bots.controller.model;

import java.sql.SQLException;

import bots.controller.AdminClass;
import bots.controller.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class OperaModel {
	
	public Integer ID;
	public String Title;
	public String Author;
	public String Show;
	public String Data;
	public PageModel[] Pages;
	public Integer Lenght;
	
	public AnchorPane Container;
	
	@FXML
	public Label title;
	@FXML
	public Label author;
	@FXML
	public Label data;
	@FXML
	public Label show;
	@FXML
	public Button showhide;
	@FXML
	public Button accept;
	@FXML
	public Button deny;
	
	public OperaModel (Integer id, String tit, String aut, String data, String show, AnchorPane cont, Integer x, AdminClass parent)
	{
		ID = id;
		Title = tit;
		Author = aut;
		Data = data;
		Show = show;
		Container = cont;
		Show (x, parent);
	}
	
	public OperaModel (Integer id, String Tit, String Aut, String datax, Integer PageN)
	{
		ID = id;
		Title = Tit;
		Author = Aut;
		Data = datax;
		Pages = new PageModel[PageN+1];
		Lenght = PageN+1;
	}

	public PageModel GetPage (Integer num)
	{
		return Pages[num];
	}
	
	public Integer GetLenght ()
	{
		return Lenght;
	}
	
	public String GetTitle ()
	{
		return Title;
	}
	
	public void Show (Integer x, AdminClass parent)
	{
		SetShow (title, 0, x * 30, 70, Title);
		SetShow (author, 70, x * 30, 70, Author);
		SetShow (data, 140, x * 30, 40, Data);
		SetShow (show, 180, x * 30, 30, Show);
		if (Show.equals("1") || Show.equals("0"))
		{
			showhide = new Button();
			showhide.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
		    		if (Show.equals("1"))
		    		{
		    			try {
							parent.start.mySql.OperaQuery.SetShow(ID, "0");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    		}
		    		else if (Show.equals("0"))
		    		{
		    			try {
							parent.start.mySql.OperaQuery.SetShow(ID, "1");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    		}
		    	}
			});
			showhide.setText("Show/Hide");
			showhide.setMinWidth(70);
			showhide.setLayoutX(210);
			showhide.setLayoutY(x*30);
			Container.getChildren().add(showhide);
		}
		else
		{
			showhide = new Button();
			showhide.setText("View");
			showhide.setLayoutX(210);
			showhide.setLayoutY(x*30);
			showhide.setPrefWidth(60);
			showhide.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
		    		parent.start.changeStageOpera(ID);
		    	}
			});
			Container.getChildren().add(showhide);
			accept = new Button();

			accept.setText("View");
			accept.setLayoutX(270);
			accept.setLayoutY(x*30);
			accept.setPrefWidth(80);
			accept.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
					try {
						parent.start.mySql.OperaQuery.SetShow(ID, "1");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
			});
			Container.getChildren().add(accept);

			deny = new Button();
			deny.setText("View");
			deny.setLayoutX(270);
			deny.setLayoutY(x*30);
			deny.setPrefWidth(80);
			deny.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
		    		
		    	}
			});
			Container.getChildren().add(deny);
		}
	}
	
	public void SetShow (Label obj, Integer x, Integer y, Integer width, String text)
	{
		obj = new Label();
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		Container.getChildren().add(obj);
	}
}
