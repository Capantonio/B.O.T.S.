package bots.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
	private ScrollPane Container;
	
	private LinkedList<ResultModel> ResultList;
	
	private MainStart start;
	private String TitleText;
	private String AuthorText;
	private String DataText;
	
	public void setStart (MainStart startx)
	{
		start = startx;
	}
	
	@FXML
	public void HandleSearch ()
	{
		if (!(Search.getText().equals(null)))
		{
			TitleText = Search.getText();
		}
		if (!(AutSearch.getText().equals(null)))
		{
			AuthorText = AutSearch.getText();
		}
		if (!(YearSearch.getText().equals(null)))
		{
			DataText = YearSearch.getText(); 
		}
		//Search on DAO command
		//Get the result
		//Call the ResultView function with result as parameter
	}

	public void ResultView (ResultSet xres) throws SQLException
	{
		ResultList = new LinkedList<ResultModel>();
		//For each element on result
		while (xres.next())
		{
			ResultList.add(new ResultModel(xres.getString("Title"),xres.getString("Author"),xres.getString("Data"),xres.getInt("idOpera"),start,Container));
		}
	}
	
}
