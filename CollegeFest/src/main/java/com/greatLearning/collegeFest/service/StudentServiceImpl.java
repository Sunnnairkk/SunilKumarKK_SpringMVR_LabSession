package com.greatLearning.collegeFest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.greatLearning.collegeFest.entity.Student;



@Repository
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionfactory;
	
	private Session session;
	
	
	public StudentServiceImpl(SessionFactory sessionfactory) {
		super();
		this.sessionfactory = sessionfactory;
		try {
		session=sessionfactory.getCurrentSession();
		}
		catch (HibernateException e) {
			session = sessionfactory.openSession();
		}
		
	}

	@Transactional
	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		
		Transaction transaction = session.beginTransaction();
		List<Student> students = session.createQuery("from student").list();
		transaction.commit();
		
		return students;
	}
	
	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(student);
		transaction.commit();
			
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class,id);
		session.delete(student);
		transaction.commit();
		
	}

	@Override
	public Student findById(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		transaction.commit();
		return student;
	}
	
}
