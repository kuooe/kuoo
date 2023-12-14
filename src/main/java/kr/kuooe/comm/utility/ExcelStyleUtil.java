package kr.kuooe.comm.utility;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelStyleUtil {

	public static XSSFCellStyle getStyleSubject(XSSFWorkbook objWorkBook) {
		// 제목 폰트
		XSSFFont fontSubject	= objWorkBook.createFont();
		fontSubject.setFontHeightInPoints((short) 18);
		fontSubject.setBoldweight((short) fontSubject.BOLDWEIGHT_BOLD);
		fontSubject.setFontName("맑은고딕");
		// 제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle styleSubject = objWorkBook.createCellStyle(); // 제목 스타일
		styleSubject.setFont(fontSubject);
		styleSubject.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleSubject.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		return styleSubject;
	}
	
	public static XSSFCellStyle getStyleTitle(XSSFWorkbook objWorkBook) {
		// 제목 폰트
		XSSFFont fontTitle	= objWorkBook.createFont();
		fontTitle.setFontHeightInPoints((short) 9);
		fontTitle.setBoldweight((short) fontTitle.BOLDWEIGHT_BOLD);
		fontTitle.setFontName("맑은고딕");
		// 제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle styleTitle = objWorkBook.createCellStyle(); // 제목 스타일
		styleTitle.setFont(fontTitle);
		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleTitle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());	// 배경
		styleTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleTitle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		styleTitle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		styleTitle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		styleTitle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleTitle.setWrapText(true);
		
		return styleTitle;
	}
	
	public static XSSFCellStyle getStyleTitleNLine(XSSFWorkbook objWorkBook) {
		// 제목 폰트
		XSSFFont fontTitle	= objWorkBook.createFont();
		fontTitle.setFontHeightInPoints((short) 9);
		fontTitle.setBoldweight((short) fontTitle.BOLDWEIGHT_BOLD);
		fontTitle.setFontName("맑은고딕");
		// 제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle styleTitle = objWorkBook.createCellStyle(); // 제목 스타일
		styleTitle.setFont(fontTitle);
		styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleTitle.setWrapText(true);
		
		return styleTitle;
	}
	
	public static XSSFCellStyle getStyleTitleNum(XSSFWorkbook objWorkBook) {
		// 제목 폰트
		XSSFFont fontTitle	= objWorkBook.createFont();
		fontTitle.setFontHeightInPoints((short) 9);
		fontTitle.setBoldweight((short) fontTitle.BOLDWEIGHT_BOLD);
		fontTitle.setFontName("맑은고딕");
		
		XSSFDataFormat df = objWorkBook.createDataFormat();
		
		// 제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle styleTitleNum = objWorkBook.createCellStyle(); // 제목 스타일
		styleTitleNum.setFont(fontTitle);
		styleTitleNum.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		styleTitleNum.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleTitleNum.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());	// 배경
		styleTitleNum.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleTitleNum.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		styleTitleNum.setBorderRight(XSSFCellStyle.BORDER_THIN);
		styleTitleNum.setBorderTop(XSSFCellStyle.BORDER_THIN);
		styleTitleNum.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleTitleNum.setDataFormat(df.getFormat("#,##0"));
		
		return styleTitleNum;
	}
	
	public static XSSFCellStyle getStyleConts(XSSFWorkbook objWorkBook) {
		// 제목 폰트
		XSSFFont fontConts	= objWorkBook.createFont();
		fontConts.setFontHeightInPoints((short) 9);
		fontConts.setFontName("맑은고딕");
		// 제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle styleConts = objWorkBook.createCellStyle(); // 제목 스타일
		styleConts.setFont(fontConts);
		styleConts.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleConts.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleConts.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderRight(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderTop(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleConts.setWrapText(true);
		
		return styleConts;
	}
	
	public static XSSFCellStyle getStyleLConts(XSSFWorkbook objWorkBook) {
		// 제목 폰트
		XSSFFont fontConts	= objWorkBook.createFont();
		fontConts.setFontHeightInPoints((short) 9);
		fontConts.setFontName("맑은고딕");
		// 제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle styleConts = objWorkBook.createCellStyle(); // 제목 스타일
		styleConts.setFont(fontConts);
		styleConts.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleConts.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleConts.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderRight(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderTop(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleConts.setWrapText(true);
		
		return styleConts;
	}
	
	public static XSSFCellStyle getStyleContsNum(XSSFWorkbook objWorkBook) {
		// 제목 폰트
		XSSFFont fontConts	= objWorkBook.createFont();
		fontConts.setFontHeightInPoints((short) 9);
		fontConts.setFontName("맑은고딕");
		
		XSSFDataFormat df = objWorkBook.createDataFormat();
		
		// 제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle styleConts = objWorkBook.createCellStyle(); // 제목 스타일
		styleConts.setFont(fontConts);
		styleConts.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		styleConts.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleConts.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderRight(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderTop(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleConts.setDataFormat(df.getFormat("#,##0"));
		
		return styleConts;
	}
	
	public static XSSFCellStyle getStyleContsDot(XSSFWorkbook objWorkBook) {
		// 제목 폰트
		XSSFFont fontConts	= objWorkBook.createFont();
		fontConts.setFontHeightInPoints((short) 9);
		fontConts.setFontName("맑은고딕");
		
		XSSFDataFormat df = objWorkBook.createDataFormat();
		
		// 제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle styleConts = objWorkBook.createCellStyle(); // 제목 스타일
		styleConts.setFont(fontConts);
		styleConts.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		styleConts.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleConts.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderRight(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderTop(XSSFCellStyle.BORDER_THIN);
		styleConts.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleConts.setDataFormat(df.getFormat("#,##0.0"));
		
		return styleConts;
	}
}
