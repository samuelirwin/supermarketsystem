/*
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
@Table (name = "Employee")
public class Employee {

	private int Emp_ID;
	private String First_Name;
	private String Last_Name;
	private String Password;
	private String Emp_Role;
	private double Salary;
	private boolean Emp_Active; 
	
	public Employee() {	
		
	}
	
	public Employee(int Emp_ID, String First_Name, String Last_Name, String Password, double Salary,
										String Emp_Role, Boolean Emp_Active) {
		this.Emp_ID = Emp_ID;
		this.First_Name = First_Name;
		this.Last_Name = Last_Name;
		this.Password = Password;
		this.Emp_Role = Emp_Role;
		this.Salary = Salary;
		this.Emp_Active = Emp_Active;
		
	}
	
	/** @Ensures({Emp_ID != null}) **/
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "Emp_ID", unique = true, nullable = false)
	public int getEmpID() {
		return this.Emp_ID;
	}

	/** @Requires({empID != null}) **/
	public void setEmpID(int empID) {
		this.Emp_ID = empID;
	}
	
	/** @Ensures({First_Name != null}) **/
	@Column (name = "Emp_FirstName")
	public String getFirstName() {
		return First_Name;
	}

	/** @Requires({firstname != null}) **/
	public void setFirstName(String firstname) {
		this.First_Name = firstname;
	}
	
	/** @Ensures({Last_Name != null}) **/
	@Column (name = "Emp_LastName")
	public String getLastName() {
		return Last_Name;
	}

	/** @Requires({lastname != null}) **/
	public void setLastName(String lastname) {
		this.Last_Name = lastname;
	}

	/** @Ensures({Password != null}) **/
	@Column (name = "Emp_Password")
	public String getPassword() {
		return Password;
	}
	
	/** @Requires({password != null}) **/
	public void setPassword(String password) {
		this.Password = password;
	}
	
	/** @Ensures({Emp_Role != null}) **/
	@Column (name = "Emp_Role")
	public String getEmpRole() {
		return this.Emp_Role;
	}
	
	/** @Requires({role != null}) **/
	public void setEmpRole(String role) {
		this.Emp_Role = role;
	}

	/** @Ensures({Salary != null}) **/
	@Column (name = "Emp_Salary")
	public double getSalary() {
		return this.Salary;
	}
	
	/** @Requires({salary != null}) **/
	public void setSalary(double salary) {
		this.Salary = salary;
	}

	/** @Ensures({Emp_Active != null}) **/
	@Column (name = "Emp_Active")
	@Type(type = "numeric_boolean")
	public boolean getEmpActive() {
		return this.Emp_Active;
	}
	
	/** @Requires({status != null}) **/
	public void setEmpActive(boolean status) {
		this.Emp_Active = status;
	}

}
