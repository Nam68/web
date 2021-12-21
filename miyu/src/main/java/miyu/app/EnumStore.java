package miyu.app;

public class EnumStore {
	
	
	public enum MyCommand implements EnumModel {
		TRIP("tripList.do"), MEMORY("memoryList.do");
		
		private String value;
		private MyCommand(String value) {
			this.value = value;
		}
		public String getKey() {
			// TODO Auto-generated method stub
			return null;
		}
		public String getValue() {
			// TODO Auto-generated method stub
			return null;
		}
			
		
	}
	
	public enum Activity {
		HOTEL("1"), RESTAURANT("2"), CAFE("3"), NATURE("4"), HISTORICAL("5"), AMUSEMENT("6"), OTHERS("7");
		
		private String value;
		private Activity(String value) {
			this.value = value;
		}
		
		
		
	}
	
}
