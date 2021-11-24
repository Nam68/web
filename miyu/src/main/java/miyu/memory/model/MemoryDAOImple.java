package miyu.memory.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MemoryDAOImple implements MemoryDAO {
	
	@Autowired
	private SqlSessionTemplate sql;
	
	public int memoryCount() {
		return sql.selectOne("memoryCount");
	}
	
	public List memoryList() {
		return sql.selectList("memoryList");
	}

	public MemoryDTO memorySelect(int idx) {
		return sql.selectOne("memorySelect", idx);
	}

	public List memoryImgList(int idx) {
		return sql.selectList("memoryImgList", idx);
	}
	
	public List memoryListForPage(Map map) {
		return sql.selectList("memoryListForPage", map);
	}
	
	public List memoryImgListForPage(List list) {
		return sql.selectList("memoryImgListForPage", list);
	}

	public int memoryAdd(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int memoryDelete(int idx) {
		return sql.delete("memoryDelete", idx);
	}

	public int memoryUpdate(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
