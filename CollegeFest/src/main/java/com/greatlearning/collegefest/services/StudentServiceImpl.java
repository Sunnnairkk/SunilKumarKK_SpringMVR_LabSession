package com.greatlearning.collegefest.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.greatlearning.collegefest.entity.Student;

@Repository
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionFactory;

	private Session session;
	
	public StudentServiceImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		
		Transaction transaction =  session.beginTransaction();
		List<Student> students = session.createQuery("from Student").list();  //class
		transaction.commit();
		
		return students;
	}

	@Override
	public Student findById(int id) {
		// TODO Auto-generated method stub
		
		Transaction transaction =  session.beginTransaction();
		Student student = session.get(Student.class, id);
		transaction.commit();
		
		return student;
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(student);
		transaction.commit();
		
	}

	@Override
	public void deleteById(int theid) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		Student student=session.get(Student.class, theid);
		session.delete(student);
		transaction.commit();
	}

}
