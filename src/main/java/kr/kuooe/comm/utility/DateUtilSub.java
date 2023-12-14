package kr.kuooe.comm.utility;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DateUtilSub {

	public static String changeDate(String dateStr, String inType, String outType) {
		if(StringUtil.isNull(inType)) {
			inType	= "yyyy-MM-dd HH:mm:ss";
		}
		if(StringUtil.isNull(outType)) {
			outType	= "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat inFormat	= new SimpleDateFormat(inType);
		SimpleDateFormat outFormat	= new SimpleDateFormat(outType);
		
		Date date	= null;
		try {
			if(StringUtil.isNull(dateStr)) {
				date	= new Date();
			} else {
				date	= inFormat.parse(dateStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return outFormat.format(date);
	}
	public static String currentDate( String dateFormat ) {
		// 날짜포맷형식 설정
		if(StringUtil.isNull(dateFormat)) {
			dateFormat	= "yyyy-MM-dd HH:mm:ss";
		}
		log.debug("DateUtil - getCurrentDate() =====> dateFormat : "+ dateFormat);
		SimpleDateFormat sdf = new SimpleDateFormat( dateFormat );
		
		return sdf.format(new Date());
	}
	
	public static String preDate(String dateType, String preType, int preNum) {
		if(StringUtil.isNull(dateType)) {
			dateType	= "yyyy-MM-dd HH:mm:ss";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		SimpleDateFormat format	= new SimpleDateFormat (dateType);
		
		switch (preType) {
			case "H":	cal.add(Calendar.HOUR, (preNum * -1));	break;
			case "D":	cal.add(Calendar.DATE, (preNum * -1));	break;
			case "M":	cal.add(Calendar.MONTH, (preNum * -1));	break;
			case "Y":	cal.add(Calendar.YEAR, (preNum * -1));	break;
		}
		
		return format.format(cal.getTime());
	}
	public static String preDate(String dateStr, String dateType, String preType, int preNum) {
		if(StringUtil.isNull(dateType)) {
			dateType	= "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat simpleFormat	= new SimpleDateFormat(dateType);
		
		Calendar cal = Calendar.getInstance();
		try {
			if(dateStr.equals("")) {
				cal.setTime(new Date());
			} else {
				cal.setTime(simpleFormat.parse(dateStr));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		switch (preType) {
			case "H":	cal.add(Calendar.HOUR, (preNum * -1));	break;
			case "D":	cal.add(Calendar.DATE, (preNum * -1));	break;
			case "M":	cal.add(Calendar.MONTH, (preNum * -1));	break;
			case "Y":	cal.add(Calendar.YEAR, (preNum * -1));	break;
		}
		return simpleFormat.format(cal.getTime());
	}
	
	public static String nextDate(String dateType, String nextType, int nextNum) {
		if(StringUtil.isNull(dateType)) {
			dateType	= "yyyy-MM-dd HH:mm:ss";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		SimpleDateFormat format	= new SimpleDateFormat (dateType);
		
		switch (nextType) {
			case "H":	cal.add(Calendar.HOUR, nextNum);	break;
			case "D":	cal.add(Calendar.DATE, nextNum);	break;
			case "M":	cal.add(Calendar.MONTH, nextNum);	break;
			case "Y":	cal.add(Calendar.YEAR, nextNum);	break;
		}
		
		return format.format(cal.getTime());
	}
	public static String nextDate(String dateStr, String dateType, String nextType, int nextNum) {
		if(StringUtil.isNullToStr(dateType).equals("")) {
			dateType	= "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat simpleFormat	= new SimpleDateFormat(dateType);
		
		Calendar cal = Calendar.getInstance();
		try {
			if(dateStr.equals("")) {
				cal.setTime(new Date());
			} else {
				cal.setTime(simpleFormat.parse(dateStr));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		switch (nextType) {
			case "H":	cal.add(Calendar.HOUR, nextNum);	break;
			case "D":	cal.add(Calendar.DATE, nextNum);	break;
			case "M":	cal.add(Calendar.MONTH, nextNum);	break;
			case "Y":	cal.add(Calendar.YEAR, nextNum);	break;
		}
		
		return simpleFormat.format(cal.getTime());
	}
	
	public static String dateToString(Date date, String dateType) {
		if(StringUtil.isNull(dateType)) {
			dateType	= "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format	= new SimpleDateFormat (dateType);
		
		return format.format(date);
	}
	
	public static Date stringToDate(String strDate, String dateType) throws ParseException {
		if(StringUtil.isNull(dateType)) {
			dateType	= "yyyy-MM-dd";
		}
		SimpleDateFormat format	= new SimpleDateFormat (dateType);
		
		return format.parse(strDate);
	}
	
	public static Long dateToMs (String dateStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateStr);
		String dateImsi = String.valueOf(date.getTime());		
		return Long.parseLong(dateImsi.substring(0, dateImsi.length()-3));
	}
}
