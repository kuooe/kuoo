package kr.kuooe.Groomie.localWeather.controller;

import io.swagger.annotations.Api;
import kr.kuooe.Groomie.localWeather.service.vo.LocationVO;
import kr.kuooe.Groomie.localWeather.service.vo.WeatherVO;
import kr.kuooe.comm.utilityKuooe.GroomieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Api(value = "LocationController")
@Controller
public class LocationController {
	public Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ResponseBody
	@RequestMapping("/Groomie/LocationView.do")
	public Map<String, Object> LocationView(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes, LocationVO locationVO, WeatherVO weatherVO) {
	    System.out.println("LocationView() =====> Start" + locationVO);
	    Map<String, Object> result = new HashMap<String, Object>();
	    String rtnFlag = "N";
	    String rtnMsg = "";

	    try {
	        LocationVO locationKakaoVO = GroomieUtil.kakaoLocation(locationVO);
	        weatherVO = GroomieUtil.openWeather(locationVO);

	        result.put("addressName", locationKakaoVO.getAddressName());    //주소
	        result.put("addressName1", locationKakaoVO.getAddressName1());    //상세주소1
	        result.put("temperature", weatherVO.getTemperature());
	        result.put("weatherCode", weatherVO.getWeatherCode());
	        //GroomieUtil.naverShop();

	        rtnFlag = "Y"; // 정상적으로 처리되면 Y로 설정

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e);
	        rtnFlag = "N";
	        rtnMsg = e.getMessage(); // 예외 메시지를 설정하거나 필요에 따라 다른 처리를 할 수 있습니다.
	    }

	    result.put("rtnFlag", rtnFlag);
	    result.put("rtnMsg", rtnMsg);
	    System.out.println("LocationView() =====> End" + result);
	    log.debug("LocationView() =====> End" + result);

	    return result;
	}
	
	
	
}