package com.testModules.classes;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.testModules.bean.DBXbean;

public class DBXclass {
	
	RESTclass rest = new RESTclass();
	
	public String getTOKEN(String code) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>(5);
	    params.add(new BasicNameValuePair("code", code));
	    params.add(new BasicNameValuePair("grant_type", "authorization_code"));
	    params.add(new BasicNameValuePair("client_id", DBXbean.APP_KEY));
	    params.add(new BasicNameValuePair("client_secret", DBXbean.APP_SECRET));
	    params.add(new BasicNameValuePair("redirect_uri", DBXbean.URL_REDIRECT));
	    
	    URI uri = new URI(DBXbean.URL_GET_TOKEN);
	    
	    HttpResponse response = rest.POST(uri, params);
	    
	    HttpEntity respEntity = response.getEntity();
	    String jsonResponse = EntityUtils.toString(respEntity);			    
	    JSONObject obj = JSONObject.fromObject(jsonResponse);
	    
	    return obj.getString("access_token");
		}
	
	public void AUTHORIZE(HttpServletResponse response) throws Exception{
	    
	    String query = DBXbean.URL_AUTHORIZE+"?response_type=code"+
	    "&redirect_uri="+DBXbean.URL_REDIRECT+
	    "&client_id="+DBXbean.APP_KEY;
	    
		response.sendRedirect(query);
	}
	
	public JSONArray getMETADATA(String path, HttpSession session) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("access_token", session.getAttribute(DBXbean.NAME_TOKEN).toString()));
			
		URI uri = new URI(DBXbean.URL_METADATA + path);
		
		HttpResponse response= rest.GET(uri,params);
		
		HttpEntity respEntity = response.getEntity();
        String jsonResponse = EntityUtils.toString(respEntity);
        
        JSONObject jsonObject=JSONObject.fromObject(jsonResponse);
        JSONArray contents=jsonObject.getJSONArray("contents");
        
        //create jsonArr to send to jsp
        JSONArray jsonArr = new JSONArray();
        @SuppressWarnings("rawtypes")
		Iterator it = contents.iterator();
        String []temp;
        while(it.hasNext()){
        	JSONObject objInner=(JSONObject)it.next();
        	JSONObject jsonObj = new JSONObject();
        	
        	jsonObj.put("path", objInner.getString("path"));
        	String type=objInner.getBoolean("is_dir")?"folder":objInner.getString("mime_type");
        	jsonObj.put("type", type);
        	temp=objInner.getString("path").split("/");
        	jsonObj.put("name",temp[temp.length-1]);
        	
        	jsonArr.add(jsonObj);
        }
        
        //create pathList for Navigation
//        HashMap<String,Object> hashMap = new HashMap<String, Object>();
//        if(jsonObject.getString("path").equals("/")){
//        	hashMap.put("name", "Home");	
//        }
//        else{
//        	temp = jsonObject.getString("path").split("/");
//        	hashMap.put("name", temp[temp.length-1]);
//        }
//        hashMap.put("path", jsonObject.getString("path"));
//            	
//    	List<Object> pathList = new ArrayList<Object>();
//		ArrayList<Object> currPathList = (ArrayList<Object>)session.getAttribute(DBXbean.NAME_PATH_LIST);
//    	if(currPathList!=null){
//			pathList=(ArrayList<Object>) session.getAttribute(DBXbean.NAME_PATH_LIST);
//			if(pathList.contains(hashMap)){
//        		pathList=new ArrayList<Object>(pathList.subList(0,pathList.indexOf(hashMap)));		        		
//        	}	
//    	}
//		pathList.add(hashMap);
//		session.setAttribute(DBXbean.NAME_PATH_LIST, pathList);
		
		return jsonArr;
	}
	
	@SuppressWarnings("rawtypes")
	public JSONArray merge(JSONArray input1, JSONArray input2){
		if(input1.isEmpty()){
			//iterate input 2 to extract names
			JSONArray jsonArr = new JSONArray();
			Iterator it = input2.iterator();
			while(it.hasNext()){
				JSONObject innerObject = (JSONObject)it.next();
				
				JSONObject name=new JSONObject();
				
				name.put("name", innerObject.getString("name"));
					JSONObject dbx = new JSONObject();
					dbx.put("path", innerObject.getString("path"));
					dbx.put("type", innerObject.getString("type"));				
					
					JSONObject value = new JSONObject();
					value.put("dbx", dbx);
				name.put("value",value);
				
				jsonArr.add(name);
			}
			return jsonArr;
		}
		else{
			//iterate input1 to check if input2 contains same name
			for(int i = 0; i < input1.size(); i++){
				JSONObject name = (JSONObject)input1.get(i);
				JSONObject value = (JSONObject)name.get("value");
				JSONObject temp = contains(input2, name.getString("name"));
				if(temp!=null){
					input2.remove(temp);
						JSONObject dbx = new JSONObject();
						dbx.put("path",temp.getString("path"));
						dbx.put("type", temp.getString("type"));
					value.put("dbx",dbx);
					name.put("value", value);
					input1.set(i, name);
				}
			}
			for(int i=0; i < input2.size(); i++){
				JSONObject name = new JSONObject();
				JSONObject innerObject = input2.getJSONObject(i);
				name.put("name", innerObject.getString("name"));
				
				JSONObject value = new JSONObject();
					JSONObject dbx = new JSONObject();
					dbx.put("type", innerObject.getString("type"));
					dbx.put("path", innerObject.getString("path"));
				value.put("bx", dbx);
				
				name.put("value", value);
				input1.add(name);
			}
			return input1;
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public JSONObject contains(JSONArray input2, String name){
		
		Iterator it = input2.iterator();
		JSONObject innerObject = null;
		while(it.hasNext()){
			innerObject = (JSONObject)it.next();
			if(innerObject.getString("name").equals(name))
				return innerObject;
		}
		return null;
	}
	
	public DBXclass() {
		// TODO Auto-generated constructor stub
	}
}
