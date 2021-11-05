package miyu.user.model;

import java.sql.Date;

public class UserDTO {
	
	private int useridx;
	private String userid;
	private String userpwd;
	private String username;
	private int permit;
	private Date joindate;
	
	public UserDTO() {
		
	}

	public UserDTO(int useridx, String userid, String userpwd, String username, int permit, Date joindate) {
		super();
		this.useridx = useridx;
		this.userid = userid;
		this.userpwd = userpwd;
		this.username = username;
		this.permit = permit;
		this.joindate = joindate;
	}

	public int getUseridx() {
		return useridx;
	}

	public void setUseridx(int useridx) {
		this.useridx = useridx;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPermit() {
		return permit;
	}

	public void setPermit(int permit) {
		this.permit = permit;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	
}
