package bots.Model;

import java.sql.Date;

public class NotificationModel {

	public Integer ID;
	public String Text;
	public Date Data;
	
	public NotificationModel(Integer id, String text, Date date)
	{
		ID = id;
		Text = text;
		Data = date;
	}
}
