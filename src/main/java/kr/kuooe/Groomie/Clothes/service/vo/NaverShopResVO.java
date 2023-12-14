package kr.kuooe.Groomie.Clothes.service.vo;

import io.swagger.annotations.ApiParam;
import kr.kuooe.comm.vo.DefaultVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class NaverShopResVO extends DefaultVO {
	
	@ApiParam(value = "상품 이름", 		required = false)
	private String title	= "";

	@ApiParam(value = "상품 정보 URL", 		required = false)
	private String link	= "";
	
	@ApiParam(value = "상품 이미지", 		required = false)
	private String image	= "";
	
}