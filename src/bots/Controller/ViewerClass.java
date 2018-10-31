package bots.Controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import bots.Controller.MainStart;
import bots.Model.*;

public class ViewerClass {

	public Integer Method;
	public OperaModel LoadOpera;
	public Integer PageNum;
	
	public ViewerClass (OperaModel opload, Integer method, Integer page) throws SQLException
	{
		LoadOpera = opload;
		Method = method;
	}
	
	public void AcceptOperaRevision()
	{
		try {
			MainStart.mySql.OperaQuery.SetShow(LoadOpera.ID, "1");
		} catch (SQLException e) 
		{e.printStackTrace();}
	}
	
	public void DenyOperaRevision()
	{	
		try {
			MainStart.mySql.PageQuery.DeletePages(LoadOpera.ID);
			MainStart.mySql.OperaQuery.DeleteOpera(LoadOpera.ID);
		} catch (SQLException e) 
		{e.printStackTrace();}
	}
	
	public void AcceptTrscRevision()
	{
		try {
			MainStart.mySql.PageQuery.AcceptTranscribe(LoadOpera.GetPage(PageNum).PageID);
		} catch (SQLException e) 
		{e.printStackTrace();}
	}
	
	public void DenyTrscRevision()
	{
		try {
			MainStart.mySql.PageQuery.DenyTranscribe(LoadOpera.GetPage(PageNum).PageID);
		} catch (SQLException e) 
		{e.printStackTrace();}
	}
	
	public PageModel ChangePage(Integer num)
	{
		PageModel ret=null;
		LoadOpera.Pages[num] = MainStart.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, num);
		return ret;
	}
	
	public Boolean CheckPermission() throws SQLException
	{
		return MainStart.mySql.UserQuery.TranscribePermission(MainStart.ConnectedUser.ID, LoadOpera.Pages[PageNum].PageID);
	}
	
	
	public void Forward () throws SQLException
	{
		if (PageNum < LoadOpera.Lenght  && Method != 2)
		{
			PageNum++;
			if (LoadOpera.Pages[PageNum] == null)
				LoadOpera.Pages[PageNum] = ChangePage(PageNum);
		}
	}
	
	
	public void Backward () throws SQLException
	{
		if (PageNum > 1 && Method != 2)
		{
			PageNum--;
			if (LoadOpera.Pages[PageNum] == null)
				LoadOpera.Pages[PageNum] = ChangePage(PageNum);
		}
	}
	
	public void Start () throws SQLException
	{
		if (Method != 2)
		{
			PageNum = 1;
			if (LoadOpera.Pages[PageNum] == null)
				LoadOpera.Pages[PageNum] = ChangePage(PageNum);
		}
	}
	
	
	public void End () throws SQLException
	{
		if (Method != 2)
		{
			PageNum = LoadOpera.Lenght;
			if (LoadOpera.Pages[PageNum] == null)
				LoadOpera.Pages[PageNum] = ChangePage(PageNum);
		}
	}
	
	public String getJarFolder() {
        System.out.println("Working Directory = " +
               System.getProperty("user.dir"));
        return System.getProperty("user.dir");
        }
	
	public void Download () throws IOException
	{
		File F = new File(getJarFolder() +  "\\" + LoadOpera.Title);
		F.mkdirs();
		for (int i = 1; i < LoadOpera.Lenght+1; i ++)
		{
			OutputStream targetFile;
			if (LoadOpera.Pages[i] == null)
				LoadOpera.Pages[i] = MainStart.mySql.PageQuery.GetPageFromOpera(LoadOpera.ID, i);
			targetFile = new FileOutputStream(getJarFolder() + "\\" + LoadOpera.Title + "\\" + LoadOpera.Title + "_" + LoadOpera.Pages[i].PageNumber + ".JPG");
			ImageIO.write(SwingFXUtils.fromFXImage(LoadOpera.Pages[i].PageImage, null), "PNG", targetFile);
			//byte[] fileBytes = x.PageImage.getBytes("ImagePage");
			//targetFile.write(fileBytes);
			//targetFile.close();
		}
	}
}