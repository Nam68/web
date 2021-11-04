package miyu.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@RequestMapping
	public ModelAndView index() {
		Calendar c = Calendar.getInstance();
		c.set(c.YEAR, 2018);
		c.set(c.MONTH, 9);
		c.set(c.DATE, 16);
		
		long now = System.currentTimeMillis();
		long date = c.getTimeInMillis();
		long period = (now-date)/1000/60/60/24;
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("period", (int) period);
		mav.setViewName("index");
		return mav;
	}
	
}
