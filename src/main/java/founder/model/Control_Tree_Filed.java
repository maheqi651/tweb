package founder.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity(name="control_tree_filed")
public class Control_Tree_Filed implements Serializable{
 
	
	@ManyToOne(cascade={CascadeType.ALL})        
	@JoinColumn(name="t_id") 
	Control_Tree control_tree;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="chname")
	private String chname;
	 
	public final long getId() {
		return id;
	}
	public final void setId(long id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getChname() {
		return chname;
	}
	public final void setChname(String chname) {
		this.chname = chname;
	}
	public final Control_Tree getControl_tree() {
		return control_tree;
	}
	public final void setControl_tree(Control_Tree control_tree) {
		this.control_tree = control_tree;
	}
	
	
	
}
