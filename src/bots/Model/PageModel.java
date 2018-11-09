package bots.Model;

import bots.Controller.AdminClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;

public class PageModel {

	public Integer PageID;
	public Image PageImage;
	public Integer PageNumber;
	public String Transcribe;
	public String LastUserModify;
	public String WorkTrsc;
	public Integer IdOpera;
	
	
	public PageModel (Integer id, Image source, Integer num, String trsc, String work)
	{
		PageID = id;
		PageImage = source;
		PageNumber = num;
		Transcribe = trsc;
		WorkTrsc = work;
	}
	
	public PageModel (Integer id, Integer num, Integer idOpera)
	{
		PageID = id;
		PageNumber = num;
		IdOpera = idOpera;
	}
}
