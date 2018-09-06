package bots.controller.DAO;

import bots.controller.model.UserModel;
import java.sql.*;

public class UserTable {
	
	private String Username;
	private String Password;
	private String Email;
	private Integer ID;
	
	private Connection db;
	private String SqlLogin = "Select * From mydb.user Where Nickname = ? AND Password = ?";
	private String SqlExist = "Select * From user Where Nickname = ? AND Email = ?";
	private String SqlRegister = "Insert Into User Value (?,?,?)";
	
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (x.next())
		{
				System.out.print(x.getString("Name"));
				UserLogged = new UserModel(x.getInt("idUser"), x.getString("Name"),x.getString("Email"));
				return UserLogged;
		}
		return null;
	}
	
	public Boolean RegisterUser (String name, String psw, String Email) throws SQLException
	{
		
		return true;
	}
}
