package kr.kuooe.comm.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

public class LogUtil {

	private List<String> dummyDataKeys = new ArrayList<String>();
	
	
	public String parseRequestHeader(HttpServletRequest request) {
		String parseData = "<br/>[Request Header]<br/>";
		
		Enumeration<?> allHeader = request.getHeaderNames();
		while(allHeader.hasMoreElements()) {
			String paramName	= (String)allHeader.nextElement();
			String value		= request.getHeader(paramName);
			parseData			+= "["+ paramName +" : "+ value +"]  ";
		}
		parseData += "<br/><br/>";
		
		return parseData;
	}
	
	public String parseRequset(HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		
		Enumeration<?> allParam = request.getParameterNames();
		while(allParam.hasMoreElements()) {
			String paramName = (String)allParam.nextElement();
			String[] multiValues = request.getParameterValues(paramName);
			for( int i=0; i<multiValues.length; i++ ) {
				if(multiValues.length > 1) {
					result.put(paramName +"("+ i +")", multiValues[i]);
				} else {
					result.put(paramName, multiValues[i]);
				}
			}
		}
		
		if(dummyDataKeys != null) {
			for(String dummyDataKey : dummyDataKeys) {
				if(result.containsKey(dummyDataKey)) {
					result.put(dummyDataKey, "****");
				}
			}
		}
		
		String parseData = "[Request Parameter]<br/>";
		Collection<String> keys = result.keySet();
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()) {
			String key		= itr.next();
			String value	= result.get(key);
			if(!value.trim().equals("")) {
				parseData	+= "["+ key +" : "+ value +"]  ";
			}
		}
		parseData += "<br/><br/>";
		
		return parseData;
	}
	
	public String parseRequestAttribute(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Enumeration<?> allAttribute = request.getAttributeNames();
		while(allAttribute.hasMoreElements()) {
			String paramName = (String)allAttribute.nextElement();
			if(!paramName.equals("org.springframework.core.convert.ConversionService")) {
				Object value = request.getAttribute(paramName);
				result.put(paramName, value);
			}
		}
		
		if(dummyDataKeys != null) {
			for(String dummyDataKey : dummyDataKeys) {
				if(result.containsKey(dummyDataKey)) {
					result.put(dummyDataKey, "****");
				}
			}
		}
		
		String parseData = "[Response Parameter]</br>";
		Collection<String> keys = result.keySet();
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()) {
			String key		= itr.next();
			Object value	= result.get(key);
			parseData		+= "["+ key +" : "+ value.toString() +"]  ";
		}
		
		return parseData;
	}
	
	public String parseResponseModelMap(ModelMap response) {
		String parseData = "[Response Parameter]</br>";
		Collection<String> keys	= response.keySet();
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()) {
			String key		= itr.next();
			String value	= String.valueOf(response.get(key));
			if(dummyDataKeys.contains(key)) {
				value = "****";
			}
			if(!StringUtil.isNull(value.trim())) {
				parseData += " - ["+ key +" : "+ value +"]</br>";
			}
		}
		
		return parseData;
	}
	public String parseResponse(Map<String, ?> response) {
		String parseData = "[Response Parameter]</br>";
		Collection<String> keys = response.keySet();
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()) {
			String key		= itr.next();
			String value	= String.valueOf(response.get(key));
			if(dummyDataKeys.contains(key)) {
				value = "****";
			}
			if(!StringUtil.isNull(value.trim())) {
				parseData += "["+ key +" : "+ value +"]  ";
			}
		}
		return parseData;
	}
	
	public String makeResponseMsg(String responseMsg) {
		return "[Response Parameter]</br>" + responseMsg;
	}
	public List<String> getDummyDataKeys() {
		return dummyDataKeys;
	}
	public void setDummyDataKeys(List<String> dummyDataKeys) {
		this.dummyDataKeys = dummyDataKeys;
	}
	public void addDummyDataKeys(String dummyDataKey) {
		dummyDataKeys.add(dummyDataKey);
	}
	
}
