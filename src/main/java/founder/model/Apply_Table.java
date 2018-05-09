package founder.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="apply_table")
public class Apply_Table implements Serializable{
	
	@ManyToOne
	@Cascade(value={CascadeType.SAVE_UPDATE})       
	@JoinColumn(name="lockorderid") 
	Control_Table control_table;
	
	@ManyToOne
	@Cascade(value={CascadeType.SAVE_UPDATE})      
	@JoinColumn(name="zjhm") 
	Control_Ob Control_Ob;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="type",length=30)
	private String type;
	@Column(name="desp",length=300)
	private String desp;
	@Column(name="userid",length=50)
	private String userid;
	@Column(name="userxm",length=50)
	private String userxm;
	@Column(name="orgcode",length=100)
	private String orgcode;
	@Column(name="orgname",length=100)
	private String orgname;
	@Column(name="applytime",length=20)
	private String applytime;
	@Column(name="status")
	private Integer status;
	@Column(name="dealxm",length=50)
	private String dealxm;
	@Column(name="dealid",length=50)
	private String dealid;
	@Column(name="dealorgcode",length=100)
	private String dealorgcode;
	@Column(name="dealorgname",length=100)
	private String dealorgname;
	@Column(name="dealinfo",length=300)
	private String dealinfo;
	 
	@Column(name="locktime",length=20)
	private String locktime;
	
	public final long getId() {
		return id;
	}
	public final void setId(long id) {
		this.id = id;
	}
	public final Control_Table getControl_Table() {
		return control_table;
	}
	public final void setControl_Table(Control_Table control_Table) {
		control_table = control_Table;
	}
	public final Control_Ob getControl_Ob() {
		return Control_Ob;
	}
	public final void setControl_Ob(Control_Ob control_Ob) {
		Control_Ob = control_Ob;
	}
	public final String getType() {
		return type;
	}
	public final void setType(String type) {
		this.type = type;
	}
	public final String getDesp() {
		return desp;
	}
	public final void setDesp(String desp) {
		this.desp = desp;
	}
	public final String getUserid() {
		return userid;
	}
	public final void setUserid(String userid) {
		this.userid = userid;
	}
	public final String getUserxm() {
		return userxm;
	}
	public final void setUserxm(String userxm) {
		this.userxm = userxm;
	}
	public final String getOrgcode() {
		return orgcode;
	}
	public final void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public final String getOrgname() {
		return orgname;
	}
	public final void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public final String getApplytime() {
		return applytime;
	}
	public final void setApplytime(String applytime) {
		this.applytime = applytime;
	}
	public final Integer getStatus() {
		return status;
	}
	public final void setStatus(Integer status) {
		this.status = status;
	}
	public final String getDealxm() {
		return dealxm;
	}
	public final void setDealxm(String dealxm) {
		this.dealxm = dealxm;
	}
	public final String getDealid() {
		return dealid;
	}
	public final void setDealid(String dealid) {
		this.dealid = dealid;
	}
	public final String getDealorgcode() {
		return dealorgcode;
	}
	public final void setDealorgcode(String dealorgcode) {
		this.dealorgcode = dealorgcode;
	}
	public final String getDealorgname() {
		return dealorgname;
	}
	public final void setDealorgname(String dealorgname) {
		this.dealorgname = dealorgname;
	}
	public final String getDealinfo() {
		return dealinfo;
	}
	public final void setDealinfo(String dealinfo) {
		this.dealinfo = dealinfo;
	}
	 
	public final String getLocktime() {
		return locktime;
	}
	public final void setLocktime(String locktime) {
		this.locktime = locktime;
	}
	
	
}
