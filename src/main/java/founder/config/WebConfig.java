package founder.config;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("founder.*")
 
//启动自动组件扫描
public class WebConfig extends WebMvcConfigurerAdapter {
	private Properties hibernateProperties;  
	
	@Bean  
    public SessionFactory sessionFactory() {  
        LocalSessionFactoryBuilder builder =  
                new LocalSessionFactoryBuilder(dataSource());  
        builder.scanPackages("founder.model")  
                .addProperties(getHibernateProperties());  
  
        return builder.buildSessionFactory();  
    }  
    @Bean(name="dataSource")  
    public BasicDataSource dataSource() {  
        BasicDataSource ds = new BasicDataSource();  
        ds.setDriverClassName("com.mysql.jdbc.Driver");  
        ds.setUrl("jdbc:mysql://localhost:3306/controlsys");  
        ds.setUsername("root");  
        ds.setPassword("root");  
        return ds;  
    }  
    @Bean  
    @Autowired
    public HibernateTransactionManager txManager() {  
        return new HibernateTransactionManager(sessionFactory());  
    }  
    public Properties getHibernateProperties() {  
        Properties prop = new Properties();  
        prop.put("hibernate.format_sql", "true");  
        prop.put("hibernate.show_sql", "true");  
        //自动创建table,测试专用  
        //prop.put("hibernate.hbm2ddl.auto", "create");  
        prop.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");  
        return prop;  
    }  
	
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver res=new InternalResourceViewResolver();
		res.setPrefix("/WEB-INF/");
		res.setSuffix(".jsp");
		res.setExposeContextBeansAsAttributes(true);
		return res;
	}
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
	}
       
}
