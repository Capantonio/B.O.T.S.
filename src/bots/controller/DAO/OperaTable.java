package bots.controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bots.controller.model.OperaModel;

public class OperaTable {

	private Connection db;
	private String SqlGet = "Select * From Opera Where ID = ?";
	private String SqlSearch = "Select ID, Title, Author, Data From Opera Where ";
	private String SqlTitle = "Title = ?";
	private String SqlAuthor = "Author = ?";
	private String SqlYear = "Data = ?";
	
	public OperaTable(Connection xcon)
	{
		db = xcon;
	}
	
	public ResultSet SearchOpera (String xtitle, String xauthor, String xyear) throws SQLException
	{
		String SqlQuery = SqlSearch;
		SqlQuery = SqlQuery + SqlTitle;
		
		SqlQuery = SqlQuery + ", " + SqlAuthor;
		
		SqlQuery = SqlQuery + ", " + SqlYear;
		
		PreparedStatement SearchOperaQuery = db.prepareStatement(SqlQuery);
		
		if (xtitle != null)
			{ SearchOperaQuery.setString(1, xtitle); }
		else
			{ SearchOperaQuery.setString(1, "*"); }
		
		if (xtitle != null)
			{ SearchOperaQuery.setString(2, xauthor); }
		else
			{ SearchOperaQuery.setString(2, "*"); }
		
		if (xtitle != null)
			{ SearchOperaQuery.setString(3, xyear); }
		else
			{ SearchOperaQuery.setString(3, "*"); }
		
		ResultSet x = SearchOperaQuery.executeQuery();
		return x;
	}
	
	public OperaModel GetOpera (int xid) throws SQLException
	{
		PreparedStatement GetOperaQuery = db.prepareStatement(SqlGet);
		GetOperaQuery.setInt(1, xid);
		ResultSet x = GetOperaQuery.executeQuery();
		OperaModel res = new OperaModel();
		//Fill the opera class
		return res;
	}
	
}
