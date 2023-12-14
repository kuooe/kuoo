package kr.kuooe.Groomie.Clothes.service.vo;

import io.swagger.annotations.ApiParam;
import kr.kuooe.comm.vo.DefaultVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class NaverShopParamVO extends DefaultVO {
	private static final long serialVersionUID = 7835500707081351904L;
	
	@ApiParam(value = "검색어", 						required = false)
	private String query	= "";
	@ApiParam(value = "한 번에 표시할 검색 결과 개수", 	required = false)
	private int display	= 10;
	@ApiParam(value = "검색 시작 위치", 				required = false)
	private int start	= 0;
	@ApiParam(value = "검색 결과 정렬 방법", 			required = false)	//sim 정확도순 date: 날짜순으로 내림차순 정렬 asc: 가격순으로 오름차순 정렬 dsc: 가격순으로 내림차순 정렬
	private String sort	= "sim";
	@ApiParam(value = "검색 숫자", 					required = false)	//테스트용
	private int typeNum	= 0;
}