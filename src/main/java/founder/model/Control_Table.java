package founder.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="control_table")
public class Control_Table implements Serializable{
	
	
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="control_table")   
	private Set<Control_Ob> Control_Ob; 
	
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="control_table")   
	private Set<Notify> Notify; 
	

	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="control_table")   
	private Set<Apply_Table> Apply_Table;  
	

	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="control_table")   
	private Set<Message> Message;  
	
	@Column(name="startdate",length=20)
	private String startdate;
	@Column(name="desp",length=200)
	private String desp;
	@Column(name="orgcode",length=50)
	private String orgcode;
	@Column(name="orgname",length=100)
	private String orgname;
	@Column(name="type",length=50)
	private String type;
	
	@Id
	@Column(name="orderid",length=64)
	private String orderid;
	@Column(name="grade",length=10)
	private String grade;
	@Column(name="userxm",length=20)
	private String userxm;
	@Column(name="control_rate")
	private Integer control_rate;
	@Column(name="userid",length=50)
	private String userid;
	@Column(name="notifycount")
	private Integer notifycount;
	@Column(name="catchcount")
	private Integer catchcount;
	@Column(name="dealcount")
	private Integer dealcount;
	@Column(name="status")
	private Integer status;
	
	public final Set<Control_Ob> getControl_Ob() {
		return Control_Ob;
	}
	public final void setControl_Ob(Set<Control_Ob> control_Ob) {
		Control_Ob = control_Ob;
	}
	public final Set<Notify> getNotify() {
		return Notify;
	}
	public final void setNotify(Set<Notify> notify) {
		Notify = notify;
	}
	public final Set<Apply_Table> getApply_Table() {
		return Apply_Table;
	}
	public final void setApply_Table(Set<Apply_Table> apply_Table) {
		Apply_Table = apply_Table;
	}
	public final Set<Message> getMessage() {
		return Message;
	}
	public final void setMessage(Set<Message> message) {
		Message = message;
	}
	public final String getStartdate() {
		return startdate;
	}
	public final void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public final String getDesp() {
		return desp;
	}
	public final void setDesp(String desp) {
		this.desp = desp;
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
	public final String getType() {
		return type;
	}
	public final void setType(String type) {
		this.type = type;
	}
	public final String getOrderid() {
		return orderid;
	}
	public final void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public final String getGrade() {
		return grade;
	}
	public final void setGrade(String grade) {
		this.grade = grade;
	}
	public final String getUserxm() {
		return userxm;
	}
	public final void setUserxm(String userxm) {
		this.userxm = userxm;
	}
	public final Integer getControl_rate() {
		return control_rate;
	}
	public final void setControl_rate(Integer control_rate) {
		this.control_rate = control_rate;
	}
	public final String getUserid() {
		return userid;
	}
	public final void setUserid(String userid) {
		this.userid = userid;
	}
	public final Integer getNotifycount() {
		return notifycount;
	}
	public final void setNotifycount(Integer notifycount) {
		this.notifycount = notifycount;
	}
	public final Integer getCatchcount() {
		return catchcount;
	}
	public final void setCatchcount(Integer catchcount) {
		this.catchcount = catchcount;
	}
	public final Integer getDealcount() {
		return dealcount;
	}
	public final void setDealcount(Integer dealcount) {
		this.dealcount = dealcount;
	}
	public final Integer getStatus() {
		return status;
	}
	public final void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
