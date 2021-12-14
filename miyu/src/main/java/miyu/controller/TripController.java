package miyu.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import miyu.trip.model.PlacesaveDTO;
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
	public ModelAndView tripRegister(HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("userDTO");
		
		ModelAndView mav = new ModelAndView();
		if(user == null || user.getPermit() != 2) {
			mav.addObject("title", "Trip Page");
			mav.addObject("href", "index.do");
			mav.addObject("msg", "管理者のみ閲覧できます\\nAdministrator Only");
			mav.setViewName("global");
		} else {
			session.setAttribute("header", "trip");
			mav.addObject("place", ts.placeList());
			mav.setViewName("trip/tripRegister");
		}
		return mav;
	}
	
	@RequestMapping("/tripList.do")
	public ModelAndView tripList(HttpSession session, @RequestParam(defaultValue = "1")int cp) {
		UserDTO user = (UserDTO) session.getAttribute("userDTO");
		
		ModelAndView mav = new ModelAndView();
		if(user == null || user.getPermit() != 2) {
			mav.addObject("title", "Trip Page");
			mav.addObject("href", "index.do");
			mav.addObject("msg", "管理者のみ閲覧できます\\nAdministrator Only");
			mav.setViewName("global");
		} else {
			session.setAttribute("header", "trip");
			mav.addObject("place", ts.placeList());
			if(cp != 1) mav.addObject("scroll_animation_cancel", true);
			mav.addObject("placesave", ts.placesaveListForPage(cp));
			mav.addObject("page", ts.tripPageCode(cp));
			mav.setViewName("trip/tripList");
		}
		return mav;
	}
	
	@RequestMapping("/regionPick.do")
	@ResponseBody
	public Map<String, Object> regionPick(String region) {
		return ts.regionPick(region);
	}
	
	@RequestMapping(value = "/addTrip.do", method = RequestMethod.POST)
	@ResponseBody
	public int addTrip(PlacesaveDTO dto) {
		return ts.placesaveAdd(dto);
	}
	
}
