package com.samilassed.flip.model.data;

import java.util.List;

import android.util.Log;

import com.samilassed.flip.model.PhotoInfo;
import com.samilassed.flip.model.PhotoOwner;
import com.samilassed.utils.FlickrManager;
import com.samilassed.utils.FlickrPhoto;

public class FlickerMap implements Photo2DMap{

	private List<FlickrPhoto> images;
	private List<PhotoInfo> imagesInfo;
	
	private FlickerMap(List<FlickrPhoto> images) {
		this.images = images;
	}
	
	@Override
	public int size() {
		return images.size();
	}
	@Override
	public FlickrPhoto getImage(String imageId) {
		int index = getIndex(imageId);
		if(index != -1){
			return images.get(index);
		}
		return null;
	}
	@Override
	public PhotoInfo getPhotoInfo(String imageId) {
		int index = getIndex(imageId);
		Log.e("Tag", "*****************************Images Size: " + imagesInfo.size());
		if(index != -1){
			return imagesInfo.get(index);
		}
		return null;
	}
	@Override
	public PhotoOwner getOwner(String imageId) {
		PhotoInfo photo = getPhotoInfo(imageId);
		if(photo != null){
			return photo.getOwner();
		}
		return null;
	}
	
	public int getIndex(String imageId){
		if(isValid()){
			int index = 0;
			for(FlickrPhoto photo : images){
				if(photo.getId().equalsIgnoreCase(imageId)){
					return index;
				}
				index++;
			}
		}
		return -1;
	}
	
	public List<FlickrPhoto> images(){
		return images;
	}
	
	private boolean isValid(){
		return (images != null && !images.isEmpty());
	}
	
	private void loadFromServer(){
		//Long running operation
		this.imagesInfo = FlickrManager.NEW().getPhtosInfo(images);
	}
	
	//This should be running in a thread or async task
	public static FlickerMap lazyLoad(List<FlickrPhoto> images) {
		FlickerMap instance = new FlickerMap(images);
		instance.loadFromServer();
		return instance;
	}
	
}
