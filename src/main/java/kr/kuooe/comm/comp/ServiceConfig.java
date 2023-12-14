package kr.kuooe.comm.comp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {

	public static String ServerPort;
	public static String ServerHost;

	public static String ServerTitle;
	public static String ServerVersion;

	public static String JdbcType; // JDBC연동 DB종류

	public static String DeginMode;

	public static String SuperLoginId;
	public static String SuperLoginPw;
	public static String SuperLoginSeq;
	public static String SuperLoginName;
	public static String SuperLoginEmail;
	public static String SuperLoginPhone;
	public static String SuperLoginLevel;
	public static String SuperLoginStatus;
	public static String SuperLoginClientip;
	public static String SuperLoginMailcheck;
	public static int SuperLoginListSize;

	public static String FilePath;
	public static String DocuPath;
	public static String ExcelPath;
	public static String TempPath;
	public static String GuidePath;

	public static String RelayName;
	public static String RelayCode;
	public static String RelayFlag;
	public static String RelayStyle;
	public static String RelayHost; // 중계자 api

	public static String CiConversionUse; // ci 변환 사용유무
	public static String CiConversionGubun; // ci 변환 구분
	public static String CiConversionHost; // ci 변환 api

	public static String PostUse; // 우편발송 사용유무
	public static String PostHost; // 우편발송 api

	public static String DataReadUse; // 데이터수신 사용유무
	public static String DataReadHost; // 데이터수신 api

	public static String EncrptType;
	public static String EncrptUtil;

	@Value("${server.port}")
	public void setServerPort(String serverPort) {
		ServerPort = serverPort;
	}

	@Value("${server.host}")
	public void setServerHost(String serverHost) {
		ServerHost = serverHost;
	}

	@Value("${service.title}")
	public void setServiceTitle(String serverTitle) {
		ServerTitle = serverTitle;
	}

	@Value("${service.version}")
	public void setServiceVersion(String serverVersion) {
		ServerVersion = serverVersion;
	}

	@Value("${spring.datasource.emobile.jdbcType}")
	public void setJdbcType(String jdbcType) {
		JdbcType = jdbcType;
	}

	@Value("${emobile.degin.mode}")
	public void setDeginMode(String deginMode) {
		DeginMode = deginMode;
	}

	@Value("${emobile.super.login.id}")
	public void setSuperLoginId(String superLoginId) {
		SuperLoginId = superLoginId;
	}

	@Value("${emobile.super.login.pw}")
	public void setSuperLoginPw(String superLoginPw) {
		SuperLoginPw = superLoginPw;
	}

	@Value("${emobile.super.login.seq}")
	public void setSuperLoginSeq(String superLoginSeq) {
		SuperLoginSeq = superLoginSeq;
	}

	@Value("${emobile.super.login.name}")
	public void setSuperLoginName(String superLoginName) {
		SuperLoginName = superLoginName;
	}

	@Value("${emobile.super.login.email}")
	public void setSuperLoginEmail(String superLoginEmail) {
		SuperLoginEmail = superLoginEmail;
	}

	@Value("${emobile.super.login.phone}")
	public void setSuperLoginPhone(String superLoginPhone) {
		SuperLoginPhone = superLoginPhone;
	}

	@Value("${emobile.super.login.level}")
	public void setSuperLoginLevel(String superLoginLevel) {
		SuperLoginLevel = superLoginLevel;
	}

	@Value("${emobile.super.login.status}")
	public void setSuperLoginStatus(String superLoginStatus) {
		SuperLoginStatus = superLoginStatus;
	}

	@Value("${emobile.super.login.clientip}")
	public void setSuperLoginClientip(String superLoginClientip) {
		SuperLoginClientip = superLoginClientip;
	}

	@Value("${emobile.super.login.mailcheck}")
	public void setSuperLoginMailcheck(String superLoginMailcheck) {
		SuperLoginMailcheck = superLoginMailcheck;
	}

	@Value("${emobile.super.login.listsize}")
	public void setSuperLoginListSize(int superLoginListSize) {
		SuperLoginListSize = superLoginListSize;
	}

	@Value("${emobile.upload.file.path}")
	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

	@Value("${emobile.upload.docu.path}")
	public void setDocuPath(String docuPath) {
		DocuPath = docuPath;
	}

	@Value("${emobile.upload.excel.path}")
	public void setExcelPath(String excelPath) {
		ExcelPath = excelPath;
	}

	@Value("${emobile.upload.temp.path}")
	public void setTempPath(String tempPath) {
		TempPath = tempPath;
	}
	
	 @Value("${emobile.upload.guide.path}") 
	 public void setGuidePath(String guidePath) {
		 GuidePath = guidePath; 
	 }
	 

	@Value("${emobile.api.relay.name}")
	public void setRelayName(String relayName) {
		RelayName = relayName;
	}

	@Value("${emobile.api.relay.code}")
	public void setRelayCode(String relayCode) {
		RelayCode = relayCode;
	}

	@Value("${emobile.api.relay.flag}")
	public void setRelayFlag(String relayFlag) {
		RelayFlag = relayFlag;
	}

	@Value("${emobile.api.relay.style}")
	public void setRelayStyle(String relayStyle) {
		RelayStyle = relayStyle;
	}

	@Value("${emobile.api.relay.server.host}")
	public void setRelayHost(String relayHost) {
		RelayHost = relayHost;
	}

	@Value("${emobile.api.ciConversion.use}")
	public void setCiConversionUse(String ciConversionUse) {
		CiConversionUse = ciConversionUse;
	}

	@Value("${emobile.api.ciConversion.gubun}")
	public void setCiConversionGubun(String ciConversionGubun) {
		CiConversionGubun = ciConversionGubun;
	}

	@Value("${emobile.api.ciConversion.server.host}")
	public void setCiConversionHost(String ciConversionHost) {
		CiConversionHost = ciConversionHost;
	}

	@Value("${emobile.api.post.server.use}")
	public void setPostUse(String postUse) {
		PostUse = postUse;
	}

	@Value("${emobile.api.post.server.host}")
	public void setPostHost(String postHost) {
		PostHost = postHost;
	}

	@Value("${emobile.api.dateRead.server.use}")
	public void setDataReadUse(String dataReadUse) {
		DataReadUse = dataReadUse;
	}

	@Value("${emobile.api.dateRead.server.host}")
	public void setDataReadHost(String dataReadHost) {
		DataReadHost = dataReadHost;
	}

	@Value("${emobile.encrpt.type}")
	public void setEncrptType(String encrptType) {
		EncrptType = encrptType;
	}

	@Value("${emobile.encrpt.util}")
	public void setEncrptUtil(String encrptUtil) {
		EncrptUtil = encrptUtil;
	}
}
