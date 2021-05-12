/**
 * @author Eng Sun CHEW
 */
package model;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;

/**
 * Connection between Database and the application based on Hibernate ORM framework
 * http://www.hibernate.org/
 *
 */
public class HibernateDB_Connector {
	private static Configuration config;
	private static HibernateDB_Connector hibernateconnector;
	private static StandardServiceRegistry standardServiceReg;
	private static SessionFactory sessionFactory;
	
	
	public HibernateDB_Connector() {
		config = new Configuration().configure(); 
		standardServiceReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(standardServiceReg);
	}

	/**
	 * Create a new session or get a current opening session
	 * @return the current/new Session
	 */
	public static synchronized Session GetOrCreateSession() 
	{	
		if (hibernateconnector == null) {
			hibernateconnector = new HibernateDB_Connector();
		} 
		if (sessionFactory.getCurrentSession() == null) {
			System.out.println("Creating new session");
			return sessionFactory.openSession();
		} else {
			System.out.println("Getting a current session");
			return sessionFactory.getCurrentSession();
		}
	}

	
	/**
	 * Get a current Session
	 * @return an existing session
	 */
	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public static Transaction GetOrBeginTransaction(Session session) {
		if (session != null ) {
			if (session.getTransaction() != null && session.getTransaction().isActive()) {
				return session.getTransaction();
			}
			else {
				return session.beginTransaction();
			}
		}
		
		return null;
	}
	
	public static void CommitTransaction(Transaction transaction) {
		
		if(transaction != null )
		{
			if (transaction.isActive()) {
				transaction.commit();
			} else
				System.out.println("Transaction failed");
		}
		
	}
	public static void RollbackTransaction(Transaction transaction) {
		
		if(transaction != null )
		{
			if (transaction.isActive()) {
				transaction.rollback();
			} 
			else {
				System.out.println("Rollback failed.");
			}
		}
	}
	
}
