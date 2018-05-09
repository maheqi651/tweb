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

@Entity(name="Notify")
public class Notify implements Serializable {
    
    private static final long serialVersionUID = 1L;


	
	@ManyToOne
	@Cascade(value={CascadeType.SAVE_UPDATE})       
	@JoinColumn(name="orderid") 
	Control_Table control_table;
    
    public Notify(){
        super();
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
     
    
    @Column(name="phone",length=20)
    private String phone;
    
    @Column(name="isopen")
    private Integer isopen;

    @Column(name="userid",length=50)
    private String userid;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}
	public final Control_Table getControl_Table() {
		return control_table;
	}

	public final void setControl_Table(Control_Table control_Table) {
		control_table = control_Table;
	}

	public final String getPhone() {
		return phone;
	}

	public final void setPhone(String phone) {
		this.phone = phone;
	}

	public final Integer getIsopen() {
		return isopen;
	}

	public final void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}

	public final String getUserid() {
		return userid;
	}

	public final void setUserid(String userid) {
		this.userid = userid;
	}
 
       

}