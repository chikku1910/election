package org.mgm.elector.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;

public class ElectionDaoImp implements ElectionDao{

	@Override
	public void persistElection(Election election) {
		// TODO Auto-generated method stub
	Session session=HibernateUtils.createSessionFactory().openSession();
	Transaction transaction=session.beginTransaction();
	session.merge(election);
	transaction.commit();	
	session.close();
	}

	@Override
	public List<Election> getElections() {
		// TODO Auto-generated method stub
		List<Election> elections=new ArrayList<Election>();
		Session session=HibernateUtils.createSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		CriteriaQuery<Election> criteria=session.getCriteriaBuilder().createQuery(Election.class);
		criteria.from(Election.class);
		for (Election election : session.createQuery(criteria).getResultList()) {
			elections.add(election);	
		}
		transaction.commit();
		session.close();
		Collections.sort(elections);
		return elections;
	}
	@Override
	public void deleteElection(String id) {
		// TODO Auto-generated method stub
		Session session=HibernateUtils.createSessionFactory().openSession();
		EntityManager entityManager=session.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		transaction.begin();	
		entityManager.remove(entityManager.find(Election.class, id));
		entityManager.flush();
	    entityManager.clear();
		transaction.commit();
		session.close();	
	}

}
