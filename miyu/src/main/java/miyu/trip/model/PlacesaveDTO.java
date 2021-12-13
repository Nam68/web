package miyu.trip.model;

public class PlacesaveDTO {
	
	private int pidx;
	private String pluscode;
	private int place;
	private int activity;
	private String name;
	private String memo;
	
	public PlacesaveDTO() {
		
	}

	public PlacesaveDTO(int pidx, String pluscode, int place, int activity, String name, String memo) {
		super();
		this.pidx = pidx;
		this.pluscode = pluscode;
		this.place = place;
		this.activity = activity;
		this.name = name;
		this.memo = memo;
	}

	public int getPidx() {
		return pidx;
	}

	public void setPidx(int pidx) {
		this.pidx = pidx;
	}

	public String getPluscode() {
		return pluscode;
	}

	public void setPluscode(String pluscode) {
		this.pluscode = pluscode;
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
	
}
