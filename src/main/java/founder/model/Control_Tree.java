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
@Entity(name="control_tree")
public class Control_Tree implements Serializable{
	
	
	   @OneToMany(cascade=CascadeType.REMOVE,mappedBy="control_tree")   
	   private Set<Control_Tree_Filed> Control_Tree_Fileds; 
	    public Control_Tree(){
		        super();
		    }
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(name="id")
	    private Integer id;
	    @Column(name="createuserid",length=100)
	    private String createuserid;
	    @Column(name="createuserxm",length=100)
	    private String createuserxm;
	    @Column(name="createtime",length=20)
	    private String createtime;
	    @Column(name="createorg",length=100)
	    private String createorg;
	    @Column(name="createorgname",length=100)
	    private String createorgname;
	    @Column(name="isstart")
	    private Integer isstart;
	    @Column(name="starttime",length=20)
	    private String starttime;
	    @Column(name="tablename",length=50)
	    private String tablename;
	    @Column(name="retype",length=20)
	    private String type;
	    @Column(name="userid",length=50)
	    private String userid;
	    @Column(name="cnname",length=100)
	    private String cnname;
	    @Column(name="addtime",length=20)
	    private String addtime;
	    
	    @Column(name="url",length=100)
	    private String url;
	    @Column(name="driver",length=100)
	    private String driver;
	    @Column(name="pass",length=50)
	    private String pass;
	    
	    @Column(name="filedid",length=200)
	    private String filedid;
	    @Column(name="mainkey",length=100)
	    private String mainkey;
	    @Column(name="maintime",length=20)
	    private String maintime;
	    @Column(name="searchratte")
	    private Integer searchratte;
		public final Integer getId() {
			return id;
		}
		public final void setId(Integer id) {
			this.id = id;
		}
		public final String getCreateuserid() {
			return createuserid;
		}
		public final void setCreateuserid(String createuserid) {
			this.createuserid = createuserid;
		}
		public final String getCreateuserxm() {
			return createuserxm;
		}
		public final void setCreateuserxm(String createuserxm) {
			this.createuserxm = createuserxm;
		}
		public final String getCreatetime() {
			return createtime;
		}
		public final void setCreatetime(String createtime) {
			this.createtime = createtime;
		}
		public final String getCreateorg() {
			return createorg;
		}
		public final void setCreateorg(String createorg) {
			this.createorg = createorg;
		}
		public final String getCreateorgname() {
			return createorgname;
		}
		public final void setCreateorgname(String createorgname) {
			this.createorgname = createorgname;
		}
		public final Integer getIsstart() {
			return isstart;
		}
		public final void setIsstart(Integer isstart) {
			this.isstart = isstart;
		}
		public final String getStarttime() {
			return starttime;
		}
		public final void setStarttime(String starttime) {
			this.starttime = starttime;
		}
		public final String getTablename() {
			return tablename;
		}
		public final void setTablename(String tablename) {
			this.tablename = tablename;
		}
		public final String getType() {
			return type;
		}
		public final void setType(String type) {
			this.type = type;
		}
		public final String getUserid() {
			return userid;
		}
		public final void setUserid(String userid) {
			this.userid = userid;
		}
		public final String getCnname() {
			return cnname;
		}
		public final void setCnname(String cnname) {
			this.cnname = cnname;
		}
		public final String getAddtime() {
			return addtime;
		}
		public final void setAddtime(String addtime) {
			this.addtime = addtime;
		}
		 
		public final String getUrl() {
			return url;
		}
		public final void setUrl(String url) {
			this.url = url;
		}
		public final String getDriver() {
			return driver;
		}
		public final void setDriver(String driver) {
			this.driver = driver;
		}
		public final String getPass() {
			return pass;
		}
		public final void setPass(String pass) {
			this.pass = pass;
		}
		public final String getFiledid() {
			return filedid;
		}
		public final void setFiledid(String filedid) {
			this.filedid = filedid;
		}
		public final String getMainkey() {
			return mainkey;
		}
		public final void setMainkey(String mainkey) {
			this.mainkey = mainkey;
		}
		public final String getMaintime() {
			return maintime;
		}
		public final void setMaintime(String maintime) {
			this.maintime = maintime;
		}
		public final Integer getSearchratte() {
			return searchratte;
		}
		public final void setSearchratte(Integer searchratte) {
			this.searchratte = searchratte;
		}
		public final Set<Control_Tree_Filed> getControl_Tree_Fileds() {
			return Control_Tree_Fileds;
		}
		public final void setControl_Tree_Fileds(
				Set<Control_Tree_Filed> control_Tree_Fileds) {
			Control_Tree_Fileds = control_Tree_Fileds;
		}
	    
	    
	    


}
