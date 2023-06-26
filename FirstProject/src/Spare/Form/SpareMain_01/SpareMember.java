package Spare.Form.SpareMain_01;

import java.util.Date;

public class SpareMember {
	private String mid, mpw, mname, mclub, sid, gid, gscore, rankName, rankCount, rankSum, rankMax, rankAvg;

	private Date gdate;

	public SpareMember(String mid, String mpw, String mname, String mclub) {
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mclub = mclub;
	}

	public SpareMember(String sid, String gid, Date gdate, String gscore) {
		this.sid = sid;
		this.gid = gid;
		this.gdate = gdate;
		this.gscore = gscore;
	}
	
	public SpareMember(String rankName,String rankCount,String rankSum,String rankMax,String rankAvg) {
		this.rankName = rankName;
		this.rankCount = rankCount;
		this.rankSum = rankSum;
		this.rankMax = rankMax;
		this.rankAvg = rankAvg;
	}

	public String getRankName() {
		return rankName;
	}

	public String getRankCount() {
		return rankCount;
	}

	public String getRankSum() {
		return rankSum;
	}

	public String getRankMax() {
		return rankMax;
	}

	public String getRankAvg() {
		return rankAvg;
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

	public String getSid() {
		return sid;
	}

	public void setSid(String gid) {
		this.gid = sid;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public Date getGdate() {
		return gdate;
	}

	public void setGdate(Date gdate) {
		this.gdate = gdate;
	}

	public String getGscore() {
		return gscore;
	}

	public void setGscore(String gscore) {
		this.gscore = gscore;
	}

}
