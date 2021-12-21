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
		return dao.placesaveListForPage(p.startList(cp, LIST_SIZE), p.lastList(cp, dao.placesaveCount(), LIST_SIZE));
	}
	
	public PlacesaveDTO placesaveSelect(int pidx) {
		return dao.placesaveSelect(pidx);
	}
	
	public int placesaveAdd(PlacesaveDTO dto) {
		return dao.placesaveAdd(dto);
	}
	
	public int placesaveUpdate(PlacesaveDTO dto) {
		return dao.placesaveUpdate(dto);
	}
	
	
	
	/*** activityDTO 관련 메서드 ***/
	
	public List activityList() {
		return dao.activityList();
	}
	
	
	
	/*** App 관련 메서드 ***/
	
	public String tripPageCode(int cp) {
		return p.paginationCode(cp, dao.placesaveCount(), PAGE_SIZE, LIST_SIZE, COMMAND);
	}
	
	public List placeListSearchMenu(int searchNum) {
		return null;
	}
	
	public List placeListSearch(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
