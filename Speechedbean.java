package cn.hoNoSayaka.jt;

public class Speechedbean extends Speechbean {
	// group1���� group2ʱ�� group3���� group4ǩ�� group5id
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
		return "�γ�id " + super.getId() + "�γ�����" + name + " �γ�ʱ�� " + super.getStratTime() + "ǩ����� " + signed + "�Ƿ����"
				+ ended;
	}
}
