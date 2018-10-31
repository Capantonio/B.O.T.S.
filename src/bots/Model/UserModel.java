package bots.Model;

import bots.Controller.AdminClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;

public class UserModel {

	private AdminClass Parent;
	
	public Integer ID;
	public String Username;
	public String Email;
	public String Name;
	public String Surname;
	public String Transcriber;
	public String Admin;
	public String Revisioner;
	public String Download;
	
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
	
	public String GetUsername () { return Username; }
	public String GetEmail () { return Email; }
	public String GetName () { return Name; }
	public String GetSurname () { return Surname; }
	public Integer GetID () { return ID; }
	public String GetAdmin () { return Admin; }
	
	
}
