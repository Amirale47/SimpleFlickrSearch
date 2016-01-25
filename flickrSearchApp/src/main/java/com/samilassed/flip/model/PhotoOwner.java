package com.samilassed.flip.model;

public class PhotoOwner {

	private String username;
	private String realname;
	
	public PhotoOwner() {}
	
	public PhotoOwner(String username, String realname) {
		this.username = username;
		this.realname = realname;
	}
	
	 public String getRealName() {
		return realname;
	}
	 
	 public String getUserName() {
		return username;
	}
	
	 
	 public void setRealName(String realname) {
		this.realname = realname;
	}
	 
	 public void setUserName(String username) {
		this.username = username;
	}
}
