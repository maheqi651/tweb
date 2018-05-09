package founder.model2;

// Generated 2018-4-27 18:39:40 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * ControlTree generated by hbm2java
 */
public class ControlTree implements java.io.Serializable {

	private int id;
	private String type;
	private String tablename;
	private String cnname;
	private String url;
	private String pass;
	private String driver;
	private String user;
	private String addtime;
	private String filedid;
	private String mainkey;
	private String maintime;
	private Integer searchratte;
	private String starttime;
	private Short isstart;
	private String createuserid;
	private String createuserxm;
	private String createtime;
	private String createorg;
	private String createorgname;
	private Set controlTreeFileds = new HashSet(0);

	public ControlTree() {
	}

	public ControlTree(int id) {
		this.id = id;
	}

	public ControlTree(int id, String type, String tablename, String cnname,
			String url, String pass, String driver, String user,
			String addtime, String filedid, String mainkey, String maintime,
			Integer searchratte, String starttime, Short isstart,
			String createuserid, String createuserxm, String createtime,
			String createorg, String createorgname, Set controlTreeFileds) {
		this.id = id;
		this.type = type;
		this.tablename = tablename;
		this.cnname = cnname;
		this.url = url;
		this.pass = pass;
		this.driver = driver;
		this.user = user;
		this.addtime = addtime;
		this.filedid = filedid;
		this.mainkey = mainkey;
		this.maintime = maintime;
		this.searchratte = searchratte;
		this.starttime = starttime;
		this.isstart = isstart;
		this.createuserid = createuserid;
		this.createuserxm = createuserxm;
		this.createtime = createtime;
		this.createorg = createorg;
		this.createorgname = createorgname;
		this.controlTreeFileds = controlTreeFileds;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getCnname() {
		return this.cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getFiledid() {
		return this.filedid;
	}

	public void setFiledid(String filedid) {
		this.filedid = filedid;
	}

	public String getMainkey() {
		return this.mainkey;
	}

	public void setMainkey(String mainkey) {
		this.mainkey = mainkey;
	}

	public String getMaintime() {
		return this.maintime;
	}

	public void setMaintime(String maintime) {
		this.maintime = maintime;
	}

	public Integer getSearchratte() {
		return this.searchratte;
	}

	public void setSearchratte(Integer searchratte) {
		this.searchratte = searchratte;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public Short getIsstart() {
		return this.isstart;
	}

	public void setIsstart(Short isstart) {
		this.isstart = isstart;
	}

	public String getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}

	public String getCreateuserxm() {
		return this.createuserxm;
	}

	public void setCreateuserxm(String createuserxm) {
		this.createuserxm = createuserxm;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreateorg() {
		return this.createorg;
	}

	public void setCreateorg(String createorg) {
		this.createorg = createorg;
	}

	public String getCreateorgname() {
		return this.createorgname;
	}

	public void setCreateorgname(String createorgname) {
		this.createorgname = createorgname;
	}

	public Set getControlTreeFileds() {
		return this.controlTreeFileds;
	}

	public void setControlTreeFileds(Set controlTreeFileds) {
		this.controlTreeFileds = controlTreeFileds;
	}

}
