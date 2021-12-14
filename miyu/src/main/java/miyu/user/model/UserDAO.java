package miyu.user.model;

import java.util.Map;

public interface UserDAO {
	
	public int duplicateIdCheck(String id);
	public int signup(UserDTO dto);
	public UserDTO signin(UserDTO dto);
	public int updatePermit(int useridx, int permit);
	
	public int updateAutoSignin(Map map);
	public UserDTO autoSingin(String sessionId);
	
}
