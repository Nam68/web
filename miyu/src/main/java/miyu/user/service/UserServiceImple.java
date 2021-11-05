package miyu.user.service;

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

	public UserDTO login(UserDTO dto) {
		return dao.login(dto);
	}

	public int updatePermit(int useridx, int permit) {
		// TODO Auto-generated method stub
		return 0;
	}

}
