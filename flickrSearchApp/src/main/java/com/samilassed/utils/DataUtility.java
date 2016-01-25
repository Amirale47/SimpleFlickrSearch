package com.samilassed.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

@SuppressLint("DefaultLocale")
public class DataUtility {
	
	/*********************************SHARED PREFERENCES**************************/
	private static final String SHARED_PREFERENCES = "FIND_ME_SHARED_PREFERENCES";
	private static final String SHARED_PREFERENCES_USERNAME = "SHARED_PREFERENCES_USERNAME";
	private static final String SHARED_PREFERENCES_FIRSTNAME = "SHARED_PREFERENCES_FIRSTNAME";
	private static final String SHARED_PREFERENCES_LASTNAME = "SHARED_PREFERENCES_LASTNAME";
	private static final String SHARED_PREFERENCES_USER_ID = "SHARED_PREFERENCES_USER_ID";
	private static final String SHARED_PREFERENCES_PASSWORD = "SHARED_PREFERENCES_PASSWORD";
	private static final String SHARED_PREFERENCES_IS_CONNECTED = "SHARED_PREFERENCES_IS_CONNECTED";
	private static final String SHARED_PREFERENCES_TYPE = "SHARED_PREFERENCES_TYPE";
	
	/******************************SETTINGS SHARED PREFERENCES*****************************************/
	private static final String SOUND_SETTINGS = "SOUND_SETTINGS";
	private static final String VEBRATION_SETTINGS = "VEBRATION_SETTINGS";
	private static final String VIEW_PHONE_NUMBER_SETTINGS = "VIEW_PHONE_NUMBER_SETTINGS";
	private static final String ENABLE_TUTORIAL_MODE_SETTINGS = "ENABLE_TUTORIAL_MODE_SETTINGS";
	
	private static final String LAST_TIME_SOUND_PLAYED = "LAST_TIME_SOUND_PLAYED";
	private static final String LAST_TIME_CHAT_SOUND_PLAYED = "LAST_TIME_CHAT_SOUND_PLAYED";
	private static final String LAST_TIME_VEBRATED = "LAST_TIME_SOUND_VEBRATED";
	//Global variables
	private static SharedPreferences sharedPreferences;
	
	/*************************************************PREFERENCES*************************************************/
	public static void storeLoginData(Context cnt, int id, String fName, String lName, String email, String password, boolean rememberMe, String type){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		Editor editor = sharedPreferences.edit();
		editor.putInt(SHARED_PREFERENCES_USER_ID, id);
		editor.putString(SHARED_PREFERENCES_FIRSTNAME, fName);
		editor.putString(SHARED_PREFERENCES_LASTNAME, lName);
		editor.putString(SHARED_PREFERENCES_USERNAME, email);
		/*Encrypt on Iteration 2*/
		editor.putString(SHARED_PREFERENCES_PASSWORD, password);
		editor.putString(SHARED_PREFERENCES_TYPE, type);
		editor.putBoolean(SHARED_PREFERENCES_IS_CONNECTED, rememberMe);
		editor.commit();
	}
	
	public static void storeSettingsData(Context cnt, boolean soundPlay, boolean vebration, boolean viewPhoneNumber, boolean enableTutorial){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(SOUND_SETTINGS, soundPlay);
		editor.putBoolean(VEBRATION_SETTINGS, vebration);
		editor.putBoolean(VIEW_PHONE_NUMBER_SETTINGS, viewPhoneNumber);
		editor.putBoolean(ENABLE_TUTORIAL_MODE_SETTINGS, enableTutorial);
		editor.commit();
	}
	
	public static boolean isSoundEnabled(Context cnt){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		return sharedPreferences.getBoolean(SOUND_SETTINGS, true);
	}
	
	public static boolean isVibrationEnabled(Context cnt){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		return sharedPreferences.getBoolean(VEBRATION_SETTINGS, true);
	}
	
	public static boolean isViewPhoneNumberEnabled(Context cnt){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		return sharedPreferences.getBoolean(VIEW_PHONE_NUMBER_SETTINGS, true);
	}
	
	public static boolean isTutorialModeEnabled(Context cnt){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		return sharedPreferences.getBoolean(ENABLE_TUTORIAL_MODE_SETTINGS, true);
	}
	
	public static void StoreConnected(Context cnt,boolean isConnected){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(SHARED_PREFERENCES_IS_CONNECTED, isConnected);
		editor.commit();
	}
	
	public static String getUsername(Context cnt){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		return sharedPreferences.getString(SHARED_PREFERENCES_USERNAME, null);
	}
	
	public static String getFullname(Context cnt){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		String fname = sharedPreferences.getString(SHARED_PREFERENCES_FIRSTNAME, null);
		String lname = sharedPreferences.getString(SHARED_PREFERENCES_LASTNAME, null);
		return fname+" "+lname.toUpperCase();
	}
	
	
	public static boolean isConnected(Context cnt){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		return sharedPreferences.getBoolean(SHARED_PREFERENCES_IS_CONNECTED, false);
	}
	
	public static int RetreiveID(Context cnt){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		return sharedPreferences.getInt(SHARED_PREFERENCES_USER_ID, -1);
	}
	
	public static void setLastTimeSoundPlayed(Context cnt,long time){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		Editor editor = sharedPreferences.edit();
		editor.putLong(LAST_TIME_SOUND_PLAYED, time);
		editor.commit();
	}
	
	public static long getLastTimeVebrated(Context cnt){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		return sharedPreferences.getLong(LAST_TIME_VEBRATED, 0);
	}
	
	public static void setLastTimeVebrated(Context cnt,long time){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		Editor editor = sharedPreferences.edit();
		editor.putLong(LAST_TIME_VEBRATED, time);
		editor.commit();
	}
	
	public static long getLastTimeSoundPlayed(Context cnt){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		return sharedPreferences.getLong(LAST_TIME_SOUND_PLAYED, 0);
	}
	
	public static void setLastTimeChatSoundPlayed(Context cnt,long time){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		Editor editor = sharedPreferences.edit();
		editor.putLong(LAST_TIME_CHAT_SOUND_PLAYED, time);
		editor.commit();
	}
	
	public static long getLastTimeChatSoundPlayed(Context cnt){
		sharedPreferences = cnt.getSharedPreferences(SHARED_PREFERENCES, Activity.MODE_PRIVATE | Activity.MODE_APPEND);
		return sharedPreferences.getLong(LAST_TIME_CHAT_SOUND_PLAYED, 0);
	}
	
	
}//end class utility
