package bots.controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;

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
	@FXML 
	private Button back;
	@FXML
	private Button download;
	@FXML
	private Button trsc;
	@FXML
	private Button cancel;
	@FXML
	private Button apply;
	
	@FXML
	private Button button1, button2, button3, button4;
	@FXML
	private TextField button5;
	
	private MainStart start;
	private OperaModel LoadOpera;
	private Integer PageNum;
	
	
	public void setStart (MainStart startx, Integer opload) throws SQLException
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
		Transcribe.setEditable(false);
		cancel.setVisible(false);
		apply.setVisible(false);
		PageNumber.setEditable(false);
		LoadOpera.Pages[1] = start.mySql.PageQuery.GetPageFromOpera(opload, 1);
		PageNum = 1;
		Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
		Transcribe.setText(LoadOpera.GetPage(PageNum).Transcribe);
		PageNumber.setText(PageNum.toString());
		Title.setText(LoadOpera.GetTitle());
		download.setVisible(start.ConnectedUser.Download.equals("1"));
		trsc.setVisible(start.mySql.UserQuery.TranscribePermission(start.ConnectedUser.ID, LoadOpera.Pages[1].PageID));
	}
	
	@FXML
	public void handleBackSearch ()
	{
		start.changeStageSearch();
	}
	
	@FXML
	public void HandleForward () throws SQLException
	{
		if (PageNum < LoadOpera.GetLenght()+1)
		{
			PageNum++;
			if (LoadOpera.Pages[PageNum] == null)
				LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
			Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
			Transcribe.setText(LoadOpera.GetPage(PageNum).Transcribe);
			PageNumber.setText(PageNum.toString());
			trsc.setVisible(start.mySql.UserQuery.TranscribePermission(start.ConnectedUser.ID, LoadOpera.Pages[PageNum].PageID));
		}
	}
	
	@FXML
	public void HandleBackward () throws SQLException
	{
		if (PageNum > 1)
		{
			PageNum--;
			if (LoadOpera.Pages[PageNum] == null)
				LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
			Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
			Transcribe.setText(LoadOpera.GetPage(PageNum).Transcribe);
			PageNumber.setText(PageNum.toString());
			trsc.setVisible(start.mySql.UserQuery.TranscribePermission(start.ConnectedUser.ID, LoadOpera.Pages[PageNum].PageID));
		}
	}
	
	@FXML
	public void HandleStart () throws SQLException
	{
		PageNum = 1;
		if (LoadOpera.Pages[PageNum] == null)
			LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
		Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
		Transcribe.setText(LoadOpera.GetPage(PageNum).Transcribe);
		PageNumber.setText(PageNum.toString());
		trsc.setVisible(start.mySql.UserQuery.TranscribePermission(start.ConnectedUser.ID, LoadOpera.Pages[PageNum].PageID));
	}
	
	@FXML
	public void HandleEnd () throws SQLException
	{
		PageNum = LoadOpera.GetLenght()+1;
		if (LoadOpera.Pages[PageNum] == null)
			LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
		Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
		Transcribe.setText(LoadOpera.GetPage(PageNum).Transcribe);
		PageNumber.setText(PageNum.toString());
		trsc.setVisible(start.mySql.UserQuery.TranscribePermission(start.ConnectedUser.ID, LoadOpera.Pages[PageNum].PageID));
	}
	
	@FXML
	public void handleDownload () throws IOException
	{
		for (int i = 1; i < LoadOpera.GetLenght(); i ++)
		{
			OutputStream targetFile;
			if (LoadOpera.Pages[i] == null)
				LoadOpera.Pages[i] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, i);
			targetFile = new FileOutputStream("C:\\Users\\Fabio\\Desktop\\Fabio\\Univaq3\\" + LoadOpera.GetTitle() + "_" + LoadOpera.Pages[i].PageNumber + ".PNG");
			ImageIO.write(SwingFXUtils.fromFXImage(LoadOpera.Pages[i].PageImage, null), "PNG", targetFile);
			//byte[] fileBytes = x.PageImage.getBytes("ImagePage");
			//targetFile.write(fileBytes);
			//targetFile.close();
		}
	}
	
	@FXML
	public void handleTrsc2 () throws SQLException
	{
		if (start.mySql.PageQuery.GetLock(LoadOpera.GetPage(PageNum).PageID))
		{
			start.mySql.PageQuery.SetLock(LoadOpera.GetPage(PageNum).PageID, "1");
			Transcribe.setEditable(true);
			cancel.setVisible(true);
			apply.setVisible(true);
			ShowCursor(false);
		}
	}
	
	@FXML
	public void handleApply2 () throws SQLException
	{
		start.mySql.PageQuery.SetWork(start.ConnectedUser.ID, Transcribe.getText(), LoadOpera.GetPage(PageNum).PageID);
		cancel.setVisible(false);
		apply.setVisible(false);
		Transcribe.setEditable(false);
		ShowCursor(true);
	}
	
	@FXML
	public void handleCancel2 () throws SQLException
	{
		start.mySql.PageQuery.SetLock(LoadOpera.GetPage(PageNum).PageID, "0");
		cancel.setVisible(false);
		apply.setVisible(false);
		Transcribe.setEditable(false);
		ShowCursor(true);
	}
	
	public void ShowCursor (boolean value)
	{
		button1.setVisible(value);
		button2.setVisible(value);
		button3.setVisible(value);
		button4.setVisible(value);
		PageNumber.setVisible(value);
		trsc.setVisible(value);
	}
}