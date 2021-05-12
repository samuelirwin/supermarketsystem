/**
 * @author Jun Han LIAW
 */

package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class AddEditEmployee_View extends JFrame
{
	/**
	 * Declaration of GUI variables and values.
	 */

	private Container addEditEmployee_Container;
	private JPanel addEditEmployee_Panel, label_Panel, textField_Panel, button_Panel;
	public  JLabel employeeId_Label;
	private JLabel employeeFirstName_Label, employeeLastName_Label, password_Label, confirmPassword_Label,
				   salary_Label, role_Label, active_Label;
	public  JTextField employeeId_TextField, employeeFirstName_TextField, employeeLastName_TextField;
	public  JFormattedTextField salary_TextField;
	public  JPasswordField password_PasswordField, confirmPassword_PasswordField;
	public  JComboBox role_ComboBox, active_ComboBox;
	public  JButton add_Button, save_Button;
	private JButton cancel_Button;
	
	private String[] role = {"Manager", "Staff"};
	private String[] active = {"True", "False"};
	
	private DecimalFormat DecimalFormatter = new DecimalFormat("#.##");
	  
	public AddEditEmployee_View()
	{
		super("Add / Edit Employee");
		
		/**
		 * 1. Create a base panel to include all panels.
		 * 2. Create a label panel to include all labels.
		 * 3. Create a text field panel to include all text fields and combo boxes.
		 * 4. Create a button panel to include all buttons.
		 * 5. Create a container to include the base panel.
		 * 6. Declare any necessary set methods to the frame.
		 */

		/*************************** BASE PANEL - START ***************************/

		addEditEmployee_Panel = new JPanel();
		addEditEmployee_Panel.setLayout(null);
		
		/*************************** BASE PANEL - END *****************************/
		
		/************************** LABEL PANEL - START ***************************/
		  
		label_Panel = new JPanel();
		label_Panel.setLayout(null);
		label_Panel.setLocation(30, 20);
		label_Panel.setSize(120, 305);
        addEditEmployee_Panel.add(label_Panel);
        
        employeeId_Label = new JLabel("Employee ID");
        employeeId_Label.setLocation(0, 0);
        employeeId_Label.setSize(120, 25);
        employeeId_Label.setHorizontalAlignment(SwingConstants.LEFT);
        employeeId_Label.setVisible(false);
        label_Panel.add(employeeId_Label);
        
        employeeFirstName_Label = new JLabel("First Name");
        employeeFirstName_Label.setLocation(0, 40);
        employeeFirstName_Label.setSize(120, 25);
        employeeFirstName_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(employeeFirstName_Label);
        
        employeeLastName_Label = new JLabel("Last Name");
        employeeLastName_Label.setLocation(0, 80);
        employeeLastName_Label.setSize(120, 25);
        employeeLastName_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(employeeLastName_Label);
        
        password_Label = new JLabel("Password");
        password_Label.setLocation(0, 120);
        password_Label.setSize(120, 25);
        password_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(password_Label);
        
        confirmPassword_Label = new JLabel("Confirm Password");
        confirmPassword_Label.setLocation(0, 160);
        confirmPassword_Label.setSize(120, 25);
        confirmPassword_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(confirmPassword_Label);
        
        salary_Label = new JLabel("Salary");
        salary_Label.setLocation(0, 200);
        salary_Label.setSize(120, 25);
        salary_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(salary_Label);
        
        role_Label = new JLabel("Role");
        role_Label.setLocation(0, 240);
        role_Label.setSize(120, 25);
        role_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(role_Label);
        
        active_Label = new JLabel("Active");
        active_Label.setLocation(0, 280);
        active_Label.setSize(120, 25);
        active_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(active_Label);
        
        /************************** LABEL PANEL - END *****************************/
        
        /************************ TEXTFIELD PANEL - START *************************/
        
        textField_Panel = new JPanel();
        textField_Panel.setLayout(null);
        textField_Panel.setLocation(150, 20);
        textField_Panel.setSize(170, 305);
        addEditEmployee_Panel.add(textField_Panel);
        
        employeeId_TextField = new JTextField();
        employeeId_TextField.setLocation(0, 0);
        employeeId_TextField.setSize(170, 25);
        employeeId_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        employeeId_TextField.setEditable(false); 
        employeeId_TextField.setVisible(false);
        textField_Panel.add(employeeId_TextField);
        
        employeeFirstName_TextField = new JTextField();
        employeeFirstName_TextField.setLocation(0, 40);
        employeeFirstName_TextField.setSize(170, 25);
        employeeFirstName_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(employeeFirstName_TextField);
        
        employeeLastName_TextField = new JTextField();
        employeeLastName_TextField.setLocation(0, 80);
        employeeLastName_TextField.setSize(170, 25);
        employeeLastName_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(employeeLastName_TextField);
        
        password_PasswordField = new JPasswordField();
        password_PasswordField.setLocation(0, 120);
        password_PasswordField.setSize(170, 25);
        password_PasswordField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(password_PasswordField);
        
        confirmPassword_PasswordField = new JPasswordField();
        confirmPassword_PasswordField.setLocation(0, 160);
        confirmPassword_PasswordField.setSize(170, 25);
        confirmPassword_PasswordField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(confirmPassword_PasswordField);
        
        salary_TextField = new JFormattedTextField(DecimalFormatter);
        salary_TextField.setLocation(0, 200);
        salary_TextField.setSize(170, 25);
        salary_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(salary_TextField);
        
        role_ComboBox = new JComboBox(role);
        role_ComboBox.setLocation(0, 240);
        role_ComboBox.setSize(170, 25);
        textField_Panel.add(role_ComboBox);
        
        active_ComboBox = new JComboBox(active);
        active_ComboBox.setLocation(0, 280);
        active_ComboBox.setSize(170, 25);
        textField_Panel.add(active_ComboBox);
        
        /************************ TEXTFIELD PANEL - END ***************************/
        
        /************************ BUTTON PANEL - START ****************************/

        button_Panel = new JPanel();
        button_Panel.setLayout(null);
        button_Panel.setLocation(70, 355);
        button_Panel.setSize(200, 25);
        addEditEmployee_Panel.add(button_Panel);
        
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
        
		addEditEmployee_Container = this.getContentPane();
		addEditEmployee_Container.add(addEditEmployee_Panel);
		
		/************************* CONTAINER - END ********************************/
		  
		setBounds(500,170,355,440);
		setResizable(false);
		setVisible(true);
	}
	  
	/**
	 * Listener for add button 
	 * 
	 */
	/** @Requires({listenForAddButton != null}) **/
	public void Listener_AddEmp(ActionListener listenForAddButton)
	{
		add_Button.addActionListener(listenForAddButton);
	}
	
	/**
	 * Listener for save button 
	 * 
	 */
	/** @Requires({listenForSaveButton != null}) **/
	public void Listener_SaveEmp(ActionListener listenForSaveButton)
	{
		save_Button.addActionListener(listenForSaveButton);
	}
	
	/**
	 * Listener for cancel button 
	 * 
	 */
	/** @Requires({listenForCancelButton != null}) **/
	public void Listener_CancelEmp(ActionListener listenForCancelButton)
	{
		cancel_Button.addActionListener(listenForCancelButton);
	}
}
