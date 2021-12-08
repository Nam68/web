package miyu.trip.model;

import java.util.List;

public interface TripDAO {
	
	/*** place 테이블 관련 메서드 ***/
	public PlaceDTO selectPlace(String region);
	public List listPlace();
	
}
