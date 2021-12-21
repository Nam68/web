package miyu.trip.model;

public class CountryDTO {
	
	private int count;
	private String name;
	
	public CountryDTO() {
		
	}

	public CountryDTO(int count, String name) {
		super();
		this.count = count;
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
