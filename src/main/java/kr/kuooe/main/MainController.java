package kr.kuooe.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.annotations.Api;

@Api(value = "MainController")
@Controller
public class MainController {
	public Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/")
	public String main(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) {
		System.out.println("Main() =====> Start");
		log.debug("Main() =====> End");
		return "Main/Main";
	}
	@RequestMapping("/Main/Main2.do")
	public String main2(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) {
		log.debug("Main() =====> Start");
		log.debug("Main() =====> End");
		return "Main/generic";
	}
	@RequestMapping("/Main/Main3.do")
	public String main3(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) {
		log.debug("Main() =====> Start");
		log.debug("Main() =====> End");
		return "Main/elements";
	}
}