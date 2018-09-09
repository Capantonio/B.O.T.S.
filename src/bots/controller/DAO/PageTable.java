package bots.controller.DAO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.*;

import javax.imageio.ImageIO;
import bots.controller.model.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class PageTable {

	private Connection db;
	
	public PageTable(Connection xcon)
	{
		db = xcon;
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

                    query = ("insert Into mydb.page (ImagePage, Number, Lenght, Opera_idOpera) VALUES(?,?,?,?)");
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
        	query = "select ImagePage from mydb.page where Opera_idOpera = ? AND Number = ?";
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
            }    
            res = new PageModel(image, number);
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
        return res;
	}
	
	
	public void GetPageFromOpera(Integer OperaId, Integer xstart, Integer xend)
	{
		
		
	}
}
