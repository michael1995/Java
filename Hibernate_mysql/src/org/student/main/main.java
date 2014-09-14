package org.student.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.student.msq.layer.*;

public class main {

	public static void main(String[] args) {
		
		
		SessionFactory sessionFacotry = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFacotry.openSession();
		session.beginTransaction();
		
	
	    student stu = (student) session.get(student.class,6);
	    System.out.println("Student is " + stu.getID());
		
		session.getTransaction().commit();
		session.close();

	}

}
/**for (int i = 0; i < 10; i++){
	student student = new student();
	student.setID(1 + i);
	student.setStudentName("Name");
	}
*/