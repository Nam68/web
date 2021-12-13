package miyu.trip.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class TripDAOImple implements TripDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	
	
	/*** placeDTO 관련 메서드 ***/
	
	public PlaceDTO placeSelect(String region) {
		PlaceDTO dto = sql.selectOne("placeSelectForKor", region);
		if(dto == null) dto = sql.selectOne("placeSelectForJpn", region);
		if(dto == null) dto = sql.selectOne("placeSelectForEng", region);
		return dto;
	}

	public List placeList() {
		return sql.selectList("placeList");
	}
	
	
	
	/*** placesaveDTO 관련 메서드 ***/
		
	public int placesaveCount() {
		return sql.selectOne("placesaveCount");
	}
	
	public List placesaveListForPage(Map<String, Integer> map) {
		return sql.selectList("placesaveListForPage", map);
	}
	
	public PlacesaveDTO placesaveSelect(int pidx) {
		return sql.selectOne("placesaveSelect", pidx);
	}
	
	public int placesaveAdd(PlacesaveDTO dto) {
		return sql.insert("placesaveAdd", dto);
	}

}
