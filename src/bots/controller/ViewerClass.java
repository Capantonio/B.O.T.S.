package bots.controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;

import java.io.File;
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
	private AnchorPane Container;
	private Integer Method;
	
	
	@FXML
	private Button Rev_a = new Button();
	@FXML
	private Button Rev_d = new Button();
	
	@FXML
	private Button button1, button2, button3, button4;
	@FXML
	private TextField button5;
	
	private MainStart start;
	private OperaModel LoadOpera;
	private Integer PageNum;
	
	
	public void setStart (MainStart startx, Integer opload, Integer method, Integer page) throws SQLException
	{
		start = startx;
		OperaID = opload;
		Method = method;
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
		if (Method == 2)
			PageNum = page;
		else
			PageNum = 1;
		LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(opload, PageNum);
		Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
		if (Method == 2)
			Transcribe.setText(LoadOpera.GetPage(PageNum).WorkTrsc);
		else
			Transcribe.setText(LoadOpera.GetPage(PageNum).Transcribe);
		PageNumber.setText(PageNum.toString());
		Title.setText(LoadOpera.GetTitle());
		if (Method == 0)
			download.setVisible(start.ConnectedUser.Download.equals("1"));
		else
			download.setVisible(false);
		if (Method == 0)
			trsc.setVisible(start.mySql.UserQuery.TranscribePermission(start.ConnectedUser.ID, LoadOpera.Pages[1].PageID));
		else
			trsc.setVisible(false);
		
		if (Method == 1)
		{
			Rev_a.setText("Accept");
			Rev_a.setLayoutX(400);
			Rev_a.setLayoutY(600);
			Rev_a.setPrefWidth(80);
			Rev_a.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
					try {
						start.mySql.OperaQuery.SetShow(OperaID, "1");
						start.changeStageAdmin();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
			});
			Container.getChildren().add(Rev_a);
			Rev_d.setText("Refuse");
			Rev_d.setLayoutX(500);
			Rev_d.setLayoutY(600);
			Rev_d.setPrefWidth(80);
			Rev_d.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
					try {
						start.mySql.PageQuery.DeletePages(OperaID);
						start.mySql.OperaQuery.DeleteOpera(OperaID);
						start.changeStageAdmin();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
			});
			Container.getChildren().add(Rev_d);
		}
		
		if (Method == 2)
		{
			Rev_a.setText("Accept");
			Rev_a.setLayoutX(400);
			Rev_a.setLayoutY(600);
			Rev_a.setPrefWidth(80);
			Rev_a.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
					try {
						start.mySql.PageQuery.AcceptTranscribe(LoadOpera.GetPage(PageNum).PageID);
						start.changeStageAdmin();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
			});
			Container.getChildren().add(Rev_a);
			Rev_d.setText("Refuse");
			Rev_d.setLayoutX(500);
			Rev_d.setLayoutY(600);
			Rev_d.setPrefWidth(80);
			Rev_d.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
					try {
						start.mySql.PageQuery.DenyTranscribe(LoadOpera.GetPage(PageNum).PageID);
						start.changeStageAdmin();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
			});
			Container.getChildren().add(Rev_d);
		}
	}
	
	@FXML
	public void handleBackSearch ()
	{
		if (Method == 0)
			start.changeStageSearch();
		else
			start.changeStageAdmin();
	}
	
	@FXML
	public void HandleForward () throws SQLException
	{
		if (PageNum < LoadOpera.GetLenght()+1  && Method != 2)
		{
			PageNum++;
			if (LoadOpera.Pages[PageNum] == null)
				LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
			Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
			Transcribe.setText(LoadOpera.GetPage(PageNum).Transcribe);
			PageNumber.setText(PageNum.toString());
			if (Method == 0)
				trsc.setVisible(start.mySql.UserQuery.TranscribePermission(start.ConnectedUser.ID, LoadOpera.Pages[PageNum].PageID));
			else
				trsc.setVisible(false);
		}
	}
	
	@FXML
	public void HandleBackward () throws SQLException
	{
		if (PageNum > 1 && Method != 2)
		{
			PageNum--;
			if (LoadOpera.Pages[PageNum] == null)
				LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
			Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
			Transcribe.setText(LoadOpera.GetPage(PageNum).Transcribe);
			PageNumber.setText(PageNum.toString());
			if (Method == 0)
				trsc.setVisible(start.mySql.UserQuery.TranscribePermission(start.ConnectedUser.ID, LoadOpera.Pages[PageNum].PageID));
			else
				trsc.setVisible(false);
		}
	}
	
	@FXML
	public void HandleStart () throws SQLException
	{
		if (Method != 2)
		{
			PageNum = 1;
			if (LoadOpera.Pages[PageNum] == null)
				LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
			Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
			Transcribe.setText(LoadOpera.GetPage(PageNum).Transcribe);
			PageNumber.setText(PageNum.toString());
			if (Method == 0)
				trsc.setVisible(start.mySql.UserQuery.TranscribePermission(start.ConnectedUser.ID, LoadOpera.Pages[PageNum].PageID));
			else
				trsc.setVisible(false);
		}
	}
	
	@FXML
	public void HandleEnd () throws SQLException
	{
		if (Method != 2)
		{
			PageNum = LoadOpera.GetLenght();
			if (LoadOpera.Pages[PageNum] == null)
				LoadOpera.Pages[PageNum] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, PageNum);
			Page.setImage(LoadOpera.GetPage(PageNum).GetImage());
			Transcribe.setText(LoadOpera.GetPage(PageNum).Transcribe);
			PageNumber.setText(PageNum.toString());
			if (Method == 0)
				trsc.setVisible(start.mySql.UserQuery.TranscribePermission(start.ConnectedUser.ID, LoadOpera.Pages[PageNum].PageID));
			else
				trsc.setVisible(false);
		}
	}
	
	public String getJarFolder() {
        System.out.println("Working Directory = " +
               System.getProperty("user.dir"));
        return System.getProperty("user.dir");
   }


	@FXML
	public void handleDownload () throws IOException
	{
		File F = new File(getJarFolder() +  "\\" + LoadOpera.GetTitle());
		F.mkdirs();
		for (int i = 1; i < LoadOpera.GetLenght()+1; i ++)
		{
			OutputStream targetFile;
			if (LoadOpera.Pages[i] == null)
				LoadOpera.Pages[i] = start.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, i);
			targetFile = new FileOutputStream(getJarFolder() + "\\" + LoadOpera.GetTitle() + "\\" + LoadOpera.GetTitle() + "_" + LoadOpera.Pages[i].PageNumber + ".JPG");
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