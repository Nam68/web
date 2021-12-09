package miyu.trip.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class TripDAOImple implements TripDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	public PlaceDTO selectPlace(String region) {
		PlaceDTO dto = sql.selectOne("selectPlaceForKor", region);
		if(dto == null) dto = sql.selectOne("selectPlaceForJpn", region);
		if(dto == null) dto = sql.selectOne("selectPlaceForEng", region);
		return dto;
	}

	public List listPlace() {
		return sql.selectList("listPlace");
	}

}
