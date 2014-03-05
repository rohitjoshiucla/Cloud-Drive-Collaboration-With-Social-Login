package com.testModules.bean;

public class DBXbean {

	public DBXbean() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String APP_KEY = "wke6spdwao027uj";
	public static final String APP_SECRET = "l7zrpjy3um4z845";
	
	public static final String URL_GET_TOKEN = "https://api.dropbox.com/1/oauth2/token";
	public static final String URL_AUTHORIZE = "https://www.dropbox.com/1/oauth2/authorize";
	public static final String URL_REDIRECT = "http://localhost:8080/testFacebookLogin/DBXservlet";
	public static final String URL_METADATA = "https://api.dropbox.com/1/metadata/sandbox";
	
	
	public static final String NAME_TOKEN = "dbx-access-token";
	public static final String NAME_PATH = "dbx-path";
	public static final String NAME_PATH_LIST = "bx-paths";
	
}
