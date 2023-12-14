package kr.kuooe.comm.utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUtil {

	public static boolean createFolder(String filePath) {
		String[] arrPath	= filePath.split("\\/");
		String createPath	= arrPath[0];
		log.debug("FileUtil - createFolder() =====> filePath : "+ filePath);
		
		for(int i=1; i<arrPath.length; i++) {
			createPath	= createPath +"/"+ arrPath[i];
			File Folder = new File(createPath);
			if(!Folder.exists()) {
				try {
					Folder.mkdir();
				} catch(Exception e) {
					e.getStackTrace();
				}
			}
		}
		File Folder = new File(filePath);
		
		return Folder.exists();
	}
	
	public static boolean fileExists(String filePath) {
		boolean rtn = false;
		log.debug("fileExists() =====> filePath : "+ filePath);
		if(!StringUtil.isNull(filePath)) {
			File file = new File(filePath);
			if(file.exists()) {
				rtn	= true;
			}
		}
		log.debug("fileExists() =====> rtn : "+ rtn);
		
		return rtn;
		
	}
	
	public static String exclFileToJson(String filePath, int changeRow) throws Exception {
		String formData	= "";
		if(!StringUtil.isNull(filePath)) {
			if(fileExists(filePath)) {
				FileInputStream file = new FileInputStream(filePath);
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				
				JSONObject formMatc		= new JSONObject();
				JSONArray formMatcArr	= new JSONArray();
				XSSFSheet sheet			= workbook.getSheetAt(0);
				
				String[] callTitle	= new String[sheet.getRow(0).getPhysicalNumberOfCells()];
				
				int rows = sheet.getPhysicalNumberOfRows();
				for(int i = 0; i < rows; i++) {
					if(i > changeRow) {
						continue;
					}
					XSSFRow row	= sheet.getRow(i);
					if(row != null) {
						int cells = row.getPhysicalNumberOfCells();
						JSONObject formMatcObj = new JSONObject();
						for(int c = 0; c <= cells; c ++) {
							XSSFCell cell	= row.getCell(c);
							String callData	= "";
							if(cell==null) {
								continue;
							} else {
								String cellValue	= "";
								switch (cell.getCellType()) {
									case XSSFCell.CELL_TYPE_FORMULA:
										cellValue	= cell.getCellFormula();
										break;
									case XSSFCell.CELL_TYPE_NUMERIC:
										cellValue	= cell.getNumericCellValue() +"";
										break;
									case XSSFCell.CELL_TYPE_STRING:
										cellValue	= cell.getStringCellValue() +"";
										break;
									case XSSFCell.CELL_TYPE_BLANK:
										cellValue	= cell.getBooleanCellValue() +"";
										break;
									case XSSFCell.CELL_TYPE_ERROR:
										cellValue	= cell.getErrorCellValue() +"";
										break;
								}
								callTitle[c]	= i == 0 ? cellValue : callTitle[c];
								callData		= i == 0 ? "" : cellValue;
								log.debug("exclFileToJson() =====> "+ i +"-"+ c +" | "+ cellValue +" | "+ callTitle[c] +" : "+ callData);
								formMatcObj.put(callTitle[c], callData);
							}
						}
						formMatcArr.add(formMatcObj);
					}
				}
				formMatc.put("reqData", formMatcArr);
				formData	= formMatc.toJSONString();
			}
		}
		log.debug("exclFileToJson() =====> formData : "+ formData);
		
		return formData;
	}
}
