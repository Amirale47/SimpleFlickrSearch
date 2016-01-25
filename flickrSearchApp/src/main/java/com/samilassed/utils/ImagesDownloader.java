package com.samilassed.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.samilassed.gui.activity.fragment.PhotosFragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

public class ImagesDownloader implements Runnable{

	private static final int THREADS_CAPACITY = 20;
	
	/*
	 * Here I use a downloading tracker to keep tracking already  downloaded images,
	 * so in case Internet connection is lost during download process, I can know
	 * which one should be re-downloaded once internet connection is back or once
	 * =========> SCREEN ROTATION HAPPENS!!!!!
	 * I use content providers(sqlite database) for that tracking, but now I am skeeping this step 
	 * */
	private static final ArrayList<Integer> DOWNLOADING_LIST = new ArrayList<Integer>();

	//private Context cnt;
	private final Handler uiHandler;
	private final List<FlickrPhoto> list;
	private final ExecutorService service;

	private ImagesDownloader(/*Context cnt,*/ List<FlickrPhoto> list, Handler uiHandler) {
		//this.cnt = cnt;
		this.list = list;
		this.uiHandler = uiHandler;
		//Why Fixed length thread pool? Nice question, I will answer it on the meeting
		service = Executors.newFixedThreadPool(THREADS_CAPACITY);
	}

	protected Void doInBackground() {
		
		if(list != null && !list.isEmpty()){
			try {
				for (FlickrPhoto photo: list) {
					String imageId = photo.getId();
					File imageFile = Utility.getImageFile(imageId);
					if(imageFile.exists()){
						imageFile.delete();
					}
					service.execute(new Downloader(photo, uiHandler));
				}
				service.awaitTermination(1, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static synchronized ArrayList<Integer> getDownloadingList() {
		return DOWNLOADING_LIST;
	}

	@Override
	public void run() {
		//Start the main downloader thread
		doInBackground();
	}

	public static ImagesDownloader getInstance(List<FlickrPhoto> list, Handler uiHandler){
		return new ImagesDownloader(list, uiHandler);
	}

	public void downloadAllImages(){
		new Thread(this).start();
	}

	//Thread that downloads one image
	private class Downloader implements Runnable{
		
		private FlickrPhoto photo;
		private Handler uiHandler;
		
		public Downloader(FlickrPhoto photo, Handler uiHandler) {
			this.photo = photo;
			this.uiHandler = uiHandler;
		}
		@Override
		public void run() {
			Helper.downloadImage(photo);
			Message msg = Message.obtain(uiHandler, PhotosFragment.UPDATE);
			uiHandler.sendMessage(msg);
		}
	}

}
