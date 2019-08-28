package com.edu.realestate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDaoHib {

//	private static StandardServiceRegistry registry;

    @Autowired
    private SessionFactory factory;
    
    protected Session getSession() {
    	Session session = factory.getCurrentSession();
    	return session;
    }

//	OLD CODE FROM NATIVE HIBERNATE, NOW WITH SPRING ABOVE
//    public static SessionFactory getSessionFactory() {
//        if (factory == null) {
//            try {
//                // Create registry
//                registry = new StandardServiceRegistryBuilder().configure().build();
//
//                // Create MetadataSources
//                MetadataSources sources = new MetadataSources(registry);
//
//                // Create Metadata
//                Metadata metadata = sources.getMetadataBuilder().build();
//
//                // Create SessionFactory
//                factory = metadata.getSessionFactoryBuilder().build();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                if (registry != null) {
//                    StandardServiceRegistryBuilder.destroy(registry);
//                }
//            }
//        }
//        return factory;
//    }
//
//    public static void shutdown() {
//        if (registry != null) {
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
//    }
}