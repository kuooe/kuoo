package kr.kuooe.Groomie.Clothes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.annotations.Api;
import kr.kuooe.Groomie.Clothes.service.vo.NaverShopParamVO;
import kr.kuooe.Groomie.Clothes.service.vo.NaverShopResVO;
import kr.kuooe.Groomie.localWeather.service.vo.LocationVO;
import kr.kuooe.Groomie.localWeather.service.vo.WeatherVO;
import kr.kuooe.comm.enums.WeatherEnum;
import kr.kuooe.comm.utilityKuooe.GroomieUtil;


@Api(value = "ClothesController")
@Controller
public class ClothesController {
	public Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/Groomie/NaverClothesView.do")
	public String NaverClothesView(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes, LocationVO locationVO, WeatherVO weatherVO, NaverShopParamVO naverShopParamVO) {
		System.out.println("NaverClothesView() =====> Start"+locationVO);
		System.out.println("NaverClothesView() =====> Start"+weatherVO);

		
		try {
			String query = ""; // 검색어
			System.out.println("첫주소: "+ locationVO.getAddressName1());
			System.out.println("날씨코드"+ weatherVO.getWeatherCode());
			System.out.println("온도: "+ weatherVO.getTemperature());
			
			;
			
			model.addAttribute("weatherDescription",	WeatherEnum.getWeatherDescription(weatherVO.getWeatherCode()));
			model.addAttribute("temperature",			weatherVO.getTemperature());
			model.addAttribute("addressName1",			locationVO.getAddressName1());
			
			if(weatherVO.getWeatherCode().equals("09d") || weatherVO.getWeatherCode().equals("09n") || weatherVO.getWeatherCode().equals("10d") || weatherVO.getWeatherCode().equals("10n") || weatherVO.getWeatherCode().equals("11d") || weatherVO.getWeatherCode().equals("11n")) {//소나기 || 비
				if(naverShopParamVO.getTypeNum() == 0) {
					query = "레인부츠";
				}else if(naverShopParamVO.getTypeNum() == 1) {
					query = "우비";
				}else if(naverShopParamVO.getTypeNum() == 2) {
					query = "방수가방";
				}else if(naverShopParamVO.getTypeNum() == 3) {
					query = "우산";
				}
			}else if(weatherVO.getWeatherCode().equals("13d") || weatherVO.getWeatherCode().equals("13n")) {//눈
				if(naverShopParamVO.getTypeNum() == 0) {
					query = "따뜻한 옷";
				}else if(naverShopParamVO.getTypeNum() == 1) {
					query = "우산";
				}else if(naverShopParamVO.getTypeNum() == 2) {
					query = "눈 제거 도구";
				}else if(naverShopParamVO.getTypeNum() == 3) {
					query = "방수용품";
				}
			}else {
				query = GroomieUtil.detectSeaso();
			}
			
			naverShopParamVO.setQuery(query);
			
			List<NaverShopResVO> naverShopResList = GroomieUtil.naverShop(naverShopParamVO);
			
			model.addAttribute("naverShopResList",	naverShopResList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		
		
		
		log.debug("LocationView() =====> End");
		
		return "/Groomie/NaverClothesView";
	}
	
	
	
}