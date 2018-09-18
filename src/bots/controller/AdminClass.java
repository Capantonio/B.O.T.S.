package bots.controller;

import bots.controller.model.*;

import java.sql.SQLException;
import java.util.LinkedList;

import bots.controller.DAO.*;
import javafx.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class AdminClass {

	public MainStart start;
	
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
	
	
	private LinkedList<UserModel> res = new LinkedList<UserModel>();
	
	private LinkedList<OperaModel> opres = new LinkedList<OperaModel>();
	
	private LinkedList<PageModel> prev = new LinkedList<PageModel>();
	
	private LinkedList<OperaModel> orev = new LinkedList<OperaModel>();
	
	public void setStart (MainStart startx)
	{
		start = startx;
	}
	
	@FXML
	 public void ViewOpera(){
	  start.changeStageSearch();  
	 }
	 
	@FXML
	 public void Logout() {
	  start.changeStageLogin();
	 }
	
	@FXML
	public void handleSearch ()
	{
		res.clear();
		Container.getChildren().clear();
		try {
			res = start.mySql.UserQuery.SearchUser(Name.getText(), Surname.getText(), Email.getText(), Username.getText(), Transcriber.getText(), Admin.getText(), Revisioner.getText(), Download.getText(), Container, this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleOperaSearch()
	{
		opres.clear();
		OperaContainer.getChildren().clear();
		try
		{
			opres = start.mySql.OperaQuery.SearchOpera(Title.getText(), Author.getText(), DataOpera.getText(), this, null, OperaContainer, 0);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleRevOpera()
	{
		orev.clear();
		RevContainer.getChildren().clear();
		try
		{
			orev = start.mySql.OperaQuery.SearchOpera("", "", "",this, null, RevContainer, 2);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleRevTrsc()
	{
		prev.clear();
		RevContainer.getChildren().clear();
		try
		{
			prev = start.mySql.PageQuery.GetRevPage(this, RevContainer);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}
}