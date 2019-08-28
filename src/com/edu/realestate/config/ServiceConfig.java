package com.edu.realestate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.edu.realestate")
@EnableTransactionManagement
public class ServiceConfig {

	@Bean
	@Autowired
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(ds);
		factory.setPackagesToScan("com.edu.realestate.model");
		factory.setHibernateProperties(hibernateProperties());
		return factory;
	}
	
	private Properties hibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.format_sql", "true");
		return props;
	}

	@Bean
	@Autowired
    public HibernateTransactionManager txManager(SessionFactory factory) {
		return new HibernateTransactionManager(factory);
    }

}
