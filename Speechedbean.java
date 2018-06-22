package cn.hoNoSayaka.jt;

public class Speechedbean extends Speechbean {
	// group1名称 group2时间 group3结束 group4签走 group5id
	private String name;
	private String ended;
	private String signed;

	//
	public Speechedbean(String id, String stratTime, String name, String ended, String sigined) {
		super(id, stratTime);
		this.name = name;
		this.ended = ended;
		this.signed = sigined;
	}

	public String getName() {
		return name;
	}

	public String getEnded() {
		return ended;
	}

	public String getSigined() {
		return signed;
	}

	public void regest() {
		JDBCConnect.insert(this);
	}

	@Override
	public String toString() {
		return "课程id " + super.getId() + "课程名称" + name + " 课程时间 " + super.getStratTime() + "签到情况 " + signed + "是否结束"
				+ ended;
	}
}
