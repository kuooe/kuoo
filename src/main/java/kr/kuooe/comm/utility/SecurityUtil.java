package kr.kuooe.comm.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtil {

	/** SHA-256 인코딩 **/
	public static String getSha256(String inData) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(inData.getBytes());
		
		return byteToHexString(md.digest());
	}
	/** SHA-521 인코딩 **/
	public static String getSha512(String inData) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(inData.getBytes());
		
		return byteToHexString(md.digest());
	}
	
	/** Base64 인코딩 **/
	public static String getEncBase64(String inData) {
		Encoder encoder = Base64.getEncoder();
		byte[] encBytes = encoder.encode(inData.getBytes());
		
		String encData	= new String(encBytes);
		
		return encData;
	}
	/** Base64 디코딩 **/
	public static String getDecBase64(String inData) {
		Decoder decoder = Base64.getDecoder();
		byte[] decBytes = decoder.decode(inData.getBytes());
		
		String decData	= "";
		try {
			decData	= new String(decBytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return decData;
	}
	
	/** Byte To HaxString **/
	public static String byteToHexString(byte[] data) {
		StringBuilder sb = new StringBuilder();
	    for(byte b : data) {
	        sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	    }
	    
	    return sb.toString();
	}
	
	public static boolean passDataCheck(String inPass, String dbPass) {
		boolean passRtn = false;
		
		if(getSha256(inPass).equals(dbPass)) {
			passRtn	= true;
		} else if(getSha512(inPass).equals(dbPass)) {
			passRtn	= true;
		}
		
		return passRtn;
	}
}
