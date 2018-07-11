package bots.controller.model;

public class UserModel {

	private String Username;
	private String Email;
	private Integer ID;
	
	public UserModel (Integer xID, String Name, String xEmail)
	{
		ID = xID;
		Username = Name;
		Email = xEmail;
	}
	
}
