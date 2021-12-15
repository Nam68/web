package miyu.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import miyu.user.model.UserDTO;
import miyu.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService us;
	
	@RequestMapping("/signin.do")
	public String signin(HttpSession session) {
		session.setAttribute("header", "user");
		return "user/signin";
	}
	
	@RequestMapping(value = "/signin.do", method = RequestMethod.POST)
	@ResponseBody
	public int signin(UserDTO dto, boolean check, HttpSession session, HttpServletResponse resp) {
		UserDTO user = us.signin(dto);
		session.setAttribute("userDTO", user);
	
		// check되어 있다면 로그인용 쿠키를 생성
		if(user != null && check != false) us.setSigninCookie(resp, dto.getUseridx(), session.getId());
				
		int count = user == null? 0:1;
		return count;
	}
	
	@RequestMapping("/signout.do")
	@ResponseBody
	public int signout(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
		// 자동 로그인을 하지 않을 것이므로 쿠키 초기화
		us.deleteSigninCookie(resp, req);
		
		// 세션 초기와
		session.invalidate();
		return 1;
	}
	
	@RequestMapping("/signup.do")
	public String signup(HttpSession session) {
		session.setAttribute("header", "user");
		return "user/signup";
	}
	
	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	@ResponseBody
	public int signup(UserDTO dto) {
		return us.signup(dto);
	}
	
	@RequestMapping("/duplicateIdCheck.do")
	@ResponseBody
	public int duplicateIdCheck(String id) {
		return us.duplicateIdCheck(id);
	}
	
}
