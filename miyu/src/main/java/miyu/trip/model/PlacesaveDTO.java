package miyu.trip.model;

public class PlacesaveDTO {
	
	private int pidx;
	private String name;
	private int place;
	private int activity;
	private String addr;
	private String memo;
	private double lat;
	private double lng;
	
	
	public PlacesaveDTO() {
		
	}
	
	public PlacesaveDTO(int pidx, String name, int place, int activity, String addr, String memo, double lat,
			double lng) {
		super();
		this.pidx = pidx;
		this.name = name;
		this.place = place;
		this.activity = activity;
		this.addr = addr;
		this.memo = memo;
		this.lat = lat;
		this.lng = lng;
	}

	public int getPidx() {
		return pidx;
	}

	public void setPidx(int pidx) {
		this.pidx = pidx;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}	
	
}
