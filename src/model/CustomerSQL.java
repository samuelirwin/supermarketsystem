/**
 * @author Eng Sun CHEW
 */
package model;


import java.util.ArrayList;

import model.Customer;
import model.HibernateDB_Connector;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 * Contains functions to access Customer related database using Hibernate session
 *
 */
public class CustomerSQL {

	
	/**
	 * Retrieve an customer object by given ID
	 * @param id - ID of the customer
	 * @return Customer - customer object retrieved from MSSQL
	 * 
	 */
	/** @Requires({id != null}) **/
	public Customer getCustByID(int id) {
		Customer cust = null;
		
		try{
			Session session = HibernateDB_Connector.GetOrCreateSession();
			Transaction h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			cust = (Customer) session.createQuery("FROM Customer WHERE Cust_ID = :id")
									.setParameter("id", id).uniqueResult();
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} 
		
		return cust;
	}
	
	/**
	 * Add customer to the database
	 * 
	 * @param firstName - First name of the customer
	 * @param lastName - Last name of the customer
	 * @param contact - Contact number of the customer
	 * @return boolean Indicate success or failure of the commit. 
	 * 
	 */
	/** @Requires(!{contact.matches(".*\\d.*")}) **/
	public Boolean AddCustomer(String firstName, String lastName, String contact, boolean status) {
		
		Transaction h_transaction = null;
		
		try{		
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			Customer cust = new Customer();
			cust.setFirstName(firstName);
			cust.setLastName(lastName);
			cust.setContact(contact);
			cust.setCustActive(status);
			session.save(cust);
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
			return true;
			
		} catch (HibernateException e) {
			HibernateDB_Connector.RollbackTransaction(h_transaction);
			e.printStackTrace(); 
			return false;
		} 
	}
	
	/**
	 * Update all fields in the customer object
	 * 
	 * @param firstName - First name of the customer
	 * @param lastName - Last name of the customer
	 * @param contact - New contact number of the customer
	 * @return boolean - Indicate success or failure of the commit. 
	 * 
	 */
	/** @Requires(!{contact.matches(".*\\d.*")}) **/
	public boolean UpdateCustomer(int id, String firstName, String lastName, String contact, boolean status) {
		Transaction h_transaction = null;
		
		try {
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			Customer cust = (Customer) session.createQuery("FROM Customer WHERE Cust_ID = :id")
					.setParameter("id", id).uniqueResult();
			
			cust.setFirstName(firstName);
			cust.setLastName(lastName);
			cust.setContact(contact);
			cust.setCustActive(status);
			session.update(cust);
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
			return true;
			
		} catch (HibernateException e) {
			HibernateDB_Connector.RollbackTransaction(h_transaction);
			return false;
		} 
	}
	
	/**
	 * Load all customers from Database into array list
	 * 
	 * @return an customer array list
	 */
	/** @Ensures({custList != null}) **/
	@SuppressWarnings("unchecked")
	public ArrayList<Customer> getAllCustomer() {
		Transaction h_transaction = null;
		ArrayList<Customer> custList = new ArrayList<Customer>();
		
		try{
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			custList = (ArrayList<Customer>) session.createQuery("FROM Customer ORDER BY Cust_ID").list();
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
	         
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} 
		
		return custList;
	}

}
