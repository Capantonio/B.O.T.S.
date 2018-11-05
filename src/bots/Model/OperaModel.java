package bots.Model;

import java.sql.SQLException;

import bots.Controller.AdminClass;
import bots.Controller.SearchClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class OperaModel {
	
	public Integer ID;
	public String Title;
	public String Author;
	public String Show;
	public String Data;
	public PageModel[] Pages;
	public Integer Lenght;
	
	public OperaModel (Integer id, String tit, String aut, String data, String show, Integer page)
	{
		ID = id;
		Title = tit;
		Author = aut;
		Data = data;
		Show = show;
		Lenght = page;
		Pages = new PageModel[page+1];
	}
	
	public PageModel GetPage (Integer num)
	{
		return Pages[num];
	}
}
