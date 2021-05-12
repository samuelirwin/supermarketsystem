/**
 * @author Eng Sun CHEW
 */
package model;


import java.util.ArrayList;


import model.Employee;
import model.HibernateDB_Connector;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 * Contains functions to access Transaction related database using Hibernate session
 *
 */
public class EmployeeSQL {

	/**
	 * Authenticate user with given id and password by retrieving the employee and cross checking with given password
	 * 
	 * @param id - inputed ID
	 * @param password - inputed password
	 * @param session - current hibernate session
	 * @return Employee - Returned employee object that matched the credential in database.
	 */
	/** @Requires({session != null}) **/
	public Employee VerifyLogin(String id, String password, Session session) {
		Employee emp = null ;

		try{
			Transaction h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			 
			emp = (Employee) session.createQuery("FROM Employee WHERE Emp_ID = :ID")
									.setParameter("ID", id).uniqueResult();
			HibernateDB_Connector.CommitTransaction(h_transaction);
			
			if(emp == null){
				System.out.println("Employee not found in database");
				return null;
			}
			
			if(!emp.getPassword().equals(password)) {
				System.out.println("Credential not match");
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
	
		return emp;
	}
	
	/**
	 * Retrieve an employee object by given ID
	 * 
	 * @param id - ID of the employee
	 * @return Employee - employee object retrieved from MSSQL
	 * 
	 */
	/** @Requires({id != null}) **/
	public Employee getEmpByID(String id) {
		Employee emp = null;
		
		try{
			Session session = HibernateDB_Connector.GetOrCreateSession();
			Transaction h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			emp = (Employee) session.createQuery("FROM Employee WHERE Emp_ID = :id")
									.setParameter("id", id).uniqueResult();
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} 
		
		return emp;
	}
	
	/**
	 * Add employee to the database
	 * 
	 * @param firstName First name of the employee
	 * @param lastName Last name of the employee
	 * @param empRole Staff or Manager
	 * @param password Password for the employee login
	 * @param Salary Salary for the employee
	 * @param active Current status of the employee active/not active
	 * @return boolean Indicate success or failure of the commit. 
	 * 
	 */
	/** @Requires({empRole == Staff || empRole == Manager}) **/
	public Boolean AddEmployee(String firstName, String lastName, String empRole, 
								String password, double Salary, Boolean active) {
		
		Transaction h_transaction = null;
		
		try{
			System.out.println("Adding Employee to database...");		
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			Employee emp = new Employee();
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			emp.setEmpRole(empRole);
			emp.setPassword(password);
			emp.setSalary(Salary);
			emp.setEmpActive(active);
			
			session.save(emp);
			HibernateDB_Connector.CommitTransaction(h_transaction);
			
			System.out.println("Employee added to database");
			return true;
			
		} catch (HibernateException e) {
			HibernateDB_Connector.RollbackTransaction(h_transaction);
			e.printStackTrace(); 
			
			return false;
		} 
	}
	
	/**
	 * Update all fields in the employee object
	 * 
	 * @param firstName - First name of the employee
	 * @param lastName - Last name of the employee
	 * @param empRole - New role for the Employee
	 * @param password - New password for the employee login
	 * @param Salary - New salary for the employee
	 * @param active - New status of the employee active/not active
	 * @return boolean - Indicate success or failure of the commit. 
	 * 
	 */
	/** @Requires({empRole == Staff || empRole == Manager}) **/
	public boolean UpdateEmployee(String id, String firstName, String lastName, String empRole, 
										String password, double Salary, Boolean active) {
		Transaction h_transaction = null;
		
		try {
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			Employee emp = (Employee) session.createQuery("FROM Employee WHERE Emp_ID = :id")
					.setParameter("id", id).uniqueResult();
			
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			emp.setEmpRole(empRole);
			emp.setPassword(password);
			emp.setSalary(Salary);
			emp.setEmpActive(active);
			session.update(emp);

			HibernateDB_Connector.CommitTransaction(h_transaction);
			return true;
		} catch (HibernateException e) {
			HibernateDB_Connector.RollbackTransaction(h_transaction);
			return false;
		} 
	}
	
	/**
	 * Load all Employees from Database into array list
	 * 
	 * @return an employee array list
	 */
	/** @Ensures({empList != null}) **/
	@SuppressWarnings("unchecked")
	public ArrayList<Employee> getAllEmployee() {
		
		Transaction h_transaction = null;

		ArrayList<Employee> empList = new ArrayList<Employee>();
		
		try{
			Session session = HibernateDB_Connector.GetOrCreateSession();
			h_transaction = HibernateDB_Connector.GetOrBeginTransaction(session);
			
			empList = (ArrayList<Employee>) session.createQuery("FROM Employee ORDER BY Emp_ID").list();
			
			HibernateDB_Connector.CommitTransaction(h_transaction);
	         
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} 
		return empList;
	}

}
