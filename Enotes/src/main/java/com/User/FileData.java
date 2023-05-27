package com.User;

public class FileData {
	
private int  id;
	
	private String file_name;
	
	private String remark;
	
	private int user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public FileData(int id, String file_name, String remark, int user) {
		super();
		this.id = id;
		this.file_name = file_name;
		this.remark = remark;
		this.user = user;
	}

	public FileData() {
		super();
	}

	



}
