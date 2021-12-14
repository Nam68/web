package miyu.user.model;

import java.sql.Date;

public interface UserDAO {
	
	public int duplicateIdCheck(String id);
	public int signup(UserDTO dto);
	public UserDTO signin(UserDTO dto);
	public int updatePermit(int useridx, int permit);
	
	public int updateAutoSignin(String sessionId, Date sessionAge, int useridx);
	public UserDTO autoSingin(String sessionId);
	
}
