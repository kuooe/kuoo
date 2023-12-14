package kr.kuooe.Groomie.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.annotations.Api;

@Api(value = "MainGRController")
@Controller
public class MainGRController {
	public Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/Groomie/")
	public String groomieMain(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) {
		log.debug("groomieMain() =====> Start");
		log.debug("groomieMain() =====> End");
		return "Groomie/Main";
	}
	@RequestMapping("/Groomie/Main2.do")
	public String groomieMain2(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) {
		log.debug("groomieMain2() =====> Start");
		log.debug("groomieMain2() =====> End");
		return "Groomie/generic";
	}
	@RequestMapping("/Groomie/Main3.do")
	public String groomieMain3(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) {
		log.debug("groomieMain3() =====> Start");
		log.debug("groomieMain3() =====> End");
		return "Groomie/elements";
	}
}