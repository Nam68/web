package miyu.user.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

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

	public UserDTO signin(UserDTO dto) {
		return sql.selectOne("login", dto);
	}

	public int updatePermit(int useridx, int permit) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int updateAutoSignin(String sessionId, Date sessionAge, int useridx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("sessionAge", sessionAge);
		map.put("useridx", useridx);
		return sql.update("updateAutoSignin", map);
	}
	
	public UserDTO autoSingin(String sessionId) {
		return sql.selectOne("autoSignin", sessionId);
	}

}
