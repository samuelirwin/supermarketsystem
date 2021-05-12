/**
 * @author Eng Sun CHEW
 */
package model;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Contains functions to access Item related database using Hibernate session.
 *
 */

public class ItemSQL {

	/**
	 * Retrieve an item object by given ID
	 * 
	 * @param id - ID of the item
	 * @return Item - Item object retrieved from MSSQL
	 * 
	 */
	/** @Requires({id != null}) **/
	public Item getItemByID(String id) {
		Item item = null;
		
		try{
			Session session = HibernateDB_Connector.GetOrCreateSession();
			Transaction h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			item = (Item) session.createQuery("FROM Item WHERE Item_ID = :id")
									.setParameter("id", id).uniqueResult();
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} 
		
		return item;
	}
	
	/**
	 * Add item to the database
	 * 
	 * @param itemName - Name of item
	 * @param itemCategory - Category of item
	 * @param price - price of the item, with 2 decimal
	 * @param quantity - quantity of the item
	 * @return boolean - Indicate success or failure of the commit. 
	 * 
	 */
	/** @Requires({itemName.length < 20 && quantity >=0}) **/
	public Boolean AddItem(String itemName, String itemCategory, 
								double price, int quantity, boolean status) {
		Transaction h_transaction = null;
		
		try{
			System.out.println("Adding Item to database...");		
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			Item item = new Item();
			
			item.setItemName(itemName);
			item.setCategory(itemCategory);
			item.setPrice(price);
			item.setQuantity(quantity);
			item.setItemActive(status);
			
			session.save(item);
			HibernateDB_Connector.CommitTransaction(h_transaction);
			
			System.out.println("Item added to database");
			return true;
			
		} catch (HibernateException e) {
			HibernateDB_Connector.RollbackTransaction(h_transaction);
			e.printStackTrace(); 
			
			return false;
		} 
	}
	
	/**
	 * Update all fields in the item object
	 * 
	 * @param itemName - New name of item
	 * @param itemCategory - New category of item
	 * @param price - New price of the item, with 2 decimal
	 * @param quantity - New quantity of the item
	 * @return boolean - Indicate success or failure of the commit. 
	 * 
	 */
	/** @Requires({itemName.length < 20 && quantity >=0}) **/
	public boolean UpdateItem(String id, String itemName, String itemCategory, 
								double price, int quantity, boolean status) {
		Transaction h_transaction = null;
		
		try {
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			Item item = (Item) session.createQuery("FROM Item WHERE Item_ID = :id")
					.setParameter("id", id).uniqueResult();
			
			item.setItemName(itemName);
			item.setCategory(itemCategory);
			item.setPrice(price);
			item.setQuantity(quantity);
			item.setItemActive(status);
			session.update(item);
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
			return true;
			
		} catch (HibernateException e) {
			HibernateDB_Connector.RollbackTransaction(h_transaction);
			return false;
		} 	
	}
	
	/**
	 * Update quantity of an item in database
	 * 
	 * @param id - ID of the item
	 * @param quantity - New quantity of the item
	 * @return Item - item object with updated details
	 * 
	 */
	/** @Requires({quantity >=0}) **/
	public Item UpdateItemQuantity(int id, int quantity) {
		Transaction h_transaction = null;
		
		try {
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			Item item = (Item) session.createQuery("FROM Item WHERE Item_ID = :id")
									.setParameter("id", id).uniqueResult();
		
			item.setQuantity(quantity);
			session.update(item);
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
			return item;
			
		} catch (HibernateException e) {
			HibernateDB_Connector.RollbackTransaction(h_transaction);
			return null;
		} 	
	}
	
	/**
	 * Load all Item from Database into array list
	 * 
	 * @return an item array list
	 */
	/** @Ensures({itemList != null}) **/
	@SuppressWarnings("unchecked")
	public ArrayList<Item> getAllItem() {
		
		Transaction h_transaction = null;

		ArrayList<Item> itemList = new ArrayList<Item>();
		
		try{
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			itemList = (ArrayList<Item>) session.createQuery("FROM Item ORDER BY Item_ID").list();
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
	         
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} 
		
		return itemList;
	}
	
}
