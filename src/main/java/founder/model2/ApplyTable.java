package founder.model2;

// Generated 2018-4-27 18:39:40 by Hibernate Tools 3.4.0.CR1

/**
 * ApplyTable generated by hbm2java
 */
public class ApplyTable implements java.io.Serializable {

	private ApplyTableId id;
	private ControlTable controlTable;

	public ApplyTable() {
	}

	public ApplyTable(ApplyTableId id) {
		this.id = id;
	}

	public ApplyTable(ApplyTableId id, ControlTable controlTable) {
		this.id = id;
		this.controlTable = controlTable;
	}

	public ApplyTableId getId() {
		return this.id;
	}

	public void setId(ApplyTableId id) {
		this.id = id;
	}

	public ControlTable getControlTable() {
		return this.controlTable;
	}

	public void setControlTable(ControlTable controlTable) {
		this.controlTable = controlTable;
	}

}