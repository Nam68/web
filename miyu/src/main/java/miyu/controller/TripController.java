package miyu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import miyu.app.EnumStore.Activity;
import miyu.trip.model.PlacesaveDTO;
import miyu.trip.service.TripService;
import miyu.user.model.UserDTO;
import miyu.user.service.UserService;

@Controller
public class TripController {
	
	@Autowired
	private TripService ts;
	
	@Autowired
	private UserService us;
		
	@RequestMapping("/registerPlace.do")
	public ModelAndView registerPlace(HttpSession session, @RequestParam(defaultValue = "0")int pidx) {
		ModelAndView mav = new ModelAndView();
		if(us.adminCheck(session, mav)) {
			session.setAttribute("header", "trip");
			mav.addObject("place", ts.placeList());
			if(pidx != 0) mav.addObject("placesave", ts.placesaveSelect(pidx));
			mav.setViewName("trip/place/registerPlace");
		}
		return mav;
	}
	
	@RequestMapping("/placeList.do")
	public ModelAndView placeList(HttpSession session, @RequestParam(defaultValue = "1")int cp, 
			@RequestParam(defaultValue = "0")int searchNum, @RequestParam(defaultValue = "0")String searchText) {
		ModelAndView mav = new ModelAndView();
		if(us.adminCheck(session, mav)) {
			session.setAttribute("header", "trip");
			if(cp != 1) mav.addObject("scroll_animation_cancel", true);
			
			mav.addObject("placesave", ts.placesaveListForPage(cp));
			mav.addObject("page", ts.tripPageCode(cp));
			
			Class Activity = Activity.class;
			
			mav.addObject("test", Activity.getEnumConstants());
			mav.setViewName("trip/place/placeList");
		}
		return mav;
	}
	
	@RequestMapping("/placeContent.do")
	@ResponseBody
	public PlacesaveDTO placeContent(int pidx) {
		return ts.placesaveSelect(pidx);
	}
	
	@RequestMapping("/regionPick.do")
	@ResponseBody
	public Map<String, Object> regionPick(String region) {
		return ts.regionPick(region);
	}
	
	@RequestMapping(value = "placeAdd.do", method = RequestMethod.POST)
	@ResponseBody
	public int placeAdd(PlacesaveDTO dto) {
		return ts.placesaveAdd(dto);
	}
	
	@RequestMapping(value = "/placeUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public int placeUpdate(PlacesaveDTO dto) {
		return ts.placesaveUpdate(dto);
	}
	
	@RequestMapping("/placeListSearchMenu.do")
	@ResponseBody
	public List placeListSearchMenu(int searchNum) {
		return ts.placeListSearchMenu(searchNum);
	}
	
}
