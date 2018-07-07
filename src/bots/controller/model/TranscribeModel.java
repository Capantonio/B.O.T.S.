package bots.controller.model;

import bots.controller.model.*;

public class TranscribeModel {

	private String Text;
	private String LastUser;
	
	public TranscribeModel (String text)
	{
		Text = text;
	}
	
	public TranscribeModel ()
	{
		Text = new String();
	}
	
	public String GetText ()
	{
		return Text;
	}
}
