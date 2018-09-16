package bots.controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bots.controller.AdminClass;
import bots.controller.model.OperaModel;
import bots.controller.model.ResultModel;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class OperaTable {

	private Connection db;
	private String SqlGet = "Select * From mydb.opera Where idOpera = ?";
	private String SqlSearch = "Select idOpera, Title, Author, DataOpera From mydb.opera Where ";
	private String SqlTitle = "Title = ?";
	private String SqlAuthor = "Author = ?";
	private String SqlYear = "DataOpera = ?";
	
	public OperaTable(Connection xcon)
	{
		db = xcon;
	}
	
	public LinkedList<ResultModel> SearchOpera (String xtitle, String xauthor, String xyear, bots.controller.SearchClass object, Boolean flagt, Boolean flaga, Boolean flagy, AnchorPane cont ) throws SQLException
	{
		String SqlQuery = SqlSearch;
		if (!flagt)
			SqlQuery = SqlQuery + "!(" + SqlTitle + ")";
		else
			SqlQuery = SqlQuery + SqlTitle;
		if (!flaga)
			SqlQuery = SqlQuery + " AND !(" + SqlAuthor + ")";
		else
			SqlQuery = SqlQuery + " AND " + SqlAuthor;
		if (flagy)
			SqlQuery = SqlQuery + " AND " + SqlYear;
		else
			SqlQuery = SqlQuery + " AND !(" + SqlYear + ")";
		SqlQuery = SqlQuery + " AND ShowOpera = ?";
		
		PreparedStatement SearchOperaQuery = db.prepareStatement(SqlQuery);
		
		if (flagt)
			{ SearchOperaQuery.setString(1, xtitle); }
		else
			{ SearchOperaQuery.setString(1, ""); }
		
		if (flaga)
			{ SearchOperaQuery.setString(2, xauthor); }
		else
			{ SearchOperaQuery.setString(2, ""); }
		
		if (flagy)
			{ SearchOperaQuery.setInt(3, Integer.parseInt (xyear)); }
		else
			{ SearchOperaQuery.setString(3, ""); }
		
		SearchOperaQuery.setString(4, "1");
		
		System.out.println(SearchOperaQuery.toString());
		ResultSet x = SearchOperaQuery.executeQuery();
		LinkedList<ResultModel> ResultList = new LinkedList<ResultModel>();
		System.out.println("Get Result");
		//For each element on result
		int count = 0;
		while (x.next())
		{
			System.out.println(x.getString("Title"));
			ResultList.add(new ResultModel(x.getString("Title"),x.getString("Author"),x.getString("DataOpera"),x.getInt("idOpera"), object, cont, count));
			count++;
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
		//Fill the opera class
		return res;
	}
	
	public LinkedList<OperaModel> ListOpera (String tit, String aut, String data, String show, AnchorPane cont, AdminClass parent) throws SQLException
	{
		LinkedList<OperaModel> res = new LinkedList<OperaModel>();
		String Query = "Select * From mydb.opera Where ";
		if (tit.equals(""))
			Query = Query + "!(" + SqlTitle + ")";
		else
			Query = Query + SqlTitle;
		if (aut.equals(""))
			Query = Query + " AND !(" + SqlAuthor + ")";
		else
			Query = Query + " AND " + SqlAuthor;
		if (data.equals(""))
			Query = Query + " AND !(" + SqlYear + ")";
		else
			Query = Query + " AND " + SqlYear;
		if ( !(show.equals("")) )
				Query = Query + " AND ShowOpera = ?";
			
		PreparedStatement ListQuery = db.prepareStatement(Query);
		
		ListQuery.setString(1, tit);
		ListQuery.setString(2, aut);
		if (data.equals(""))
			ListQuery.setString(3, "");
		else
			ListQuery.setInt(3, Integer.parseInt(data));
		if ( !(show.equals("")) )
			ListQuery.setString(4, "2");
		ResultSet x = ListQuery.executeQuery();
		Integer i=0;
		while (x.next())
		{
			if (x.getString("ShowOpera").equals("2") && !(show.equals("2")) )
				break;
			res.add(new OperaModel(x.getInt("idOpera"),x.getString("Title"), x.getString("Author"), x.getString("DataOpera"), x.getString("ShowOpera"), cont, i, parent));
			i++;
		}
		cont.setPrefHeight(30*i);
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
}
