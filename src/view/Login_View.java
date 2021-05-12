/**
 * @author Jun Han LIAW
 */

package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Login_View extends JFrame
{
	/**
	 * Declaration of GUI variables and values.
	 */

	private Container login_Container;
	private JPanel login_Panel, label_Panel, textField_Panel, button_Panel;
	public  JLabel password_Label;
	private JLabel userId_Label;
	public  JTextField userId_TextField;
	public  JPasswordField password_PasswordField;
	private JButton login_Button, cancel_Button;
	  
	public Login_View()
	{
		super("Login");
		
		/**
		 * 1. Create a base panel to include all panels.
		 * 2. Create a label panel to include all labels.
		 * 3. Create a text field panel to include all text fields.
		 * 4. Create a button panel to include all buttons.
		 * 5. Create a container to include the base panel.
		 * 6. Declare any necessary set methods to the frame.
		 */
		
		/*************************** BASE PANEL - START ***************************/

		login_Panel = new JPanel();
		login_Panel.setLayout(null);
		
		/*************************** BASE PANEL - END *****************************/
		
		/************************** LABEL PANEL - START ***************************/
		  
		label_Panel = new JPanel();
		label_Panel.setLayout(null);
		label_Panel.setLocation(45, 20);
		label_Panel.setSize(80, 70);
        login_Panel.add(label_Panel);
        
        userId_Label = new JLabel("User ID");
        userId_Label.setLocation(0, 0);
        userId_Label.setSize(80, 25);
        userId_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(userId_Label);
        
        password_Label = new JLabel("Password");
        password_Label.setLocation(0, 40);
        password_Label.setSize(80, 25);
        password_Label.setHorizontalAlignment(SwingConstants.LEFT);
        password_Label.setVisible(true);
        label_Panel.add(password_Label);
        
        /************************** LABEL PANEL - END *****************************/
        
        /************************ TEXTFIELD PANEL - START *************************/

        textField_Panel = new JPanel();
        textField_Panel.setLayout(null);
        textField_Panel.setLocation(125, 20);
        textField_Panel.setSize(120, 70);
        login_Panel.add(textField_Panel);
        
        userId_TextField = new JTextField();
        userId_TextField.setLocation(0, 0);
        userId_TextField.setSize(120, 25);
        userId_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(userId_TextField);
        
        password_PasswordField = new JPasswordField();
        password_PasswordField.setLocation(0, 40);
        password_PasswordField.setSize(120, 25);
        password_PasswordField.setHorizontalAlignment(SwingConstants.LEFT);
        password_PasswordField.setVisible(true);
        textField_Panel.add(password_PasswordField);
        
        /************************ TEXTFIELD PANEL - END ***************************/
        
        /************************ BUTTON PANEL - START ****************************/

        button_Panel = new JPanel();
        button_Panel.setLayout(null);
        button_Panel.setLocation(45, 110);
        button_Panel.setSize(200, 25);
        login_Panel.add(button_Panel);
        
        login_Button = new JButton("Login");
        login_Button.setLocation(0, 0);
        login_Button.setSize(90, 25);
        login_Button.setHorizontalAlignment(0);
        button_Panel.add(login_Button);
		
        cancel_Button = new JButton("Cancel");
        cancel_Button.setLocation(110, 0);
        cancel_Button.setSize(90, 25);
        cancel_Button.setHorizontalAlignment(0);
        button_Panel.add(cancel_Button);
        
        /************************ BUTTON PANEL - END ******************************/
        
        /************************* CONTAINER - START ******************************/
        
		login_Container = this.getContentPane();
		login_Container.add(login_Panel);
		
		/************************* CONTAINER - END ********************************/
		
		getRootPane().setDefaultButton(login_Button); 
		setBounds(525,275,295,190);
		setResizable(false);
		setVisible(true);
	}
	  
	/**
	 * Add Login button listener
	 * 
	 */
	/** @Requires({listenForLoginButton != null}) **/
	public void Listener_Login(ActionListener listenForLoginButton)
	{
		login_Button.addActionListener(listenForLoginButton);
	}
	
	/**
	 * Add Cancel button listener
	 * 
	 */
	/** @Requires({listenForCancelButton != null}) **/
	public void Listener_CancelLogin(ActionListener listenForCancelButton)
	{
		cancel_Button.addActionListener(listenForCancelButton);
	}
}
