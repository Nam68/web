package miyu.user.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

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
		dao.updateAutoSignin(sessionId, sessionAge, useridx);
	}
	
	public void deleteSigninCookie(HttpServletResponse resp, HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserDTO dto = (UserDTO) session.getAttribute("userDTO");
		
		Cookie cookie = WebUtils.getCookie(req, "autoSignin");
		if(cookie != null) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			resp.addCookie(cookie);
			
			Date now = new Date(System.currentTimeMillis());
			dao.updateAutoSignin(session.getId(), now, dto.getUseridx());
		}
		
	}
	
	public UserDTO autoSignin(String sessionId) {
		return dao.autoSingin(sessionId);
	}
	
	
	
	/*** 관리자 계정인지 체크 ***/
	public boolean adminCheck(HttpSession session, ModelAndView mav) {
		UserDTO dto = (UserDTO) session.getAttribute("userDTO");
		
		if(dto == null || dto.getPermit() != 2) {
			mav.addObject("title", "Trip Page");
			mav.addObject("href", "index.do");
			mav.addObject("msg", "管理者のみ閲覧できます\\nAdministrator Only");
			mav.setViewName("global");
			return false;
		}
		
		return true;
	}

}
