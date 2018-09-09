package bots.controller.model;

import java.awt.image.BufferedImage;

import bots.controller.model.*;
import javafx.scene.image.*;

public class PageModel {

	private Image PageImage;
	private Integer PageNumber;
	private TranscribeModel PageTranscr;
	
	public PageModel (Image source, Integer num)
	{
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
