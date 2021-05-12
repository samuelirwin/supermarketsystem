/**
 * @author Jun Han LIAW
 */

package view;

import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Pay_View extends JFrame
{
	/**
	 * Declaration of GUI variables and values.
	 */

	private Container pay_Container;
	private JPanel pay_Panel, label_Panel, textField_Panel, button_Panel;
	private JLabel total_Label, amountPaid_Label, change_Label;
	public  JFormattedTextField total_TextField, amountPaid_TextField, change_TextField;
	private JButton pay_Button, cancel_Button;
	
	private DecimalFormat DecimalFormatter = new DecimalFormat("#.##");
	  
	public Pay_View()
	{
		super("Please Pay");
		
		/**
		 * 1. Create a base panel to include all panels.
		 * 2. Create a label panel to include all labels.
		 * 3. Create a text field panel to include all text fields.
		 * 4. Create a button panel to include all buttons.
		 * 5. Create a container to include the base panel.
		 * 6. Declare any necessary set methods to the frame.
		 */
		
		/*************************** BASE PANEL - START ***************************/

		pay_Panel = new JPanel();
		pay_Panel.setLayout(null);
		
		/*************************** BASE PANEL - END *****************************/
		
		/************************** LABEL PANEL - START ***************************/
		  
		label_Panel = new JPanel();
		label_Panel.setLayout(null);
		label_Panel.setLocation(45, 20);
		label_Panel.setSize(100, 105);
        pay_Panel.add(label_Panel);
        
        total_Label = new JLabel("Total                   $");
        total_Label.setLocation(0, 0);
        total_Label.setSize(100, 25);
        total_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(total_Label);
        
        amountPaid_Label = new JLabel("Amount Paid    $");
        amountPaid_Label.setLocation(0, 40);
        amountPaid_Label.setSize(100, 25);
        amountPaid_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(amountPaid_Label);
        
        change_Label = new JLabel("Change              $");
        change_Label.setLocation(0, 80);
        change_Label.setSize(100, 25);
        change_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(change_Label);
        
        /************************** LABEL PANEL - END *****************************/
        
        /************************ TEXTFIELD PANEL - START *************************/
        
        textField_Panel = new JPanel();
        textField_Panel.setLayout(null);
        textField_Panel.setLocation(145, 20);
        textField_Panel.setSize(100, 105);
        pay_Panel.add(textField_Panel);
        
        total_TextField = new JFormattedTextField(DecimalFormatter);
        total_TextField.setLocation(0, 0);
        total_TextField.setSize(100, 25);
        total_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        total_TextField.setEditable(false);
        textField_Panel.add(total_TextField);
        
        amountPaid_TextField = new JFormattedTextField(DecimalFormatter);
        amountPaid_TextField.setLocation(0, 40);
        amountPaid_TextField.setSize(100, 25);
        amountPaid_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(amountPaid_TextField);
        
        change_TextField = new JFormattedTextField(DecimalFormatter);
        change_TextField.setLocation(0, 80);
        change_TextField.setSize(100, 25);
        change_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        change_TextField.setEditable(false);
        textField_Panel.add(change_TextField);
        
        /************************ TEXTFIELD PANEL - END ***************************/
        
        /************************ BUTTON PANEL - START ****************************/

        button_Panel = new JPanel();
        button_Panel.setLayout(null);
        button_Panel.setLocation(45, 150);
        button_Panel.setSize(200, 25);
        pay_Panel.add(button_Panel);
        
        pay_Button = new JButton("Pay");
        pay_Button.setLocation(0, 0);
        pay_Button.setSize(90, 25);
        pay_Button.setHorizontalAlignment(0);
        button_Panel.add(pay_Button);
		
        cancel_Button = new JButton("Cancel");
        cancel_Button.setLocation(109, 0);
        cancel_Button.setSize(90, 25);
        cancel_Button.setHorizontalAlignment(0);
        button_Panel.add(cancel_Button);
        
        /************************ BUTTON PANEL - END ******************************/
        
        /************************* CONTAINER - START ******************************/
        
		pay_Container = this.getContentPane();
		pay_Container.add(pay_Panel);
		
		/************************* CONTAINER - END ********************************/
		  
		setBounds(525,250,295,230);
		setResizable(false);
		setVisible(true);
	}
	  
	/**
	 * Add Pay Button listener 
	 * 
	 */
	/** @Requires({listenForPayButton != null}) **/
	public void Listener_Pay(ActionListener listenForPayButton)
	{
		pay_Button.addActionListener(listenForPayButton);
	}
	
	/**
	 * Add Cancel Button listener
	 * 
	 */
	/** @Requires({listenForCancelButton != null}) **/
	public void Listener_Cancel(ActionListener listenForCancelButton)
	{
		cancel_Button.addActionListener(listenForCancelButton);
	}
}
