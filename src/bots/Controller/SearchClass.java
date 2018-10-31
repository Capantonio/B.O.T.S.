package bots.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.sql.SQLException;
import java.util.*;

import javax.swing.JOptionPane;

import bots.Controller.MainStart;
import bots.Model.*;


public class SearchClass {

	private LinkedList<OperaModel> res = new LinkedList<OperaModel>();
	
	public LinkedList<OperaModel> Search (String title, String aut, String year)
	{
		res.clear();
		//Search on DAO command
		try {
			res = MainStart.mySql.OperaQuery.SearchOpera(title, aut, year);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public void Load () throws SQLException
	{
		/*
		 String Title="", Author="", Page="", Path="", Data="";
		 while (Title.equals(""))
			 Title = JOptionPane.showInputDialog("Enter the title:");
		 while (Author.equals(""))
			 Author = JOptionPane.showInputDialog("Enter the author:");
		 while (Page.equals(""))
			 Page = JOptionPane.showInputDialog("Enter the number of page:");
		 while (Data.equals(""))
			 Data = JOptionPane.showInputDialog("Enter the data");
		 while (Path.equals(""))
			 Path = JOptionPane.showInputDialog("Enter the path of images:");
	    
		 Integer x = Integer.parseInt(Page);
		 MainStart.mySql.OperaQuery.LoadOpera(Title, Author, x, Data);
		 Integer y = MainStart.mySql.OperaQuery.GetIdOpera(Title);
	    
		 //create opera
		 for (int i = 1; i < x+1; i++)
		 {
			 MainStart.mySql.PageQuery.LoadImage(y, Path + "\\" + Title +  "_" + i + ".JPG" , i);
		 }
		 */
	}
	
}
