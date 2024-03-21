package org.mgm.elector.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class NomineeDaoImp implements NomineeDao {

	@Override
	public List<Nominee> getNominees(Designation designation) {
		List<Nominee> nominees=new ArrayList<Nominee>();
		Session session=HibernateUtils.createSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		CriteriaQuery<Nominee> criteria=session.getCriteriaBuilder().createQuery(Nominee.class);
		Root<Nominee> root= criteria.from(Nominee.class);
		criteria.where(session.getCriteriaBuilder().equal(root.get("designation"), designation));
		for (Nominee nominee : session.createQuery(criteria).getResultList()) {
			nominees.add(nominee);	
		}
		transaction.commit();
		session.close();
		return nominees;
	}

	@Override
	public void addNominee(Nominee nominee) {
		// TODO Auto-generated method stub
		Session session=HibernateUtils.createSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		session.merge(nominee);
		transaction.commit();
		session.close();
	}

	@Override
	public void deleteNominee(Nominee nominee) {
		// TODO Auto-generated method stub
		Session session=HibernateUtils.createSessionFactory().openSession();
		EntityManager entityManager=session.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		transaction.begin();	
		entityManager.remove(nominee);
		entityManager.flush();
	    entityManager.clear();
		transaction.commit();
		session.close();
	}

	@Override
	public void addVote(List<Nominee> nominees) {
		// TODO Auto-generated method stub
		Session session=HibernateUtils.createSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		for (Nominee nominee : nominees) {
			nominee.setVote(nominee.getVote()+1);
			session.merge(nominee);
		}
		transaction.commit();
		session.close();
	}

}
