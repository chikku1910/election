package org.mgm.elector.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;

public class StudentDAOImp implements StudentDAO {

	public StudentDAOImp() {
		
	}
	@Override
	public List<Department> getDepartments() {
		// TODO second level of cache
		List<Department> departments=new ArrayList<Department>();
		Session session=HibernateUtils.createSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		CriteriaQuery<Department> criteria=session.getCriteriaBuilder().createQuery(Department.class);
		criteria.from(Department.class);
		for (Department department : session.createQuery(criteria).getResultList()) {
			departments.add(department);	
		}
		transaction.commit();
		session.close();
		return departments;
	}

	@Override
	public void persistStudent(Student student) {
		// TODO Auto-generated method stub
		Session session=HibernateUtils.createSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		session.merge(student);
		transaction.commit();
		session.close();
	}

	@Override
	public Set<Student> getAllStudents() {
		Set<Student> students=new HashSet<Student>();
		Session session=HibernateUtils.createSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		CriteriaQuery<Student> criteria=session.getCriteriaBuilder().createQuery(Student.class);
		criteria.from(Student.class);
		for (Student student : session.createQuery(criteria).getResultList()) {
			students.add(student);	
		}
		transaction.commit();
		session.close();
		return students;
	}
	@Override
	public void deleteStudent(String id) {
		Session session=HibernateUtils.createSessionFactory().openSession();
		EntityManager entityManager=session.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		transaction.begin();	
		entityManager.remove(entityManager.find(Student.class, id));
		entityManager.flush();
	    entityManager.clear();
		transaction.commit();
		session.close();
	}

}
