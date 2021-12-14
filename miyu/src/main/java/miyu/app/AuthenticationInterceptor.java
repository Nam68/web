package miyu.app;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import miyu.user.model.UserDTO;
import miyu.user.service.UserService;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService us;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Object dto = session.getAttribute("userDTO");
		
		if(dto == null) {
			Cookie cookie = WebUtils.getCookie(request, "autoSignin");
			if(cookie != null) {
				String sessionId = cookie.getValue();
				UserDTO signedDto = us.autoSignin(sessionId);
				
				if(signedDto != null) session.setAttribute("userDTO", signedDto); 
			}
		}
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

}
