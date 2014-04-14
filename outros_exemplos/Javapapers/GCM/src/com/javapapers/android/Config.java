package com.javapapers.android;

public interface Config {

	// used to share GCM regId with application server - using php app server
	//static final String APP_SERVER_URL = "http://192.168.1.17/gcm/gcm.php?shareRegId=1";

	// GCM server using java
	// static final String APP_SERVER_URL =
	// "http://192.168.0.101:8080/gcm-demo";
	 
	// GCM server using java
	//	 static final String APP_SERVER_URL =
		// "http://192.168.0.101:8080/servidorgcm/GCMNotification?shareRegId=1";
	//static final String APP_SERVER_URL = "http://192.168.0.108:8080/gcm-demo/home";
	
	//static final String APP_SERVER_URL = "http://192.168.0.108:8080/_ah/admin";
	
	//Meu ip tplink
	 static final String APP_SERVER_URL = 
			 "http://192.168.0.117:8080/GCM-App-Server/GCMNotification?shareRegId=1";
	 
	// Google Project Number
	static final String GOOGLE_PROJECT_ID = "170735191260";
	static final String MESSAGE_KEY = "message";

}
