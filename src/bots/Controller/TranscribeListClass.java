package bots.Controller;

import java.sql.SQLException;
import java.util.LinkedList;

import bots.Model.PageModel;
import bots.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TranscribeListClass {

	public UserModel user;
	
	public Integer[] idPage = new Integer[100];
	
	public void setStart (UserModel xuser)
	{
		user = xuser;
	}
	
	public LinkedList<String[]> Refresh()
	{
		try {
			return MainStart.mySql.UserQuery.LoadTrasncribeList(user.ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void Add(String Title, String Page) throws SQLException
	{
		PageModel x = MainStart.mySql.PageQuery.GetPageFromOpera(MainStart.mySql.OperaQuery.GetIdOpera(Title), Integer.parseInt(Page));
		if (!(x.equals(null)))
			MainStart.mySql.UserQuery.AddTrscList(user.ID, x.PageID);
	}
}