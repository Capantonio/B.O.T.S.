package bots.Controller;

import java.util.LinkedList;

import bots.Model.NotificationModel;

public class MessageClass {

	LinkedList<NotificationModel> ret;
	
	public MessageClass ()
	{
		ret = MainStart.mySql.UserQuery.GetNotification(MainStart.ConnectedUser);
	}
	
	public LinkedList<NotificationModel> GetResult()
	{
		return ret;
	}
}
