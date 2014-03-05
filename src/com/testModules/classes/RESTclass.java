package com.testModules.classes;

import java.net.URI;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

public class RESTclass {
	
	HttpClient httpClient = new DefaultHttpClient();
	HttpPost httpPost = new HttpPost();
	HttpGet httpGet = new HttpGet();
	
	public HttpResponse POST(URI uri, List<NameValuePair> params) throws Exception{
		httpPost.setURI(uri);
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		return httpClient.execute(httpPost);
	}
	
	public HttpResponse GET(URI uri, List<NameValuePair> params) throws Exception{
		String queryString = URLEncodedUtils.format(params, "UTF-8");
		uri = new URI(uri.toString() + "?"+queryString);
		httpGet.setURI(uri);
		return httpClient.execute(httpGet);
	}
	
	
	
}
