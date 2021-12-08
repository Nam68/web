package miyu.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import miyu.trip.service.TripService;
import miyu.user.model.UserDTO;

@Controller
public class TripController {
	
	@Autowired
	private TripService ts;
	
	@RequestMapping("/tripMainPage.do")
	public ModelAndView tripMainPage(HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("userDTO");
		
		ModelAndView mav = new ModelAndView();
		if(user == null || user.getPermit() != 2) {
			mav.addObject("title", "Trip Page");
			mav.addObject("href", "index.do");
			mav.addObject("msg", "管理者のみ閲覧できます\\nAdministrator Only");
			mav.setViewName("global");
		} else {
			session.setAttribute("header", "trip");
			mav.setViewName("trip/tripMainPage");
		}
		return mav;
	}
	
	@RequestMapping("/tripRegister.do")
	public String tripRegister() {
		return "trip/tripRegister";
	}
	
	@RequestMapping("/regionPick.do")
	@ResponseBody
	public Map<String, Object> regionPick(String region) {
		return ts.regionPick(region);
	}
	
}
