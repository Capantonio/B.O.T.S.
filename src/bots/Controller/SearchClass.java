package bots.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.File;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JFileChooser;
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
	
	public void Load (String title, String author, String data, File[] files) throws SQLException
	{
		 MainStart.mySql.OperaQuery.LoadOpera(title, author, files.length, data, MainStart.ConnectedUser.ID);
		 
		 //create opera
		 Integer y = MainStart.mySql.OperaQuery.GetIdOpera(title);
	    
		 //Insert pages
		 for (int i = 1; i < files.length+1; i++)
		 {
			 MainStart.mySql.PageQuery.LoadImage(y, files[i-1].getPath(), i);
		 }
	}
	
}
