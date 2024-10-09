package com.tapsfoods.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.TapFoods.model.user;
import com.tappfoods.dbutil.DBUtils;
import com.tapsfoods.dao.UserdDAO;

public class UserDaoImpl implements UserdDAO 
{
	int status=0;
	Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultset;
	
	ArrayList<user> userList=new ArrayList<user>();
	 user Userin;
	
	private static final String ADD_USER= "insert into `user`(`username`,`email`,`phoneNumber`,`password`,`address`)"
			+ " values(?,?,?,?,?)";
	private static final String SELECT_ALL= "select * from `user`";
	private static final String SELECT_ON_EMAIL= "select * from user where `email` =? ";
	private static final String UPDATE_ON_EMAIL= "update `user` set `username`=?,`phoneNumber`=?,password=?,address=?"
			+ " where `email` =? ";
	private static final String DELETE_ON_EMAIL= "delete from `user` where `email` =? ";
	
	
	public UserDaoImpl() //constructor
	{
		try 
		{
			con=DBUtils.myConnect();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public int addUser(user u) //method add user
	{
		try 
		{
			pstmt=con.prepareStatement(ADD_USER);
			
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPhoneNumber());
			pstmt.setString(4, u.getPassword());
			pstmt.setString(5, u.getAddress());
			
			status=pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	@Override
	public ArrayList<user> getAllUser()  //method add all user
	{
		try {
			
			stmt=con.createStatement();
			resultset=stmt.executeQuery(SELECT_ALL);
			userList=extractUserFromResultSet( resultset);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
		
		
	}
	
	
	@Override
	public user getUser(String email) { // method get user
		try {
			pstmt=con.prepareStatement(SELECT_ON_EMAIL);
			
			pstmt.setString(1, email);
			
			resultset=pstmt.executeQuery();
			userList=extractUserFromResultSet(resultset);
			Userin=userList.get(0);// if any error try to change userin
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Userin;
		
	}
	@Override
	public int updateUser(user u) { // method update user
		try {
			pstmt=con.prepareStatement(UPDATE_ON_EMAIL);
			
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPhoneNumber());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getAddress());
			
			pstmt.setString(5, u.getEmail());
			
			status=pstmt.executeUpdate();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}
	@Override
	public int deleteUser(String email) {  // method delete user
		try {
			pstmt=con.prepareStatement(DELETE_ON_EMAIL);
			pstmt.setString(1, email);
			
			status=pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}
	
	
	ArrayList<user> extractUserFromResultSet(ResultSet resultset) //extract method to get all user method
	{
		try 
		{
			while(resultset.next()) 
			{
				userList.add(new user(resultset.getInt("userId"),
				resultset.getString("username"),
				resultset.getString("email"),
				resultset.getString("phoneNumber"),
				resultset.getString("password"),
				resultset.getString("address")));
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
		
		
	}

}
