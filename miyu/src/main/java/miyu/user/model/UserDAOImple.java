package miyu.user.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOImple implements UserDAO {
	
	@Autowired
	private SqlSessionTemplate sql;
	
	public int duplicateIdCheck(String id) {
		return sql.selectOne("duplicateIdCheck", id);
	}

	public int signup(UserDTO dto) {
		return sql.insert("signup", dto);
	}

	public UserDTO login(UserDTO dto) {
		return sql.selectOne("login", dto);
	}

	public int updatePermit(int useridx, int permit) {
		// TODO Auto-generated method stub
		return 0;
	}

}
