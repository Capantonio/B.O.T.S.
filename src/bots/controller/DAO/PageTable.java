package bots.controller.DAO;

import java.sql.Connection;

public class PageTable {

	private Connection db;
	private String SqlSearch = "";
	
	public PageTable(Connection xcon)
	{
		db = xcon;
	}
}
