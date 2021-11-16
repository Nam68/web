package miyu.memory.model;

import java.sql.Date;

public class MemoryDTO {
	
	private int idx;
	private String title;
	private int place;
	private String placeName;
	private String content;
	private Date startdate;
	private Date enddate;
	private Date writedate;
	private String img;
	
	public MemoryDTO() {
		
	}
	
	public MemoryDTO(int idx, String title, int place, String placeName, String content, Date startdate, Date enddate,
			Date writedate, String img) {
		super();
		this.idx = idx;
		this.title = title;
		this.place = place;
		this.placeName = placeName;
		this.content = content;
		this.startdate = startdate;
		this.enddate = enddate;
		this.writedate = writedate;
		this.img = img;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
		
}
