package com.samilassed.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Environment;

public class Utility {

	public static final String FILE_CONTEXT = "flicker-search/";
	public static final String PHOTOS_FILE_CONTEXT = "images/";
	
	public static final String ENCODER_CHAR_TYPE = "UTF-8";
	public static final int ANIM_DURATION = 100;
	public static final String TODAY = "Today";
	
	public static final String DATE_FORMAT_PATTERN = "dd-MM-yyyy";
	public static final String COMPLETE_DATE_FORMAT_PATTERN = "dd MMMM yyyy";
	public static final String FULL_DATE_FORMAT_PATTERN = "EEEE dd MMMM yyyy";
	public static final String COMPLETE_DATE_TIME_FORMAT_PATTERN = "dd MMMM yyyy HH:mm a";
	public static final String TIME_FORMAT_PATTERN = "hh:mma";
	
	public static final SimpleDateFormat COMPLETE_DATE_FORMAT = new SimpleDateFormat(COMPLETE_DATE_FORMAT_PATTERN,Locale.UK);
	public static final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat(FULL_DATE_FORMAT_PATTERN,Locale.UK);
	public static final SimpleDateFormat SIMPLAE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN,Locale.UK);
	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(TIME_FORMAT_PATTERN,Locale.UK);
	public static final SimpleDateFormat COMPLETE_DATE_TIME_FORMAT = new SimpleDateFormat(COMPLETE_DATE_TIME_FORMAT_PATTERN,Locale.UK);
	
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
	public static final int SECRETCODE_LENGTH = 15;
	
	public static final String[] DISCUSSION = {"I can speak on any subject.","I speak ocasuanally.","I don't speak."};
	public static final String[] TRAJET = {"I will drive on the highway.","I will drive partly on the highway.","I will not drive on the highway."};
	public static final String[] LUGGAGE = {"I accept any size luggage.","I accept only small size luggage.","I don't accept luggage."};
	public static final String[] RELAX = {"I stop for a small relax.","I drive without stop."};

	
	/*public static File createImageFile() throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = timeStamp + "_";
		File storageDir = Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName,".jpg",storageDir);
		return image;
	}*/

	public static byte[] imageFileBytes(File file){

		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			FileInputStream fis = new FileInputStream(file);

			byte[] buffer = new byte[4096];
			int index;
			while( (index = fis.read(buffer)) != -1 ){
				output.write(buffer, 0, index);
			}

			fis.close();
			return output.toByteArray();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	/******************************FILE UTILITY*****************************************************/
	public static void createAppFileContext(){
		File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+FILE_CONTEXT+PHOTOS_FILE_CONTEXT);
		if(!file.exists()){
			file.mkdirs();
		}	    	
	}
	
	public static String getAppFileContext(){
		return Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+FILE_CONTEXT+PHOTOS_FILE_CONTEXT;
	}
	
	public static String getImagePath(String imageId){
		String directory = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+FILE_CONTEXT+PHOTOS_FILE_CONTEXT+imageId+"/";
		File file = new File(directory);
		if(!file.exists()){
			file.mkdirs();
		}
		return directory+imageId+".jpg";
	}
	
	public static File getImageFile(String imageId){
		return new File(Utility.getImagePath(imageId));
	}
	
	public static boolean storeUserImage(String userId, byte[] bytes){
		String imageFilePath = Utility.getImagePath(userId);
		File file = new File(imageFilePath);
		try {
			FileOutputStream fous = new FileOutputStream(file);
			fous.write(bytes);
			fous.flush();
			fous.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}//end utility class
