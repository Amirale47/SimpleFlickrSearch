package com.samilassed.flip.model;

public class PhotoInfo {

	private int id;
	private String title;
	private PhotoOwner owner;
	private String description;
	
	public PhotoInfo() {}
	
	public PhotoInfo(int id, String title, PhotoOwner owner, String description) {
		this.id = id;
		this.title = title;
		this.owner = owner;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getId() {
		return id;
	}
	
	public PhotoOwner getOwner() {
		return owner;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setOwner(PhotoOwner owner) {
		this.owner = owner;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}
