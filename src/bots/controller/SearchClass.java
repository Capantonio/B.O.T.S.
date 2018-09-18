package bots.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.sql.SQLException;
import java.util.*;

import javax.swing.JOptionPane;

import bots.controller.MainStart;
import bots.controller.model.*;


public class SearchClass {
	
	@FXML
	private TextField Search;
	@FXML
	private TextField AutSearch;
	@FXML
	private TextField YearSearch;
	
	@FXML
	private AnchorPane Container;
	@FXML
	private ScrollPane Scrollp;
	@FXML
	private Button Admin;
	
	private MainStart start;

	private LinkedList<OperaModel> res = new LinkedList<OperaModel>();
	
	public void setStart (MainStart startx)
	{
		start = startx;
		if (start.ConnectedUser.Admin.equals("0"))
			Admin.setVisible(false);
	}
	 
	 public void Logout() {
	  start.changeStageLogin();
	 }
	
	@FXML
	public void HandleSearch ()
	{
		res.clear();
		
		Container.getChildren().clear();
		
		//Search on DAO command
		try {
			res = start.mySql.OperaQuery.SearchOpera(Search.getText(), AutSearch.getText(), YearSearch.getText(), null, this, Container, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Only for test
		//start.mySql.PageQuery.LoadImage(1, "C:\\Users\\Fabio\\Desktop\\Fabio\\Varie scartoffie\\Discord.png", 2);
		//start.mySql.PageQuery.LoadImage(1, "C:\\Users\\Fabio\\Desktop\\Fabio\\Varie scartoffie\\blender.png", 3);
	}
	
	@FXML
	  public void handleLoad () throws SQLException
	  {
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
	    start.mySql.OperaQuery.LoadOpera(Title, Author, x, Data);
	    Integer y = start.mySql.OperaQuery.GetIdOpera(Title);
	    
	    //create opera
	    for (int i = 1; i < x+1; i++)
	    {
	      start.mySql.PageQuery.LoadImage(y, Path + "\\" + Title +  "_" + Page + ".JPG" , x);
	    }
	  }
	
	@FXML
	public void handleAdmin()
	{
		start.changeStageAdmin();
	}
	
	public void ReadOpera (int xid)
	{
		start.changeStageOpera(xid, 0, null);
	}
	
}
