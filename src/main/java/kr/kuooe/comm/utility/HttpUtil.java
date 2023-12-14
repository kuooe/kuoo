package kr.kuooe.comm.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HttpUtil {

	// HTTP 통신
	public static Map<String, Object> httpConnection(Map<String, String> connMap, String param) {
		param				= param.replaceAll("\\\\" ,"");
		String hostUrl		= connMap.get("url");
		String method		= connMap.get("method");
		String contentType	= connMap.get("contentType");
		String rlyId		= connMap.get("rlyId") == null ? "" : connMap.get("rlyId");;
		String token		= connMap.get("token") == null ? "" : connMap.get("token");;
		String relay		= connMap.get("relay") == null ? "" : connMap.get("relay");;
		String cnt			= connMap.get("cnt") == null ? "(1/1)" : connMap.get("cnt");
		int wait			= 10;
		
		log.debug("hostUrl : "+ hostUrl);
		log.debug("method : "+ method);
		log.debug("contentType : "+ contentType);
		
		Map<String, Object> resMap	= null;
		HttpURLConnection conn		= null;
		OutputStream os				= null;
		//OutputStreamWriter os		= null;
		InputStream is				= null;
		ByteArrayOutputStream baos	= null; // 효율적이고 길이를 모를때 효과적

		
        try {
        	URL url = new URL(hostUrl);
        	log.debug("발송 URL "+ cnt +" : "+ url);
			conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(wait * 1000);
			conn.setReadTimeout(wait * 1000);
			conn.setRequestMethod(method);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", contentType);
			if(!token.equals("")) {
				conn.setRequestProperty("Authorization", "Bearer "+ token.trim());
			}
			conn.setDoInput(true);
			
			if(relay.equals("KAKAO") || relay.equals("PAYCO")) {
				conn.setRequestProperty("Contract-Uuid", rlyId);
			}
			if(relay.equals("NAVER")) {
				conn.setRequestProperty("X-Naver-Client-Id", rlyId.trim());
				conn.setRequestProperty("X-Naver-Client-Secret", token.trim());
			}
			
			if(relay.equals("KT_MPOSTBIZ")) {
				conn.setRequestProperty("client-id", "1000000002");
				conn.setRequestProperty("client-tp", "20");
			}
			
			log.debug("발송 데이터	    : "+ param);
			// POST 전송 세팅
			if(method.equals("POST")) {
				conn.setDoOutput(true);
				
				// POST 데이터 전송
				os = conn.getOutputStream();
				if(!param.equals("")) {
					byte[] reqBody = param.getBytes("UTF-8");
					os.write(reqBody);
				}
				
				/*
				os = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
				PrintWriter pw = new PrintWriter(os);
				pw.write(testParam);
				pw.flush();*/
			} else if(method.equals("GET")) {
				conn.connect();
			}
			
			log.debug("응답 결과	    : "+ conn.getResponseCode() +", "+ conn.getResponseMessage());
	        
	        if(conn.getResponseCode() != 200) {
	        	is = conn.getErrorStream();
	        } else {
	        	is = conn.getInputStream();
	        }
	        
	        // 서버에서 보낸 응답 데이터 수신 받기
	        if(connMap.get("resBodyNull") == null) {
		        baos = new ByteArrayOutputStream();
		        byte[] buf = new byte[1024 * 8];
	            int length = 0;
	            while((length = is.read(buf)) != -1) {
	            	baos.write(buf, 0, length);
	            }
	            
	            String resBody = new String(baos.toByteArray(), "UTF-8");
	            log.debug("응답 데이터	    : "+resBody);
		        
		        // 리턴 데이터
		        resMap = new LinkedHashMap<String, Object>();
				resMap.put("resBody", new JSONObject(resBody));		// HTTP 응답 데이터
				resMap.put("resCode", conn.getResponseCode()+"");	// HTTP 응답 코드
				
				is.close();
	        }
			if(method.equals("POST")) {
				os.flush();
				os.close();
			}
        } catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(os != null) os.close();
				if(is != null) is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	        
		return resMap;
	}
}
