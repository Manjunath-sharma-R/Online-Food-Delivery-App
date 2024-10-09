package com.tappfoods.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final public class DBUtils //nobody should inherit(ie final)
{
	static Connection con = null;//nobody to create object means static,abstract;
	private static String username="root";
	private static String password="Manjunath_9";
	private static String url="jdbc:mysql://localhost:3306/tapfoods";
	
	
	public static Connection myConnect() 
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection(url,username,password);
		} 
		catch (ClassNotFoundException |SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
		
	}

}
