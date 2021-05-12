/**
 * @author Jun Han LIAW
 */

package controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.Checkout_View;
import view.Pay_View;

public class DEC_GeneralInvoice extends DEC_Invoice {
	
	private Checkout_View Checkout_View;
	private Pay_View Pay_View;
	private JTable table;
	private DefaultTableModel tableModel;
	
	/** @Requires({checkout_View != null && pay_View != null}) **/
	public DEC_GeneralInvoice(Checkout_View checkout_View, Pay_View pay_View) {
		
		this.Checkout_View = checkout_View;
		this.Pay_View = pay_View;
		
		table = Checkout_View.checkout_Table;
		tableModel = Checkout_View.checkout_TableModel;

		invoice_Text  = "================= SMART Supermarket =================\n\n";
		invoice_Text += "****************************** INVOICE *******************************\n\n";
		invoice_Text += " Item\t\tQuantity\t     Subtotal\n";
		
		for(int i = 0; i < tableModel.getRowCount(); i++) {
			invoice_Text += " " + table.getValueAt(i,1) + "\t\t";
			invoice_Text += table.getValueAt(i,2) + "\t     ";
			invoice_Text += table.getValueAt(i,3) + "\n";
		}
		
		invoice_Text += "-----------------------------------------------------------------------------------------\n\n";
		invoice_Text += " Total \t\t\t: $ " + Pay_View.total_TextField.getText() + "\n";
		invoice_Text += " Amount Paid \t\t\t: $ " + Pay_View.amountPaid_TextField.getText() + "\n";
		invoice_Text += " Changes \t\t\t: $ " + Pay_View.change_TextField.getText() + "\n\n";
		invoice_Text += "***********************************************************************";
	}
}
