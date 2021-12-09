package miyu.trip.service;

import java.util.List;
import java.util.Map;

public interface TripService {
	
	public Map<String, Object> regionPick(String region);
	public List listPlace();
	
}
