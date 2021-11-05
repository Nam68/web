package miyu.user.model;

public interface UserDAO {
	
	public int duplicateIdCheck(String id);
	public int signup(UserDTO dto);
	public UserDTO login(String id, String pwd);
	public int updatePermit(int useridx, int permit);
	
}
