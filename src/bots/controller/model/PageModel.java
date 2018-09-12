package bots.controller.model;

import java.awt.image.BufferedImage;

import bots.controller.model.*;
import javafx.scene.image.*;

public class PageModel {

	public Integer PageID;
	public Image PageImage;
	public Integer PageNumber;
	public String Transcribe;
	public String LastUserModify;
	public String Revision;
	
	
	public PageModel (Integer id, Image source, Integer num, String trsc, String lastu, String rev)
	{
		PageID = id;
		PageImage = source;
		PageNumber = num;
		Transcribe = trsc;
		LastUserModify = lastu;
		Revision = rev;
	}
	
	public Image GetImage ()
	{
		return PageImage;
	}
}
