package bots.GUIController;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import bots.Controller.MainStart;
import bots.Controller.SearchClass;
import bots.Model.OperaModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class GuiSearch {
	
	@FXML
	private TextField Search;
	@FXML
	private TextField AutSearch;
	@FXML
	private TextField YearSearch;
	
	@FXML
	private AnchorPane Container;
	@FXML
	private ScrollPane Scrollp;
	@FXML
	private Button Admin;
	
	private SearchClass controller;
	
	public void setStart()
	{
		controller = new SearchClass();
		if (MainStart.ConnectedUser.Admin.equals("0"))
			Admin.setVisible(false);
	}
	
	@FXML
	public void Logout() {
		MainStart.GUI.changeStageLogin();	
	}
	
	@FXML
	public void HandleSearch () throws SQLException
	{
		LinkedList<OperaModel> res;
		Container.getChildren().clear();
		
		res = controller.Search(Search.getText(), AutSearch.getText(), YearSearch.getText());
		
		Iterator <OperaModel> it = res.iterator();
		Integer count = 0;
        while(it.hasNext()) {
        	OperaModel x = it.next();
        	
        	Shows(count, x, 0);
        	count++;
        }
	}
	
	@FXML
	public void handleLoad () throws SQLException
	{
		controller.Load();
	}
	
	@FXML
	public void handleAdmin()
	{
		MainStart.GUI.changeStageAdmin();
	}
	
	public void Shows (Integer x, OperaModel op, Integer method) throws SQLException
	{
		SetShow (new Label(), 0, x * 30, 70, op.Title);
		SetShow (new Label(), 70, x * 30, 70, op.Author);
		SetShow (new Label(), 140, x * 30, 40, op.Data);
		ShowButton(new Button(),210,x*30,70,"View",op,method);
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
	
	public void ShowButton (Button obj, Integer x, Integer y, Integer width, String text, OperaModel op, Integer method)
	{
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		obj.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e)
	    	{
				MainStart.GUI.changeStageOpera(op, method, 1);
	    	}
		});
		Container.getChildren().add(obj);
	}
	
}
