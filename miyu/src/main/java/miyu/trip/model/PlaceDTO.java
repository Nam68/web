package miyu.trip.model;

public class PlaceDTO {
	
	private int pnum;
	private String pname;
	private String jpname;
	private String kpname;
	private String img;
	
	public PlaceDTO() {
		
	}

	public PlaceDTO(int pnum, String pname, String jpname, String kpname, String img) {
		super();
		this.pnum = pnum;
		this.pname = pname;
		this.jpname = jpname;
		this.kpname = kpname;
		this.img = img;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getJpname() {
		return jpname;
	}

	public void setJpname(String jpname) {
		this.jpname = jpname;
	}

	public String getKpname() {
		return kpname;
	}

	public void setKpname(String kpname) {
		this.kpname = kpname;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
