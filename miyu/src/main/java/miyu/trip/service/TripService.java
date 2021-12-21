package miyu.trip.service;

import java.util.List;
import java.util.Map;

import miyu.trip.model.PlacesaveDTO;

public interface TripService {
	
	public static final int LIST_SIZE = 15;
	public static final int PAGE_SIZE = 10;
	
	enum PlaceSearchMenu {
		ALL(0), ACTIVTY(1), COUNTRY(2), REGION(3);
		private int menu;
		private PlaceSearchMenu(int menu) {
			this.menu = menu;
		}
		protected int getNum() {
			return menu;
		}
	}
	
	/*** placeDTO 관련 메서드 ***/
	public Map<String, Object> regionPick(String region);
	public List placeList();
	
	/*** placesaveDTO 관련 메서드 ***/
	public List placesaveListForPage(int cp);
	public PlacesaveDTO placesaveSelect(int pidx);
	public int placesaveAdd(PlacesaveDTO dto);
	public int placesaveUpdate(PlacesaveDTO dto);
	
	/*** activityDTO 관련 메서드 ***/
	public List activityList();
	
	/*** App 관련 메서드 ***/
	public String tripPageCode(int cp);
	public List placeListSearchMenu(int searchNum);
	public List placeListSearch(String searchText);
	
}
