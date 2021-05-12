/**
 * @author Jun Han LIAW
 */

package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class AddEditStock_View extends JFrame
{
	/**
	 * Declaration of GUI variables and values.
	 */

	private Container addEditStock_Container;
	private JPanel addEditStock_Panel, label_Panel, textField_Panel, button_Panel;
	public  JLabel itemId_Label;
	private JLabel itemName_Label, category_Label, price_Label, quantity_Label, active_Label;
	public  JTextField itemId_TextField, itemName_TextField;
	public  JFormattedTextField price_TextField, quantity_TextField;
	public  JComboBox category_ComboBox, active_ComboBox;
	public  JButton add_Button, save_Button;
	private JButton cancel_Button;
	
	private String[] category = {"Fruits", "Vegetables", "Meat", "Drinks", "Dairy Products", "Hardware"};
	private String[] active = {"True", "False"};
	
	private DecimalFormat DecimalFormatter = new DecimalFormat("#.##");
	private DecimalFormat NumberFormatter = new DecimalFormat("#");
	  
	public AddEditStock_View()
	{
		super("Add / Edit Stock");
		
		/**
		 * 1. Create a base panel to include all panels.
		 * 2. Create a label panel to include all labels.
		 * 3. Create a text field panel to include all text fields and combo boxes.
		 * 4. Create a button panel to include all buttons.
		 * 5. Create a container to include the base panel.
		 * 6. Declare any necessary set methods to the frame.
		 */

		/*************************** BASE PANEL - START ***************************/

		addEditStock_Panel = new JPanel();
		addEditStock_Panel.setLayout(null);

		/*************************** BASE PANEL - END *****************************/
		
		/************************** LABEL PANEL - START ***************************/
		  
		label_Panel = new JPanel();
		label_Panel.setLayout(null);
		label_Panel.setLocation(30, 20);
		label_Panel.setSize(100, 225);
        addEditStock_Panel.add(label_Panel);
        
        itemId_Label = new JLabel("Item ID");
        itemId_Label.setLocation(0, 0);
        itemId_Label.setSize(100, 25);
        itemId_Label.setHorizontalAlignment(SwingConstants.LEFT);
        itemId_Label.setVisible(false);
        label_Panel.add(itemId_Label);
        
        itemName_Label = new JLabel("Item Name");
        itemName_Label.setLocation(0, 40);
        itemName_Label.setSize(100, 25);
        itemName_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(itemName_Label);
        
        category_Label = new JLabel("Item Category");
        category_Label.setLocation(0, 80);
        category_Label.setSize(100, 25);
        category_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(category_Label);
        
        price_Label = new JLabel("Price");
        price_Label.setLocation(0, 120);
        price_Label.setSize(100, 25);
        price_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(price_Label);
        
        quantity_Label = new JLabel("Quantity");
        quantity_Label.setLocation(0, 160);
        quantity_Label.setSize(100, 25);
        quantity_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(quantity_Label);
        
        active_Label = new JLabel("Active");
        active_Label.setLocation(0, 200);
        active_Label.setSize(100, 25);
        active_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label_Panel.add(active_Label);
        
        /************************** LABEL PANEL - END *****************************/
        
        /************************ TEXTFIELD PANEL - START *************************/
        
        textField_Panel = new JPanel();
        textField_Panel.setLayout(null);
        textField_Panel.setLocation(140, 20);
        textField_Panel.setSize(170, 225);
        addEditStock_Panel.add(textField_Panel);
        
        itemId_TextField = new JTextField();
        itemId_TextField.setLocation(0, 0);
        itemId_TextField.setSize(170, 25);
        itemId_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        itemId_TextField.setEditable(false); 
        itemId_TextField.setVisible(false);
        textField_Panel.add(itemId_TextField);
        
        itemName_TextField = new JTextField();
        itemName_TextField.setLocation(0, 40);
        itemName_TextField.setSize(170, 25);
        itemName_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(itemName_TextField);
        
        category_ComboBox = new JComboBox(category);
        category_ComboBox.setLocation(0, 80);
        category_ComboBox.setSize(170, 25);
        textField_Panel.add(category_ComboBox);
        
        price_TextField = new JFormattedTextField(DecimalFormatter);
        price_TextField.setLocation(0, 120);
        price_TextField.setSize(170, 25);
        price_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(price_TextField);
        
        quantity_TextField = new JFormattedTextField(NumberFormatter);
        quantity_TextField.setLocation(0, 160);
        quantity_TextField.setSize(170, 25);
        quantity_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField_Panel.add(quantity_TextField);
        
        active_ComboBox = new JComboBox(active);
        active_ComboBox.setLocation(0, 200);
        active_ComboBox.setSize(170, 25);
        textField_Panel.add(active_ComboBox);
        
        /************************ TEXTFIELD PANEL - END ***************************/
        
        /************************ BUTTON PANEL - START ****************************/

        button_Panel = new JPanel();
        button_Panel.setLayout(null);
        button_Panel.setLocation(70, 275);
        button_Panel.setSize(200, 25);
        addEditStock_Panel.add(button_Panel);
        
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
        
		addEditStock_Container = this.getContentPane();
		addEditStock_Container.add(addEditStock_Panel);
		
		/************************* CONTAINER - END ********************************/
		  
		setBounds(500,210,350,360);
		setResizable(false);
		setVisible(true);
	}
	  
	/**
	 * Adding of the add item listener 
	 * 
	 */
	/** @Requires({listenForAddButton != null}) **/
	public void Listener_AddItem(ActionListener listenForAddButton)
	{
		add_Button.addActionListener(listenForAddButton);
	}
	
	/**
	 * Adding of the save item listener 
	 * 
	 */
	/** @Requires({listenForSaveButton != null}) **/
	public void Listener_SaveItem(ActionListener listenForSaveButton)
	{
		save_Button.addActionListener(listenForSaveButton);
	}
	
	
	/**
	 * Adding of the cancel item listener 
	 * 
	 */
	/** @Requires({listenForCancelButton != null}) **/
	public void Listener_CancelItem(ActionListener listenForCancelButton)
	{
		cancel_Button.addActionListener(listenForCancelButton);
	}
}
