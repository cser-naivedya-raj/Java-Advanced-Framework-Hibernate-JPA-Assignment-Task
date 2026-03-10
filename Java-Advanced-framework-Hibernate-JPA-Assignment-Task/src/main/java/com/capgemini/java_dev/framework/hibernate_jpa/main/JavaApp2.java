package com.capgemini.java_dev.framework.hibernate_jpa.main;

import java.util.Scanner;

import com.capgemini.java_dev.framework.hibernate_jpa.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JavaApp2 {
	public static void execute() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Employee");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
		Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Employee Management =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Read Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

            case 1:

                System.out.print("How many employees to add: ");
                int n = sc.nextInt();

                tx.begin();

                for (int i = 0; i < n; i++) {

                    Employee emp = new Employee();

                    System.out.print("Enter ID: ");
                    emp.setId(sc.nextInt());

                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    emp.setName(sc.nextLine());

                    System.out.print("Enter Email: ");
                    emp.setEmail(sc.nextLine());

                    System.out.print("Enter Salary: ");
                    emp.setSalary(sc.nextDouble());

                    em.persist(emp);
                }

                tx.commit();
                System.out.println("Employees Added Successfully!");
                break;

            case 2:

                System.out.print("Enter Employee ID: ");
                int id = sc.nextInt();

                Employee emp = em.find(Employee.class, id);

                if (emp != null) {
                    emp.display();
                } else {
                    System.out.println("Employee Not Found");
                }

                break;

            case 3:

                System.out.print("Enter Employee ID to Update: ");
                int uid = sc.nextInt();

                Employee updateEmp = em.find(Employee.class, uid);

                if (updateEmp != null) {

                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    updateEmp.setName(sc.nextLine());

                    System.out.print("Enter New Email: ");
                    updateEmp.setEmail(sc.nextLine());

                    System.out.print("Enter New Salary: ");
                    updateEmp.setSalary(sc.nextDouble());

                    tx.begin();
                    em.merge(updateEmp);
                    tx.commit();

                    System.out.println("Employee Updated!");
                } else {
                    System.out.println("Employee Not Found!");
                }

                break;

            case 4:

                System.out.print("Enter Employee ID to Delete: ");
                int did = sc.nextInt();

                Employee delEmp = em.find(Employee.class, did);

                if (delEmp != null) {

                    tx.begin();
                    em.remove(delEmp);
                    tx.commit();

                    System.out.println("Employee Deleted!");
                } else {
                    System.out.println("Employee Not Found!");
                }

                break;

            case 5:

                System.out.println("Exiting Program...");
                em.close();
                emf.close();
                System.exit(0);

            default:
                System.out.println("Invalid Choice!");

            }

        }
	}
}
