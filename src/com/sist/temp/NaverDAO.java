package com.sist.temp;

import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.sql.*;
public class NaverDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:ORCL";
	
	public NaverDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(URL, "scott", "tiger");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void disConnection(){
		try{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	public void movieInsert(NaverMovieVO vo){
		try{
			getConnection();
			//Ã³¸®
			
			String sql = "INSERT INTO movie VALUES(?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getCode());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getPoster());
			ps.setString(4, vo.getDirector());
			ps.setString(5, vo.getActor());
			ps.setString(6, vo.getStory());
			ps.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			disConnection();
		}
	}
}
