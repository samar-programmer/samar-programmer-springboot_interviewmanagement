package com.revature.interviewmanagement.db;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	public static SessionFactory getSessionFactory() {
		    

    	Properties dbConnectionProperties = new Properties();
        try {
			dbConnectionProperties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        SessionFactory sessionFactory = new Configuration().mergeProperties(dbConnectionProperties).buildSessionFactory();
         
        return sessionFactory;
		

	}
			
		
	

}

