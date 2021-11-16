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
		mav.addObject("cp", cp);
		mav.setViewName("memory/memoryList");
		return mav;
	}
	
	@RequestMapping(value = "/tmpMemoryImgAdd.do", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String tmpMemoryImgAdd(MultipartFile[] files) {
		List list = ms.tmpMemoryImgAdd(files);
		ObjectMapper mapper = new ObjectMapper();
		String data = ""; 
		try {
			data = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping("/memoryAdd.do")
	public String memoryAdd() {
		return "memory/memoryAdd";
	}
	
}
