package bots.controller.model;

import bots.controller.model.*;

public class TranscribeModel {

	public String Text;
	public String LastUser;
	
	public TranscribeModel (String text, String last)
	{
		Text = text;
		LastUser = last;
	}
	
	public TranscribeModel ()
	{
		Text = new String();
		LastUser = "";
	}
}
