package miyu.user.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import miyu.user.model.UserDAO;
import miyu.user.model.UserDTO;

public class UserServiceImple implements UserService {

	@Autowired
	private UserDAO dao;
	
	public int duplicateIdCheck(String id) {
		return dao.duplicateIdCheck(id);
	}

	public int signup(UserDTO dto) {
		return dao.signup(dto);
	}

	public UserDTO signin(UserDTO dto) {
		return dao.signin(dto);
	}

	public int updatePermit(int useridx, int permit) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	/*** 쿠키 관련 메서드 ***/
	public void setSigninCookie(HttpServletResponse resp, int useridx, String sessionId) {
		// 로그인용 쿠키 생성
		Cookie cookie = new Cookie("autoSignin", sessionId);
		int age = 60*60*24*365;
		cookie.setMaxAge(age);
		cookie.setPath("/");
		resp.addCookie(cookie);
		
		// DB에 쿠키정보를 저장
		Date sessionAge = new Date(System.currentTimeMillis() + (1000 * age));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("sessionAge", sessionAge);
		map.put("useridx", useridx);
		dao.updateAutoSignin(map);
	}
	
	public UserDTO autoSignin(String sessionId) {
		return dao.autoSingin(sessionId);
	}

}
