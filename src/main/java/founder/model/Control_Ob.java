package founder.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
@Entity(name="control_ob")
public class Control_Ob implements Serializable{

	
	@ManyToOne  
	@Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})  
	@JoinColumn(name="orderid") 
	Control_Table control_table;
	
	@OneToMany(cascade={CascadeType.REMOVE},mappedBy="Control_Ob")   
	private Set<Apply_Table> Apply_Table;  
	@Column(name="zkid",length=10)
	private String zkid;
	@Column(name="zkzjhm",length=50)
	private String zkzjhm;
	@Column(name="zjhm",length=50)
	private String zjhm;
	@Column(name="person_type",length=50)
	private String person_type;
	
	 
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="islock")
	private Integer islock;
	@Column(name="iszk")
	private Integer iszk;
	
	public final Set<Apply_Table> getApply_Table() {
		return Apply_Table;
	}
	public final void setApply_Table(Set<Apply_Table> apply_Table) {
		Apply_Table = apply_Table;
	}
	public final Control_Table getControl_Table() {
		return control_table;
	}
	public final void setControl_Table(Control_Table control_Table) {
		control_table = control_Table;
	}
	public final String getZkid() {
		return zkid;
	}
	public final void setZkid(String zkid) {
		this.zkid = zkid;
	}
	public final String getZkzjhm() {
		return zkzjhm;
	}
	public final void setZkzjhm(String zkzjhm) {
		this.zkzjhm = zkzjhm;
	}
	public final String getZjhm() {
		return zjhm;
	}
	public final void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public final String getPerson_type() {
		return person_type;
	}
	public final void setPerson_type(String person_type) {
		this.person_type = person_type;
	}
	 
	public final long getId() {
		return id;
	}
	public final void setId(long id) {
		this.id = id;
	}
	public final Integer getIslock() {
		return islock;
	}
	public final void setIslock(Integer islock) {
		this.islock = islock;
	}
	public final Integer getIszk() {
		return iszk;
	}
	public final void setIszk(Integer iszk) {
		this.iszk = iszk;
	}
	
}
