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
	
	private UserModel appo;
	
	public void ModifyPopup (final Stage primaryStage, UserModel x)
	{
		appo = x;
		primaryStage.setTitle("Popup Example");
		final Popup popup = new Popup();
		popup.centerOnScreen();
		popup.setHeight(340);
		popup.setWidth(260);
		primaryStage.setHeight(340);
		primaryStage.setWidth(250);
		
		AnchorPane layout = new AnchorPane();
		layout.setStyle("-fx-background-color: white; -fx-padding: 10;");
		SetShow (new Label(), 10, 10, 80, "Name", layout);
		SetShow (new Label(), 10, 40, 80, "Surname", layout);
		SetShow (new Label(), 10, 70, 80, "Username", layout);
		SetShow (new Label(), 10, 100, 80, "Email", layout);
		SetShow (new Label(), 10, 130, 80, "Transcriber", layout);
		SetShow (new Label(), 10, 160, 80, "Admin", layout);
		SetShow (new Label(), 10, 190, 80, "Revisioner", layout);
		SetShow (new Label(), 10, 220, 80, "Download", layout);
		
		SetShow (TName, 100, 10, 130, x.Name, layout);
		SetShow (TSurname, 100, 40, 130, x.Surname, layout);
		SetShow (TUsername, 100, 70, 130,x.Username, layout);
		SetShow (TEmail, 100, 100, 130, x.Email, layout);
		SetShow (TTranscriber, 100, 130, 30, x.Transcriber, layout);
		SetShow (TAdmin, 100, 160, 30, x.Admin, layout);
		SetShow (TRev, 100, 190, 30, x.Revisioner, layout);
		SetShow (TDownload, 100, 220, 30, x.Download, layout);
		
		Button apply = new Button("Apply");
	    apply.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent event) {
	    	  ApplyModify();
	      }
	    });
	    apply.setLayoutX(10);
	    apply.setLayoutY(250);
	    apply.setMaxWidth(80);
	    apply.setPrefHeight(30);
		layout.getChildren().add(apply);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}
	
	public void ApplyModify ()
	{
		appo.Name = TName.getText();
  	  appo.Surname = TSurname.getText();
  	  appo.Username = TUsername.getText();
  	  appo.Email = TEmail.getText();
  	  appo.Transcriber = TTranscriber.getText();
  	  appo.Admin = TAdmin.getText();
  	  appo.Revisioner = TRev.getText();
  	  appo.Download = TDownload.getText();
  	  try {
			start.mySql.UserQuery.ChangeUser(appo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SetShow (Label obj, Integer x, Integer y, Integer width, String text, AnchorPane Cont)
	{
		obj = new Label();
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		Cont.getChildren().add(obj);
	}
	
	public void SetShow (TextField obj, Integer x, Integer y, Integer width, String text, AnchorPane Cont)
	{
		obj = new TextField();
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		Cont.getChildren().add(obj);
	}
	
	public void ListPopup (final Stage primaryStage)
	{
		primaryStage.setTitle("Popup Example");
		final Popup popup = new Popup();
		popup.centerOnScreen();
		popup.setHeight(400);
		popup.setWidth(500);
		primaryStage.setHeight(400);
		primaryStage.setWidth(500);

		

		HBox layout = new HBox(10);
		layout.setStyle("-fx-background-color: grey; -fx-padding: 10;");
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}
}