/**
 * @author Eng Sun CHEW
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;





import model.Employee;
import model.EmployeeSQL;
import model.HibernateDB_Connector;
import view.AddEditCustomer_View;

import org.hibernate.Session;

import controller.Main_Controller.StaffLogin;
import controller.Management_Controller.AddManageListener;
import controller.Management_Controller.EditManageListener;
import model.Customer;
import model.CustomerSQL;

public class AddEditCustomer_Controller {
	
	private AddEditCustomer_View AddEditCustomer_View; 
	private AddManageListener AddManageListener;
	private EditManageListener EditManageListener;
	private CustomerSQL CustomerSQL;
	private Customer Customer;
	
	/** @Requires({addeditCustomer_View != null && amclass != null}) **/
	public AddEditCustomer_Controller(AddEditCustomer_View addeditCustomer_View, AddManageListener amclass)
	{
		this.AddEditCustomer_View = addeditCustomer_View;
		this.AddManageListener = amclass;
		this.AddEditCustomer_View.Listener_AddCust(new AddCustomer());
		this.AddEditCustomer_View.Listener_CancelCust(new CancelCustomer());
		
		this.CustomerSQL = new CustomerSQL();
	}
	
	/** @Requires({addeditCustomer_View != null && emclass != null}) **/
	public AddEditCustomer_Controller(AddEditCustomer_View addeditCustomer_View, EditManageListener emclass)
	{
		this.AddEditCustomer_View = addeditCustomer_View;
		this.EditManageListener = emclass;
		this.AddEditCustomer_View.Listener_SaveCust(new EditCustomer());
		this.AddEditCustomer_View.Listener_CancelCust(new CancelCustomer());
		
		this.CustomerSQL = new CustomerSQL();
	}
	

//******************************************
// Add Customer Listener START
//******************************************	
	/**
	  * Listens to the add button
	  * 
	  */
	 class AddCustomer implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			String fName = AddEditCustomer_View.customerFirstName_TextField.getText();
			String lName = AddEditCustomer_View.customerLastName_TextField.getText();
			String phone = AddEditCustomer_View.customerContactNo_TextField.getText();
			String active = (String) AddEditCustomer_View.active_ComboBox.getSelectedItem();
			
			boolean status = convertActiveToBoolean(active);
			
			
			if(!verifyEmptyField(fName, lName, phone))
					return;
			
			if(CustomerSQL.AddCustomer(fName, lName, phone, status))
				JOptionPane.showMessageDialog(null,"Customer "+fName+" "+lName+" is added to database.","Customer Added",1);
			else
				JOptionPane.showMessageDialog(null, "Customer failed to add into database, something went wrong.", "Error", 0);
			
			AddEditCustomer_View.setVisible(false);
			resetAddCustView();
			AddManageListener.reloadCustTable();
		}
			
	 }
	 
	/**
	  * Listens to the save button
	  * 
	  */
	 class EditCustomer implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			int id = Integer.parseInt(AddEditCustomer_View.customerId_TextField.getText());
			String fName = AddEditCustomer_View.customerFirstName_TextField.getText();
			String lName = AddEditCustomer_View.customerLastName_TextField.getText();
			String phone = AddEditCustomer_View.customerContactNo_TextField.getText();
			String active = (String) AddEditCustomer_View.active_ComboBox.getSelectedItem();
			
			boolean status = convertActiveToBoolean(active);
			
			
			if(!verifyEmptyField(fName, lName, phone))
					return;
			
			if(CustomerSQL.UpdateCustomer(id, fName, lName, phone, status))
				JOptionPane.showMessageDialog(null,"Edited Customer "+fName+" "+lName+" is saved to database.","Customer Added",1);
			else
				JOptionPane.showMessageDialog(null, "Customer failed to saved into database, something went wrong.", "Error", 0);
			
			AddEditCustomer_View.setVisible(false);
			resetAddCustView();
			EditManageListener.reloadCustTable();
		}
			
	 }
			
	 
	 /**
	  * Listens the cancel button
	  * 
	  */
	 class CancelCustomer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			AddEditCustomer_View.setVisible(false);
			resetAddCustView();
		}	 
	 }
//*************************************
//   Add Customer Listener END
//*************************************	 

//*************************************
//   Non Listeners functions
//*************************************
	 
	
	 public void resetAddCustView(){
		 AddEditCustomer_View.customerFirstName_TextField.setText("");
		 AddEditCustomer_View.customerLastName_TextField.setText("");
		 AddEditCustomer_View.customerContactNo_TextField.setText("");
	 }
	 
	 /** @Requires({active == True || active == False}) **/
	 public boolean convertActiveToBoolean(String active){
		 if(active.equals("True"))
			 return true;
		 else
			 return false;
	 }
	 
	 public boolean verifyEmptyField(String fName, String lName, String phone) {
		 	
		 	if(fName.isEmpty()){
				JOptionPane.showMessageDialog(null, "First name cannot be empty", "Error", 0);
				return false;
			}
			
			if(lName.isEmpty()){
				JOptionPane.showMessageDialog(null, "Last name cannot be empty", "Error", 0);
				return false;
			}
			
			if(phone.isEmpty() || phone.contains(" ")){
				JOptionPane.showMessageDialog(null, "Phone number cannot be empty", "Error", 0);
				return false;
			}
			return true;
	 }
	 
}
