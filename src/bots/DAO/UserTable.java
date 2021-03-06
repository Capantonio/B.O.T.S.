package bots.DAO;

import bots.Controller.AdminClass;
import bots.Controller.MainStart;
import bots.Controller.TranscribeListClass;
import bots.Model.NotificationModel;
import bots.Model.UserModel;
import javafx.scene.layout.AnchorPane;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.util.LinkedList;

public class UserTable {
	
	private Connection db;
	private String SqlLogin = "Select * From mydb.user Where Nickname = ? AND Password = ?";
	private String SqlExist = "Select * From mydb.user Where Nickname = ? AND Email = ?";
	private String SqlRegister = "Insert Into mydb.user (Name, Surname, Email, Nickname, Password, Transcriber, Download, Admin, Revisioner) Value (?,?,?,?,?,'0','0','0','0')";
	private String SqlModify = "Update mydb.user Set Name = ?, Surname = ?, Nickname = ?, Email = ?, Transcriber = ?, Admin = ?, Revisioner = ?, Download = ? Where idUser = ?";
	private String SqlTranscribe = "Select * From mydb.permission_transcribe Inner Join mydb.page On permission_transcribe.Page = page.idPage Inner Join opera On page.Opera_idOpera = opera.idOpera Where User = ?";
	
	private String SqlSearch = "Select idUser, Name, Surname, Nickname, Email, Transcriber, Download, Admin, Revisioner  From mydb.user Where ";
	private String SqlName = "Name = ?";
	private String SqlSurname = "Surname = ?";
	private String SqlEmail = "Email = ?";
	private String SqlUsername = "Nickname = ?";
	private String SqlAdmin = "Admin = ?";
	private String SqlDownload = "Download = ?";
	private String SqlRevisioner = "Revisioner = ?";
	private String SqlTranscriber = "Transcriber = ?";
	
	public UserTable(Connection xcon)
	{
		db = xcon;
	}
	
	public void RemoveUserTranscription(Integer page) throws SQLException
	{
		PreparedStatement RemoveQuery = db.prepareStatement("DELETE FROM mydb.transcription WHERE PageID=?");
		RemoveQuery.setInt(1, page);
		RemoveQuery.executeUpdate();
		}
	
	public void CheckIdTranscription (Integer page) throws SQLException
	{
		PreparedStatement ListQuery = db.prepareStatement("SELECT * FROM mydb.transcription WHERE PageID = ? AND UserID = ?");
		ListQuery.setInt(1, page);
		ListQuery.setInt(2, MainStart.ConnectedUser.ID);
		ResultSet res = ListQuery.executeQuery();
		while (res.next())
		{
			return;
		}
		System.out.println("add key in transcription table");
		PreparedStatement AddListQuery = db.prepareStatement("INSERT INTO mydb.transcription (PageID, UserID) VALUE (?,?)");
		AddListQuery.setInt(1, page);
		AddListQuery.setInt(2, MainStart.ConnectedUser.ID);
		AddListQuery.executeUpdate();
	}
	
	public void SendNotificationPage (String text, Integer page) throws SQLException
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		String now = LocalDate.now().toString();
		PreparedStatement GetListQuery = db.prepareStatement("SELECT UserID FROM mydb.transcription WHERE PageID = ?");
		GetListQuery.setInt(1, page);
		ResultSet res = GetListQuery.executeQuery();
		while (res.next())
		{
			PreparedStatement NotifyQuery = db.prepareStatement("INSERT INTO mydb.notification (UserID,Message,Data)  VALUE (?,?,?)");
			NotifyQuery.setInt(1, res.getInt("UserID"));
			NotifyQuery.setString(2, text);
			NotifyQuery.setString(3, now);
			NotifyQuery.executeUpdate();
		}
	}
	
	public void SendNotificationOpera (String text, Integer opera) throws SQLException
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		String now = LocalDate.now().toString();
		Integer user = MainStart.mySql.OperaQuery.GetLoader(opera);
		PreparedStatement NotifyQuery = db.prepareStatement("INSERT INTO mydb.notification (UserID,Message,Data)  VALUE (?,?,?)");
		NotifyQuery.setInt(1, user);
		NotifyQuery.setString(2, text);
		NotifyQuery.setString(3, now);
		NotifyQuery.executeUpdate();
	}
	
	public void AddTrscList (Integer user, Integer page) throws SQLException
	{
		PreparedStatement AddQuery = db.prepareStatement("INSERT INTO mydb.permission_transcribe VALUE (?,?)");
		AddQuery.setInt(1, user);
		AddQuery.setInt(2, page);
		AddQuery.executeUpdate();
	}
	
	public void RemoveTrscList (Integer user, Integer page) throws SQLException
	{
		PreparedStatement RemoveQuery = db.prepareStatement("DELETE FROM mydb.permission_transcribe WHERE User=? and Page=?");
		RemoveQuery.setInt(1, user);
		RemoveQuery.setInt(2, page);
		RemoveQuery.executeUpdate();
	}
	
	public LinkedList<String[]> LoadTrasncribeList (Integer id) throws SQLException
	{
		LinkedList<String[]> ret = new LinkedList<String[]>();
		PreparedStatement ListTrsc = db.prepareStatement(SqlTranscribe);
		ListTrsc.setInt(1, id);
		ResultSet x = ListTrsc.executeQuery();
		while (x.next())
		{
			String [] appo = new String[3];
			appo[0] = x.getString("Number");
			appo[1] = x.getString("Title");
			appo[2] = x.getString("idPage");
			ret.add(appo);
		}
		return ret;
	}
	
	public void ChangeUser (UserModel x) throws SQLException
	{
		PreparedStatement ModifyUser = db.prepareStatement(SqlModify);
		ModifyUser.setString(1, x.Name);
		ModifyUser.setString(2, x.Surname);
		ModifyUser.setString(3, x.Username);
		ModifyUser.setString(4, x.Email);
		ModifyUser.setString(5, x.Transcriber);
		ModifyUser.setString(6, x.Admin);
		ModifyUser.setString(7, x.Revisioner);
		ModifyUser.setString(8, x.Download);
		ModifyUser.setInt(9, x.ID);
		ModifyUser.executeUpdate();
	}
	
	public Boolean ExistUser (String name, String email) throws SQLException
	{
		ResultSet x = null;
		PreparedStatement FindUserQuery = db.prepareStatement(SqlExist);
		FindUserQuery.setString(1, name);
		FindUserQuery.setString(2, email);
		x = FindUserQuery.executeQuery();
		if (x.next())
			return false;
		else
			return true;
	}
	
	public Boolean TranscribePermission (Integer user, Integer page) throws SQLException
	{
		PreparedStatement TrscPerm = db.prepareStatement("Select * From mydb.permission_transcribe Where User = ? AND Page = ?");
		TrscPerm.setInt(1, user);
		TrscPerm.setInt(2, page);
		ResultSet x = TrscPerm.executeQuery();
		while (x.next())
			return true;
		return false;
	}
	
	public UserModel LoginUser (String name, String psw) throws SQLException
	{
		System.out.println("load method");
		UserModel UserLogged;
		ResultSet x = null;
		if (db == null)
			System.out.println("no db found");
		PreparedStatement FindUserQuery = db.prepareStatement(SqlLogin);
		System.out.println("prepare query for " + name + " and " + psw);
		FindUserQuery.setString(1, name);
		FindUserQuery.setString(2, psw);
		x = FindUserQuery.executeQuery();
		System.out.println("query");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (x.next())
		{
				System.out.print(x.getString("Nickname"));
				UserLogged = new UserModel(x.getInt("idUser"),x.getString("Name"),x.getString("Surname"),x.getString("Email"),x.getString("Nickname"),x.getString("Transcriber"),x.getString("Admin"),x.getString("Revisioner"),x.getString("Download"));
				return UserLogged;
		}
		return null;
	}
	
	public Boolean RegisterUser (String name, String psw, String email, String surname, String username) throws SQLException
	{
		PreparedStatement AddUserQuery = db.prepareStatement(SqlRegister); 
		  AddUserQuery.setString(1, name);
		  AddUserQuery.setString(2, surname);
		  AddUserQuery.setString(3, email);
		  AddUserQuery.setString(4, username);
		  AddUserQuery.setString(5, psw);
		  AddUserQuery.executeUpdate();
		  return true;
	}
	
	public LinkedList<UserModel> SearchUser (String xname, String xsurname, String xemail, String xusername, String xtrsc, String xadmin, String xrev, String xdl) throws SQLException
	{
		String SqlQuery = SqlSearch;
		if (xname.length() < 1)
			SqlQuery = SqlQuery + "!(" + SqlName + ")";
		else
			SqlQuery = SqlQuery + SqlName;
		if (xsurname.length() < 1)
			SqlQuery = SqlQuery + " AND !(" + SqlSurname + ")";
		else
			SqlQuery = SqlQuery + " AND " + SqlSurname;
		if (xusername.length() < 1)
			SqlQuery = SqlQuery + " AND !(" + SqlUsername + ")";
		else
			SqlQuery = SqlQuery + " AND " + SqlUsername;
		if (xemail.length() < 1)
			SqlQuery = SqlQuery + " AND !(" + SqlEmail + ")";
		else
			SqlQuery = SqlQuery + " AND " + SqlEmail;
		if (xadmin.length() < 1)
			SqlQuery = SqlQuery + " AND !(" + SqlAdmin + ")";
		else
			SqlQuery = SqlQuery + " AND " + SqlAdmin;
		if (xtrsc.length() < 1)
			SqlQuery = SqlQuery + " AND !(" + SqlTranscriber + ")";
		else
			SqlQuery = SqlQuery + " AND " + SqlTranscriber;
		if (xdl.length() < 1)
			SqlQuery = SqlQuery + " AND !(" + SqlDownload + ")";
		else
			SqlQuery = SqlQuery + " AND " + SqlDownload;
		if (xrev.length() < 1)
			SqlQuery = SqlQuery + " AND !(" + SqlRevisioner + ")";
		else
			SqlQuery = SqlQuery + " AND " + SqlRevisioner;
		
		PreparedStatement SearchOperaQuery = db.prepareStatement(SqlQuery);
		
		if (xname.length() > 0)
			SearchOperaQuery.setString(1, xname);
		else
			SearchOperaQuery.setString(1, "");
		if (xsurname.length() > 0)
			SearchOperaQuery.setString(2, xsurname);
		else
			SearchOperaQuery.setString(2, "");
		if (xusername.length() > 0)
			SearchOperaQuery.setString(3, xusername);
		else
			SearchOperaQuery.setString(3, "");
		if (xemail.length() > 0)
			SearchOperaQuery.setString(4, xemail);
		else
			SearchOperaQuery.setString(4, "");
		if (xadmin.length() > 0)
			SearchOperaQuery.setString(5, xadmin);
		else
			SearchOperaQuery.setString(5, "");
		if (xtrsc.length() > 0)
			SearchOperaQuery.setString(6, xtrsc);
		else
			SearchOperaQuery.setString(6, "");
		if (xdl.length() > 0)
			SearchOperaQuery.setString(7, xdl);
		else
			SearchOperaQuery.setString(7, "");
		if (xrev.length() > 0)
			SearchOperaQuery.setString(8, xrev);
		else
			SearchOperaQuery.setString(8, "");
		
		System.out.println(SearchOperaQuery.toString());
		ResultSet x = SearchOperaQuery.executeQuery();
		LinkedList<UserModel> ResultList = new LinkedList<UserModel>();
		while (x.next())
		{
			System.out.println(x.getString("Name"));
			ResultList.add(new UserModel(x.getInt("idUser"),x.getString("Name"),x.getString("Surname"),x.getString("Email"),x.getString("Nickname"),x.getString("Transcriber"),x.getString("Admin"),x.getString("Revisioner"),x.getString("Download")));
		}
		
		return ResultList;
	}
	
	public LinkedList<NotificationModel> GetNotification (UserModel user) throws SQLException
	{
		LinkedList<NotificationModel> ret = new LinkedList<NotificationModel>();
		ResultSet x = null;
		PreparedStatement FindUserQuery = db.prepareStatement("SELECT * FROM mydb.notification WHERE UserID = ?");
		FindUserQuery.setInt(1, user.ID);
		x = FindUserQuery.executeQuery();
		while (x.next())
		{
			System.out.println(x.getString("Message"));
			ret.add(new NotificationModel(x.getInt("ID"),x.getString("Message"),x.getDate("Data")));
		}
		return ret;
	}
}
