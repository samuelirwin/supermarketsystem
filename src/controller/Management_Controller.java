/**
 * @author Eng Sun CHEW
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.*;
import view.*;
public class Management_Controller {
	
	private Management_View Management_View;
	private AddEditCustomer_View AddEditCustomer_View;
	private AddEditCustomer_View EditCustomerView;
	private AddEditStock_View AddEditStock_View;
	private AddEditStock_View EditStock_View;
	private AddEditEmployee_View AddEditEmployee_View;
	private AddEditEmployee_View EditEmployee_View;
	private EmployeeSQL EmployeeSQL;
	private ItemSQL ItemSQL;
	private CustomerSQL CustomerSQL;
	private TransactionSQL TransactionSQL;
	private Report_View Report_View;
	
	/** @Requires({managementView != null}) **/
	public Management_Controller(Management_View managementView)
	{
		this.Management_View = managementView;
		
		Management_View.Listener_CustomerManage(new CustomerManageListener());
		Management_View.Listener_StockManage(new StockManageListener());
		Management_View.Listener_EmployeeManage(new EmployeeManageListener());
		Management_View.Listener_TransactionManage(new TransactionManageListener());
		Management_View.Listener_GenerateReport(new GenerateReportListener());
		Management_View.Listener_AddManage(new AddManageListener());
		Management_View.Listener_EditManage(new EditManageListener());
		Management_View.Listener_ExitManage(new ExitManageListener());
		
		this.ItemSQL = new ItemSQL();
		this.EmployeeSQL = new EmployeeSQL();
		this.CustomerSQL = new CustomerSQL();
		this.TransactionSQL = new TransactionSQL();
	}	
	
	/**
	  * Listens the the Customer button
	  * 
	  */
	 class CustomerManageListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			loadCustomerTable();
			
		}
	 }
	 
	 /**
	  * Listens the the Stock button
	  * 
	  */
	 class StockManageListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			loadItemTable();
		}
	 }
	 
	 /**
	  * Listens the the Employee button
	  * 
	  */
	 class EmployeeManageListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			loadEmployeeTable();
		}
	 }
	 
	 /**
	  * Listens the the Transaction button
	  * 
	  */
	 class TransactionManageListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			loadTransactionTable();
		}
	 }
	 
	 /**
	  * Listens the the Generate Report button
	  * 
	  */
	 class GenerateReportListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//TODO IRWIN
			//loadGenerateReportScreen();
			Report_View = new Report_View();
			Report_Controller reportcontroller = new Report_Controller(Report_View);
		}
	 }
	 
	 /**
	  * Listens the the Add button
	  * 
	  */
	 class AddManageListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String columnName = Management_View.management_TableModel.getColumnName(0);
			
			if(columnName.equals("Cust ID")){
				if(AddEditCustomer_View == null){	
					AddEditCustomer_View = new AddEditCustomer_View();
					AddEditCustomer_Controller addeditcustomer = 
							new AddEditCustomer_Controller(AddEditCustomer_View, this);
				}
				
				else {
					AddEditCustomer_View.customerId_Label.setVisible(false);
					AddEditCustomer_View.setVisible(true);
				}	
			}
			
			if(columnName.equals("Item ID")){
				if(AddEditStock_View == null){	
					AddEditStock_View = new AddEditStock_View();
					AddEditStock_Controller addeditstock = 
							new AddEditStock_Controller(AddEditStock_View, this);
				}
				
				else
					AddEditStock_View.setVisible(true);
			}
			
			if(columnName.equals("Emp ID")){
				if(AddEditEmployee_View == null){	
					AddEditEmployee_View = new AddEditEmployee_View();
					AddEditEmployee_Controller addeditemployee = 
							new AddEditEmployee_Controller(AddEditEmployee_View, this);
				}
				
				else
					AddEditEmployee_View.setVisible(true);
			}
		}
		
		public void reloadCustTable(){
			loadCustomerTable();
		}
		
		public void reloadItemTable(){
			loadItemTable();
		}
		
		public void reloadEmpTable(){
			loadEmployeeTable();
		}
		
	 }
	 
	 /**
	  * Listens the the Edit button
	  * 
	  */
	 class EditManageListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String columnName = Management_View.management_TableModel.getColumnName(0);
			
			int selectedID = getSelectedID();
			
			if(selectedID == -1) {
				JOptionPane.showMessageDialog(null, "Please select a unit from the table to edit", "Error", 2);
				return;
			}
			
			if(columnName.equals("Cust ID")){
				Customer customer = new Customer();
				customer = CustomerSQL.getCustByID(selectedID);
				
				if(EditCustomerView == null){
					EditCustomerView = new AddEditCustomer_View();
					initializeEditCustomer(customer, EditCustomerView);
					AddEditCustomer_Controller addeditcustomer = 
							new AddEditCustomer_Controller(EditCustomerView, this);
				}
				
				else
					initializeEditCustomer(customer, EditCustomerView);
					EditCustomerView.setVisible(true);
			}
			
			if(columnName.equals("Item ID")){
				Item item = new Item();
				item = ItemSQL.getItemByID(Integer.toString(selectedID));
				
				if(EditStock_View == null){	
					EditStock_View = new AddEditStock_View();
					initializeEditItem(item, EditStock_View);
					AddEditStock_Controller addeditstock = 
							new AddEditStock_Controller(EditStock_View, this);
				}
				
				else
					initializeEditItem(item, EditStock_View);
					EditStock_View.setVisible(true);
			}
			
			if(columnName.equals("Emp ID")){
				Employee emp = new Employee();
				emp = EmployeeSQL.getEmpByID(Integer.toString(selectedID));
				
				if(EditEmployee_View == null){	
					EditEmployee_View = new AddEditEmployee_View();
					initializeEditEmployee(emp, EditEmployee_View);
					AddEditEmployee_Controller addeditemployee = 
							new AddEditEmployee_Controller(EditEmployee_View, this);
				}
				
				else
					initializeEditEmployee(emp, EditEmployee_View);
					EditEmployee_View.setVisible(true);
			}
		}
		
		public void reloadCustTable(){
			loadCustomerTable();
		}
		
		public void reloadItemTable(){
			loadItemTable();
		}
			
		public void reloadEmpTable(){
			loadEmployeeTable();
		}
	 }
	 
	 /**
	  * Listens the the Delete button
	  * 
	  */
	 class DeleteManageListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
		}
	 }
	 
	 /**
	  * Listens the the Exit button
	  * 
	  */
	 class ExitManageListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			loadCustomerTable();
			Management_View.setVisible(false);
		}
	 }
	 
//**************************************	 
//	 Non-Listener Functions
//**************************************	
	 public void initializeEditCustomer(Customer customer, AddEditCustomer_View view){
		 	view.customerId_Label.setVisible(true);
		 	view.customerId_TextField.setVisible(true);
		 	view.add_Button.setVisible(false);
		 	view.save_Button.setVisible(true);
		 	view.customerId_TextField.setText(Integer.toString(customer.getCustID()));
		 	view.customerFirstName_TextField.setText(customer.getFirstName());
		 	view.customerLastName_TextField.setText(customer.getLastName());
		 	view.customerContactNo_TextField.setText(customer.getContact());
	 }
	 
	 public void initializeEditItem(Item item, AddEditStock_View view){
		 	view.itemId_Label.setVisible(true);
		 	view.itemId_TextField.setVisible(true);
		 	view.add_Button.setVisible(false);
		 	view.save_Button.setVisible(true);
		 	view.itemId_TextField.setText(Integer.toString(item.getItemID()));
		 	view.itemName_TextField.setText(item.getItemName());
		 	view.price_TextField.setText(Double.toString(item.getPrice()));
		 	view.quantity_TextField.setText(Integer.toString(item.getQuantity()));
	 }
	 
	 public void initializeEditEmployee(Employee emp, AddEditEmployee_View view){
		 	view.employeeId_Label.setVisible(true);
		 	view.employeeId_TextField.setVisible(true);
		 	view.add_Button.setVisible(false);
		 	view.save_Button.setVisible(true);
		 	view.employeeId_TextField.setText(Integer.toString(emp.getEmpID()));
		 	view.employeeFirstName_TextField.setText(emp.getFirstName());
		 	view.employeeLastName_TextField.setText(emp.getLastName());
		 	view.password_PasswordField.setText(emp.getPassword());
		 	view.confirmPassword_PasswordField.setText(emp.getPassword());	 	
		 	view.salary_TextField.setText(Double.toString(emp.getSalary()));
	 }
	 
	 public void loadCustomerTable(){
		 	ArrayList<Customer> custList = new ArrayList<Customer>();
		 
		    custList = CustomerSQL.getAllCustomer();
			Object[][] custList2D = CustArrayTo2D(custList);
			Management_View.management_TableModel.setDataVector(custList2D, Management_View.customer_Columns);
			Management_View.management_TableModel.setColumnIdentifiers(Management_View.customer_Columns);
	 }
	 
	 public void loadItemTable(){
		 	ArrayList<Item> itemList = new ArrayList<Item>();
			
			itemList = ItemSQL.getAllItem();
			Object[][] itemList2D = ItemArrayTo2D(itemList);
			Management_View.management_TableModel.setDataVector(itemList2D, Management_View.stock_Columns);
			Management_View.management_TableModel.setColumnIdentifiers(Management_View.stock_Columns);
	 }
	 
	 public void loadEmployeeTable(){
		 	ArrayList<Employee> empList = new ArrayList<Employee>();
			
			empList = EmployeeSQL.getAllEmployee();
			Object[][] empList2D = EmpArrayTo2D(empList);
			Management_View.management_TableModel.setDataVector(empList2D, Management_View.employee_Columns);
			Management_View.management_TableModel.setColumnIdentifiers(Management_View.employee_Columns);
	 }
	 
	 public void loadTransactionTable(){
		 	ArrayList<Transaction> transList = new ArrayList<Transaction>();
			
		 	transList = TransactionSQL.getAllTransaction();
			Object[][] transList2D = TransArrayTo2D(transList);
			Management_View.management_TableModel.setDataVector(transList2D, Management_View.transaction_Columns);
			Management_View.management_TableModel.setColumnIdentifiers(Management_View.transaction_Columns);
	 }
	 
	 public Object[][] EmpArrayTo2D(ArrayList<Employee> empList)
		{
			Object[][] empList2D = new Object[empList.size()][6];
			
			for(int i = 0; i < empList.size(); i++){
				empList2D[i][0] = empList.get(i).getEmpID();
				empList2D[i][1] = empList.get(i).getFirstName();
				empList2D[i][2] = empList.get(i).getLastName();
				empList2D[i][3] = empList.get(i).getSalary();
				empList2D[i][4] = empList.get(i).getEmpRole();
				empList2D[i][5] = empList.get(i).getEmpActive();
			}	
			return empList2D;
		}
	 
	 public Object[][] ItemArrayTo2D(ArrayList<Item> itemList)
		{
			Object[][] itemList2D = new Object[itemList.size()][6];
			
			for(int i = 0; i < itemList.size(); i++){
				itemList2D[i][0] = itemList.get(i).getItemID();
				itemList2D[i][1] = itemList.get(i).getItemName();
				itemList2D[i][2] = itemList.get(i).getCategory();
				itemList2D[i][3] = itemList.get(i).getPrice();
				itemList2D[i][4] = itemList.get(i).getQuantity();
				itemList2D[i][5] = itemList.get(i).getItemActive();
			}	
			return itemList2D;
		}
	 
	 public Object[][] CustArrayTo2D(ArrayList<Customer> custList)
		{
			Object[][] custList2D = new Object[custList.size()][5];
			
			for(int i = 0; i < custList.size(); i++){
				custList2D[i][0] = custList.get(i).getCustID();
				custList2D[i][1] = custList.get(i).getFirstName();
				custList2D[i][2] = custList.get(i).getLastName();
				custList2D[i][3] = custList.get(i).getContact();
				custList2D[i][4] = custList.get(i).getCustActive();
			}	
			return custList2D;
		}
	 
	 public Object[][] TransArrayTo2D(ArrayList<Transaction> transList)
		{
			Object[][] transList2D = new Object[transList.size()][5];
			
			for(int i = 0; i < transList.size(); i++){
				transList2D[i][0] = transList.get(i).getTransID();
				transList2D[i][1] = transList.get(i).getCustID();
				transList2D[i][2] = transList.get(i).getEmpID();
				transList2D[i][3] = transList.get(i).getTransAmt();
				transList2D[i][4] = transList.get(i).getTransDate();
			}	
			return transList2D;
		}
	 
	 /** @Ensures({selectedID != null}) **/
	 public int getSelectedID(){
		 try{
		 int row = Management_View.management_Table.getSelectedRow();
		 int selectedID = (Integer) Management_View.management_Table.getValueAt(row,0);
		 return selectedID;
		 
		 }catch (ArrayIndexOutOfBoundsException e){
			 return -1;
		 }
	 }


}
