package bots.controller.DAO;

import java.sql.*;

public class MysqlConnection 
{
	private Connection connect = null;
    
    public UserTable UserQuery;
    public OperaTable OperaQuery;
    public PageTable PageQuery;
	
	public MysqlConnection()
	{
		try {
			Connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserQuery = new UserTable(connect);
		OperaQuery = new OperaTable(connect);
		PageQuery = new PageTable(connect);
		
	}
	
	public void Connect() throws Exception
	{
		try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            
		} catch (Exception e) {
            throw e;
        }
 	}
	
}