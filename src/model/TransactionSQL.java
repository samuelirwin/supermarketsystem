/**
 * @author Eng Sun CHEW
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Contains functions to access Transaction related database using Hibernate session
 *
 */

public class TransactionSQL {

	/**
	 * Retrieve an transaction object by given ID
	 * 
	 * @param id - ID of the transaction
	 * @return Transaction - Transaction object retrieved from MSSQL
	 * 
	 */
	/** @Requires({id != null}) **/
	public model.Transaction getTransactionByID(String id) {
		model.Transaction transaction = null;
		
		try{
			Session session = HibernateDB_Connector.GetOrCreateSession();
			Transaction h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			transaction = (model.Transaction) session.createQuery("FROM Transaction WHERE Trans_ID = :id")
									.setParameter("id", id).uniqueResult();
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} 
		
		return transaction;
	}
	
	/**
	 * Add transaction to the database
	 * 
	 * @param t_custID - Customer that perform the transaction
	 * @param t_empID - Employee that assist the transaction
	 * @param amount - Transaction amount
	 * @return boolean Indicate success or failure of the commit. 
	 * 
	 */
	/** @Requires({amount >0}) **/
	public Boolean AddTransaction(int t_custID, int t_empID, 
								double amount) {
		@SuppressWarnings("unused")
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Transaction h_transaction = null;
		
		try{	
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			model.Transaction transaction = new model.Transaction();
			
			transaction.setCustID(t_custID);
			transaction.setEmpID(t_empID);
			transaction.setTransAmt(amount);
			transaction.setTransDate(date);
			session.save(transaction);
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
			return true;
			
		} catch (HibernateException e) {
			HibernateDB_Connector.RollbackTransaction(h_transaction);
			e.printStackTrace(); 
			
			return false;
		} 
	}
	
	
	/**
	 * Load all Item from Transaction into array list
	 * 
	 * @return an transaction array list
	 */
	/** @Ensures({transList != null}) **/
	@SuppressWarnings("unchecked")
	public ArrayList<model.Transaction> getAllTransaction() {
		
		Transaction h_transaction = null;

		ArrayList<model.Transaction> transList = new ArrayList<model.Transaction>();
		
		try{
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			transList = (ArrayList<model.Transaction>) session.createQuery("FROM Transaction ORDER BY Trans_ID").list();
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
	         
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} 
		
		return transList;
	}
	
}
