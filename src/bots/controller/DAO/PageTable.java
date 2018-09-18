package bots.controller.DAO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import bots.controller.AdminClass;
import bots.controller.model.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class PageTable {

	private Connection db;
	
	public PageTable(Connection xcon)
	{
		db = xcon;
	}
	
	public void DeletePages (Integer id) throws SQLException
	{
		PreparedStatement DelPage = db.prepareStatement("Delete From mydb.page Where Opera_idOpera = ?");
		DelPage.setInt(1, id);
		DelPage.executeUpdate();
	}
	
	public void DenyTranscribe (Integer page) throws SQLException
	{
		PreparedStatement AlterTrsc = db.prepareStatement("UPDATE mydb.page SET WorkTrsc='' WHERE idPage=?");
		AlterTrsc.setInt(1, page);
		AlterTrsc.executeUpdate();
	}
	
	public void AcceptTranscribe (Integer page) throws SQLException
	{
		String data = null;
		PreparedStatement AlterTrsc = db.prepareStatement("SELECT WorkTrsc FROM mydb.page WHERE idPage=?");
		AlterTrsc.setInt(1, page);
		ResultSet x = AlterTrsc.executeQuery();
		while (x.next())
			data = x.getString("WorkTrsc");
		PreparedStatement AlterTrsc2 = db.prepareStatement("UPDATE mydb.page SET WorkTrsc='', Transcribe=? WHERE idPage=?");
		AlterTrsc2.setString(1, data);
		AlterTrsc2.setInt(2, page);
		AlterTrsc2.executeUpdate();
	}
	
	public void LoadImage(Integer opera, String img, Integer num)
    {
            int len;
            String query;
            PreparedStatement pstmt;
             
            try
            {
                    File file = new File(img);
                    FileInputStream fis = new FileInputStream(file);
                    len = (int)file.length();

                    query = ("insert Into mydb.page (ImagePage, Number, Lenght, Opera_idOpera, LockOpera) VALUES(?,?,?,?,'0')");
                    pstmt = db.prepareStatement(query);
                    pstmt.setInt(2, num);
                    pstmt.setInt(3, len);

                    // Method used to insert a stream of bytes
                    pstmt.setBinaryStream(1, fis, len); 
                    pstmt.setInt(4, opera);
                    pstmt.executeUpdate();

            }
            catch (Exception e)
            {
                    e.printStackTrace();
            }
    }
	
	public LinkedList<PageModel> GetRevPage (AdminClass obj, AnchorPane container) throws SQLException
	{
		LinkedList<PageModel> result = new LinkedList<PageModel>();
		PreparedStatement Query = db.prepareStatement("SELECT * FROM mydb.page INNER JOIN mydb.opera ON mydb.page.Opera_idOpera = idOpera WHERE !(WorkTrsc='')");
		ResultSet x = Query.executeQuery();
		Integer Counter = 0;
		while (x.next())
		{
			result.add(new PageModel(x.getInt("idPage"), x.getInt("Number"), x.getString("Title"), x.getInt("idOpera"), x.getString("Transcribe"), x.getString("LastUser"), x.getString("WorkTrsc"),obj,Counter,container));
			Counter ++;
		}
		return result;
	}
	
	
	public PageModel GetPageFromOpera(Integer OperaId,Integer number)
	{
		Image image = null;
		BufferedImage img;
		PageModel res = null;
		byte[] fileBytes;
        String query;
        PreparedStatement pstmt;
        try
        {
        	query = "select idPage, ImagePage, Transcribe, LastUser, WorkTrsc from mydb.page where Opera_idOpera = ? AND Number = ?";
            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, OperaId);
            pstmt.setInt(2, number);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                     fileBytes = rs.getBytes("ImagePage");
                     //OutputStream targetFile=  
                     //new FileOutputStream("d://filepath//new.JPG");
                     //targetFile.write(fileBytes);
                     //targetFile.close();
                     img = ImageIO.read(new ByteArrayInputStream(fileBytes));
                     image = SwingFXUtils.toFXImage(img, null);
                     res = new PageModel(rs.getInt("idPage"), image, number, rs.getString("Transcribe"), rs.getString("LastUser"), rs.getString("WorkTrsc"));
            }    
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
        return res;
	}
	
	
	public boolean GetLock(Integer id) throws SQLException
	{
		PreparedStatement TrscPerm = db.prepareStatement("Select LockOpera From mydb.page Where idPage = ?");
		TrscPerm.setInt(1, id);
		ResultSet x = TrscPerm.executeQuery();
		while (x.next())
			return x.getString("LockOpera").equals("0");
		return false;
	}
	
	public void SetLock(Integer id, String lock) throws SQLException
	{
		PreparedStatement pstmt;
        pstmt = db.prepareStatement("Update mydb.page Set LockOpera = ? Where idPage = ?");
        pstmt.setString(1, lock);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
	}
	
	public void SetWork(Integer user, String work, Integer id) throws SQLException
	{
		PreparedStatement pstmt;
        pstmt = db.prepareStatement("Update mydb.page Set WorkTrsc = ?, LastUser = ?, LockOpera = 0 Where idPage = ?");
        pstmt.setInt(3, id);
        pstmt.setInt(2, user);
        pstmt.setString(1, work);
        pstmt.executeUpdate();
	}
	
	public String LoadWork (Integer id) throws SQLException
	{
		PreparedStatement TrscPerm = db.prepareStatement("Select WorkTrsc From mydb.page Where idPage = ?");
		TrscPerm.setInt(1, id);
		ResultSet x = TrscPerm.executeQuery();
		while (x.next())
			return x.getString("WorkTrsc");
		return null;
	}
}
