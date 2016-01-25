package com.samilassed.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.samilassed.flip.model.PhotoInfo;
import com.samilassed.flip.model.PhotoOwner;

public class FlickrManager {

	private static final FlickrManager INSTANCE = new FlickrManager(); 
	
	// String to create Flickr API urls
	private static final String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/?method=";
	//https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=bb6f567fa7e0c41e4afdc15945830116&photo_id=2177060015&format=json&nojsoncallback=1
	private static final String FLICKR_PHOTOS_INFO_METHOD = "flickr.photos.getInfo";
	private static final String FLICKR_PHOTOS_SEARCH_METHOD = "flickr.photos.search";
	private static final String FLICKR_GET_SIZES_METHOD = "flickr.photos.getSizes";
	private static final String FLICKR_OWNER_INFO_METHOD = "flickr.people.getInfo";
	
	private static final int FLICKR_PHOTOS_SEARCH_ID = 1;
	private static final int FLICKR_GET_SIZES_ID = 2;
	private static final int FLICKR_PHOTO_INFO_ID = 3;
	private static final int FLICKR_OWNER_INFO_ID = 4;
	
	//We use 30 photos for the test, but throught the result 
	private static final int NUMBER_OF_PHOTOS = 20;
	
	//You can set here your API_KEY
	private static final String APIKEY_SEARCH_STRING = "&api_key=a415a214a006bdaeb047f2d861e2a066";
	
	private static final String TAGS_STRING = "&tags=";
	private static final String PHOTO_ID_STRING = "&photo_id=";
	private static final String FORMAT_STRING = "&format=json";
	private static final String USER_ID_STRING = "&user_id=";
	public static final int PHOTO_THUMB = 111;
	public static final int PHOTO_LARGE = 222;

	/*Make singleton instance. 
	 * FlickrManager can act as a config resource,
	 * that serves us what we need without new instances*/
	private FlickrManager() {}
	
	//public static UIHandler uihandler;

	private static String createURL(int methodId, String parameter) {
		//String method_type;
		String url = null;
		switch (methodId) {
		case FLICKR_PHOTOS_SEARCH_ID:
			//method_type = FLICKR_PHOTOS_SEARCH_STRING;
			url = FLICKR_BASE_URL + FLICKR_PHOTOS_SEARCH_METHOD + APIKEY_SEARCH_STRING + TAGS_STRING + parameter + FORMAT_STRING + "&per_page="+NUMBER_OF_PHOTOS+"&media=photos";
			break;
		case FLICKR_GET_SIZES_ID:
			//method_type = FLICKR_GET_SIZES_STRING;
			url = FLICKR_BASE_URL + FLICKR_GET_SIZES_METHOD + PHOTO_ID_STRING + parameter + APIKEY_SEARCH_STRING + FORMAT_STRING;
			break;
		case FLICKR_PHOTO_INFO_ID:
			//method_type = FLICKR_PHOTOS_SEARCH_INFO;
			url = FLICKR_BASE_URL + FLICKR_PHOTOS_INFO_METHOD + PHOTO_ID_STRING + parameter + APIKEY_SEARCH_STRING + FORMAT_STRING;
			break;
		case FLICKR_OWNER_INFO_ID:
			//method_type = FLICKR_PHOTOS_SEARCH_INFO;
			url = FLICKR_BASE_URL + FLICKR_OWNER_INFO_METHOD + USER_ID_STRING + parameter + APIKEY_SEARCH_STRING + FORMAT_STRING;
			break;
		}
		return url;
	}
	
	// http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
	public static void getImageURLS(FlickrPhoto imgCon) throws Exception{
		String url = createURL(FLICKR_GET_SIZES_ID, imgCon.id);
		if(url != null){
			ByteArrayOutputStream baos = URLConnector.readBytes(url);
			String json = baos.toString();
			//Log.e("JSON", json);
			try {
				JSONObject root = new JSONObject(json.replace("jsonFlickrApi(", "").replace(")", ""));
				JSONObject sizes = root.getJSONObject("sizes");
				JSONArray size = sizes.getJSONArray("size");
				for (int i = 0; i < size.length(); i++) {
					JSONObject image = size.getJSONObject(i);
					if (image.getString("label").equals("Square")) {
						imgCon.setThumbURL(image.getString("source"));
					} else if (image.getString("label").equals("Medium")) {
						imgCon.setLargeURL(image.getString("source"));
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			/*This exception will never happen because we use the right one.
			 * HOWEVER, I have put this just to show that it is very important
			 *  to check exception for url. if url is wrong so all the operation is 
			 *  wrong and we have to notify for the exception.*/  
			throw new Exception("Wrong method id " + FLICKR_GET_SIZES_ID);
		}
		
	}

	public static Bitmap getImage(FlickrPhoto imgCon) {
		Bitmap bm = null;
		try {
			URL aURL = new URL(imgCon.largeURL);
			//Log.e("Flicker", imgCon.largeURL);
			URLConnection conn = aURL.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bm = BitmapFactory.decodeStream(bis);
			//bis.close();
			is.close();
		} catch (Exception e) {
			Log.e("FlickrManager", e.getMessage()+"");
		}finally{
			
		}
		return bm;
	}

	public static void getThumbnails(ArrayList<FlickrPhoto> imgCon/*, UIHandler uih*/) {
		for (int i = 0; i < imgCon.size(); i++){
			//new GetThumbnailsThread(uih, imgCon.get(i)).start();
		}
	}

	public static Bitmap getThumbnail(FlickrPhoto imgCon) {
		Bitmap bm = null;
		try {
			URL aURL = new URL(imgCon.thumbURL);
			URLConnection conn = aURL.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bm = BitmapFactory.decodeStream(bis);
			bis.close();
			is.close();
		} catch (Exception e) {
			Log.e("FlickrManager", e.getMessage());
		}
		return bm;
	}

	public static class GetThumbnailsThread extends Thread {
		//UIHandler uih;
		FlickrPhoto imgContener;

		/*public GetThumbnailsThread(UIHandler uih, FlickrPhoto imgCon) {
			this.uih = uih;
			this.imgContener = imgCon;
		}*/

		@Override
		public void run() {
			imgContener.thumb = getThumbnail(imgContener);
			if (imgContener.thumb != null) {
				/*Message msg = Message.obtain(uih, UIHandler.ID_UPDATE_ADAPTER);
				uih.sendMessage(msg);*/

			}
		}

	}

	/*public static ArrayList<ImageContener> searchImagesByTag(UIHandler uih, Context ctx, String tag) {
		uihandler = uih;
		String url = createURL(FLICKR_PHOTOS_SEARCH_ID, tag);
		ArrayList<ImageContener> tmp = new ArrayList<ImageContener>();
		String jsonString = null;
		try {
			if (URLConnector.isOnline(ctx)) {
				ByteArrayOutputStream baos = URLConnector.readBytes(url);
				jsonString = baos.toString();
			}
			try {
				JSONObject root = new JSONObject(jsonString.replace("jsonFlickrApi(", "").replace(")", ""));
				JSONObject photos = root.getJSONObject("photos");
				JSONArray imageJSONArray = photos.getJSONArray("photo");
				for (int i = 0; i < imageJSONArray.length(); i++) {
					JSONObject item = imageJSONArray.getJSONObject(i);
					ImageContener imgCon = new ImageContener(item.getString("id"), item.getString("owner"), item.getString("secret"), item.getString("server"),
							item.getString("farm"));
					imgCon.position = i;
					tmp.add(imgCon);
				}
				Message msg = Message.obtain(uih, UIHandler.ID_METADATA_DOWNLOADED);
				msg.obj = tmp;
				uih.sendMessage(msg);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (NullPointerException nue) {
			nue.printStackTrace();
		}

		return tmp;
	}*/
	
	protected PhotoInfo getPhotoInfo(String imageId) {
		String url = createURL(FLICKR_PHOTO_INFO_ID, imageId);
		String jsonStr = Helper.get(url);
		if(url != null){
			try {
				jsonStr = jsonStr.replace("jsonFlickrApi(", "").replace(")", "");
				JSONObject root = new JSONObject(jsonStr);
				//Log.e("Tagggg", jsonStr);
				JSONObject photo = root.getJSONObject("photo");
				JSONObject owner = photo.getJSONObject("owner");
				String username = owner.getString("username");//.getString("_content");
				String realname = owner.getString("realname");//.getString("_content");
				
				int id = photo.getInt("id");
				String title = photo.getString("title");
				String description = photo.getString("description");
				/*
				 * Can add other info like photos or anything else we need
				 * */
				return new PhotoInfo(id, title, new PhotoOwner(username, realname), description);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<PhotoInfo> getPhtosInfo(List<FlickrPhoto> images) {
		if(images != null && !images.isEmpty()){
			List<PhotoInfo> photos = new ArrayList<PhotoInfo>();
			for(FlickrPhoto image : images){
				photos.add(getPhotoInfo(image.id));
			}
			return photos;
		}
		return null;
	}
	
	protected PhotoOwner getOwnerInfo(String ownerId) {
		String url = createURL(FLICKR_PHOTOS_SEARCH_ID, ownerId);
		String jsonStr = Helper.get(url);
		if(url != null){
			try {
				JSONObject root = new JSONObject(jsonStr.replace("jsonFlickrApi(", "").replace(")", ""));
				JSONObject person = root.getJSONObject("person");
				String username = person.getString("username");
				String realname = person.getString("realname");
				/*
				 * Can add other info like photos or anything else we need
				 * */
				return new PhotoOwner(username, realname);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<PhotoOwner> getOwners(List<FlickrPhoto> images) {
		if(images != null && !images.isEmpty()){
			List<PhotoOwner> owners = new ArrayList<PhotoOwner>();
			for(FlickrPhoto image : images){
				owners.add(getOwnerInfo(image.owner));
			}
			return owners;
		}
		return null;
	}
	
	public ArrayList<FlickrPhoto> searchImagesByTag(Context ctx, String tag) {
		String url = createURL(FLICKR_PHOTOS_SEARCH_ID, tag);
		if(url != null){
			
			ArrayList<FlickrPhoto> tmp = new ArrayList<FlickrPhoto>();
			String jsonString = null;
			try {
				if (URLConnector.isOnline(ctx)) {
					ByteArrayOutputStream baos = URLConnector.readBytes(url);
					jsonString = baos.toString();
				}
				try {
					JSONObject root = new JSONObject(jsonString.replace("jsonFlickrApi(", "").replace(")", ""));
					JSONObject photos = root.getJSONObject("photos");
					JSONArray imageJSONArray = photos.getJSONArray("photo");
					for (int i = 0; i < imageJSONArray.length(); i++) {
						JSONObject item = imageJSONArray.getJSONObject(i);
						FlickrPhoto imgCon = new FlickrPhoto(item.getString("id"), item.getString("owner"), item.getString("secret"), item.getString("server"),
								item.getString("farm"));
						imgCon.position = i;
						tmp.add(imgCon);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} catch (NullPointerException nue) {
				nue.printStackTrace();
			}

			return tmp;
		}
		return null;
	}
	
	public static FlickrManager NEW(){
		return INSTANCE;
	}

}
