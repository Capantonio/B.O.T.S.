package bots.controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bots.controller.AdminClass;
import bots.controller.SearchClass;
import bots.controller.model.OperaModel;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class OperaTable {

	private Connection db;
	private String SqlGet = "Select * From mydb.opera Where idOpera = ?";
	private String SqlSearch = "Select * From mydb.opera Where ";
	private String SqlTitle = "Title = ?";
	private String SqlAuthor = "Author = ?";
	private String SqlYear = "DataOpera = ?";
	
	public OperaTable(Connection xcon)
	{
		db = xcon;
	}
	
	public LinkedList<OperaModel> SearchOpera (String xtitle, String xauthor, String xyear, AdminClass ca, SearchClass cs, AnchorPane cont, Integer method ) throws SQLException
	{
		String SqlQuery = SqlSearch;
		if (xtitle.equals(""))
			SqlQuery = SqlQuery + "!(" + SqlTitle + ")";
		else
			SqlQuery = SqlQuery + SqlTitle;
		if (xauthor.equals(""))
			SqlQuery = SqlQuery + " AND !(" + SqlAuthor + ")";
		else
			SqlQuery = SqlQuery + " AND " + SqlAuthor;
		if (xyear.equals(""))
			SqlQuery = SqlQuery + " AND !(" + SqlYear + ")";
		else
			SqlQuery = SqlQuery + " AND " + SqlYear;
		
		PreparedStatement SearchOperaQuery = db.prepareStatement(SqlQuery);
		
		SearchOperaQuery.setString(1, xtitle);
		
		SearchOperaQuery.setString(2, xauthor);
		
		if (xyear.equals(""))
			SearchOperaQuery.setString(3, "");
		else
			SearchOperaQuery.setInt(3, Integer.parseInt (xyear));
		
		System.out.println(SearchOperaQuery.toString());
		ResultSet x = SearchOperaQuery.executeQuery();
		LinkedList<OperaModel> ResultList = new LinkedList<OperaModel>();
		System.out.println("Get Result");
		//For each element on result
		int count = 0;
		while (x.next())
		{
			if (method == 2)
			{
				if (x.getString("ShowOpera").equals("2"))
				{
					System.out.println(x.getString("Title"));
					ResultList.add(new OperaModel(x.getInt("idOpera"), x.getString("Title"),x.getString("Author"),x.getString("DataOpera"),x.getString("Showopera"), cont, count, ca, method));
					count++;
				}
			}
			else if (method == 1)
			{
				if (x.getString("ShowOpera").equals("1"))
				{
					System.out.println(x.getString("Title"));
					ResultList.add(new OperaModel(x.getInt("idOpera"), x.getString("Title"),x.getString("Author"),x.getString("DataOpera"),x.getString("Showopera"), cont, count, cs, method));
					count++;
				}
			}
			else
			{
				if (x.getString("ShowOpera").equals("0") || x.getString("ShowOpera").equals("1"))
				{
					System.out.println(x.getString("Title"));
					ResultList.add(new OperaModel(x.getInt("idOpera"), x.getString("Title"),x.getString("Author"),x.getString("DataOpera"),x.getString("Showopera"), cont, count, ca, method));
					count++;
				}
			}
			
		}
		cont.setPrefHeight(count*30);
		return ResultList;
	}
	
	public OperaModel GetOpera (int xid) throws SQLException
	{
		PreparedStatement GetOperaQuery = db.prepareStatement(SqlGet);
		GetOperaQuery.setInt(1, xid);
		ResultSet x = GetOperaQuery.executeQuery();
		OperaModel res = null;
		while (x.next())
		{
			res = new OperaModel(x.getInt("idOpera"), x.getString("Title"), x.getString("Author"), x.getString("DataOpera"), x.getInt("Page"));
		}
		return res;
	}
	
	public void SetShow (Integer id, String x) throws SQLException
	{
		PreparedStatement pstmt;
        pstmt = db.prepareStatement("Update mydb.opera Set ShowOpera = ? Where idOpera = ?");
        pstmt.setString(1, x);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
	}
	
	public Integer GetIdOpera (String title) throws SQLException
	{
		PreparedStatement GeneralQuery = db.prepareStatement("Select idOpera From mydb.opera Where Title = ?");
		GeneralQuery.setString(1, title);
		ResultSet x = GeneralQuery.executeQuery();
		while (x.next())
			return x.getInt("idOpera");
		return null;
		
	}
}
