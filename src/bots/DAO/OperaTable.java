package bots.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bots.Controller.AdminClass;
import bots.Controller.SearchClass;
import bots.Model.OperaModel;
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
	
	public void DeleteOpera (Integer id) throws SQLException
	{
		PreparedStatement DelOpera = db.prepareStatement("Delete From mydb.opera Where idOpera = ?");
		DelOpera.setInt(1, id);
		DelOpera.executeUpdate();
	}
	
	public LinkedList<OperaModel> SearchOpera (String xtitle, String xauthor, String xyear) throws SQLException
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
		while (x.next())
		{
			if (x.getString("ShowOpera").equals("1"))
			{
				System.out.println(x.getString("Title"));
				ResultList.add(new OperaModel(x.getInt("idOpera"), x.getString("Title"),x.getString("Author"),x.getString("DataOpera"),x.getString("Showopera"), x.getInt("Page")));
			}
		}
		return ResultList;
	}
	
	public void LoadOpera(String Title, String Author, Integer Page, String Data)
	  {
	    String query;
	    PreparedStatement pstmt;
	        
	    try 
	    {
	      query = ("insert Into mydb.opera (Title, Author, DataOpera, Page, ShowOpera) VALUES (?,?,?,?,'2')");
	      pstmt = db.prepareStatement(query);
	      pstmt.setString(1, Title);
	      pstmt.setString(2, Author);
	      pstmt.setString(3, Data);
	      pstmt.setInt(4, Page);  
	      pstmt.executeUpdate();
	    }
	    catch(Exception e){
	      e.printStackTrace();
	    }
	  }
	
	public OperaModel GetOpera (int xid) throws SQLException
	{
		PreparedStatement GetOperaQuery = db.prepareStatement(SqlGet);
		GetOperaQuery.setInt(1, xid);
		ResultSet x = GetOperaQuery.executeQuery();
		OperaModel res = null;
		while (x.next())
		{
			res = new OperaModel(x.getInt("idOpera"), x.getString("Title"), x.getString("Author"), x.getString("DataOpera"),x.getString("Showopera"), x.getInt("Page"));
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
	
	public LinkedList <OperaModel> GetOperaRev () throws SQLException
	{
		String SqlQuery = "SELECT *FROM db.opera WHERE ShowOpera = 2";
		PreparedStatement SearchOperaQuery = db.prepareStatement(SqlQuery);
		ResultSet x = SearchOperaQuery.executeQuery();
		LinkedList<OperaModel> ResultList = new LinkedList<OperaModel>();
		System.out.println("Get Result");
		//For each element on result
		while (x.next())
		{
			System.out.println(x.getString("Title"));
			ResultList.add(new OperaModel(x.getInt("idOpera"), x.getString("Title"),x.getString("Author"),x.getString("DataOpera"),x.getString("Showopera"), x.getInt("Page")));
		}
		return ResultList;
	}
}
