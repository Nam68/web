package miyu.controller;

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
	
	@RequestMapping("/login.do")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/signup.do", method = RequestMethod.GET)
	public String signup() {
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
