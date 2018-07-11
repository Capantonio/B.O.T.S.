package bots.controller.DAO;

import bots.controller.model.UserModel;
import java.sql.*;

public class UserTable {
	
	private String Username;
	private String Password;
	private String Email;
	private Integer ID;
	
	private Connection db;
	private String SqlLogin = "Select * From User Where Name = ? AND Password = ?";
	private String SqlExist = "Select * From User Where Name = ? AND Email = ?";
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
		UserModel UserLogged;
		ResultSet x = null;
		PreparedStatement FindUserQuery = db.prepareStatement(SqlLogin);
		FindUserQuery.setString(1, name);
		FindUserQuery.setString(2, psw);
		x = FindUserQuery.executeQuery();
		if (x.getRow() == 1)
		{
			UserLogged = new UserModel(x.getInt(0), x.getString("Name"),x.getString("Email"));
			return UserLogged;
		}
		return null;
	}
	
	public Boolean RegisterUser (String name, String psw, String Email) throws SQLException
	{
		
		return true;
	}
}
