package miyu.trip.model;

import java.util.List;
import java.util.Map;

public interface TripDAO {
	
	/*** placeDTO 관련 메서드 ***/
	public PlaceDTO placeSelect(String region);
	public List placeList();
	
	/*** placesaveDTO 관련 메서드 ***/
	public int placesaveCount();
	public List placesaveListForPage(int start, int last);
	public PlacesaveDTO placesaveSelect(int pidx);
	public int placesaveAdd(PlacesaveDTO dto);
	
}
