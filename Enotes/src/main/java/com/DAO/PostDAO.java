package com.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.User.Post;
public class PostDAO {
  
	
	private Connection conn;

	public PostDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean AddNotes(String subject , String ti ,String  co ,int  ui) {
		boolean f = false;
		try {
			String qu = "Insert into post (title ,content ,uid , subject) values (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(qu);
			ps.setString(1,ti);
			ps.setString(2,co);
			ps.setInt(3,ui);
			ps.setString(4, subject);
			
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				f=true;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	
	public boolean PostUpdate(int nid , String title , String content) {
		
		boolean f = false;
		
		try {
			String qu = "update post set title = ? , content=? where id=?";
			PreparedStatement ps = conn.prepareStatement(qu);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, nid);
			
			int i = ps.executeUpdate();
			
			if(i==1) f=true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	
	
	public List<Post> getData(int id){
		
		List<Post> list = new ArrayList<Post>();
		Post po = null;
		
		
		try {
			String qu = "Select * from post where uid=? order by id desc;";
			PreparedStatement ps = conn.prepareStatement(qu);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				po = new Post();
				po.setId(rs.getInt(1));
				po.setTitle(rs.getString(2));
				po.setContent(rs.getString(3));
				//po.setPdate(rs.getTimestamp(4));
				list.add(po);
			}
			
		}
		catch(Exception e) {
			
		}
		return list;
	}
	
	
	public Post getDataByID(int noteId) {
		 
		Post p = null;
		try {
			String qu = "Select * from post where id=?";
			PreparedStatement ps = conn.prepareStatement(qu);
			ps.setInt(1, noteId);
			
			
			ResultSet rs = ps.executeQuery();
			boolean flag = rs.next();
			if(flag == true) {
				
				p=new Post();
				
				p.setId(rs.getInt(1));
				p.setTitle(rs.getString(2));
				p.setContent(rs.getString(3));
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return p;
	}
	
	
	public boolean deleteNote(int nid) {
		boolean f = false;
		try {
			String qu = "Delete from post where id = ?";
			PreparedStatement ps = conn.prepareStatement(qu);
			ps.setInt(1, nid);
			int i = ps.executeUpdate();
			if(i == 1) {
				f=true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
