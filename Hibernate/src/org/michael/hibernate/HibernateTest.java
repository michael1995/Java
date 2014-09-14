package org.michael.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.michael.dto.Address;
import org.javabrains.michael.dto.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {

		UserDetails user = new UserDetails();

		user.setUserName("third User");
		
		Address addr = new Address();
		addr.setStreet("daydream");
		addr.setCity("brisbane");
		addr.setState("QLD");
		addr.setPinCode("4113");
	
		user.setAddress(addr);
		
		
		UserDetails user2 = new UserDetails();

		user2.setUserName("fourth User");
		
		Address addr2 = new Address();
		addr2.setStreet("daydream");
		addr2.setCity("brisbane");
		addr2.setState("QLD");
		addr2.setPinCode("4113");
	
		user2.setAddress(addr2);
		
		SessionFactory sessionFacotry = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFacotry.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(user2);
		session.getTransaction().commit();
		session.close();

		/*
		 *  *to display the code*
		 *  user = null;
		 * 
		 * session = sessionFacotry.openSession(); session.beginTransaction();
		 * user = (UserDetails) session.get(UserDetails.class, user);
		 * 
		 * System.out.print("User Name retrived from " + user.getUserName());
		 */

	}
}
