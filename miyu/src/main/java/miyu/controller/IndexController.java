package miyu.controller;

import java.text.DecimalFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index.do")
	public String index(HttpSession session) {
		Calendar c = Calendar.getInstance();
		c.set(c.YEAR, 2018);
		c.set(c.MONTH, 9);
		c.set(c.DATE, 16);
		
		long now = System.currentTimeMillis();
		long date = c.getTimeInMillis();
		long period = (now-date)/1000/60/60/24;
		
		DecimalFormat df = new DecimalFormat("###,###");
		session.setAttribute("period", df.format(period));
		
		return "index";
	}
	
}
