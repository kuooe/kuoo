package kr.kuooe.Groomie.localWeather.service.vo;

import io.swagger.annotations.ApiParam;
import kr.kuooe.comm.vo.DefaultVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class WeatherVO {
	
	@ApiParam(value = "온도 (섭씨)", 		required = false)
	private String temperature			= "";
	@ApiParam(value = "날씨", 			required = false)
	private String weatherMain			= "";
	@ApiParam(value = "날씨 상세", 		required = false)
	private String weatherDescription	= "";
	@ApiParam(value = "날씨 코드", 		required = false)
	private String weatherCode			= "";
} 