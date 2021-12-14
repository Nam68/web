package miyu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public int signin(UserDTO dto, HttpSession session) {
		UserDTO user = us.login(dto);
		session.setAttribute("userDTO", user);
		int count = user == null? 0:1;
		return count;
	}
	
	@RequestMapping("/signout.do")
	@ResponseBody
	public int signout(HttpSession session) {
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
