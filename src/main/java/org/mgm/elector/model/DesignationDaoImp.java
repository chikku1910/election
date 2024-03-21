package org.mgm.elector.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class DesignationDaoImp implements DesignationDAO {

	@Override
	public void adddesignation(Designation designation) {
		// TODO Auto-generated method stub
		Session session=HibernateUtils.createSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		session.merge(designation);
		transaction.commit();	
		session.close();
	}

	@Override
	public List<Designation> getAllDesignation(Election election) {
		List<Designation> designations=new ArrayList<Designation>();
		Session session=HibernateUtils.createSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		CriteriaQuery<Designation> criteria=session.getCriteriaBuilder().createQuery(Designation.class);
		Root<Designation> root= criteria.from(Designation.class);
		criteria.where(session.getCriteriaBuilder().equal(root.get("election"), election));
		for (Designation designation : session.createQuery(criteria).getResultList()) {
			designations.add(designation);	
		}
		transaction.commit();
		session.close();
		Collections.sort(designations);
		return designations;
	}

	@Override
	public void deleteDesignation(Designation designation) {
		// TODO Auto-generated method stub
		Session session=HibernateUtils.createSessionFactory().openSession();
		EntityManager entityManager=session.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		transaction.begin();	
		entityManager.remove(designation);
		entityManager.flush();
	    entityManager.clear();
		transaction.commit();
		session.close();	
	}

}
