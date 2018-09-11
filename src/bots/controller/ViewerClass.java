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
		try {
			LoadOpera.Pages[1].ModTranscr(start.mySql.TrscQuery.getTranscribe(LoadOpera.Pages[1].PageID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PageNum = 1;
		Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
		Transcribe.setText(LoadOpera.GetPage(PageNum).GetTrsc().Text);
		PageNumber.setText(PageNum.toString());
		Title.setText(LoadOpera.GetTitle());
	}
	
	@FXML
	public void HandleForward () throws SQLException
	{
		if (PageNum < LoadOpera.GetLenght())
		{
			PageNum++;
			if (LoadOpera.Pages[PageNum] == null) {
				LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
				LoadOpera.Pages[PageNum].ModTranscr(start.mySql.TrscQuery.getTranscribe(LoadOpera.Pages[1].PageID));
			}
			Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
			Transcribe.setText(LoadOpera.GetPage(PageNum).GetTrsc().Text);
			PageNumber.setText(PageNum.toString());
		}
	}
	
	@FXML
	public void HandleBackward () throws SQLException
	{
		if (PageNum > 1)
		{
			PageNum--;
			if (LoadOpera.Pages[PageNum] == null) {
				LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
				LoadOpera.Pages[PageNum].ModTranscr(start.mySql.TrscQuery.getTranscribe(LoadOpera.Pages[1].PageID));
			}
			Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
			Transcribe.setText(LoadOpera.GetPage(PageNum).GetTrsc().Text);
			PageNumber.setText(PageNum.toString());
		}
	}
	
	@FXML
	public void HandleStart () throws SQLException
	{
		PageNum = 1;
		if (LoadOpera.Pages[PageNum] == null) {
			LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
			LoadOpera.Pages[PageNum].ModTranscr(start.mySql.TrscQuery.getTranscribe(LoadOpera.Pages[1].PageID));
		}
		Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
		Transcribe.setText(LoadOpera.GetPage(PageNum).GetTrsc().Text);
		PageNumber.setText(PageNum.toString());
	}
	
	@FXML
	public void HandleEnd () throws SQLException
	{
		PageNum = LoadOpera.GetLenght();
		if (LoadOpera.Pages[PageNum] == null) {
			LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
			LoadOpera.Pages[PageNum].ModTranscr(start.mySql.TrscQuery.getTranscribe(LoadOpera.Pages[1].PageID));
		}
		Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
		Transcribe.setText(LoadOpera.GetPage(PageNum).GetTrsc().Text);
		PageNumber.setText(PageNum.toString());
	}
	
}
