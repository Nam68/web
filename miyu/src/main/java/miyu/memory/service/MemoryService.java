package miyu.memory.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import miyu.memory.model.MemoryDTO;
import miyu.memory.model.MemoryImgDTO;

public interface MemoryService {
	
	public static final int LIST_SIZE = 9;
	public static final int PAGE_SIZE = 10;
	
	public int memoryCount();
	
	public List memoryList();
	public List memoryListForPage(int cp, int count);
	public Map memorySelect(int idx);
	
	public int memoryAdd(MemoryDTO dto, MemoryImgDTO idto);
	public int memoryDelete(int idx);
	public int memoryUpdate(MemoryDTO dto, MemoryImgDTO idto);
	
	public List tmpMemoryImgAdd(MultipartFile[] files);
	public int tmpMemoryImgCopy(int idx);
	
	public String memoryPageCode(int cp, int count);
	
}
