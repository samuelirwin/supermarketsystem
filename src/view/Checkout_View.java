/**
 * @author Jun Han LIAW
 */

package view;

import java.text.DecimalFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.*;

import model.Employee;

@SuppressWarnings("serial")
public class Checkout_View extends JFrame
{
	/**
	 * Declaration of GUI variables and values.
	 */

	private Container checkout_Container;
	private JPanel checkout_Panel, label1_Panel, label2_Panel, label3_Panel, label4_Panel, 
		    textField1_Panel, textField2_Panel, textField3_Panel, textField4_Panel,
		    table_Panel, button2_Panel;
	private JMenuBar checkout_MenuBar;
	private JMenu file_Menu;	
	private JMenuItem management_MenuItem, logout_MenuItem, exit_MenuItem;
	private JLabel customerId_Label, customerName_Label, dateTime_Label,
		   		   staffName_Label, itemId_Label, total_Label;
	public  JTextField customerId_TextField, customerName_TextField, dateTime_TextField,
					   staffName_TextField, itemId_TextField;
	public  JFormattedTextField total_TextField;
	public  JTable checkout_Table;
	public  DefaultTableModel checkout_TableModel;
	private JScrollPane checkout_ScrollPane;
	private JButton staffLogin_Button, addItem_Button, deleteItem_Button, checkout_Button;

	public  Employee loggedInEmp;
	private javax.swing.table.TableColumn column = null;
	
	private Object[] checkout_Columns = {"Item ID", "Item Name", "Quantity", "Subtotal"};
	
	private SimpleDateFormat DateFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
	private DecimalFormat DecimalFormatter = new DecimalFormat("#.##");
	  
	public Checkout_View()
	{
		super("SMART Supermarket");
		
		/**
		 * 1. Create a base panel to include all panels.
		 * 2. Create label panels to include all labels.
		 * 3. Create text field panels to include all text fields.
		 * 4. Create a table panel to include table.
		 * 5. Create button panels to include all buttons.
		 * 6. Create a container to include the base panel.
		 * 7. Declare any necessary set methods to the frame.
		 */

		/*************************** BASE PANEL - START ***************************/

		checkout_Panel = new JPanel();
		checkout_Panel.setLayout(null);
		
		/*************************** BASE PANEL - END *****************************/
		
		/************************** LABEL PANEL - START ***************************/
		
		checkout_MenuBar = new JMenuBar();
		file_Menu = new JMenu("File");
		management_MenuItem = new JMenuItem("Management");
		logout_MenuItem = new JMenuItem("Logout");
		exit_MenuItem = new JMenuItem("Exit");
		management_MenuItem.setVisible(false);
		logout_MenuItem.setVisible(false);
		file_Menu.add(management_MenuItem);
		file_Menu.add(logout_MenuItem);
		file_Menu.add(exit_MenuItem);
		checkout_MenuBar.add(file_Menu);
		  
		label1_Panel = new JPanel();
		label1_Panel.setLayout(null);
		label1_Panel.setLocation(25, 15);
		label1_Panel.setSize(100, 70);
        checkout_Panel.add(label1_Panel);
        
        customerId_Label = new JLabel("Customer ID");
        customerId_Label.setLocation(0, 0);
        customerId_Label.setSize(100, 25);
        customerId_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label1_Panel.add(customerId_Label);
        
        customerName_Label = new JLabel("Customer Name");
        customerName_Label.setLocation(0, 35);
        customerName_Label.setSize(100, 25);
        customerName_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label1_Panel.add(customerName_Label);
        
        label2_Panel = new JPanel();
        label2_Panel.setLayout(null);
        label2_Panel.setLocation(375, 15);
        label2_Panel.setSize(80, 70);
        checkout_Panel.add(label2_Panel);
        
        dateTime_Label = new JLabel("Date / Time");
        dateTime_Label.setLocation(0, 0);
        dateTime_Label.setSize(80, 25);
        dateTime_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label2_Panel.add(dateTime_Label);
        
        staffName_Label = new JLabel("Staff Name");
        staffName_Label.setLocation(0, 35);
        staffName_Label.setSize(80, 25);
        staffName_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label2_Panel.add(staffName_Label);
        
        label3_Panel = new JPanel();
        label3_Panel.setLayout(null);
        label3_Panel.setLocation(25, 360);
        label3_Panel.setSize(100, 25);
        checkout_Panel.add(label3_Panel);
        
        itemId_Label = new JLabel("Item ID");
        itemId_Label.setLocation(0, 0);
        itemId_Label.setSize(100, 25);
        itemId_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label3_Panel.add(itemId_Label);
        
        label4_Panel = new JPanel();
        label4_Panel.setLayout(null);
        label4_Panel.setLocation(500, 360);
        label4_Panel.setSize(60, 25);
        checkout_Panel.add(label4_Panel);
        
        total_Label = new JLabel("Total :");
        total_Label.setLocation(0, 0);
        total_Label.setSize(60, 25);
        total_Label.setHorizontalAlignment(SwingConstants.LEFT);
        label4_Panel.add(total_Label);
        
        /************************** LABEL PANEL - END *****************************/
        
        /************************ TEXTFIELD PANEL - START *************************/
        
        textField1_Panel = new JPanel();
        textField1_Panel.setLayout(null);
        textField1_Panel.setLocation(135, 15);
        textField1_Panel.setSize(150, 70);
        checkout_Panel.add(textField1_Panel);
        
        customerId_TextField = new JTextField();
        customerId_TextField.setLocation(0, 0);
        customerId_TextField.setSize(150, 25);
        customerId_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField1_Panel.add(customerId_TextField);
        
        customerName_TextField = new JTextField();
        customerName_TextField.setLocation(0, 35);
        customerName_TextField.setSize(150, 25);
        customerName_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        customerName_TextField.setEditable(false);
        textField1_Panel.add(customerName_TextField);
        
        textField2_Panel = new JPanel();
        textField2_Panel.setLayout(null);
        textField2_Panel.setLocation(460, 15);
        textField2_Panel.setSize(150, 70);
        checkout_Panel.add(textField2_Panel);
        
        dateTime_TextField = new JTextField((DateFormatter.format(new Date())).toString());
        dateTime_TextField.setLocation(0, 0);
        dateTime_TextField.setSize(150, 25);
        dateTime_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        dateTime_TextField.setEditable(false);
        textField2_Panel.add(dateTime_TextField);
        
        staffLogin_Button = new JButton("Staff Login");
        staffLogin_Button.setLocation(0, 35);
        staffLogin_Button.setSize(150, 25);
        staffLogin_Button.setHorizontalAlignment(0);
        textField2_Panel.add(staffLogin_Button);
        
        staffName_TextField = new JTextField();
        staffName_TextField.setLocation(0, 35);
        staffName_TextField.setSize(150, 25);
        staffName_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        staffName_TextField.setEditable(false);
        staffName_TextField.setVisible(false);
        textField2_Panel.add(staffName_TextField);
        
        textField3_Panel = new JPanel();
        textField3_Panel.setLayout(null);
        textField3_Panel.setLocation(135, 360);
        textField3_Panel.setSize(150, 25);
        checkout_Panel.add(textField3_Panel);
        
        itemId_TextField = new JTextField();
        itemId_TextField.setLocation(0, 0);
        itemId_TextField.setSize(150, 25);
        itemId_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        textField3_Panel.add(itemId_TextField);
        
        textField4_Panel = new JPanel();
        textField4_Panel.setLayout(null);
        textField4_Panel.setLocation(560, 360);
        textField4_Panel.setSize(120, 25);
        checkout_Panel.add(textField4_Panel);

        total_TextField = new JFormattedTextField(DecimalFormatter);
        total_TextField.setLocation(0, 0);
        total_TextField.setSize(120, 25);
        total_TextField.setHorizontalAlignment(SwingConstants.LEFT);
        total_TextField.setEditable(false);
        textField4_Panel.add(total_TextField);
        
        /************************ TEXTFIELD PANEL - END ***************************/
        
        /************************* TABLE PANEL - START ****************************/
        
        table_Panel = new JPanel();
        table_Panel.setLayout(new BorderLayout());
        table_Panel.setLocation(0, 90);
        table_Panel.setSize(695, 250);
        checkout_Panel.add(table_Panel);
        
        checkout_Table = new JTable(new DefaultTableModel(checkout_Columns, 0));
        checkout_Table.getTableHeader().setReorderingAllowed(false);
        checkout_TableModel = (DefaultTableModel)checkout_Table.getModel();
        checkout_ScrollPane = new JScrollPane(checkout_Table);
        table_Panel.add(checkout_ScrollPane, BorderLayout.CENTER);
        
        for(int i = 0; i < 4; i++)
        {
        	column = checkout_Table.getColumnModel().getColumn(i);

        	if(i == 0)
            {
            	column.setPreferredWidth(65);
            }
        	else if(i == 1)
            {
            	column.setPreferredWidth(300);
            }
            else if(i == 2)
            {
            	column.setPreferredWidth(20);
            }
            
            column.setResizable(false);
        }
        
        /************************* TABLE PANEL - END ******************************/
        
        /************************ BUTTON PANEL - START ****************************/
        
        button2_Panel = new JPanel();
        button2_Panel.setLayout(null);
        button2_Panel.setLocation(25, 410);
        button2_Panel.setSize(655, 25);
        checkout_Panel.add(button2_Panel);
        
        addItem_Button = new JButton("Add");
        addItem_Button.setLocation(0, 0);
        addItem_Button.setSize(110, 25);
        addItem_Button.setHorizontalAlignment(0);
        button2_Panel.add(addItem_Button);
        
        deleteItem_Button = new JButton("Delete");
        deleteItem_Button.setLocation(149, 0);
        deleteItem_Button.setSize(110, 25);
        deleteItem_Button.setHorizontalAlignment(0);
        deleteItem_Button.setVisible(false);
        button2_Panel.add(deleteItem_Button);
        
        checkout_Button = new JButton("Checkout");
        checkout_Button.setLocation(535, 0);
        checkout_Button.setSize(120, 25);
        checkout_Button.setHorizontalAlignment(0);
        button2_Panel.add(checkout_Button);

        /************************ BUTTON PANEL - END ******************************/
        
        /************************* CONTAINER - START ******************************/
        
        checkout_Container = this.getContentPane();
        checkout_Container.add(checkout_Panel);
        
		/************************* CONTAINER - END ********************************/
		
        getRootPane().setDefaultButton(addItem_Button);
		setBounds(335,130,700,500);
		setJMenuBar(checkout_MenuBar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setEnabled(true);
		setVisible(true);
		
		loggedInEmp = new Employee();
	}
	
	/**
	 * Staff Login button listener
	 * 
	 */
	/** @Requires({listenStaffLogin != null}) **/
	public void Listener_StaffLogin(ActionListener listenStaffLogin)
	{
		staffLogin_Button.addActionListener(listenStaffLogin);
	}
	
	/**
	 * Add Item button listener
	 * 
	 */
	/** @Requires({listenAddItem != null}) **/
	public void Listener_AddItem(ActionListener listenAddItem)
	{
		addItem_Button.addActionListener(listenAddItem);
	}
	
	/**
	 * Delete Item button listener
	 * 
	 */
	/** @Requires({listenDeleteItem != null}) **/
	public void Listener_DeleteItem(ActionListener listenDeleteItem)
	{
		deleteItem_Button.addActionListener(listenDeleteItem);
	}
	
	/**
	 * Checkout button listener
	 * 
	 */
	/** @Requires({listenCheckout != null}) **/
	public void Listener_Checkout(ActionListener listenCheckout)
	{
		checkout_Button.addActionListener(listenCheckout);
	}
	
	/**
	 * Logout button listener
	 * 
	 */
	/** @Requires({listenLogout != null}) **/
	public void Listener_Logout(ActionListener listenLogout)
	{
		logout_MenuItem.addActionListener(listenLogout);
	}
	
	/**
	 * Management menu listener
	 * 
	 */
	/** @Requires({ListenManagement != null}) **/
	public void Listener_Management(ActionListener ListenManagement)
	{
		management_MenuItem.addActionListener(ListenManagement);
	}
		
	
	/**
	 * Exit menu listener
	 * 
	 */
	/** @Requires({listenExit != null}) **/
	public void Listener_Exit(ActionListener listenExit)
	{
		exit_MenuItem.addActionListener(listenExit);
	}
	
	/**
	 * Initialize Staff Checkout View
	 *
	 */
	/** @Requires({emp != null}) **/
	public void InitializeStaffCheckoutView(Employee emp) {
		
		 loggedInEmp = emp;
		 management_MenuItem.setVisible(true);
		 logout_MenuItem.setVisible(true); 
		 staffName_TextField.setVisible(true);
		 staffName_TextField.setText(loggedInEmp.getFirstName()+" "+loggedInEmp.getLastName()
				 						+ " - "+loggedInEmp.getEmpRole());
		 staffLogin_Button.setVisible(false);
		 deleteItem_Button.setVisible(true);		 
	}
	
	/**
	 * Initialize Default Checkout View
	 *
	 */
	public void InitializeDefaultCheckoutView() {
		
		 loggedInEmp = null;
		 management_MenuItem.setVisible(false);
		 logout_MenuItem.setVisible(false); 
		 staffName_TextField.setVisible(false);
		 staffName_TextField.setText("");
		 staffLogin_Button.setVisible(true);
		 deleteItem_Button.setVisible(false);
	}
}
