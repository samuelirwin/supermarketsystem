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
import view.AddEditEmployee_View;
import view.Checkout_View;
import view.Login_View;

public class AddEditEmployee_Controller {
	
	private AddEditEmployee_View AddEditEmployee_View; 
	private AddManageListener AddManageListener;
	private EditManageListener EditManageListener;
	private EmployeeSQL EmployeeSQL;
	private Employee Employee;
	
	/** @Requires({addeditEmployee != null && amclass != null}) **/
	public AddEditEmployee_Controller(AddEditEmployee_View addeditEmployee, AddManageListener amclass)
	{
		this.AddEditEmployee_View = addeditEmployee;
		this.AddManageListener = amclass;
		this.AddEditEmployee_View.Listener_AddEmp(new AddEmployee());
		this.AddEditEmployee_View.Listener_CancelEmp(new CancelEmployee());
		
		this.EmployeeSQL = new EmployeeSQL();
	}	
	
	/** @Requires({addeditEmployee != null && emclass != null}) **/
	public AddEditEmployee_Controller(AddEditEmployee_View addeditEmployee, EditManageListener emclass)
	{
		this.AddEditEmployee_View = addeditEmployee;
		this.EditManageListener = emclass;
		this.AddEditEmployee_View.Listener_SaveEmp(new SaveEmployee());
		this.AddEditEmployee_View.Listener_CancelEmp(new CancelEmployee());
		
		this.EmployeeSQL = new EmployeeSQL();
	}	
	

//******************************************
// Add Employee Listener START
//******************************************	
	/**
	  * Listens to the add button
	  * 
	  */
	 class AddEmployee implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			String fName = AddEditEmployee_View.employeeFirstName_TextField.getText();
			String lName = AddEditEmployee_View.employeeLastName_TextField.getText();
			String password1 = AddEditEmployee_View.password_PasswordField.getText();
			String password2 = AddEditEmployee_View.confirmPassword_PasswordField.getText();
			String salaryString = AddEditEmployee_View.salary_TextField.getText();
			String role = (String) AddEditEmployee_View.role_ComboBox.getSelectedItem();
			String active = (String) AddEditEmployee_View.active_ComboBox.getSelectedItem();

			if(!verifyEmptyField(fName, lName, salaryString))
					return;
			
			if(!verifyPassword(password1, password2))
					return;
			
			boolean status = convertActiveToBoolean(active);
			double salary = Double.parseDouble(salaryString);
			
			if(EmployeeSQL.AddEmployee(fName, lName, role, password1, salary, status))
				JOptionPane.showMessageDialog(null,"Employee "+fName+" "+lName+" is added to database.","Employee Added",1);
			else
				JOptionPane.showMessageDialog(null, "Empoloyee failed to add into database, something went wrong.", "Error", 0);
			
			AddEditEmployee_View.setVisible(false);
			resetAddEmptView();
			AddManageListener.reloadEmpTable();
		}
			
	 }
	 
	 /**
	  * Listens to the add button
	  * 
	  */
	 class SaveEmployee implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			String id = AddEditEmployee_View.employeeId_TextField.getText();
			String fName = AddEditEmployee_View.employeeFirstName_TextField.getText();
			String lName = AddEditEmployee_View.employeeLastName_TextField.getText();
			String password1 = AddEditEmployee_View.password_PasswordField.getText();
			String password2 = AddEditEmployee_View.confirmPassword_PasswordField.getText();
			String salaryString = AddEditEmployee_View.salary_TextField.getText();
			String role = (String) AddEditEmployee_View.role_ComboBox.getSelectedItem();
			String active = (String) AddEditEmployee_View.active_ComboBox.getSelectedItem();

			if(!verifyEmptyField(fName, lName, salaryString))
					return;
			
			if(!verifyPassword(password1, password2))
					return;
			
			boolean status = convertActiveToBoolean(active);
			double salary = Double.parseDouble(salaryString);
			
			if(EmployeeSQL.UpdateEmployee(id, fName, lName, role, password1, salary, status))
				JOptionPane.showMessageDialog(null,"Editted Employee "+fName+" "+lName+" is saved to database.","Employee Added",1);
			else
				JOptionPane.showMessageDialog(null, "Empoloyee failed to saved into database, something went wrong.", "Error", 0);
			
			AddEditEmployee_View.setVisible(false);
			resetAddEmptView();
			EditManageListener.reloadEmpTable();
		}
			
	 }
			
	 
	 /**
	  * Listens the cancel button
	  * 
	  */
	 class CancelEmployee implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			AddEditEmployee_View.setVisible(false);
			resetAddEmptView();
		}	 
	 }
//*************************************
//   Add Employee Listener END
//*************************************	 

//*************************************
//   Non Listeners functions
//*************************************
	 
	
	 public void resetAddEmptView(){
		 AddEditEmployee_View.employeeFirstName_TextField.setText("");
		 AddEditEmployee_View.employeeLastName_TextField.setText("");
		 AddEditEmployee_View.salary_TextField.setText("");
		 AddEditEmployee_View.password_PasswordField.setText("");
		 AddEditEmployee_View.confirmPassword_PasswordField.setText("");
	 }
	 
	 /** @Requires({active == True || active == False}) **/
	 public boolean convertActiveToBoolean(String active){
		 if(active.equals("True"))
			 return true;
		 else
			 return false;
	 }
	 
	 public boolean verifyPassword(String password1, String password2) {
		 if(password1.compareTo(password2)!= 0) {
			 JOptionPane.showMessageDialog(null, "Passwords are not the same", "Error", 0);
			 return false;
		 }		 
		 return true;
	 }
	 
	 public boolean verifyEmptyField(String fName, String lName, String salary) {
		 	
		 	if(fName.isEmpty()){
				JOptionPane.showMessageDialog(null, "First name cannot be empty", "Error", 0);
				return false;
			}
			
			if(lName.isEmpty()){
				JOptionPane.showMessageDialog(null, "Last name cannot be empty", "Error", 0);
				return false;
			}
			
			if(salary.isEmpty() || salary.contains(" ")){
				JOptionPane.showMessageDialog(null, "Salary cannot be empty", "Error", 0);
				return false;
			}
			return true;
	 }
	 
}
