package bots.Controller;

import java.sql.SQLException;
import java.util.LinkedList;

import bots.Model.NotificationModel;

public class MessageClass {

	LinkedList<NotificationModel> ret;
	
	public MessageClass ()
	{
		try {
			ret = MainStart.mySql.UserQuery.GetNotification(MainStart.ConnectedUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public LinkedList<NotificationModel> GetResult()
	{
		return ret;
	}
}
