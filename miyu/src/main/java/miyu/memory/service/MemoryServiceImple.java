package miyu.memory.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import miyu.app.EnumStore;
import miyu.app.FileManagement;
import miyu.app.PageManagement;
import miyu.memory.model.MemoryDAO;
import miyu.memory.model.MemoryDTO;
import miyu.memory.model.MemoryImgDTO;

public class MemoryServiceImple implements MemoryService {
	
	@Autowired
	private MemoryDAO dao;
	
	@Autowired
	private PageManagement p;
	
	@Autowired
	private FileManagement f;
	
	public int memoryCount() {
		return dao.memoryCount();
	}
	
	public List memoryList() {
		return dao.memoryList();
	}
	
	public List memoryListForPage(int cp, int count) {
		
		
		List<MemoryDTO> list = dao.memoryListForPage(p.startList(cp, LIST_SIZE), p.lastList(cp, count, LIST_SIZE));
		List<MemoryImgDTO> imgs = dao.memoryImgListForPage(list);
		
		Map<Integer, MemoryImgDTO> imgMap = new HashMap<Integer, MemoryImgDTO>();
		for(MemoryImgDTO dto : imgs) imgMap.put(dto.getIdx(), dto);
		for(MemoryDTO dto : list) if(imgMap.get(dto.getIdx())!=null) {dto.setImg(imgMap.get(dto.getIdx()).getImg());}
		
		return list;
	}

	public Map memorySelect(int idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memory", dao.memorySelect(idx));
		map.put("imgs", dao.memoryImgList(idx));
		return map;
	}

	public int memoryAdd(MemoryDTO dto, MemoryImgDTO idto) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int memoryDelete(int idx) {
		return dao.memoryDelete(idx);
	}

	public int memoryUpdate(MemoryDTO dto, MemoryImgDTO idto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List tmpMemoryImgAdd(MultipartFile[] files) {
		f.tempFileAdd(files);
		return f.getTempDbPath();
	}
	
	public int tmpMemoryImgCopy(int idx) {
		List<MemoryImgDTO> list = dao.memoryImgList(idx);
		List<String> imgs = new ArrayList<String>();
		for(MemoryImgDTO dto : list) imgs.add(dto.getImg());
		return f.tmpMemoryImgCopy(imgs);
	}
	
	public String memoryPageCode(int cp, int count) {
		return p.paginationCode(cp, count, PAGE_SIZE, LIST_SIZE, EnumStore.MyCommand.MEMORY);
	}

}
