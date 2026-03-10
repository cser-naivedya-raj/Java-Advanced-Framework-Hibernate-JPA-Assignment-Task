package com.capgemini.java_dev.framework.hibernate_jpa.main;

import com.capgemini.java_dev.framework.hibernate_jpa.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JavaApp {
	public static void execute() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Student");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		
		Student student1 = new Student();
		student1.setId(1);
		student1.setName("Raja");
		student1.setEmailId("raja@gmail.com");
		student1.setContactNumber(9876543210L);
		
		Student student2 = new Student();
		student2.setId(2);
		student2.setName("Rani");
		student2.setEmailId("rani@gmail.com");
		student2.setContactNumber(9876543211L);
		
		Student student3 = new Student();
		student3.setId(3);
		student3.setName("Reena");
		student3.setEmailId("reena@gmail.com");
		student3.setContactNumber(9876543212L);
		
//		entityManager.persist(student3);

		
		
		Student student = entityManager.find(Student.class, 1);
		if(student != null) {
			System.out.println(student);
			student.setEmailId("king@gmail.com");
			student.setContactNumber(7894561230L);
			entityManager.merge(student);
			System.out.println("Student Data Updated!");
		}
		
		else {
			System.out.println("Student Data Not found!");
		}
		
		Student studentDel = entityManager.find(Student.class, 3);
		
		entityManager.remove(studentDel);
		
		
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
		
	
		System.out.println("Hello");
	}
}
