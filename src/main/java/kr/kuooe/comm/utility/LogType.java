package kr.kuooe.comm.utility;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class LogType implements Serializable {
	private static final long serialVersionUID = -180411385600662082L;
	
	
	/** 로그 Gubun 목록 */
	// 로그인
	public static final String	LogIn					= "LogIn";					// 사용자 인증(로그인)
	public static final String	LogInCert				= "LogInCert";				// 사용자 인증(2차인증)
	// 정보 관리
	public static final String	SelectAdminCampDetail	= "SelectAdminCampDetail";	// 정보관리 조회
	public static final String	UpdateAdminCampHelp		= "UpdateAdminCampHelp";	// 정보관리 수정(고객지원)
	public static final String	UpdateAdminCampEmail	= "UpdateAdminCampEmail";	// 정보관리 수정(이메일)
	// 코드 관리
	public static final String	SelectCommCode			= "SelectCommCode";			// 코드정보 조회
	public static final String	CreateCommCode			= "CreateCommCode";			// 코드정보 등록
	public static final String	UpdateCommCode			= "UpdateCommCode";			// 코드정보 수정
	public static final String	DeleteCommCode			= "DeleteCommCode";			// 코드정보 삭제
	// 사용자 관리
	public static final String	SelectUserList			= "SelectUserList";			// 사용자 목록 조회
	public static final String	SelectUserDetail		= "SelectUserDetail";		// 사용자 상세 조회
	public static final String	CreateUser				= "CreateUser";				// 사용자 정보 등록
	public static final String	UpdateUser				= "UpdateUser";				// 사용자 정보 수정
	public static final String	DeleteUser				= "DeleteUser";				// 사용자 정보 삭제
	public static final String	SelectUserAuth			= "SelectUserAuth";			// 사용자 권한 조회
	public static final String	UpdateUserAuth			= "UpdateUserAuth";			// 사용자 권한 수정
	// 서식 관리
	public static final String	SelectDcouList			= "SelectDcouList";			// 서식 목록 조회
	public static final String	SelectDocuDetail		= "SelectDocuDetail";		// 서식 상세 조회
	public static final String	CreateDocu				= "CreateDocu";				// 서식 정보 등록
	public static final String	UpdateDocu				= "UpdateDocu";				// 서식 정보 수정
	public static final String	DeleteDocu				= "DeleteDocu";				// 서실 정보 삭제
	// 중계자 관리
	public static final String	SelectRelayList			= "SelectRelayList";		// 중계자 목록 조회
	public static final String	SelectRelayDetail		= "SelectRelayDetail";		// 중계자 상세 조회
	public static final String	CreateRelay				= "CreateRelay";			// 중계자 정보 등록
	public static final String	UpdateRelay				= "UpdateRelay";			// 중계자 정보 수정
	public static final String	DeleteRelay				= "DeleteRelay";			// 중계자 정보 삭제
	// 스케줄 관리
	public static final String	SelectScheduleList		= "SelectScheduleList";		// 스케줄 목록 조회
	public static final String	SelectScheduleDetail	= "SelectScheduleDetail";	// 스케줄 상세 조회
	public static final String	CreateSchedule			= "CreateSchedule";			// 스케줄 정보 등록
	public static final String	UpdateSchedule			= "UpdateSchedule";			// 스케줄 정보 수정
	public static final String	DeleteSchedule			= "DeleteSchedule";			// 스케줄 정보 삭제
	// 접수현황
	public static final String	SelectRectList			= "SelectRectList";			// 접수현황 목록 조회
	public static final String	SelectRectSend			= "SelectRectSend";			// 접수 정보 조회(발송내역)
	public static final String	SelectRectDetail		= "SelectRectDetail";		// 접수 상세 조회
	public static final String	CreateRect				= "CreateRect";				// 접수 정보 등록
	public static final String	UpdateRect				= "UpdateRect";				// 접수 정보 수정
	public static final String	DeleteRect				= "DeleteRect";				// 접수 정보 삭제
	// 발송현황
	public static final String	SelectSendList			= "SelectSendList";			// 발송현황 목록 조회
	public static final String	SelectSendDetail		= "SelectSendDetail";		// 발송 상세 조회
	public static final String	CreateSend				= "CreateSend";				// 발송 정보 등록
	public static final String	UpdateSend				= "UpdateSend";				// 발송 정보 수정
	public static final String	DeleteSend				= "DeleteSend";				// 발송 정보 삭제
	public static final String	DownloadSend			= "DownloadSend";			// 발송 정보 다운로드
	// 통계
	public static final String	SelectStatDate			= "SelectStatDate";			// 일자별 통계 조회
	public static final String	ExcelStatDate			= "ExcelStatDate";			// 일자별 통계 다운
	public static final String	SelectStatDocu			= "SelectStatDocu";			// 서식별 통계 조회
	public static final String	ExcelStatDocu			= "ExcelStatDocu";			// 서식별 통계 다운
	public static final String	SelectStatSystem		= "SelectStatSystem";		// 시스템별 통계 조회
	public static final String	ExcelStatSystem			= "ExcelStatSystem";		// 시스템별 통계 다운
	
	// 회원 관리
	public static final String SelectListMember			= "SelectListMember";		// 회원 정보 목록조회
	public static final String UpdateMember				= "UpdateMember";			// 회원 정보 수정
	public static final String UpdateMemberStatus		= "UpdateMemberStatus";		// 회원 상태 수정(재발송)
	public static final String DeleteMember				= "DeleteMember";			// 회원 정보 삭제
	public static final String CreateMember				= "CrateMember";			// 회원 정보 등록
	public static final String UploadMember				= "UploadMember";			// 회원 정보 업로드
	public static final String MemberExcelUpload		= "MemberExcelUpload";		// 회원 정보 엑셀 업로드
	public static final String MemberDetail				= "MemberDetail";			// 회원 정보 서식 상세보기
	
	// 지자체 관리
	public static final String SelectAgencyList			= "SelectAgencyList";		// 지자체 목록 조회
	public static final String CreateAgency				= "CreateAgency";			// 지자체 고지 양식 생성
	public static final String UpdateAgency				= "UpdateAgency";			// 지자체 안내문 변경
	public static final String DeleteAgency				= "DeleteAgency";			// 지자체 고지 양식 삭제
	public static final String AgencyDetail				= "AgencyDetail";			// 지자체 상세보기
	public static final String AgencyPreview			= "AgencyPreview";			// 지자체 안내문 미리보기	
	
	public static final String[] logGubunCodes	= { "LogIn", "LogInCert", "SelectAdminCampDetail", "UpdateAdminCampHelp", "UpdateAdminCampEmail", "SelectCommCode", "CreateCommCode", "UpdateCommCode", "DeleteCommCode", "SelectUserList", "SelectUserDetail", "CreateUser", "UpdateUser", "DeleteUser", "SelectUserAuth", "UpdateUserAuth", "SelectDcouList", "SelectDocuDetail", "CreateDocu", "UpdateDocu", "DeleteDocu", "SelectRelayList", "SelectRelayDetail", "CreateRelay", "UpdateRelay", "DeleteRelay", "SelectScheduleList", "SelectScheduleDetail", "CreateSchedule", "UpdateSchedule", "DeleteSchedule", "SelectRectList", "SelectRectSend", "SelectRectDetail", "CreateRect", "UpdateRect", "DeleteRect", "SelectSendList", "SelectSendDetail", "CreateSend", "UpdateSend", "DeleteSend", "DownloadSend", "SelectStatDate", "ExcelStatDate", "SelectStatDocu", "ExcelStatDocu", "SelectStatSystem", "ExcelStatSystem", "SelectListMember", "UpdateMember", "DeleteMember", "CrateMember", "UploadMember", "MemberExcelUpload", "MemberDetail" , "SelectAgencyList" , "CreateAgency" , "AgencyDetail" ,"UpdateAgency" , "AgencyPreview", "UpdateMemberStatus" };
	public static final String[] logGubunNames	= { "사용자 인증(로그인)", "사용자 인증(2차인증)", "정보관리 조회", "정보관리 수정(고객지원)", "정보관리 수정(이메일)", "코드정보 조회", "코드정보 등록", "코드정보 수정", "코드정보 삭제", "사용자 목록 조회", "사용자 상세 조회", "사용자 정보 등록", "사용자 정보 수정", "사용자 정보 삭제", "사용자 권한 조회", "사용자 권한 수정", "서식 목록 조회", "서식 상세 조회", "서식 정보 등록", "서식 정보 수정", "서실 정보 삭지", "중계자 목록 조회", "중계자 상세 조회", "중계자 정보 등록", "중계자 정보 수정", "중계자 정보 삭제", "스케줄 목록 조회", "스케줄 상세 조회", "스케줄 정보 등록", "스케줄 정보 수정", "스케줄 정보 삭제", "접수현황 목록 조회", "접수 정보 조회(발송내역)", "접수 상세 조회", "접수 정보 등록", "접수 정보 수정", "접수 정보 삭제", "발송현황 목록 조회", "발송 상세 조회", "발송 정보 등록", "발송 정보 수정", "발송 정보 다운로드", "발송 정보 삭제", "일자별 통계 조회", "일자별 통계 다운", "서식별 통계 조회", "서식별 통계 다운", "시스템별 통계 조회", "시스템별 통계 다운", "회원정보 목록 조회", "회원정보 수정", "회원정보 삭제", "회원정보 등록", "회원정보 업로드", "회원정보 엑셀 업로드", "회원정보 서식 상세보기" , "지자체관리 목록 조회", "지자체 신규등록" , "지자체 상세보기" , "지자체 안내문변경" , "지자체 안내문 미리보기" , "회원상태 수정(재발송)"};

	
	public static String getLogGubunName(String logGubunCode) {
		for (int i = 0; i < logGubunCodes.length; i++) {
			if (logGubunCodes[i].equalsIgnoreCase(logGubunCode)) {
				return logGubunNames[i];
			}
		}
		return logGubunCode;
	}
	public static String getLogGubunCode(String logGubunName) {
		for (int i = 0; i < logGubunNames.length; i++) {
			if (logGubunNames[i].equalsIgnoreCase(logGubunName)) {
				return logGubunCodes[i];
			}
		}
		return logGubunName;
	}
	
	/*
	public static final String[] logGubunCodes	= { "LogIn", "InsertCommCode", "DeleteCommCode", "UserList", "UserCreate", "UserDetail", "UserSaveAction", "UserAuth", "UserAuthUpdateAction", "RelayList", "RelayDetail", "RelaySaveAction", "ScheduleList", "ScheduleDetail", "ScheduleSaveAction", "ScheduleChangeAction", "DocuList", "DocuCreate", "DocuUpdate", "DocuSaveAction", "DocuSendAction", "RecordDetail", "RectList", "RectDetail", "SendList" , "SendDetail" , "RectListExcelDown" , "StatSystemExcelDown" , "StatSystem" , "StatDocuExcelDown" , "StatDocu" , "StatDateExcelDown", "StatDate"  };
	public static final String[] logGubunNames	= { "로그인", "기본코드 추가", "기본코드 삭제", "사용자 리스트 확인", "사용자 계정 추가", "사용자 계정 상세보기", "사용자 정보 저장", "사용자 권한 상세보기", "사용자 권한 수정", "중계자리스트 확인", "중계자 상세보기", "중계자 정보수정", "스캐줄러 리스트확인", "스캐줄러 상세보기", "스캐줄러 등록", "스캐줄러 변경", "문서리스트 확인", "문서생성", "문서 수정", "전자문서 서식저장", "문서접수 저장", "접수 문서 상세보기", "접수리스트 보기", "문서접수 상세보기", "발송현황 리스트보기", "발송현황 상세보기", "발송현황 다운로드", "시스템별 통계 다운로드", "시스템별 통계 리스트", "서식별통계 다운로드", "서식별통계리스트", "일자별 통계 다운로드", "일자별통계리스트" };
	public static String getLogGubunName(String logGubunCode) {
		for (int i = 0; i < logGubunCodes.length; i++) {
			if (logGubunCodes[i].equalsIgnoreCase(logGubunCode)) {
				return logGubunNames[i];
			}
		}
		return logGubunCode;
	}
	public static String getLogGubunItem(String logGubunName) {
		for (int i = 0; i < logGubunNames.length; i++) {
			if (logGubunNames[i].equalsIgnoreCase(logGubunName)) {
				return logGubunCodes[i];
			}
		}
		return logGubunName;
	}
	*/
	
	
	public static final String	LogSuccess	= "Y";
	public static final String	LogFail		= "N";
	
	/** 로그 Type 목록 */
	public static final String[] logTypeCodes	= { "Y", "N" };
	public static final String[] logTypeNames	= { "성공", "실패" };
	public static String getLogTypeName(String logTypeCode) {
		for (int i = 0; i < logTypeCodes.length; i++) {
			if (logTypeCodes[i].equalsIgnoreCase(logTypeCode)) {
				return logTypeNames[i];
			}
		}
		return logTypeCode;
	}
	public static String getLogTypeItem(String logTypeName) {
		for (int i = 0; i < logTypeNames.length; i++) {
			if (logTypeNames[i].equalsIgnoreCase(logTypeName)) {
				return logTypeCodes[i];
			}
		}
		return logTypeName;
	}
}
