package bots.controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bots.controller.model.TranscribeModel;

public class TranscribeTable {

private Connection db;
	
	public TranscribeTable(Connection xcon)
	{
		db = xcon;
	}
	
	public TranscribeModel getTranscribe (Integer page) throws SQLException 
	{
		TranscribeModel res = null;
		String query;
        PreparedStatement pstmt;
        
		query = "select * from mydb.transcribe where Page_idPage = ?";
        pstmt = db.prepareStatement(query);
        pstmt.setInt(1, page);
        ResultSet x = pstmt.executeQuery();
        
        while (x.next())
        {
        	res = new TranscribeModel(x.getString("Text"), x.getString("Last edit"));
        }
		
		return res;
	}
}
