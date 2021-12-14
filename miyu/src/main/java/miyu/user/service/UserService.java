package miyu.user.service;

import javax.servlet.http.HttpServletResponse;

import miyu.user.model.UserDTO;

public interface UserService {

	public int duplicateIdCheck(String id);
	public int signup(UserDTO dto);
	public UserDTO signin(UserDTO dto);
	public int updatePermit(int useridx, int permit);	
	
	public void setSigninCookie(HttpServletResponse resp, int useridx, String sessionId);
	public UserDTO autoSignin(String sessionId);
	
}
