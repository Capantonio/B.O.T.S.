package bots.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import bots.controller.MainStart;

public class SearchClass {
	
	@FXML
	private TextField Search;
	@FXML
	private TextField AutSearch;
	@FXML
	private TextField YearSearch;
	@FXML
	private TableView Result;
	
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

	public void ResultView ()
	{
		
	}
	
}
