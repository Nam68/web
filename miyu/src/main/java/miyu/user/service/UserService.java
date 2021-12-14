package miyu.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import miyu.user.model.UserDTO;

public interface UserService {

	public int duplicateIdCheck(String id);
	public int signup(UserDTO dto);
	public UserDTO signin(UserDTO dto);
	public int updatePermit(int useridx, int permit);	
	
	public void setSigninCookie(HttpServletResponse resp, int useridx, String sessionId);
	public void deleteSigninCookie(HttpServletResponse resp, HttpServletRequest req);
	public UserDTO autoSignin(String sessionId);
	
}
