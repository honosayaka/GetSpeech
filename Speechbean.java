package cn.hoNoSayaka.jt;

public class Speechbean {

	private String id;
	private String stratTime;

	public Speechbean(String id, String stratTime) {
		super();
		this.id = id;
		this.stratTime = stratTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStratTime() {
		return stratTime;
	}

	public void setStratTime(String stratTime) {
		this.stratTime = stratTime;
	}

	@Override
	public String toString() {
		return "the id is " + this.id + " the date is " + this.stratTime;

	}
}
