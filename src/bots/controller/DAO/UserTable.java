package bots.controller.DAO;

import bots.controller.AdminClass;
import bots.controller.model.ResultModel;
import bots.controller.model.UserModel;
import javafx.scene.layout.AnchorPane;

import java.sql.*;
import java.util.LinkedList;

public class UserTable {
	
	private String Username;
	private String Password;
	private String Email;
	private Integer ID;
	
	private Connection db;
	private String SqlLogin = "Select * From mydb.user Where Nickname = ? AND Password = ?";
	private String SqlExist = "Select * From mydb.user Where Nickname = ? AND Email = ?";
	private String SqlRegister = "Insert Into mydb.user Value (?,?,?)";
	
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
	
	public Boolean ExistUser (String name, String email) throws SQLException
	{
		ResultSet x = null;
		PreparedStatement FindUserQuery = db.prepareStatement(SqlExist);
		FindUserQuery.setString(1, name);
		FindUserQuery.setString(2, email);
		x = FindUserQuery.executeQuery();
		if (x.wasNull())
			return true;
		else
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
			// TODO Auto-generated catch block
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
	
	public Boolean RegisterUser (String name, String psw, String Email) throws SQLException
	{
		
		return true;
	}
	
	public LinkedList<UserModel> SearchUser (String xname, String xsurname, String xemail, String xusername, String xtrsc, String xadmin, String xrev, String xdl, AnchorPane cont, AdminClass obj) throws SQLException
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
		int count = 0;
		while (x.next())
		{
			System.out.println(x.getString("Name"));
			ResultList.add(new UserModel(x.getInt("idUser"),x.getString("Name"),x.getString("Surname"),x.getString("Email"),x.getString("Nickname"),x.getString("Transcriber"),x.getString("Admin"),x.getString("Revisioner"),x.getString("Download"),cont, obj, count));
			count++;
		}
		
		return ResultList;
	}
}
