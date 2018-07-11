package bots.controller.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import bots.controller.*;

public class ResultModel 
{
	private MainStart Main;
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
	private ScrollPane Container;
	
	public ResultModel (String xTit, String xAut, String xYea, Integer xid, MainStart xmain, ScrollPane xpane)
	{
		Main = xmain;
		OperaID = xid;
		Container = xpane;
		Title = new Label();
		Title.setText(xTit);
		Author = new Label();
		Author.setText(xAut);
		Year = new Label();
		Year.setText(xYea);
		OpenB = new Button();
		OpenB.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e)
		    {
		    	Main.changeStageOpera(xid);
		    }
		});
	}
	
}
