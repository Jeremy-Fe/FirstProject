package Spare.Form.SpareMain_01;

public class SpareMember {
	private String mid, mpw, mname, mclub, gid, gdate, gscore;

	SpareMember(String mid, String mpw, String mname, String mclub) {
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mclub = mclub;
	}
	
	
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMclub() {
		return mclub;
	}

	public void setMclub(String mclub) {
		this.mclub = mclub;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGdate() {
		return gdate;
	}

	public void setGdate(String gdate) {
		this.gdate = gdate;
	}

	public String getGscore() {
		return gscore;
	}

	public void setGscore(String gscore) {
		this.gscore = gscore;
	}
	
}
