package org.mgm.elector.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
private static SessionFactory sessionFactory;
private static Session session;
private static Transaction transaction;

public static SessionFactory getSessionFactory() {
	return sessionFactory;
}

public static SessionFactory createSessionFactory() {
    Configuration configuration=new Configuration().configure()
			.addAnnotatedClass(Address.class)
			.addAnnotatedClass(Designation.class)
			.addAnnotatedClass(Department.class)
			.addAnnotatedClass(Election.class)
			.addAnnotatedClass(Nominee.class)
			.addAnnotatedClass(Student.class);
   
	sessionFactory=configuration.buildSessionFactory();
	return sessionFactory;
}
public static Session createSession() {
	createSessionFactory();
	session=sessionFactory.openSession();
	return session ;
	
}

public static Session getSession() {
	return session;
}

public static Transaction getTransaction() {
	transaction=session.beginTransaction();
	return transaction;
}

}
