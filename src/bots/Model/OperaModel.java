package bots.Model;

import java.sql.SQLException;

import bots.Controller.AdminClass;
import bots.Controller.SearchClass;
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
	
	/*
	@FXML
	public Label title;
	@FXML
	public Label author;
	@FXML
	public Label data;
	@FXML
	public Label show;
	@FXML
	public Button showhide = new Button();
	@FXML
	public Button accept = new Button();
	@FXML
	public Button deny = new Button();
	@FXML
	public Button view = new Button();
	
	*/
	public OperaModel (Integer id, String tit, String aut, String data, String show, Integer page)
	{
		ID = id;
		Title = tit;
		Author = aut;
		Data = data;
		Show = show;
		Lenght = page;
		Pages = new PageModel[page];
	}
	
	public PageModel GetPage (Integer num)
	{
		return Pages[num];
	}
	
	/*
	public void Showa (Integer x, AdminClass parent, Integer method) throws SQLException
	{
		SetShow (title, 0, x * 30, 70, Title);
		SetShow (author, 70, x * 30, 70, Author);
		SetShow (data, 140, x * 30, 40, Data);
		SetShow (show, 180, x * 30, 70, Show);
		if (Show.equals("2"))
		{
			ShowButton(view,210,x*30,70,"View");
			view.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
					parent.start.changeStageOpera(ID, 1, null);
		    	}
			});
		}
		else
		{
			ShowButton(showhide,210,x * 30,70,"Show/Hide");
			showhide.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
		    		if (Show.equals("1"))
						try { parent.start.mySql.OperaQuery.SetShow(ID, "0");}
		    		catch (SQLException e1) { e1.printStackTrace();}
					else if (Show.equals("0"))
						try { parent.start.mySql.OperaQuery.SetShow(ID, "1");}
		    		catch (SQLException e1) { e1.printStackTrace();}
		    	}
			});
		}
		
	}
	
	public void Shows (Integer x, SearchClass parent, Integer method) throws SQLException
	{
		SetShow (title, 0, x * 30, 70, Title);
		SetShow (author, 70, x * 30, 70, Author);
		SetShow (data, 140, x * 30, 40, Data);
		SetShow (show, 180, x * 30, 30, Show);
		ShowButton(view,210,x*30,70,"View");
		view.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e)
	    	{
				parent.ReadOpera(ID);
	    	}
		});
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
	
	public void ShowButton (Button obj, Integer x, Integer y, Integer width, String text)
	{
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		Container.getChildren().add(obj);
	}
	
	*/
}
