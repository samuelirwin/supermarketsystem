/**
 * @author Jun Han LIAW
 */

package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import model.*;
import view.Checkout_View;
import view.Pay_View;
import view.Invoice_View;

public class Pay_Controller {
	
	private Checkout_View Checkout_View;
	private Pay_View Pay_View;
	private Invoice_View Invoice_View;
	
	private ItemSQL ItemSQL;
	private TransactionSQL TransactionSQL;
	
	SimpleDateFormat DateFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
	
	/** @Requires({payView != null && checkoutView != null}) **/
	public Pay_Controller(Pay_View payView, Checkout_View checkoutView)
	{
		this.Pay_View = payView;
		this.Checkout_View = checkoutView;
		this.Pay_View.Listener_Pay(new PayListener());
		this.Pay_View.Listener_Cancel(new CancelListener());
		this.Pay_View.amountPaid_TextField.getDocument().addDocumentListener(new AmountPaidListener());
		
		this.ItemSQL = new ItemSQL();
		this.TransactionSQL = new TransactionSQL();
	}	
	
//**************************************	 
//	 AmountPaid TextField Listener START
//**************************************
	
	 class AmountPaidListener implements DocumentListener{
		
		public void insertUpdate(DocumentEvent e) {
			updateChangeTextField();
	    }
	    public void removeUpdate(DocumentEvent e) {
	    	updateChangeTextField();
	    }
	    public void changedUpdate(DocumentEvent e) {
	    }  
	 }
//**************************************	 
//	 AmountPaid TextField Listener END
//**************************************	
//**************************************	 
//	 Pay Button Listener START
//**************************************
	
	 class PayListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			double change;
			
			try {
				change = Double.parseDouble(Pay_View.change_TextField.getText());
				
				if(change >= 0)
					generateInvoice(Checkout_View);
				else 
					JOptionPane.showMessageDialog(null,"Insufficient amount! Please try again.","Insufficient Amount",2);
				
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null,"Invalid amount! Please try again.","Invalid Amount",2);
			}
		}
	 }
//**************************************	 
//	 Pay Button Listener END
//**************************************	 
//**************************************	 
//	 Exit/Cancel Button Listener START
//**************************************

	 class CancelListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			resetPayView();
			Pay_View.setVisible(false);
		}
	 }
	 
	 public WindowAdapter WindowCloseListener(){
		WindowAdapter w = new WindowAdapter() {
			public void windowDeactivated(WindowEvent e){
				resetPayView();
			}
		};
		return w;
	 }
//**************************************	 
//	 Exit/Cancel Button Listener END
//**************************************
//**************************************	 
//	 Ok Button Listener START
//**************************************

	 class OkListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			JOptionPane.showMessageDialog(null,"Thank you for shopping with SMART Supermarket! Please come again.",
										  "Transaction Complete",1);
			
			//1. Checkout_View.customerId_TextField.getText() to get Customer ID and store in transaction table
			//2. Checkout_View.staffName_TextField.getText() to get staff name then search database to get the staff ID to 
			//   store in transaction table (this is the part I am unsure for updating stock quantity, which I do not store my item ID anywhere)
			//3. Checkout_View.total_TextField.getText() to get total and store in transaction table
			//4. Generate a transaction date to store in transaction table

			addTransactionToDB();
			updateStockQuantity();
			resetCheckoutView();
			resetPayView();
			resetInvoiceView();
			Invoice_View.setVisible(false);
			Pay_View.setVisible(false);
		}
	 }
//**************************************	 
//	 Ok Button Listener END
//**************************************
//**************************************	 
//	 Non-Listener Functions
//**************************************
	 /** @Requires({checkout_Table != null && checkout_TableModel != null}) **/
	 public void resetCheckoutView(){
		 Checkout_View.customerName_TextField.setText("");
		 Checkout_View.dateTime_TextField.setText((DateFormatter.format(new Date())).toString());
		 Checkout_View.itemId_TextField.setText("");
		 Checkout_View.total_TextField.setText("");
		 
		 for(int i = Checkout_View.checkout_Table.getRowCount() - 1; i >= 0; i--)
	 			Checkout_View.checkout_TableModel.removeRow(i);
	 }

	 public void resetPayView(){
		 Pay_View.total_TextField.setText("");
		 Pay_View.amountPaid_TextField.setText("");
		 Pay_View.change_TextField.setText("");
	 }
	 
	 public void resetInvoiceView(){
		 Invoice_View.invoice_TextArea.setText("");
	 }
	 
	 public void updateChangeTextField() {

	    DecimalFormat DecimalFormatter;
		double total, amountPaid, change;
	
		DecimalFormatter = new DecimalFormat("#.##");
		
		try {
			total = Double.parseDouble(Pay_View.total_TextField.getText());
			amountPaid = Double.parseDouble(Pay_View.amountPaid_TextField.getText());
	
			change = amountPaid - total;
				
			Pay_View.change_TextField.setText(DecimalFormatter.format(change));
		}
		catch(NumberFormatException nfe) {
			Pay_View.change_TextField.setText("");
		}
	 } 
	 
	 /** @Requires({checkoutView != null}) **/
	 public void generateInvoice(Checkout_View checkoutView) {

		 DEC_Invoice inv = null;
		 DEC_CustomerDetails cd = null;
		 DEC_StaffDetails sd = null;
		 
		 if(checkoutView.customerName_TextField.getText().equals("") && checkoutView.staffName_TextField.getText().equals("")) {
			 inv = new DEC_GeneralInvoice(Checkout_View, Pay_View);
			 Invoice_View = new Invoice_View();
			 Invoice_View.invoice_TextArea.setText(inv.getInvoiceText());
		 }
		 else if(!(checkoutView.customerName_TextField.getText().equals("")) && checkoutView.staffName_TextField.getText().equals("")) {
			 inv = new DEC_GeneralInvoice(Checkout_View, Pay_View);
			 cd = new DEC_CustomerDetails(inv, Checkout_View);
			 Invoice_View = new Invoice_View();
			 Invoice_View.invoice_TextArea.setText(cd.getInvoiceText());	
		 }
		 else if(checkoutView.customerName_TextField.getText().equals("") && !(checkoutView.staffName_TextField.getText().equals(""))) {
			 inv = new DEC_GeneralInvoice(Checkout_View, Pay_View);
			 sd = new DEC_StaffDetails(inv, Checkout_View);
			 Invoice_View = new Invoice_View();
			 Invoice_View.invoice_TextArea.setText(sd.getInvoiceText());
		 }
		 else if(!(checkoutView.customerName_TextField.getText().equals("")) && !(checkoutView.staffName_TextField.getText().equals(""))) {
			 inv = new DEC_GeneralInvoice(Checkout_View, Pay_View);
			 sd = new DEC_StaffDetails(inv, Checkout_View);
			 cd = new DEC_CustomerDetails(sd, Checkout_View);
			 Invoice_View = new Invoice_View();
			 Invoice_View.invoice_TextArea.setText(cd.getInvoiceText());
		 }
		 
		 Invoice_View.Listener_Ok(new OkListener());
	}
	
	/** @Requires({checkout_Table != null}) **/
	public void updateStockQuantity() {
		
		int id;
		int reduceQuantity;
		int quantity;
		Item item;
		
		for(int i = Checkout_View.checkout_Table.getRowCount() - 1; i >= 0; i--) {
 			id = (Integer) Checkout_View.checkout_Table.getValueAt(i,0);
 			reduceQuantity = (Integer) Checkout_View.checkout_Table.getValueAt(i,2);
 			
 			item = ItemSQL.getItemByID(String.valueOf(id));
 			quantity = item.getQuantity();
 			
 			ItemSQL.UpdateItemQuantity(id, quantity - reduceQuantity);
		}
	}
	
	public void addTransactionToDB() {
		
		int t_custID = 0;
		int t_empID = 0;
		double amount = 0;
		
		if(!Checkout_View.customerName_TextField.getText().equals(""))
			t_custID = Integer.parseInt(Checkout_View.customerId_TextField.getText());
		if(Checkout_View.loggedInEmp != null)
			t_empID = Checkout_View.loggedInEmp.getEmpID();
		if(!Pay_View.total_TextField.getText().equals(""))
		amount = Double.parseDouble(Pay_View.total_TextField.getText());
		
		TransactionSQL.AddTransaction(t_custID, t_empID, amount);
	}
}
