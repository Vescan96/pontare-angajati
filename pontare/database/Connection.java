package database;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class Connection {

	private Session session;
	
	public Connection() {
		session = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
		session.beginTransaction();
		System.out.println("conexiune realizata");
	}
	
	public Session getSession() {
		return session;
	}
}

