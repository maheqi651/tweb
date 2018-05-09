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

@Entity(name="Message")
public class Message implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@ManyToOne
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="orderid") 
	Control_Table control_table;
	
	
    public Message(){
        super();
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    
    @Column(name="phone",length=20)
    private String phone;
    
    @Column(name="userid",length=50)
    private String userid;
    
    @Column(name="content",length=300)
    private String content;
    
    @Column(name="addtime",length=20)
    private String addtime;
    
    @Column(name="userxm",length=50)
    private String userxm;

	public final Control_Table getControl_Table() {
		return control_table;
	}

	public final void setControl_Table(Control_Table control_Table) {
		control_table = control_Table;
	}

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	 

	public final String getPhone() {
		return phone;
	}

	public final void setPhone(String phone) {
		this.phone = phone;
	}

	public final String getUserid() {
		return userid;
	}

	public final void setUserid(String userid) {
		this.userid = userid;
	}

	public final String getContent() {
		return content;
	}

	public final void setContent(String content) {
		this.content = content;
	}

	public final String getAddtime() {
		return addtime;
	}

	public final void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public final String getUserxm() {
		return userxm;
	}

	public final void setUserxm(String userxm) {
		this.userxm = userxm;
	}
    
	 
}
