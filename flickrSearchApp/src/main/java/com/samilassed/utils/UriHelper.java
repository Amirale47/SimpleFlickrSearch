package com.samilassed.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.net.Uri;

public class UriHelper {
	
	public static final String DRIVER = "driver/";
	public static final String TRAVELER = "traveler/";
	
	private static final String REST_SERVICES_BASE = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    private static final String REST_SERVICE_USERS = "users/";
    private static final String REST_SERVICE_INFORMATION_CENTER = "informationCenter/";
    private static final String REST_SERVICE_PUBLISHER = "publisher/";
    private static final String ANOUNCEMENTS_REST_SERVICE = "anouncements/";
    private static final String REST_SERVICE_PUBLISHER_ANOUNCEMENTS_TRAVELER = "publisher/anouncements/traveler/";
    private static final String REST_SERVICE_PUBLISHER_ANOUNCEMENTS_DRIVER = "publisher/anouncements/driver/";
    private static final String REST_SERVICE_GEOSERVICE = "geoservice/";
    private static final String REST_SERVICE_CARSERVICE = "carservice/";
    private static final String REST_SERVICE_SETTINGS = "settings/";
    private static final String REST_SERVICE_USER_SUGGESTION = "userservice/";
    private static final String REST_SERVICE_MESSAGES = "messages/";
    private static final String REST_SERVICE_EMAIL = "emailservice/";
    private static final String NOTIFICATION_INFORMATION_REST_SERVICE = "notificationsservice/";
    private static final String JOURNEY_REST_SERVICE = "journey/";
    private static final String JOURNEY_RESERVATION_REST_SERVICE = "reservations/";
    private static final String NOTIFICATION_REST_SERVICE = "notifications/";
    private static final String FRIENDSHIP_REST_SERVICE = "friendship/";
    private static final String PAYMENTS_REST_SERVICE = "payments/";
    private static final String CHAT_REST_SERVICE = "chatService/";
    
    private static final String DEVIDER = "/";
    
    public static String getImageURL(String userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append("FILE_SERVER_ADDRESS");
    	uriBuilder.append("images");
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(userId);
    	uriBuilder.append(".jpg");
    	
    	return uriBuilder.toString();
    }
    
    public static String createTestURI(){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_INFORMATION_CENTER);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String createMenuInformationURI(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_INFORMATION_CENTER);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String getLastConversationsURL(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(CHAT_REST_SERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String getConversationURL(int fromUser, int toUser){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(CHAT_REST_SERVICE);
    	uriBuilder.append(fromUser);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(toUser);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String createNotificationInformationURL(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_INFORMATION_CENTER);
    	uriBuilder.append(NOTIFICATION_INFORMATION_REST_SERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String createPublisherAnouncementsMatchesUrl(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_PUBLISHER_ANOUNCEMENTS_TRAVELER);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String deleteAnnouncementUrl(int announcementId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_PUBLISHER_ANOUNCEMENTS_TRAVELER);
    	uriBuilder.append(announcementId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String notificationsUrl(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(NOTIFICATION_REST_SERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String createSignupURI(String email,String password,String fname
        ,String lname, char gender, String birthDay, String telephoneNumber,
        String findmeGender, int address) {
    	
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append(REST_SERVICES_BASE);
        uriBuilder.append(REST_SERVICE_USERS);
        uriBuilder.append(email);
        uriBuilder.append(DEVIDER);
        uriBuilder.append(Uri.encode(password,"UTF-8"));
        uriBuilder.append(DEVIDER);
        uriBuilder.append(Uri.encode(fname,"UTF-8"));
        uriBuilder.append(DEVIDER);
        uriBuilder.append(Uri.encode(lname,"UTF-8"));
        uriBuilder.append(DEVIDER);
        uriBuilder.append(gender);
        uriBuilder.append(DEVIDER);
        uriBuilder.append(Uri.encode(birthDay));
        uriBuilder.append(DEVIDER);
        uriBuilder.append(telephoneNumber);
        uriBuilder.append(DEVIDER);
        uriBuilder.append(findmeGender);
        uriBuilder.append(DEVIDER);
        uriBuilder.append(address);
        uriBuilder.append(DEVIDER);
        
        return uriBuilder.toString();
    }
    
    public static String editUserURL(int userId, String email,String password,String fname
    		,String lname, char gender, String telephoneNumber, int address) {

    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_USERS);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(email);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(password,"UTF-8"));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(fname,"UTF-8"));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(lname,"UTF-8"));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(gender);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(telephoneNumber);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(address);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String confirmEmailURL(int userId) {

    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_USERS);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String editUserSettingsURL(int settingsId, boolean notifSound,
    		boolean notifVebration, boolean viewPhone, boolean enableTutorial) {
        	
            StringBuilder uriBuilder = new StringBuilder();
            uriBuilder.append(REST_SERVICES_BASE);
            uriBuilder.append(REST_SERVICE_USERS);
            uriBuilder.append(settingsId);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(notifSound);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(notifVebration);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(viewPhone);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(enableTutorial);
            uriBuilder.append(DEVIDER);
            
            return uriBuilder.toString();
        }
    
    public static String getSwitchProfileURI(int userId) {
        	
            StringBuilder uriBuilder = new StringBuilder();
            uriBuilder.append(REST_SERVICES_BASE);
            uriBuilder.append(REST_SERVICE_USERS);
            uriBuilder.append(userId);
            uriBuilder.append(DEVIDER);
            
            return uriBuilder.toString();
        }
    
    public static String createSignInURL(String email,String password) {
        	
            StringBuilder uriBuilder = new StringBuilder();
            uriBuilder.append(REST_SERVICES_BASE);
            uriBuilder.append(REST_SERVICE_USERS);
            uriBuilder.append(email);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(Uri.encode(password,Utility.ENCODER_CHAR_TYPE));
            uriBuilder.append(DEVIDER);
            
            return uriBuilder.toString();
        }
    
    public static String getUserProfileURL(int userId) {
    	
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append(REST_SERVICES_BASE);
        uriBuilder.append(REST_SERVICE_USERS);
        uriBuilder.append(userId);
        uriBuilder.append(DEVIDER);
        
        return uriBuilder.toString();
    }
    
    public static String lookForFriendsURL(int userId,String suggestion) {
    	
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append(REST_SERVICES_BASE);
        uriBuilder.append(REST_SERVICE_USERS);
        uriBuilder.append(userId);
        uriBuilder.append(DEVIDER);
        uriBuilder.append(Uri.encode(suggestion,Utility.ENCODER_CHAR_TYPE));
        uriBuilder.append(DEVIDER);
        uriBuilder.append(DEVIDER);
        
        return uriBuilder.toString();
    }
    
    public static String getJourneyURL(int journeyId,int driverId){

    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(JOURNEY_REST_SERVICE);
    	uriBuilder.append(journeyId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(driverId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String deleteJourneyURL(int journeyId){

    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(JOURNEY_REST_SERVICE);
    	uriBuilder.append(journeyId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String createNewJourneyURL(int journeyId,int userId,int fromCity,String startPoint
            ,int toCity, String endPoint, String startDate,long startingDateLong, String startTime,
            int seats, String basePrice, String findmeCharges, String finalPrice, int currency,
            int socialDiscussion, int lugageSize, int routeType, int trajetRelax){
        	
            StringBuilder uriBuilder = new StringBuilder();
            uriBuilder.append(REST_SERVICES_BASE);
            uriBuilder.append(JOURNEY_REST_SERVICE);
            uriBuilder.append(journeyId);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(userId);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(fromCity);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(URLEncoder.encode(startPoint));
            uriBuilder.append(DEVIDER);
            uriBuilder.append(toCity);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(URLEncoder.encode(endPoint));
            uriBuilder.append(DEVIDER);
            uriBuilder.append(URLEncoder.encode(startDate));
            uriBuilder.append(DEVIDER);
            uriBuilder.append(startingDateLong);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(URLEncoder.encode(startTime));
            uriBuilder.append(DEVIDER);
            uriBuilder.append(seats);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(basePrice);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(findmeCharges);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(finalPrice);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(currency);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(socialDiscussion);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(lugageSize);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(routeType);
            uriBuilder.append(DEVIDER);
            uriBuilder.append(trajetRelax);
            uriBuilder.append(DEVIDER);
            
            return uriBuilder.toString();
        }
    
    public static String postAnouncementURI(int announcementId, int userId, int fromCity, int toCity,String date, long dateLong, String time, long timeLong){
        	
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_PUBLISHER);
    	uriBuilder.append(announcementId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(fromCity);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(toCity);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(date);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(dateLong);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(time);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(timeLong);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
        
    }
    
    public static String getCitySuggestionURL(String suggestion){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_GEOSERVICE);
    	uriBuilder.append(Uri.encode(suggestion));
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String addCityURL(String city, String state, String country){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_GEOSERVICE);
    	uriBuilder.append(Uri.encode(city));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(state));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(country));
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String createCarSuggestionURL(String suggestion){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_CARSERVICE);
    	uriBuilder.append(Uri.encode(suggestion));
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String addNewCarModelURL(String maker, String model, int year){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_CARSERVICE);
    	uriBuilder.append(Uri.encode(maker));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(model));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(year);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String createUserSuggestionURL(String suggestion){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_INFORMATION_CENTER);
    	uriBuilder.append(REST_SERVICE_USER_SUGGESTION);
    	uriBuilder.append(Uri.encode(suggestion));
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String getUserProfileInfoURL(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_INFORMATION_CENTER);
    	uriBuilder.append(REST_SERVICE_USER_SUGGESTION);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String createNewEmailURL(int fromId, int toId, String subject, String content){
    	
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_MESSAGES);
    	uriBuilder.append(REST_SERVICE_EMAIL);
    	uriBuilder.append(fromId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(toId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(subject));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(content));
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    

    public static String emailBoxURL(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_MESSAGES);
    	uriBuilder.append(REST_SERVICE_EMAIL);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String markEmailAsReadURL(int emailId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_MESSAGES);
    	uriBuilder.append(REST_SERVICE_EMAIL);
    	uriBuilder.append(emailId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String getAnouncmentsForDriverURL(int userId, int fromAddress, int toAddress, String startingDate){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_PUBLISHER_ANOUNCEMENTS_DRIVER);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(fromAddress);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(toAddress);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(startingDate));
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String getMyAnouncmentsURL(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_PUBLISHER);
    	uriBuilder.append(ANOUNCEMENTS_REST_SERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String getAnouncmentURL(int anouncementId, int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_PUBLISHER);
    	uriBuilder.append(ANOUNCEMENTS_REST_SERVICE);
    	uriBuilder.append(anouncementId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);

    	return uriBuilder.toString();
    }
    
    public static String registerMyCarURL(int userId, int carRef, int seats, int confort, String color){
        	
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_CARSERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(carRef);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(seats);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(confort);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(color));
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
        
    }
    
    public static String getCarInfoURL(int userId){
    	
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_CARSERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
        
    }
    
    public static String getCarSeatsURL(int userId){
    	
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_INFORMATION_CENTER);
    	uriBuilder.append(DRIVER);
    	uriBuilder.append(REST_SERVICE_CARSERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
        
    }
    
    public static String hasCarURL(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_INFORMATION_CENTER);
    	uriBuilder.append(DRIVER);
    	uriBuilder.append(REST_SERVICE_CARSERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
    public static String getCurrencyURL(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(REST_SERVICE_INFORMATION_CENTER);
    	uriBuilder.append(DRIVER);
    	uriBuilder.append(REST_SERVICE_SETTINGS);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
    public static String getAppropriateJourniesURL(int userId, int fromCity, int toCity, String date, String time){
    	
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(JOURNEY_REST_SERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(fromCity);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(toCity);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(date));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(Uri.encode(time));
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    

    public static String searchJourniesURL(int fromCity, int toCity, long date){
    	
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(JOURNEY_REST_SERVICE);
    	uriBuilder.append(fromCity);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(toCity);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(date);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    

    public static String getDriverJourniesURL(int userId){
    	
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(JOURNEY_REST_SERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
	
    public static String getJourneyReserversURL(int journeyId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(JOURNEY_RESERVATION_REST_SERVICE);
    	uriBuilder.append(journeyId);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
    public static String getFriendsURL(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(FRIENDSHIP_REST_SERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
    public static String getNotificationResponseURL(int notifId, int fromUserId, short notifType){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(NOTIFICATION_REST_SERVICE);
    	uriBuilder.append(notifId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(fromUserId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(notifType);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
    public static String makeNotificationURL(int fromUser, int toUser, int notifType, String content){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(NOTIFICATION_REST_SERVICE);
    	uriBuilder.append(fromUser);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(toUser);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(notifType);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(URLEncoder.encode(content));
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
    public static String requestReservationByPaypalURL(int userId, int journeyId, String payPallAuth, String paypalPaymentId, int seats, String total){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(JOURNEY_RESERVATION_REST_SERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(journeyId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(URLEncoder.encode(payPallAuth));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(URLEncoder.encode(paypalPaymentId));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(seats);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(total);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
    public static String requestReservationByCreditCardURL(int userId, int journeyId,int seats, String total, String cardtype,
            String cardOwnerFullname, String cardNumber, String cardExpiredOn, String cardOwnerEmail){
    	
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(JOURNEY_RESERVATION_REST_SERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(journeyId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(seats);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(total);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(URLEncoder.encode(cardtype));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(URLEncoder.encode(cardOwnerFullname));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(cardNumber);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(URLEncoder.encode(cardExpiredOn));
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(URLEncoder.encode(cardOwnerEmail));
    	uriBuilder.append(DEVIDER);
    	
    	
    	return uriBuilder.toString();
    }
    
    public static String finalPaymentPriceURL(float basePrice, String currencyCode){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(PAYMENTS_REST_SERVICE);
    	uriBuilder.append(basePrice);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(currencyCode);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
    public static String editPaypalAcountURL(long paypalAcountId, int userId, String paypalAcountEmail){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(PAYMENTS_REST_SERVICE);
    	uriBuilder.append(paypalAcountId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(URLEncoder.encode(paypalAcountEmail));
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
    public static String refundInformationURL(int userId){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(PAYMENTS_REST_SERVICE);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
    public static String requestRefundURL(int journeyId, int userId, String secretCode){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(PAYMENTS_REST_SERVICE);
    	uriBuilder.append(journeyId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(URLEncoder.encode(secretCode));
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
    public static String confirmPaymentURL(int journeyId, int userId,String secretCode){
    	StringBuilder uriBuilder = new StringBuilder();
    	uriBuilder.append(REST_SERVICES_BASE);
    	uriBuilder.append(PAYMENTS_REST_SERVICE);
    	uriBuilder.append(journeyId);
    	uriBuilder.append(DEVIDER);
    	uriBuilder.append(userId);
    	uriBuilder.append(DEVIDER);
    	try {
			uriBuilder.append(URLEncoder.encode(secretCode, Utility.ENCODER_CHAR_TYPE));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	uriBuilder.append(DEVIDER);
    	
    	return uriBuilder.toString();
    }
    
}
