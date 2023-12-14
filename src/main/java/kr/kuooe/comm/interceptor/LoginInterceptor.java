package kr.kuooe.comm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@NoArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.debug("postHandle() =====> Start - "+ request.getContextPath());
		
		/** Session 체크 시작 **/
		/*
		HttpSession loginSession = request.getSession(true);
		UserVO loginVO = (UserVO) loginSession.getAttribute("userSession");
		log.debug("LoginInterceptor - preHandle() =====> loginVO - "+ loginVO);
		if(loginVO == null) {
			response.sendRedirect("../LogoutAction.do");
		}
		*/
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.debug("afterCompletion() =====> Start - "+ request.getContextPath());
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
