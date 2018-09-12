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
	
	private MainStart start;
	private String TitleText = "";
	private String AuthorText = "";
	private String DataText = "";
	
	private Boolean flagt = false, flaga = false, flagy = false;
	

	private LinkedList<ResultModel> res = new LinkedList<ResultModel>();
	
	public void setStart (MainStart startx)
	{
		start = startx;
	}
	
	@FXML
	public void HandleSearch ()
	{
		res.clear();
		if (!(Search.getText().equals("")))
		{
			TitleText = Search.getText();
			flagt = true;
		}
		else
		{
			TitleText = Search.getText();
			flagt = false;
		}
		if (!(AutSearch.getText().equals("")))
		{
			AuthorText = AutSearch.getText();
			flaga = true;
		}
		else
		{
			AuthorText = AutSearch.getText();
			flaga = false;
		}
		if (!(YearSearch.getText().equals("")))
		{
			DataText = YearSearch.getText(); 
			flagy = true;
		}
		else
		{
			DataText = YearSearch.getText();
			flagy = false;
		}
		Container.getChildren().clear();
		System.out.println(flagt.toString() + ":" + flaga.toString() + ":" + flagy.toString());
		//Search on DAO command
		try {
			res = start.mySql.OperaQuery.SearchOpera(TitleText, AuthorText, DataText, this, flagt, flaga, flagy, Container);
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
	
	public void ReadOpera (int xid)
	{
		start.changeStageOpera(xid);
	}
	
}
