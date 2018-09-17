package bots.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.*;

import java.sql.ResultSet;
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
			res = start.mySql.OperaQuery.SearchOpera(Search.getText(), AutSearch.getText(), YearSearch.getText(), null, this, Container, (Integer)1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Only for test
		//start.mySql.PageQuery.LoadImage(1, "C:\\Users\\Fabio\\Desktop\\Fabio\\Varie scartoffie\\Discord.png", 2);
		//start.mySql.PageQuery.LoadImage(1, "C:\\Users\\Fabio\\Desktop\\Fabio\\Varie scartoffie\\blender.png", 3);
	}
	
	@FXML
	public void handleLoad ()
	{
		String Title="", Author="", Page="", Path="";
		while (Title.equals(""))
			Title = JOptionPane.showInputDialog("Enter the title:");
		while (Title.equals(""))
			Author = JOptionPane.showInputDialog("Enter the author:");
		while (Title.equals(""))
			Page = JOptionPane.showInputDialog("Enter the number of page:");
		while (Title.equals(""))
			Path = JOptionPane.showInputDialog("Enter the path of images:");
		
		Integer x = Integer.parseInt(Page);
		//create opera
		for (int i = 0; i < x; i++)
		{
			//load image
		}
	}
	
	@FXML
	public void handleAdmin()
	{
		start.changeStageAdmin();
	}
	
	public void ReadOpera (int xid)
	{
		start.changeStageOpera(xid);
	}
	
}
