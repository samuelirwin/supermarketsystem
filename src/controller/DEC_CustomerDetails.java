/**
 * @author Jun Han LIAW
 */

package controller;

import view.Checkout_View;

public class DEC_CustomerDetails extends DEC_InvoiceDecorator {

	private DEC_Invoice invoice;
	private Checkout_View Checkout_View;
	
	/** @Requires({invoice != null && checkout_View != null}) **/
	public DEC_CustomerDetails(DEC_Invoice invoice, Checkout_View checkout_View) {
		this.invoice = invoice;
		this.Checkout_View = checkout_View;
	}
	
	public String getInvoiceText() {
		String customerDetails_Text;
		
		customerDetails_Text  = " Cust. ID \t: " + Checkout_View.customerId_TextField.getText() + "\n";
		customerDetails_Text += " Cust. Name \t: " + Checkout_View.customerName_TextField.getText() + "\n\n";
		
		return customerDetails_Text + invoice.getInvoiceText();
	}
}
