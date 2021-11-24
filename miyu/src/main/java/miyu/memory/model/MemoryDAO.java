package miyu.memory.model;

import java.util.List;
import java.util.Map;

public interface MemoryDAO {
	
	public int memoryCount();
	
	public List memoryList();
	public MemoryDTO memorySelect(int idx);
	public List memoryImgList(int idx);
	public List memoryListForPage(Map map);
	public List memoryImgListForPage(List list);
	
	public int memoryAdd(Map map);
	public int memoryDelete(int idx);
	public int memoryUpdate(Map map);
	
}
