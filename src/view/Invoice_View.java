/**
 * @author Jun Han LIAW
 */

package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Invoice_View extends JFrame
{
	/**
	 * Declaration of GUI variables and values.
	 */

	private Container invoice_Container;
	private JPanel invoice_Panel, textArea_Panel, button_Panel;
	public  JTextArea invoice_TextArea;
	private JButton Ok_Button;
	
	private Border border = BorderFactory.createLineBorder(Color.BLACK);
	  
	public Invoice_View()
	{
		super("Invoice");
		
		/**
		 * 1. Create a base panel to include all panels.
		 * 2. Create a text area panel to include text area.
		 * 3. Create a button panel to include button.
		 * 4. Create a container to include the base panel.
		 * 5. Declare any necessary set methods to the frame.
		 */
		
		/*************************** BASE PANEL - START ***************************/

		invoice_Panel = new JPanel();
		invoice_Panel.setLayout(null);
		
		/*************************** BASE PANEL - END *****************************/
		
		/************************* TEXTAREA PANEL - START *************************/
        
		textArea_Panel = new JPanel();
		textArea_Panel.setLayout(null);
		textArea_Panel.setLocation(20, 20);
		textArea_Panel.setSize(355, 390);
        invoice_Panel.add(textArea_Panel);
        
        invoice_TextArea = new JTextArea();
        invoice_TextArea.setLocation(0, 0);
        invoice_TextArea.setSize(355, 390);
        invoice_TextArea.setBorder(BorderFactory.createCompoundBorder(
        						   border, BorderFactory.createEmptyBorder(0, 0, 10, 10)));
        invoice_TextArea.setEditable(false);
        textArea_Panel.add(invoice_TextArea);
        
		/************************* TEXTAREA PANEL - END ***************************/
        
        /************************ BUTTON PANEL - START ****************************/

        button_Panel = new JPanel();
        button_Panel.setLayout(null);
        button_Panel.setLocation(150, 430);
        button_Panel.setSize(90, 25);
        invoice_Panel.add(button_Panel);
        
        Ok_Button = new JButton("OK");
        Ok_Button.setLocation(0, 0);
        Ok_Button.setSize(90, 25);
        Ok_Button.setHorizontalAlignment(0);
        button_Panel.add(Ok_Button);
        
        /************************ BUTTON PANEL - END ******************************/
        
        /************************* CONTAINER - START ******************************/
        
		invoice_Container = this.getContentPane();
		invoice_Container.add(invoice_Panel);
		
		/************************* CONTAINER - END ********************************/
		  
		setBounds(475,140,400,500);
		setResizable(false);
		setVisible(true);
	}
	  
	/**
	 * Add Ok button listener
	 * 
	 */
	/** @Requires({listenForOkButton != null}) **/
	public void Listener_Ok(ActionListener listenForOkButton)
	{
		Ok_Button.addActionListener(listenForOkButton);
	}
}
