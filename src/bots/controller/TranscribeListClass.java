package bots.controller;

import bots.controller.model.UserModel;

public class TranscribeListClass {

	public MainStart start;
	public UserModel user;
	
	public void setStart (MainStart startx, UserModel xuser)
	{
		start = startx;
		user = xuser;
	}
}
