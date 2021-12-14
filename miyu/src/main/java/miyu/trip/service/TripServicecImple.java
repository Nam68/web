package miyu.trip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import miyu.app.PageManagement;
import miyu.trip.model.PlaceDTO;
import miyu.trip.model.PlacesaveDTO;
import miyu.trip.model.TripDAO;

public class TripServicecImple implements TripService {
	
	@Autowired
	private TripDAO dao;
	
	@Autowired
	private PageManagement p;
	
	
	
	/*** placeDTO 관련 메서드 ***/
	
	public Map<String, Object> regionPick(String region) {
		PlaceDTO dto = dao.placeSelect(region);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("dto", dto);
		return json;
	}
	
	public List placeList() {
		return dao.placeList();
	}
	
	
	
	/*** placesaveDTO 관련 메서드 ***/
	
	public List placesaveListForPage(int cp) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", p.startList(cp, LIST_SIZE));
		map.put("last", p.lastList(cp, dao.placesaveCount(), LIST_SIZE));
		return dao.placesaveListForPage(map);
	}
	
	public PlacesaveDTO placesaveSelect(int pidx) {
		return dao.placesaveSelect(pidx);
	}
	
	public int placesaveAdd(PlacesaveDTO dto) {
		return dao.placesaveAdd(dto);
	}
	
	
	
	/*** placesaveDTO 관련 메서드 ***/
	
	public String tripPageCode(int cp) {
		return p.paginationCode(cp, dao.placesaveCount(), PAGE_SIZE, LIST_SIZE, COMMAND);
	}
	
}
