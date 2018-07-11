package bots.controller.model;

public class UserModel {

	private Integer ID;
	private String Username;
	private String Email;
	private String Name;
	private String Surname;
	
	public UserModel (Integer xID, String Name, String xEmail)
	{
		ID = xID;
		Username = Name;
		Email = xEmail;
	}
	
	public String GetUsername () { return Username; }
	public String GetEmail () { return Email; }
	public String GetName () { return Name; }
	public String GetSurname () { return Surname; }
	public Integer GetID () { return ID; }
	
}
