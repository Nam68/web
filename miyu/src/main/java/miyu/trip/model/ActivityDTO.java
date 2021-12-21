package miyu.trip.model;

public class ActivityDTO {
	
	private int anum;
	private String aname;
	private String janame;
	
	public ActivityDTO() {
		
	}

	public ActivityDTO(int anum, String aname, String janame) {
		super();
		this.anum = anum;
		this.aname = aname;
		this.janame = janame;
	}

	public int getAnum() {
		return anum;
	}

	public void setAnum(int anum) {
		this.anum = anum;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getJaname() {
		return janame;
	}

	public void setJaname(String janame) {
		this.janame = janame;
	}
	
}
