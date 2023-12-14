package kr.kuooe.Groomie.localWeather.service.vo;

import io.swagger.annotations.ApiParam;
import kr.kuooe.comm.vo.DefaultVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class LocationVO {
	
	@ApiParam(value = "사용자 위도", 		required = false)
	private String latitude	= "";
	@ApiParam(value = "사용자 경도", 		required = false)
	private String nLongitude	= "";
	@ApiParam(value = "주소", 			required = false)
	private String addressName	= "";
	@ApiParam(value = "상세주소 1", 		required = false)
	private String addressName1	= "";
	@ApiParam(value = "국가 코드", 		required = false)
	private String contryCode	= "";
}