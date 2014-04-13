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

import com.testModules.bean.BXbean;

public class BXclass {
	
	RESTclass rest = new RESTclass();
	
	public String getTOKEN(String code) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>(5);
	    params.add(new BasicNameValuePair("code", code));
	    params.add(new BasicNameValuePair("grant_type", "authorization_code"));
	    params.add(new BasicNameValuePair("client_id", BXbean.APP_KEY));
	    params.add(new BasicNameValuePair("client_secret", BXbean.APP_SECRET));
	    params.add(new BasicNameValuePair("redirect_uri", BXbean.URL_REDIRECT));
	    
	    URI uri = new URI(BXbean.URL_GET_TOKEN);
	    
	    HttpResponse response = rest.POST(uri, params);
	    
	    
	    HttpEntity respEntity = response.getEntity();
	    String jsonResponse = EntityUtils.toString(respEntity);			    
	    JSONObject obj = JSONObject.fromObject(jsonResponse);
	    
	    return obj.getString("access_token");
		}
	
	public void AUTHORIZE(HttpServletResponse response) throws Exception{
	    
	    String query = BXbean.URL_AUTHORIZE+"?response_type=code"+
	    "&redirect_uri="+BXbean.URL_REDIRECT+
	    "&client_id="+BXbean.APP_KEY;
	    
		response.sendRedirect(query);
	}
	
	public JSONArray getMETADATA(String path, HttpSession session) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
	    params.add(new BasicNameValuePair("access_token", session.getAttribute(BXbean.NAME_TOKEN).toString()));
		
		URI uri = new URI(BXbean.URL_METADATA+path);
		HttpResponse response= rest.GET(uri,params);
		
		HttpEntity respEntity = response.getEntity();
        String jsonResponse = EntityUtils.toString(respEntity);
        
        JSONObject jsonObject=JSONObject.fromObject(jsonResponse);
        JSONObject item_collection = jsonObject.getJSONObject("item_collection");
        JSONArray entries=item_collection.getJSONArray("entries");
        
        //create Metadata to send to jsp
        JSONArray jsonArr = new JSONArray();
        @SuppressWarnings("rawtypes")
		Iterator it = entries.iterator();
        
        while(it.hasNext()){
        	JSONObject objInner=(JSONObject)it.next();
        	JSONObject jsonObj = new JSONObject();
        	jsonObj.put("folder-id", objInner.getString("id"));
    		jsonObj.put("type", objInner.getString("type"));
        	jsonObj.put("name",objInner.getString("name"));
        	jsonArr.add(jsonObj);
        }
        
        //create pathList for Navigation
//        HashMap<String,Object> hashMap = new HashMap<String, Object>();
//        hashMap.put("folder-id", path);
//    	hashMap.put("name", jsonObject.getString("name"));
//    	
//    	List<Object> pathList = new ArrayList<Object>();
//		ArrayList<Object> currPathList = (ArrayList<Object>)session.getAttribute(BXbean.NAME_PATH_LIST);
//    	if(currPathList!=null){
//			pathList=(ArrayList<Object>) session.getAttribute(BXbean.NAME_PATH_LIST);
//			if(pathList.contains(hashMap)){
//        		pathList=new ArrayList<Object>(pathList.subList(0,pathList.indexOf(hashMap)));		        		
//        	}	
//    	}
//		pathList.add(hashMap);
//		
//        session.setAttribute(BXbean.NAME_PATH_LIST, pathList);
        
       return jsonArr;
	}
	
	/*
	 * [
	 * 		name1:{
	 * 			bx:{
	 * 				folder-id:
	 * 				type:
	 * 			}
	 * 		}
	 * ]
	 */
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
					JSONObject bx = new JSONObject();
					bx.put("folder-id", innerObject.getString("folder-id"));
					bx.put("type", innerObject.getString("type"));				
					
					JSONObject value = new JSONObject();
					value.put("bx", bx);
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
						JSONObject bx = new JSONObject();
						bx.put("folder-id",temp.getString("folder-id"));
						bx.put("type", temp.getString("type"));
					value.put("bx",bx);
					name.put("value", value);
					input1.set(i, name);
				}
			}
			for(int i=0; i < input2.size(); i++){
				JSONObject name = new JSONObject();
				JSONObject innerObject = input2.getJSONObject(i);
				name.put("name", innerObject.getString("name"));
				
				JSONObject value = new JSONObject();
					JSONObject bx = new JSONObject();
					bx.put("type", innerObject.getString("type"));
					bx.put("folder-id", innerObject.getString("folder-id"));
				value.put("bx", bx);
				
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
	
	public BXclass() {
		super();
		// TODO Auto-generated constructor stub
	}

}
