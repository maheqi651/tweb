package founder.model;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.mapping.Set;
import org.junit.Test;

public class Control_Tree_Test {
	  @Test
	  public void testInsertControl_Tree() throws Exception{
		   SessionFactory sf = new 
				   AnnotationConfiguration().configure().buildSessionFactory();
		   Session session=sf.openSession(); 
		   Transaction tx=session.beginTransaction();
		   Control_Tree ct=new Control_Tree();
		   ct.setAddtime("2018");
		   ct.setCnname("cs");
		   ct.setCreateorg("00");
		   ct.setCreateorgname("test");
		   ct.setCreatetime("2018");
		   ct.setCreateuserxm("user");
		   ct.setDriver("ss");
		   ct.setFiledid("tt");
		   ct.setIsstart(0);
		   ct.setMainkey("t1");
		   ct.setMaintime("2018");
		   ct.setPass("123");
		   ct.setTablename("table");
		   ct.setUserid("ma");
		   Control_Tree_Filed cf=new Control_Tree_Filed();
		   cf.setChname("t2");
		   cf.setName("ch2");
		   cf.setControl_tree(ct);
		   session.save(cf);
		   tx.commit();
		   System.out.println("success");
	  }
	  @Test 
	  public void search(){
		     SessionFactory sf = new 
				   AnnotationConfiguration().configure().buildSessionFactory();
		     Session session =sf.openSession();
		     Control_Tree ct=(Control_Tree)session.get(Control_Tree.class, 1);
		     System.out.println(ct.getCnname());
		     java.util.Set<Control_Tree_Filed> set=ct.getControl_Tree_Fileds();
		     Iterator<Control_Tree_Filed> item=set.iterator();
		     while(item.hasNext())
		     {
		    	 System.out.println(item.next().getName());
		     }
		    
	  }
}
