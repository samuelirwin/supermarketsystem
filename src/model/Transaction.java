/**
 * @author Eng Sun CHEW
 */
package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "Transaction_table")
public class Transaction {

	private int Trans_ID;
	private int Trans_CustID;
	private int Trans_EmpID;
	private double Trans_Amt;
	private Date Trans_Date;
	
	public Transaction() {	
		
	}
	
	public Transaction(int Trans_ID, int Trans_CustID, int Trans_EmpID, double Trans_Amt, Date Trans_Date) {
		this.Trans_ID = Trans_ID;
		this.Trans_CustID = Trans_CustID;
		this.Trans_EmpID = Trans_EmpID;
		this.Trans_Amt = Trans_Amt;
		this.Trans_Date = Trans_Date;
		
	}

	/** @Ensures({Trans_ID != null}) **/
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "Trans_ID", unique = true, nullable = false)
	public int getTransID() {
		return this.Trans_ID;
	}

	/** @Requires({transID != null}) **/
	public void setTransID(int transID) {
		this.Trans_ID = transID;
	}
	
	/** @Ensures({Trans_CustID != null}) **/
	@Column (name = "Trans_CustID")
	public int getCustID() {
		return Trans_CustID;
	}

	/** @Requires({custID != null}) **/
	public void setCustID(int custID) {
		this.Trans_CustID = custID;
	}

	/** @Ensures({Trans_EmpID != null}) **/
	@Column (name = "Trans_EmpID")
	public int getEmpID() {
		return Trans_EmpID;
	}

	/** @Requires({empID != null}) **/
	public void setEmpID(int empID) {
		this.Trans_EmpID = empID;
	}

	/** @Ensures({Trans_Amt != null}) **/
	@Column (name = "Trans_Amt")
	public double getTransAmt() {
		return this.Trans_Amt;
	}

	/** @Requires({transAmt != null}) **/
	public void setTransAmt(double transAmt) {
		this.Trans_Amt = transAmt;
	}

	/** @Ensures({Trans_Date != null}) **/
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "Trans_Date")
	public Date getTransDate() {
		return this.Trans_Date;
	}

	/** @Requires({transDate != null}) **/
	public void setTransDate(Date transDate) {
		this.Trans_Date = transDate;
	}
}
