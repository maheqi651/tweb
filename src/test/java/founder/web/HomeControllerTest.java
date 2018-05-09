package founder.web;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.AccessType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import founder.model.Control_Tree;
import founder.model.Control_Tree_Filed;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HomeControllerTest {
	   
	  @Test
       public void testPageHome() throws Exception{
    	      HomeController contr=new HomeController();
    	     
    	      MockMvc mock= standaloneSetup(contr).build();
    	      mock.perform(get("/welcome/222")).andExpect(view().name("home"));
    	      
       }
	 
	  
}
