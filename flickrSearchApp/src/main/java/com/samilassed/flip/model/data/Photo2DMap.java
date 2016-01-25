package com.samilassed.flip.model.data;

import com.samilassed.flip.model.PhotoInfo;
import com.samilassed.flip.model.PhotoOwner;
import com.samilassed.utils.FlickrPhoto;

public interface Photo2DMap {

	public int size();
	
	public FlickrPhoto getImage(String imageId);
	
	public PhotoInfo getPhotoInfo(String imageId);
	
	public PhotoOwner getOwner(String imageId);
	
	/*
	 * Other methods to retrieve iterators and key set and values and others
	 * */
}
