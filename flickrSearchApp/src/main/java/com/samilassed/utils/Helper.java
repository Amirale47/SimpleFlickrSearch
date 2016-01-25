package com.samilassed.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class Helper {

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String DELETE = "DELETE";
	private static final String PUT = "PUT";
	
	private static final int RESOURCE_NOT_FOUND = 404;
	private static final int RESOURCE_FOUND = 200;
	
	
	private static String requestHttp(String method,String uri){
		
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		byte[] buffer = new byte[2048];
		String jsonObject = null;
		HttpEntity entity = null;
		try {
			
			//Put the right method
			if(method.equalsIgnoreCase(POST)){
				response = client.execute(new HttpPost(uri));
			}else if(method.equalsIgnoreCase(GET)){
				response = client.execute(new HttpGet(uri));
			}else if(method.equalsIgnoreCase(PUT)){
				response = client.execute(new HttpPut(uri));
			}else if(method.equalsIgnoreCase(DELETE)){
				response = client.execute(new HttpDelete(uri));
			}
			
			int responseCode = response.getStatusLine().getStatusCode();
			if(responseCode == RESOURCE_FOUND){
				
				entity = response.getEntity();
				
				if(entity != null){
					
					InputStream inputStream = entity.getContent();
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					int readCount = 0;
					while( (readCount = inputStream.read(buffer)) != -1 ){
						outputStream.write(buffer, 0, readCount);
					}
					
					jsonObject = new String(outputStream.toByteArray());
					
					inputStream.close();
					
				}//end if
				
			}else if(responseCode == RESOURCE_NOT_FOUND){
				Log.e("Response", "Resource not found");
				return null;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			
		}//end finally
		
		return jsonObject;
	}
	
	public static String post(String uri){
		return requestHttp(POST, uri);
	}
	
	public static String get(String uri){
		return requestHttp(GET, uri);
	}
	
	public static String delete(String uri){
		return requestHttp(DELETE, uri);
	}
	
	public static String put(String uri){
		return requestHttp(PUT, uri);
	}
	
	public static byte[] downloadImage(FlickrPhoto image){
		try {
			URL url = new URL(image.getLargeURL());
			InputStream in = url.openStream();
			ByteArrayOutputStream fos = new ByteArrayOutputStream();

			int length = -1;
			byte[] buffer = new byte[16384];
			                                
			while ((length = in.read(buffer)) > -1) {
				Log.e("", length+"");
			    fos.write(buffer, 0, length);
			}
			
			byte[] bytes = fos.toByteArray();
			String imageId = image.getId();
			Utility.storeUserImage(imageId, bytes);
			fos.close();
			in.close();
			synchronized (DownloadImageAsyncTask.getDownloadingList()) {
				DownloadImageAsyncTask.getDownloadingList().remove(imageId);
			}
			return bytes;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] downloadImageNIO(String userId){
		URL url;
		try {
			url = new URL(UriHelper.getImageURL(userId));
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
	        File file = new File(Utility.getImagePath(userId)); 
	        FileOutputStream fos = new FileOutputStream(file);
	        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	        fos.close();
	        rbc.close();
	        FileInputStream fileinput = new FileInputStream(file);
	        byte[] bytes = new byte[(int) file.length()];
	        fileinput.read(bytes);
	        fileinput.close();
	        return bytes;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return null;
	}
	
}//end class
