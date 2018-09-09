package bots.controller.model;

import bots.controller.AdminClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class UserModel {

	private AdminClass Parent;
	
	private Integer ID;
	private String Username;
	private String Email;
	private String Name;
	private String Surname;
	private String Transcriber;
	private String Admin;
	private String Revisioner;
	private String Download;
	
	@FXML
	private AnchorPane Container;
	
	@FXML
	private Label Lname;
	@FXML
	private Label Lsur;
	@FXML
	private Label Lnick;
	@FXML
	private Label Lmail;
	@FXML
	private Label Ladmin;
	@FXML
	private Label Lrev;
	@FXML
	private Label Ldl;
	@FXML
	private Label Ltrsc;
	
	@FXML
	private Button Mod;
	@FXML
	private Button Trsc;
	
	public UserModel (Integer xid, String xname, String xsurname, String xemail, String xusername, String xtrsc, String xadmin, String xrev, String xdl)
	{
		ID = xid;
		Name = xname;
		Surname = xsurname;
		Username = xusername;
		Email = xemail;
		Transcriber = xtrsc;
		Admin = xadmin;
		Download = xdl;
		Revisioner = xrev;
	}
	
	public UserModel (Integer xid, String xname, String xsurname, String xemail, String xusername, String xtrsc, String xadmin, String xrev, String xdl, AnchorPane cont, AdminClass obj, Integer count)
	{
		ID = xid;
		Name = xname;
		Surname = xsurname;
		Username = xusername;
		Email = xemail;
		Transcriber = xtrsc;
		Admin = xadmin;
		Download = xdl;
		Revisioner = xrev;
		Container = cont;
		Parent = obj;
		ShowInfo (count);
	}
	
	public String GetUsername () { return Username; }
	public String GetEmail () { return Email; }
	public String GetName () { return Name; }
	public String GetSurname () { return Surname; }
	public Integer GetID () { return ID; }
	public String GetAdmin () { return Admin; }
	
	public void ShowInfo (Integer x)
	{
		String appo;
		SetShow (Lname, 0, x * 30, 70, Name);
		SetShow (Lsur, 70, x * 30, 70, Surname);
		SetShow (Lmail, 140, x * 30, 120, Email);
		SetShow (Lnick, 280, x * 30, 70, Username);
		SetShow (Ltrsc, 350, x * 30, 30, Transcriber);
		appo = Admin.equals("1") ? "YES" : "NO";
		SetShow (Ladmin, 380, x * 30, 30, appo);
		appo = Download.equals("1") ? "YES" : "NO";
		SetShow (Ldl, 410, x * 30, 30, appo);
		appo = Revisioner.equals("1") ? "YES" : "NO";
		SetShow (Lrev, 440, x * 30, 30, appo);
		
		Mod = new Button();
		Mod.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e)
		    {
		    	Parent.ModifyPopup(new Stage());
		    }
		});
		Mod.setText("Modify");
		Mod.setMinWidth(70);
		Mod.setLayoutX(470);
		Mod.setLayoutY(x*30);
		Container.getChildren().add(Mod);
		
		Trsc = new Button();
		Trsc.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e)
		    {
		    	Parent.ListPopup(new Stage());
		    }
		});
		Trsc.setText("List");
		Trsc.setMinWidth(70);
		Trsc.setLayoutX(540);
		Trsc.setLayoutY(x*30);
		Container.getChildren().add(Trsc);
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
