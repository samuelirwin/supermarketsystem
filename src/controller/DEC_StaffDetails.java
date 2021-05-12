/**
 * @author Jun Han LIAW
 */

package controller;

import view.Checkout_View;

public class DEC_StaffDetails extends DEC_InvoiceDecorator {

	private DEC_Invoice invoice;
	private Checkout_View Checkout_View;
	
	/** @Requires({invoice != null && checkout_View != null}) **/
	public DEC_StaffDetails(DEC_Invoice invoice, Checkout_View checkout_View) {
		this.invoice = invoice;
		this.Checkout_View = checkout_View;
	}
	
	public String getInvoiceText() {
		String staffDetails_Text;
		
		staffDetails_Text = " Assisted By \t: " + Checkout_View.staffName_TextField.getText() + "\n\n";
		
		return staffDetails_Text + invoice.getInvoiceText();
	}
}
