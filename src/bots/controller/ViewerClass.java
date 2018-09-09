package bots.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;

import java.sql.SQLException;

import bots.controller.MainStart;
import bots.controller.model.*;

public class ViewerClass {

	private Integer OperaID;
	@FXML
	private TextField PageNumber;
	@FXML
	private ImageView Page;
	@FXML
	private TextArea Transcribe;
	@FXML
	private Label Title;
	
	private MainStart start;
	private OperaModel LoadOpera;
	private Integer PageNum;
	
	
	public void setStart (MainStart startx, Integer opload)
	{
		start = startx;
		OperaID = opload;
		//Load Opera from DB
		try {
			LoadOpera = start.mySql.OperaQuery.GetOpera(opload);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoadOpera.Pages[1] = start.mySql.PageQuery.GetPageFromOpera(opload, 1);
		PageNum = 1;
		Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
		PageNumber.setText(PageNum.toString());
		Title.setText(LoadOpera.GetTitle());
	}
	
	@FXML
	public void HandleForward ()
	{
		if (PageNum < LoadOpera.GetLenght())
		{
			PageNum++;
			if (LoadOpera.Pages[PageNum] == null)
				LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
			Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
			Transcribe.setText(LoadOpera.GetPage(PageNum).GetTrsc().GetText());
			PageNumber.setText(PageNum.toString());
		}
	}
	
	@FXML
	public void HandleBackward ()
	{
		if (PageNum > 1)
		{
			PageNum--;
			Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
			Transcribe.setText(LoadOpera.GetPage(PageNum).GetTrsc().GetText());
			PageNumber.setText(PageNum.toString());
		}
	}
	
	@FXML
	public void HandleStart ()
	{
		PageNum = 1;
		Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
		Transcribe.setText(LoadOpera.GetPage(PageNum).GetTrsc().GetText());
		PageNumber.setText(PageNum.toString());
	}
	
	@FXML
	public void HandleEnd ()
	{
		PageNum = 0;
		Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
		Transcribe.setText(LoadOpera.GetPage(PageNum).GetTrsc().GetText());
		PageNumber.setText(PageNum.toString());
	}
	
}
