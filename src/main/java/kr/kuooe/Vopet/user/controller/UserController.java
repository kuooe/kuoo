package kr.kuooe.Vopet.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.annotations.Api;
import kr.kuooe.Countack.main.service.dto.CtClickCount;
import kr.kuooe.Countack.main.service.repository.ClickCountRepository;
import kr.kuooe.Groomie.localWeather.service.vo.LocationVO;
import kr.kuooe.Vopet.user.service.impl.UserService;
import kr.kuooe.Vopet.user.service.vo.User;
import kr.kuooe.comm.utility.StringUtil;
import kr.kuooe.comm.utilityKuooe.GroomieUtil;

@Api(value = "UserController")
@Controller
public class UserController {
	public Logger log = LoggerFactory.getLogger(this.getClass());
	
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/Vopet/Login.do")
	public String Login(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) {
		log.debug("Login() =====> Start");
		log.debug("Login() =====> End");
		return "Vopet/User/Login";
	}
    @RequestMapping("/Vopet/Join.do")
	public String Join(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) {
		log.debug("Join() =====> Start");
		log.debug("Join() =====> End");
		return "Vopet/User/Join";
	}
    
    @ResponseBody
    @RequestMapping("/Vopet/JoinAction.do")
	public Map<String, Object> JoinAction(HttpServletRequest request, HttpServletResponse response, ModelMap model, User user, RedirectAttributes redirectAttributes) {
		log.debug("JoinAction() =====> Start"+user);
		System.out.println(user);
	    Map<String, Object> result = new HashMap<String, Object>();
	    String rtnFlag = "N";
	    String rtnMsg = "";
	    try {

	        // 아이디 중복 체크
	        if (userService.isUserIdExists(user.getUserId())) {
	            rtnMsg = "이미 사용 중인 아이디입니다.";
	        } else if (userService.isUserMailExists(user.getUserMail())) { // 이메일 중복 체크
	            rtnMsg = "이미 사용 중인 이메일 주소입니다.";
	        } else {
		    	User registeredUser = userService.registerUser(user);
		        rtnFlag = "Y"; // 정상적으로 처리되면 Y로 설정
		        result.put("registeredUser", registeredUser);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        rtnFlag = "N";
	        rtnMsg = e.getMessage(); // 예외 메시지를 설정하거나 필요에 따라 다른 처리를 할 수 있습니다.
	    }

	    result.put("rtnFlag", rtnFlag);
	    result.put("rtnMsg", rtnMsg);
	    System.out.println("JoinAction() =====> End" + result);
	    log.debug("JoinAction() =====> End" + result);

	    return result;
	}
    
    @ResponseBody
    @RequestMapping("/Vopet/LoginAction.do")
    public Map<String, Object> loginAction(HttpServletRequest request, HttpServletResponse response, ModelMap model, User user, RedirectAttributes redirectAttributes, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        String rtnFlag = "N";
        String rtnMsg = "";

        try {
            // 여기에서 로그인 로직을 구현하고, 성공 여부에 따라 결과를 설정합니다.
            // 예시: 가상으로 아이디가 "test"이고 비밀번호가 "password"인 경우 로그인 성공이라 가정


            boolean loginSuccess = userService.authenticateUser(user.getUserId(), user.getUserPw());
            if (loginSuccess) {
            	User loggedInUser = userService.getUserByUserId(user.getUserId());
                session.setAttribute("loggedInUser", loggedInUser);
                rtnFlag = "Y";
            } else {
                rtnMsg = "아이디 또는 비밀번호가 올바르지 않습니다.";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            rtnMsg = e.getMessage();
        }

        result.put("rtnFlag", rtnFlag);
        result.put("rtnMsg", rtnMsg);
        return result;
    }
}