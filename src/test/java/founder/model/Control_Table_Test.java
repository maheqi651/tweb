package founder.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

public class Control_Table_Test {
       
	   @Test
	   public void insert(){
		   SessionFactory sf = new 
				   AnnotationConfiguration().configure().buildSessionFactory();
		   Session session=sf.openSession(); 
		   Transaction tx=session.beginTransaction();
		   Control_Table ct=new Control_Table();
		   ct.setOrderid("5555555");
		   ct.setControl_rate(10);
		   ct.setDesp("test");
		   ct.setGrade("10");
		   ct.setOrgname("00");
		   Notify nf=new Notify();
		   nf.setControl_Table(ct);
		   nf.setIsopen(0);
		   nf.setPhone("18614003021");
		   nf.setUserid("ma");
		   session.save(nf);
		   Notify nf1=new Notify();
		   nf1.setControl_Table(ct);
		   nf1.setIsopen(1);
		   nf1.setPhone("18611114003021");
		   nf1.setUserid("m1a");
		   session.save(nf1);
		   Message ms=new Message();
		   ms.setControl_Table(ct);
		   ms.setUserid("ma");
		   ms.setContent("11");
		   ms.setAddtime("2018");
		   ms.setPhone("18614003021");
		   session.save(ms);
		   tx.commit();
		   System.out.println("success");
	   }
	   @Test
	   public void delete(){
		   SessionFactory sf = new 
				   AnnotationConfiguration().configure().buildSessionFactory();
		   Session session=sf.openSession(); 
		   Transaction tx=session.beginTransaction();
		  /* Query query= session.createQuery("from control_table a where a.orderid= :orderid")
				   .setParameter("orderid", "6666666666");
		   List list=query.list();*/
		   
		   session.delete(session.get(Message.class, 17));
		   
		  /* for(Object ob:list)
		   {
			   session.delete(ob);
		   }*/
		   tx.commit();
		   System.out.println("delete success");
	   }
}
