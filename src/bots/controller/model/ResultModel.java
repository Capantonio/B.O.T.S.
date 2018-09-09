package bots.controller.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import bots.controller.*;

public class ResultModel 
{
	private Integer OperaID;
	@FXML
	private Label Title;
	@FXML
	private Label Author;
	@FXML
	private Label Year;
	@FXML
	private Button OpenB;
	@FXML
	private AnchorPane cont;
	
	private SearchClass object;
	
	public ResultModel (String xTit, String xAut, String xYea, Integer xid, SearchClass xobj, AnchorPane xcont, Integer count)
	{
		cont = xcont;
		object = xobj;
		OperaID = xid;
		Title = new Label();
		Title.setText(xTit);
		Title.setLayoutX(0);
		Title.setLayoutY(count*30);
		cont.getChildren().add(Title);
		Author = new Label();
		Author.setText(xAut);
		Author.setLayoutX(150);
		Author.setLayoutY(count*30);
		cont.getChildren().add(Author);
		Year = new Label();
		Year.setText(xYea);
		Year.setLayoutX(220);
		Year.setLayoutY(count*30);
		cont.getChildren().add(Year);
		OpenB = new Button();
		OpenB.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e)
		    {
		    	object.ReadOpera(xid);
		    }
		});
		OpenB.setText("Open Opera");
		OpenB.setMinWidth(70);
		OpenB.setLayoutX(280);
		OpenB.setLayoutY(count*30);
		cont.getChildren().add(OpenB);
	}
	
}
