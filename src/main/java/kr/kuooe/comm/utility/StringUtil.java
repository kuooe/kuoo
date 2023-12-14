package kr.kuooe.comm.utility;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StringUtil {

	final static String PUBLISH_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	final static String RandStr		= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final static String RandNum		= "0123456789";
	
	public static boolean isNull(String reqData) {
		String setData = "";
		if (reqData != null) {
			setData = reqData.trim();
		}
		return setData.equals("");
	}
	
	public static String isNullToStr(String str) {
		String strRet = null;
		if (str == null) {
			strRet = "";
		} else if (str == "null") {
			strRet = "";
		} else {
			strRet = str;
		}
		return strRet;
	}
	/** 랜덤키 생성 **/
	public static String randomNum(int rndLen) {
		Random rand = new Random();
		String randNum	= String.valueOf(rand.nextInt(1000000));
		for(int i=randNum.length(); i<rndLen; i++) {
			randNum	= randNum +"0";
		}
		return randNum;
	}
	public static String randStr(int len) {
		String rtnRand	= "";
		String randLen	= "1";
		for(int i=0; i<len; i++) {
			randLen	= randLen +"0";
		}
		rtnRand	= String.valueOf(Math.round(Math.random() * Long.valueOf(randLen)));
		rtnRand	= stringRight(randLen + rtnRand, len);
		
		return rtnRand;
	}
	public static String randomID(int rndLen) {
		char[] c 		= PUBLISH_STR.toCharArray();
		String randID	= "";
		
		Random random = new Random();
		for (int i = 0; i < rndLen; i++) {
			randID	= randID + c[random.nextInt(c.length-1)];
		}
		
		return randID;
	}
	public static String identID(){
		char[] s = RandStr.toCharArray();
		char[] n = RandNum.toCharArray();
		String id = "";
		Random random = new Random();
		id	+= n[random.nextInt(n.length-1)];
		for (int i = 0; i < 3; i++) {
			id	+= s[random.nextInt(s.length-1)];
			id	+= n[random.nextInt(n.length-1)];
		}
		
		return id + System.currentTimeMillis();
	}
	
	public static String byteCuter(String str, int cutLeng) {
		if( str.toString().getBytes().length > cutLeng) {
			StringBuilder stringBuilder = new StringBuilder(cutLeng);
			int nCnt = 0;
			for(char ch:str.toString().toCharArray()){
				nCnt += String.valueOf(ch).getBytes().length;
				if(nCnt > cutLeng) break;
				stringBuilder.append(ch);
			}
			return stringBuilder.toString() + ".."; // 나머지부분 ..으로 표기
		} else {
			return str;
		}
	}
	
	public static String convertContent(String inContent) {
		String outContent	= inContent;
		
		outContent = outContent.replaceAll("(\r\n|\r|\n|\n\r)", "");	// 엔터
		outContent = outContent.replaceAll("<", "&lt");					// < → &lt
		outContent = outContent.replaceAll(">", "&gt");					// > → &gt
		
		outContent = outContent.replaceAll("#", "&#35");				// # → &#35
		
		outContent = outContent.replaceAll("\'", "&apos;");				// ' → &apos;
		outContent = outContent.replaceAll("\"", "&quot;");				// " → &quot;
		
		return outContent;
	}
	public static String strConvert(String strData) {
		String rtnData = strData;
		
		rtnData = rtnData.replaceAll("<br>", "");
		rtnData = rtnData.replaceAll("<p>", "");
		rtnData = rtnData.replaceAll("</p>", "");
		
		return rtnData;
	}
	
	public static String padLeftSpace(String inputData, int length) {
		StringBuilder sb = new StringBuilder();
		if(inputData.getBytes().length < length) {
			while (sb.length() < length - inputData.length()) {
				sb.append(' ');
			}
		}
		sb.append(inputData);
		return sb.toString();
	}
	public static String padRightSpace(String inputData, int length) throws UnsupportedEncodingException {
		byte[] byteTemp	= inputData.getBytes("EUC-KR");
		
		StringBuilder sb = new StringBuilder();
		sb.append(inputData);
		for(int i=byteTemp.length; i<12; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}
	
	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);
    }
	public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
	public static String stringLeft(String strData, int strLen) {
		String rtnStr	= "";
		if(strLen < strData.length()) {
			rtnStr	= strData.substring(0, strLen);
		} else {
			rtnStr	= strData;
		}
		return rtnStr;
	}
	public static String stringRight(String strData, int strLen) {
		String rtnStr	= "";
		if(strLen < strData.length()) {
			rtnStr	= strData.substring(strData.length() - strLen);
		} else {
			rtnStr	= strData;
		}
		return rtnStr;
	}
	
	public static String substringByBytes(String str, int beginBytes, int endBytes) {
		return new String(str.getBytes(), beginBytes, endBytes);
	}
	
	public static String getByteToSize(String strByte) {
		String rtnSize	= "";
		double size, sizeKB, sizeMB, sizeGB, sizeTB, sizePB, sizeEB, sizeZB, sizeYB;
		DecimalFormat form = new DecimalFormat("#.#");
		try {
			size	= Double.parseDouble(strByte);
			if(size > 1024) {
				sizeKB	= size / 1024;
				if(sizeKB > 1024) {
					sizeMB	= sizeKB / 1024;
					if(sizeMB > 1024) {
						sizeGB	= sizeMB / 1024;
						if(sizeGB > 1024) {
							sizeTB	= sizeGB / 1024;
							if(sizeTB > 1024) {
								sizePB	= sizeTB / 1024;
								if(sizePB > 1024) {
									sizeEB	= sizePB / 1024;
									if(sizeEB > 1024) {
										sizeZB	= sizeEB / 1024;
										if(sizeZB > 1024) {
											sizeYB	= sizeZB / 1024;
											rtnSize	= String.valueOf(form.format(sizeYB)) +"YB";
										} else {
											rtnSize	= String.valueOf(form.format(sizeZB)) +"ZB";
										}
									} else {
										rtnSize	= String.valueOf(form.format(sizeEB)) +"EB";
									}
								} else {
									rtnSize	= String.valueOf(form.format(sizePB)) +"PB";
								}
							} else {
								rtnSize	= String.valueOf(form.format(sizeTB)) +"TB";
							}
						} else {
							rtnSize	= String.valueOf(form.format(sizeGB)) +"GB";
						}
					} else {
						rtnSize	= String.valueOf(form.format(sizeMB)) +"MB";
					}
				} else {
					rtnSize	= String.valueOf(form.format(sizeKB)) +"KB";
				}
			} else {
				rtnSize	= String.valueOf(form.format(size)) +"Byte";
			}
		} catch (Exception e) {
			throw e;
		}
		return rtnSize;
	}
	
	/** 사용자 아이피 추출 **/
	public static String clientIp(HttpServletRequest req) {
		String ip = req.getHeader("X-Forwarded-For");
		
		if (ip == null) ip = req.getRemoteAddr();
		
		return ip;
	}
	
	public static boolean chkClientIp(String chkIp, String connIp) {
		boolean rtnIp	= false;
		
		String tmpIp	= isNullToStr(chkIp).replaceAll("\\.", "");
		long numIp		= tmpIp.equals("") ? 0 : Long.parseLong(tmpIp);
		if(numIp > 0) {
			if(chkIp.trim().equals(connIp.trim())) {
				rtnIp	= true;
			}
		} else {
			rtnIp	= true;
		}
		log.info("chkClientIp() =====> rtnIp : "+ rtnIp);
		return rtnIp;
	}
	/** 사용자 세션 추출 
	public static UserVO getSessionUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		UserVO sessionVO	= (UserVO) session.getAttribute("userSession");
		
		return sessionVO;
	}
	**/
}
