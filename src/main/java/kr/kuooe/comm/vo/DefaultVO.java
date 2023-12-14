package kr.kuooe.comm.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DefaultVO implements Serializable {
	private static final long serialVersionUID = 5687537065694679424L;
	
	
	@ApiParam(value = "검색 일자", 				required = false, hidden = true)
	private String	searchDate		= "";	/* 검색 일자 */
	@ApiParam(value = "검색 기간 시작일", 			required = false, hidden = true)
	private String	searchSdate		= "";	/* 검색 기간 시작일 */
	@ApiParam(value = "검색 기간 종료일", 			required = false, hidden = true)
	private String	searchEdate		= "";	/* 검색 기간 종료일 */
	@ApiParam(value = "검색 전송상태", 				required = false, hidden = true)
	private String	searchSend		= "";	/* 검색 전송상태 */
	@ApiParam(value = "검색 서식명", 				required = false, hidden = true)
	private String	searchDocu		= "";	/* 검색 서식명 */
	@ApiParam(value = "검색 사용여부", 				required = false, hidden = true)
	private String	searchUse		= "";	/* 검색 사용여부 */
	@ApiParam(value = "검색 조건", 				required = false, hidden = true)
	private String	searchKey		= "";	/* 검색 조건 */
	@ApiParam(value = "검색 텍스트", 				required = false, hidden = true)
	private String	searchTxt		= "";	/* 검색 텍스트 */
	@ApiParam(value = "검색 중계자", 				required = false, hidden = true)
	private String	searchRelay		= "";	/* 검색 중계자 */
	@ApiParam(value = "게시물 시작 번호", 			required = false, hidden = true)
	private long	pageStart		= 0;	/* 게시물 시작 번호 */
	@ApiParam(value = "현재페이지", 				required = false, hidden = true)
	private long	pageNum			= 1;	/* 현재페이지 */
	@ApiParam(value = "페이지에 표기되는 게시물 건수", 	required = false, hidden = true)
	private long	pageUnit		= 10;	/* 페이지에 표기되는 게시물 건수 */
	@ApiParam(value = "페이지 리스트에 표기되는 페이지 건수",		required = false, hidden = true)
	private long	pageSize		= 10;	/* 페이지 리스트에 표기되는 페이지 건수 */
	@ApiParam(value = "첫페이지", 					required = false, hidden = true)
	private long	stPage			= 1;	/* 첫페이지 */
	@ApiParam(value = "마지막 페이지", 				required = false, hidden = true)
	private long	enPage			= 1;	/* 마지막 페이지 */
	@ApiParam(value = "전체 건수", 				required = false, hidden = true)
	private long	totSize			= 0;	/* 전체 건수 */
	@ApiParam(value = "전체 페이지", 				required = false, hidden = true)
	private long	totPage			= 0;	/* 전체 페이지 */
	@ApiParam(value = "결과 메세지", 				required = false, hidden = true)
	private String	resultMsg		= "";	// 결과 메세지
	
}