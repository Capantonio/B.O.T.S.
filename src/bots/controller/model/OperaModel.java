package bots.controller.model;

import bots.controller.model.*;

public class OperaModel {
	
	private Integer ID;
	private String Title;
	private String Author;
	private Boolean Show;
	private String UserLoad;
	private String Data;
	private PageModel[] Pages;
	private Integer Lenght;
	
	public OperaModel ()
	{
		
	}
	
	public OperaModel (String Tit, String Aut, String User, Integer PageN, PageModel[] List)
	{
		Title = Tit;
		Author = Aut;
		UserLoad = User;
		Pages = new PageModel[PageN];
		Pages = List;
		Lenght = PageN;
	}

	public PageModel GetPage (Integer num)
	{
		return Pages[num];
	}
	
	public Integer GetLenght ()
	{
		return Lenght;
	}
	
	public String GetTitle ()
	{
		return Title;
	}
}
