package bots.controller.model;

import bots.controller.model.*;

public class OperaModel {
	
	public Integer ID;
	private String Title;
	public String Author;
	public Boolean Show;
	public String Data;
	public PageModel[] Pages;
	public Integer Lenght;
	
	public OperaModel ()
	{
		
	}
	
	public OperaModel (Integer id, String Tit, String Aut, String datax, Integer PageN)
	{
		ID = id;
		Title = Tit;
		Author = Aut;
		Data = datax;
		Pages = new PageModel[PageN+1];
		Lenght = PageN+1;
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
