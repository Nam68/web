package miyu.trip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import miyu.trip.model.PlaceDTO;
import miyu.trip.model.TripDAO;

public class TripServicecImple implements TripService {
	
	@Autowired
	private TripDAO dao;
	
	public Map<String, Object> regionPick(String region) {
		PlaceDTO dto = dao.selectPlace(region);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("dto", dto);
		return json;
	}
	
	public List listPlace() {
		return dao.listPlace();
	}
	
}
