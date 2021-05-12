/**
 * @author Eng Sun CHEW
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table (name = "Customer")
public class Customer {

	private int Cust_ID;
	private String Cust_fName;
	private String Cust_lName;
	private String Cust_contactNo;
	private boolean Cust_Active;
	
	public Customer() {	
		
	}
	
	public Customer(int Cust_ID, String Cust_fName, String Cust_lName, String Cust_contactNo, boolean Cust_status) {
		
		this.Cust_ID = Cust_ID;
		this.Cust_fName = Cust_fName;
		this.Cust_lName = Cust_lName;
		this.Cust_contactNo = Cust_contactNo;
		this.Cust_Active = Cust_status;
		
	}
	
	/** @Ensures({Cust_ID != null}) **/
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "Cust_ID", unique = true, nullable = false)
	public int getCustID() {
		return this.Cust_ID;
	}
	
	/** @Requires({custID != null}) **/
	public void setCustID(int custID) {
		this.Cust_ID = custID;
	}
	
	/** @Ensures({Cust_fName != null}) **/
	@Column (name = "Cust_FirstName")
	public String getFirstName() {
		return Cust_fName;
	}
	
	/** @Requires({firstname != null}) **/
	public void setFirstName(String firstname) {
		this.Cust_fName = firstname;
	}
	
	/** @Ensures({Cust_lName != null}) **/
	@Column (name = "Cust_LastName")
	public String getLastName() {
		return Cust_lName;
	}
	
	/** @Requires({lastname != null}) **/
	public void setLastName(String lastname) {
		this.Cust_lName = lastname;
	}
	
	/** @Ensures({Cust_contactNo != null}) **/
	@Column (name = "Cust_Contact")
	public String getContact() {
		return Cust_contactNo;
	}
	
	/** @Requires({phone != null}) **/
	public void setContact(String phone) {
		this.Cust_contactNo = phone;
	}
	
	/** @Ensures({Cust_Active != null}) **/
	@Column (name = "Cust_Active")
	@Type(type = "numeric_boolean")
	public boolean getCustActive() {
		return this.Cust_Active;
	}
	
	/** @Requires({status != null}) **/
	public void setCustActive(boolean status) {
		this.Cust_Active = status;
	}

}
