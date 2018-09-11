package bots.controller.model;

import java.awt.image.BufferedImage;

import bots.controller.model.*;
import javafx.scene.image.*;

public class PageModel {

	public Integer PageID;
	public Image PageImage;
	public Integer PageNumber;
	public TranscribeModel PageTranscr;
	
	public PageModel (Integer id, Image source, Integer num)
	{
		PageID = id;
		PageImage = source;
		PageNumber = num;
		PageTranscr = new TranscribeModel();
	}
	
	public void ModTranscr (TranscribeModel newtrsc)
	{
		PageTranscr = newtrsc;
	}
	
	public Image GetImage ()
	{
		return PageImage;
	}
	
	public TranscribeModel GetTrsc ()
	{
		return PageTranscr;
	}
}
