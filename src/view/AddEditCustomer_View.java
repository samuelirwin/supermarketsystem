/**
 * @author Jun Han LIAW
 */

package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class AddEditCustomer_View extends JFrame
{
	/**
	 * Declaration of GUI variables and values.
	 */

	private Container addEditCustomer_Container;
	private JPanel addEditCustomer_Panel, label_Panel, textField_Panel, button_Panel;
	public  JLabel customerId_Label;
	private JLabel customerFirstName_Label, customerLastName_Label, customerContactNo_Label, active_Label;
	public  JTextField customerId_TextField, customerFirstName_TextField,
					  customerLastName_TextField, customerContactNo_TextField;
	public  JComboBox active_ComboBox;
	public  JButton add_Button, save_Button;
	private JButton cancel_Button;
	
	private String[] active = {"True", "False"};
	  
	public AddEditCustomer_View()
	{
		super("Add / Edit Customer");
		
		/**
		 * 1. Create a base panel to include all panels.
		 * 2. Create a label panel to include all labels.
		 * 3. Create a text field panel to include all text fields and combo boxes.
		 * 4. Create a button panel to include all buttons.
		 * 5. Create a container to include the base panel.
		 * 6. Declare any necessary set methods to the frame.
		 */

		/*************************** BASE PANEL - START ***************************/
		
		addEditCustomer_Panel = new JPanel();
		addEditCustomer_Panel.setLayout(null);
		
		/*************************** BASE PANEL - END *****************************/
		
		/************************** LABEL PANEL - START ***************************/
		  
		label_Panel = new JPanel();
		label_Panel.setLayout(null);
		label_Panel.setLocation(30, 20);
		label_Panel.setSize(100, 185);
        addEditCustomer_Panel.add(label_Panel);
        
        customerId_Label = new JLabel("Customer ID");
        customerId_Label.setLocation(0, 0);
        customerId_Label.setSize(100, 25);
        customerId_Label.setHorizontalAlignment(SwingConstants.LEFT);
        customerId_Label.setVisible(false);
        label_Panel.add(customerId_Label);
        
        customerFirstName_Label = new JLabel("First Name");
        customerFirstName_Label.setLocation(0, 40);
        customerFirstName_Label.setSize(100, 25);
        customerFirstName_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(customerFirstName_Label);
        
        customerLastName_Label = new JLabel("Last Name");
        customerLastName_Label.setLocation(0, 80);
        customerLastName_Label.setSize(100, 25);
        customerLastName_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(customerLastName_Label);
        
        customerContactNo_Label = new JLabel("Contact No.");
        customerContactNo_Label.setLocation(0, 120);
        customerContactNo_Label.setSize(100, 25);
        customerContactNo_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(customerContactNo_Label);
        
        active_Label = new JLabel("Active");
        active_Label.setLocation(0, 160);
        active_Label.setSize(100, 25);
        active_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(active_Label);
        
        /************************** LABEL PANEL - END *****************************/
        
        /************************ TEXTFIELD PANEL - START *************************/
        
        textField_Panel = new JPanel();
        textField_Panel.setLayout(null);
        textField_Panel.setLocation(140, 20);
        textField_Panel.setSize(170, 185);
        addEditCustomer_Panel.add(textField_Panel);
        
        customerId_TextField = new JTextField();
        customerId_TextField.setLocation(0, 0);
        customerId_TextField.setSize(170, 25);
        customerId_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        customerId_TextField.setEditable(false); 
        customerId_TextField.setVisible(false);
        textField_Panel.add(customerId_TextField);
        
        customerFirstName_TextField = new JTextField();
        customerFirstName_TextField.setLocation(0, 40);
        customerFirstName_TextField.setSize(170, 25);
        customerFirstName_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(customerFirstName_TextField);
        
        customerLastName_TextField = new JTextField();
        customerLastName_TextField.setLocation(0, 80);
        customerLastName_TextField.setSize(170, 25);
        customerLastName_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(customerLastName_TextField);
        
        customerContactNo_TextField = new JTextField();
        customerContactNo_TextField.setLocation(0, 120);
        customerContactNo_TextField.setSize(170, 25);
        customerContactNo_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(customerContactNo_TextField);
        
        active_ComboBox = new JComboBox(active);
        active_ComboBox.setLocation(0, 160);
        active_ComboBox.setSize(170, 25);
        textField_Panel.add(active_ComboBox);
        
        /************************ TEXTFIELD PANEL - END ***************************/
        
        /************************ BUTTON PANEL - START ****************************/

        button_Panel = new JPanel();
        button_Panel.setLayout(null);
        button_Panel.setLocation(70, 235);
        button_Panel.setSize(200, 25);
        addEditCustomer_Panel.add(button_Panel);
        
        add_Button = new JButton("Add");
        add_Button.setLocation(0, 0);
        add_Button.setSize(90, 25);
        add_Button.setHorizontalAlignment(0);
        add_Button.setVisible(true);
        button_Panel.add(add_Button);
        
        save_Button = new JButton("Save");
        save_Button.setLocation(0, 0);
        save_Button.setSize(90, 25);
        save_Button.setHorizontalAlignment(0);
        save_Button.setVisible(false);
        button_Panel.add(save_Button);
		
        cancel_Button = new JButton("Cancel");
        cancel_Button.setLocation(110, 0);
        cancel_Button.setSize(90, 25);
        cancel_Button.setHorizontalAlignment(0);
        button_Panel.add(cancel_Button);
        
        /************************ BUTTON PANEL - END ******************************/
        
        /************************* CONTAINER - START ******************************/
        
		addEditCustomer_Container = this.getContentPane();
		addEditCustomer_Container.add(addEditCustomer_Panel);
		
		/************************* CONTAINER - END ********************************/
		  
		setBounds(500,225,350,320);
		setResizable(false);
		setVisible(true);
	}
	  
	/**
	 *  Add button listener
	 * 
	 */
	/** @Requires({listenForAddButton != null}) **/
	public void Listener_AddCust(ActionListener listenForAddButton)
	{
		add_Button.addActionListener(listenForAddButton);
	}
	
	/**
	 *  Save button listener
	 * 
	 */
	/** @Requires({listenForSaveButton != null}) **/
	public void Listener_SaveCust(ActionListener listenForSaveButton)
	{
		save_Button.addActionListener(listenForSaveButton);
	}
	
	/**
	 *  Cancel button listener
	 * 
	 */
	/** @Requires({listenForCancelButton != null}) **/
	public void Listener_CancelCust(ActionListener listenForCancelButton)
	{
		cancel_Button.addActionListener(listenForCancelButton);
	}
}
