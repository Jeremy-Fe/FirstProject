package Spare;

public class SpareVo {
	private String id;
	private String password;
	
	public SpareVo() {
		
	}
	
	public SpareVo(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	
}
