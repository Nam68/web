package miyu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import miyu.memory.service.MemoryService;

@Controller
public class MemoryController {
	
	@Autowired
	private MemoryService ms;
	
	@RequestMapping("/memoryList.do")
	public ModelAndView memoryList(HttpSession session, @RequestParam(defaultValue = "1")int cp) {
		session.setAttribute("header", "memory");
		int count = ms.memoryCount();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", ms.memoryListForPage(cp, count));
		mav.addObject("page", ms.memoryPageCode(cp, count));
		if(cp != 1) mav.addObject("scroll_animation_cancel", true);
		mav.addObject("cp", cp);
		mav.setViewName("memory/memoryList");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/memoryContent.do", produces = "application/json; charset=utf-8")
	public String memoryContent(int idx) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(ms.memorySelect(idx));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value = "/tmpMemoryImgAdd.do", method = RequestMethod.POST)
	@ResponseBody
	public List<String> tmpMemoryImgAdd(MultipartFile[] files) {
		return ms.tmpMemoryImgAdd(files);
	}
	
	@RequestMapping("/memoryAdd.do")
	public String memoryAdd() {
		return "memory/memoryAdd";
	}
	
	@RequestMapping("/memoryDelete.do")
	@ResponseBody
	public int memoryDelete(int idx) {
		return ms.memoryDelete(idx);
	}
	
	@RequestMapping(value = "/memoryUpdate.do", method = RequestMethod.GET)
	@ResponseBody
	public int memoryUpdate(int idx) {
		return ms.tmpMemoryImgCopy(idx);
	}
	
}
