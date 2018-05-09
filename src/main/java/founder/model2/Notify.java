package founder.model2;

// Generated 2018-4-27 18:39:40 by Hibernate Tools 3.4.0.CR1

/**
 * Notify generated by hbm2java
 */
public class Notify implements java.io.Serializable {

	private int id;
	private ControlTable controlTable;
	private String phone;
	private Short isopen;
	private String userid;

	public Notify() {
	}

	public Notify(int id) {
		this.id = id;
	}

	public Notify(int id, ControlTable controlTable, String phone,
			Short isopen, String userid) {
		this.id = id;
		this.controlTable = controlTable;
		this.phone = phone;
		this.isopen = isopen;
		this.userid = userid;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ControlTable getControlTable() {
		return this.controlTable;
	}

	public void setControlTable(ControlTable controlTable) {
		this.controlTable = controlTable;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Short getIsopen() {
		return this.isopen;
	}

	public void setIsopen(Short isopen) {
		this.isopen = isopen;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}