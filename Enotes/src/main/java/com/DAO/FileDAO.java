package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.User.FileData;


public class FileDAO {
	
	private Connection conn;

	public FileDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public FileData getFilebyID(int id) {
	    FileData f = null;
		try {
			
			String qu = "Select * from file_details where id = ?";
			PreparedStatement ps = conn.prepareStatement(qu);
			ps.setInt(1, id);
			
			
			ResultSet rs = ps.executeQuery();
			boolean flag = rs.next();
			if(flag == true) {
				
				f=new FileData();
				
				f.setId(rs.getInt(1));
				f.setFile_name(rs.getString(2));
				f.setRemark(rs.getString(3));
				f.setUser(rs.getInt(4));
				return f;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	

}
