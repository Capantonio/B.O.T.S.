package bots.GUIController;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import bots.Controller.AdminClass;
import bots.Controller.MainStart;
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

public class GuiAdmin {

	@FXML
	private TextField Name;
	@FXML
	private TextField Surname;
	@FXML
	private TextField Username;
	@FXML
	private TextField Email;
	@FXML
	private TextField Admin;
	@FXML
	private TextField Transcriber;
	@FXML
	private TextField Revisioner;
	@FXML
	private TextField Download;
	
	@FXML
	private AnchorPane Container;
	
	@FXML
	private TextField Title;
	@FXML
	private TextField Author;
	@FXML
	private TextField DataOpera;
	
	@FXML
	private AnchorPane OperaContainer;
	
	@FXML
	private AnchorPane RevContainer;
	
	public AdminClass controller;
	
	public void setStart()
	{
		controller = new AdminClass();
		controller.setStart(this);
	}
	
	@FXML
	 public void Logout() {
	  MainStart.GUI.changeStageLogin();
	 }
	
	@FXML
	public void handleSearch ()
	{
		Container.getChildren().clear();
		controller.UserSearch(Name.getText(), Surname.getText(), Email.getText(), Username.getText(), Transcriber.getText(), Admin.getText(), Revisioner.getText(), Download.getText());	
	}
	
	@FXML
	public void handleOperaSearch()
	{
		OperaContainer.getChildren().clear();
		controller.OperaSearch(Title.getText(), Author.getText(), DataOpera.getText());
	}
	
	@FXML
	public void handleRevOpera()
	{
		RevContainer.getChildren().clear();
		controller.OperaRevisioner();
	}
	
	@FXML
	public void handleRevTrsc()
	{
		RevContainer.getChildren().clear();
	}
	

	@FXML
	 public void ViewOpera(){
	  MainStart.GUI.changeStageSearch(); 
	}
	
	
	
	public void ShowUserInfo (Integer x, UserModel user)
	{
		String appo;
		SetShowUser (new Label(), 0, x * 30, 70, user.Name);
		SetShowUser (new Label(), 70, x * 30, 70, user.Surname);
		SetShowUser (new Label(), 140, x * 30, 120, user.Email);
		SetShowUser (new Label(), 280, x * 30, 70, user.Username);
		SetShowUser (new Label(), 350, x * 30, 30, user.Transcriber);
		appo = user.Admin.equals("1") ? "YES" : "NO";
		SetShowUser (new Label(), 380, x * 30, 30, appo);
		appo = user.Download.equals("1") ? "YES" : "NO";
		SetShowUser (new Label(), 410, x * 30, 30, appo);
		appo = user.Revisioner.equals("1") ? "YES" : "NO";
		SetShowUser (new Label(), 440, x * 30, 30, appo);
		
		Button Mod = new Button();
		Mod.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e)
		    {
		    	MainStart.GUI.PopupUser(user);
		    }
		});
		Mod.setText("Modify");
		Mod.setMinWidth(70);
		Mod.setLayoutX(470);
		Mod.setLayoutY(x*30);
		Container.getChildren().add(Mod);
		
		Button Trsc = new Button();
		Trsc.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e)
		    {
		    	MainStart.GUI.PopupTrsc(user);
		    }
		});
		Trsc.setText("List");
		Trsc.setMinWidth(70);
		Trsc.setLayoutX(540);
		Trsc.setLayoutY(x*30);
		Container.getChildren().add(Trsc);
	}
	
	public void Showopera (Integer x, OperaModel opera) throws SQLException
	{
		
		if (opera.Show.equals("2"))
		{
			SetShowRev (new Label(), 0, x * 30, 70, opera.Title);
			SetShowRev (new Label(), 70, x * 30, 70, opera.Author);
			SetShowRev (new Label(), 140, x * 30, 40, opera.Data);
			SetShowRev (new Label(), 180, x * 30, 70, opera.Show);
			ShowButtonView(new Button(), opera, 210,x*30,70,"View");
		}
		else
		{
			SetShowOpera (new Label(), 0, x * 30, 70, opera.Title);
			SetShowOpera (new Label(), 70, x * 30, 70, opera.Author);
			SetShowOpera (new Label(), 140, x * 30, 40, opera.Data);
			SetShowOpera (new Label(), 180, x * 30, 70, opera.Show);
			ShowButtonHide(new Button(), opera, 210,x * 30,70,"Show/Hide");
		}
		
	}
	
	public void ShowRevPage (Integer x, PageModel page, OperaModel opera)
	{
		SetShowRev (new Label(), 0, x * 30, 100, opera.Title);
		SetShowRev (new Label(), 100, x * 30, 30, page.PageNumber.toString());
		ShowPageRevButton (new Button(), 130, x * 30, 100, "Revision", opera, page.PageNumber);
	}
	
	public void ShowButtonHide(Button obj, OperaModel op, int x, int y, int width, String text) {
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		OperaContainer.getChildren().add(obj);
		obj.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e)
	    	{
	    		if (op.Show.equals("1"))
					try { MainStart.mySql.OperaQuery.SetShow(op.ID, "0");}
	    		catch (SQLException e1) { e1.printStackTrace();}
				else if (op.Show.equals("0"))
					try { MainStart.mySql.OperaQuery.SetShow(op.ID, "1");}
	    		catch (SQLException e1) { e1.printStackTrace();}
	    	}
		});
	}

	public void ShowButtonView(Button obj, OperaModel op, int x, int y, int width, String text) {
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		RevContainer.getChildren().add(obj);
		obj.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e)
	    	{
				MainStart.GUI.changeStageOpera(op, 1, 1);
	    	}
		});
	}
	
	public void ShowPageRevButton (Button obj, Integer x, Integer y, Integer width, String text, OperaModel opera, Integer num)
	{
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		RevContainer.getChildren().add(obj);
		obj.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e)
	    	{
				MainStart.GUI.changeStageOpera(opera, 2, num);
	    	}
		});
	}

	public void SetShowUser (Label obj, Integer x, Integer y, Integer width, String text)
	{
		obj = new Label();
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		Container.getChildren().add(obj);
	}
	
	public void SetShowOpera (Label obj, Integer x, Integer y, Integer width, String text)
	{
		obj = new Label();
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		OperaContainer.getChildren().add(obj);
	}
	
	public void SetShowRev (Label obj, Integer x, Integer y, Integer width, String text)
	{
		obj = new Label();
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		RevContainer.getChildren().add(obj);
	}
}
