package miyu.user.service;

import miyu.user.model.UserDTO;

public interface UserService {

	public int duplicateIdCheck(String id);
	public int signup(UserDTO dto);
	public UserDTO login(UserDTO dto);
	public int updatePermit(int useridx, int permit);	
	
}
