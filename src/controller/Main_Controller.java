/**
 * @author Jun Han LIAW & Eng Sun CHEW
 */
package controller;

import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import org.hibernate.Session;

import model.*;
import view.*;

public class Main_Controller {
	
	private Login_View Login_View;
	private Management_View Management_View;
	private Checkout_View Checkout_View;
	private Pay_View Pay_View;
	private EmployeeSQL EmployeeSQL;
	private ItemSQL ItemSQL;
	private Employee EmployeeObject;
	
	/** @Requires({checkoutView != null}) **/
	public Main_Controller(Checkout_View checkoutView)
	{
		this.Checkout_View = checkoutView;
		this.EmployeeSQL = new EmployeeSQL();
		
		this.Checkout_View.Listener_StaffLogin(new StaffLogin());
		this.Checkout_View.Listener_AddItem(new AddItem());
		this.Checkout_View.Listener_DeleteItem(new DeleteItem());	
		this.Checkout_View.Listener_Checkout(new Checkout());
		this.Checkout_View.Listener_Management(new Management());
		this.Checkout_View.Listener_Logout(new Logout());
		this.Checkout_View.Listener_Exit(new Exit());
		this.Checkout_View.customerId_TextField.getDocument().addDocumentListener(new customerIdListener());
	}	
	
//**************************************	 
//	 CustomerId TextField Listener START
//**************************************
	
	 class customerIdListener implements DocumentListener{
		
		public void insertUpdate(DocumentEvent e) {
			updateCustomerNameTextField();
	    }
	    public void removeUpdate(DocumentEvent e) {
	    	updateCustomerNameTextField();
	    }
	    public void changedUpdate(DocumentEvent e) {
	    }  
	 }
//**************************************	 
//	 CustomerId TextField Listener END
//**************************************
//************************************
//   Staff Login Button Listener START
//************************************
	/**
	 * Listen to Staff Login Button on the Checkout View
	 */
	 class StaffLogin implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			if(Login_View == null){	
				Login_View = new Login_View();
				Login_Controller loginController = new Login_Controller(Login_View, this);
			}
			
			else
				Login_View.setVisible(true);	
		}
		
		public void InitializeStaffCheckoutView(Employee emp) {
			 Checkout_View.InitializeStaffCheckoutView(emp);
		}
	 }
//**************************************
//   Staff Login Button Listener END
//**************************************
//**************************************	 
//	 Add Item Button Listener START
//**************************************	 
	 
	 class AddItem implements ActionListener{
		 	public void actionPerformed(ActionEvent arg0) {

				Item item = new Item();
				ItemSQL ItemSQL = new ItemSQL();
				JTable table = Checkout_View.checkout_Table;
				String itemID = Checkout_View.itemId_TextField.getText();
				
				item = ItemSQL.getItemByID(itemID);
				
				if(item != null && item.getItemActive() == true) {
					checkExistingItem(item, table, itemID);
					calculateTotal(table);
				}
				else
					JOptionPane.showMessageDialog(null,"Item not found! Please try again.","Item Not Found",2);
				
				Checkout_View.itemId_TextField.setText("");
			}
	 	 }	
	 
//**************************************
//   Add Item Button Listener END
//**************************************
//**************************************	 
//	 Delete Item Button Listener START
//**************************************	 
	 
	 class DeleteItem implements ActionListener{
		 	public void actionPerformed(ActionEvent arg0) {

		 		JTable table = Checkout_View.checkout_Table;
		 		DefaultTableModel tableModel = Checkout_View.checkout_TableModel;
		 		int[] rows;
		 		
		 		rows = table.getSelectedRows();
		 		
		 		for(int i = rows.length - 1; i >= 0; i--)
		 			tableModel.removeRow(rows[i]);

		 		calculateTotal(table);
			}
	 	 }	
	 
//**************************************
//   Delete Item Button Listener END
//**************************************
//**************************************
//	 Checkout Button Listener START
//**************************************	
	
	 class Checkout implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				
				JTable table = Checkout_View.checkout_Table;
				String total_Text = Checkout_View.total_TextField.getText();
				
				if(table.getRowCount() > 0) {
					if(Pay_View == null){
							Pay_View = new Pay_View();
							Pay_Controller Pay_Controller = new Pay_Controller(Pay_View, Checkout_View);
					}
					else
						Pay_View.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null,"No item added! Please add items before checkout." ,"No Item Added",2);

				try{
				Pay_View.total_TextField.setText(total_Text);
				}catch (NullPointerException e) {
					System.out.println("Empty Checkout Table");
				}
			}
		 }	
//**************************************
//   Checkout Button Listener END
//**************************************
//**************************************	 
//	 Logout Menu Listener START
//**************************************	

	 class Logout implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"You have logged out of System" ,"Log Out Successful",1);
				Checkout_View.InitializeDefaultCheckoutView();	
			}
		 }
//**************************************	 
//	 Logout Menu Listener END
//**************************************
//**************************************	 
//	 Management Menu Listener START
//**************************************	

	 class Management implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				
				if(Management_View==null){	
					Management_View = new Management_View();
					changeManagementView();
					Management_Controller managementController = new Management_Controller(Management_View);
					}
					
				else
					changeManagementView();
					Management_View.setVisible(true);
			}
			
		 }
//**************************************	 
//	 Management Menu Listener END
//**************************************
//**************************************	 
//	 Exit Menu/Button Listener START
//**************************************	 

	class Exit implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

//**************************************	 
//	 Exit Menu/Button Listener END
//**************************************
//**************************************	 
//	 Non-Listener Functions
//**************************************	
	public void changeManagementView(){
		
		Management_View.management_TableModel.setColumnIdentifiers(Management_View.customer_Columns);
		
		if(Checkout_View.loggedInEmp.getEmpRole().equals("Staff"))
			initializeStaffManageView();
		else if (Checkout_View.loggedInEmp.getEmpRole().equals("Manager"))
			initializeManagerManageView();
	}
	
	public void initializeStaffManageView(){
		Management_View.employee_Button.setVisible(false);
		Management_View.generateReport_Button.setVisible(false);
		Management_View.viewTransaction_Button.setVisible(false);
	}
	
	public void initializeManagerManageView(){
		Management_View.employee_Button.setVisible(true);
		Management_View.generateReport_Button.setVisible(true);
		Management_View.viewTransaction_Button.setVisible(true);
	}
	
	/** @Requires({item != null && table != null}) **/
	public void checkExistingItem(Item item, JTable table, String itemID){
		int itemQuantity = 0;
		boolean itemExist_Flag = true;
		
		if(table.getRowCount() != 0) {
			
			for (int row = 0; row <= table.getRowCount() - 1; row++) {
				
				if (itemID.equals(table.getValueAt(row, 0).toString())) {
					
					itemQuantity = addQuantity(table, row, itemQuantity);
					calculateSubTotal(item, table, row, itemQuantity);
					itemExist_Flag = true;
					break;
				}
				else
					itemExist_Flag = false;
			}
		}
		else
			addItem(item);
		
		if(itemExist_Flag == false)
			addItem(item);
	}
	
	/** @Requires({item != null}) **/
	public void addItem(Item item){
		DefaultTableModel tableModel = Checkout_View.checkout_TableModel;
		
		tableModel.addRow(new Object[]{item.getItemID(), item.getItemName(), 1, item.getPrice()});
	}
	
	/** @Requires({table != null}) **/
	/** @Ensures ({itemQuantity != 0) **/
	public int addQuantity(JTable table, int row, int itemQuantity){		
		itemQuantity = Integer.parseInt(table.getValueAt(row, 2).toString());
		table.setValueAt(itemQuantity + 1, row, 2);
		
		return itemQuantity + 1;
	}
	
	/** @Requires({(item != null && table != null) && itemQuantity != 0}) **/
	public void calculateSubTotal(Item item, JTable table, int row, int itemQuantity){
		DecimalFormat DecimalFormatter;
		double itemPrice;
		
		DecimalFormatter = new DecimalFormat("#.##");
		
		itemPrice = item.getPrice();
		table.setValueAt(DecimalFormatter.format(itemPrice * itemQuantity), row, 3);
	}
	
	/** @Requires({table != null}) **/
	public void calculateTotal(JTable table){
		DecimalFormat DecimalFormatter;
		double total = 0;
		
		DecimalFormatter = new DecimalFormat("#.##");
		
		for (int row = 0; row <= table.getRowCount() - 1; row++)
			total += Double.parseDouble(table.getValueAt(row, 3).toString());
		
		Checkout_View.total_TextField.setText(DecimalFormatter.format(total));
	}
	
	public void updateCustomerNameTextField(){
		
		Customer customer = new Customer();
		CustomerSQL CustomerSQL = new CustomerSQL();
		String customerID = Checkout_View.customerId_TextField.getText();
		String customerName;

		try{
		customer = CustomerSQL.getCustByID(Integer.parseInt(customerID));
		
		if(customer != null && customer.getCustActive() == true) {
			customerName = customer.getFirstName() + " " + customer.getLastName();
			Checkout_View.customerName_TextField.setText(customerName);
		}
		else
			Checkout_View.customerName_TextField.setText("");
		}catch (NumberFormatException e){
			System.out.println("Enter number only on Customer ID field");
		}
	}
} 

