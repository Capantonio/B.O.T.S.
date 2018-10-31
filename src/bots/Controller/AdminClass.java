package bots.Controller;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import bots.GUIController.GuiAdmin;
import bots.Model.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class AdminClass {
	
	private LinkedList<UserModel> user = new LinkedList<UserModel>();
	
	private LinkedList<OperaModel> opera = new LinkedList<OperaModel>();
	
	private LinkedList<PageModel> pagerev = new LinkedList<PageModel>();
	
	private LinkedList<OperaModel> operarev = new LinkedList<OperaModel>();
	
	public GuiAdmin GuiController;
	
	public void setStart (GuiAdmin x)
	{
		GuiController = x;
	}
	
	public void UserSearch(String Name, String Surname, String Email, String Username, String Transcriber, String Admin, String Revisioner, String Download)
	{
		user.clear();
		try {
			user = MainStart.mySql.UserQuery.SearchUser(Name, Surname, Email, Username, Transcriber, Admin, Revisioner, Download);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Iterator <UserModel> it = user.iterator();
		Integer count = 0;
        while(it.hasNext()) {
        	UserModel x = it.next();
        	GuiController.ShowUserInfo(count, x);
        	count++;
        }
	}
	
	public void OperaSearch(String Title, String Author, String Data)
	{
		opera.clear();
		try {
			opera = MainStart.mySql.OperaQuery.SearchOpera(Title, Author, Data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Iterator <OperaModel> it = opera.iterator();
		Integer count = 0;
        while(it.hasNext()) {
        	OperaModel x = it.next();
        	try {
				GuiController.Showopera(count, x);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	count++;
        }
	}
	
	public void OperaRevisioner()
	{
		operarev.clear();
		try
		{
			operarev = MainStart.mySql.OperaQuery.GetOperaRev();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Iterator <OperaModel> it = opera.iterator();
		Integer count = 0;
        while(it.hasNext()) {
        	OperaModel x = it.next();
        	try {
				GuiController.Showopera(count, x);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	count++;
        }
	}
	
	public void TranscribeRevisioner()
	{
		pagerev.clear();
		Iterator <PageModel> it = pagerev.iterator();
		Integer count = 0;
        while(it.hasNext()) {
        	PageModel x = it.next();
        	try {
				GuiController.ShowRevPage(count, x, MainStart.mySql.OperaQuery.GetOpera(x.IdOpera));
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	count++;
        }
	}
}