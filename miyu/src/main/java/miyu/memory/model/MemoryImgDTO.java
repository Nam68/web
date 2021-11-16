package miyu.memory.model;

public class MemoryImgDTO {
	
	private int iidx;
	private int idx;
	private String img;
	private String memo;
	
	public MemoryImgDTO() {
		
	}

	public MemoryImgDTO(int iidx, int idx, String img, String memo) {
		super();
		this.iidx = iidx;
		this.idx = idx;
		this.img = img;
		this.memo = memo;
	}

	public int getIidx() {
		return iidx;
	}

	public void setIidx(int iidx) {
		this.iidx = iidx;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
