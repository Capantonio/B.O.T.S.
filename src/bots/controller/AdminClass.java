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
	private UserModel appo;
	
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
	TextField TName = new TextField(),
			TSurname = new TextField(),
			TUsername = new TextField(),
			TEmail = new TextField(),
			TTranscriber = new TextField(),
			TAdmin = new TextField(),
			TRev = new TextField(),
			TDownload = new TextField();
	
	
	private LinkedList<UserModel> res = new LinkedList<UserModel>();
	
	public void setStart (MainStart startx)
	{
		start = startx;
	}
	
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
}