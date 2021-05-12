/**
 * @author Jun Han LIAW
 */

package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Management_View extends JFrame
{
	/**
	 * Declaration of GUI variables and values.
	 */

	private Container management_Container;
	private JPanel management_Panel, button1_Panel, button2_Panel, table_Panel;
	public  JTable management_Table;
	public  DefaultTableModel management_TableModel;
	private JScrollPane management_ScrollPane;
	public  JButton employee_Button, viewTransaction_Button, generateReport_Button;
	private JButton customer_Button, stock_Button, exit_Button, add_Button, edit_Button;
	
	public  String[] customer_Columns = {"Cust ID", "First Name", "Last Name", "Contact No.", "Active"};
	public  String[] employee_Columns = {"Emp ID", "First Name", "Last Name", "Salary", "Role", "Active"};
	public  String[] stock_Columns = {"Item ID", "Item Name", "Category", "Price", "Quantity", "Active"};
	public  String[] transaction_Columns = {"Transaction ID", "Customer ID", "Employee ID", "Amount $", "Date"};
	  
	public Management_View()
	{
		super("Management");
		
		/**
		 * 1. Create a base panel to include all panels.
		 * 2. Create a table panel to include table.
		 * 3. Create button panels to include all buttons.
		 * 4. Create a container to include the base panel.
		 * 5. Declare any necessary set methods to the frame.
		 */
		
		/*************************** BASE PANEL - START ***************************/

		management_Panel = new JPanel();
		management_Panel.setLayout(null);
		
		/*************************** BASE PANEL - END *****************************/
		
        /************************* TABLE PANEL - START ****************************/
        
        table_Panel = new JPanel();
        table_Panel.setLayout(new BorderLayout());
        table_Panel.setLocation(195, 20);
        table_Panel.setSize(500, 400);
        management_Panel.add(table_Panel);
        
        management_Table = new JTable(new DefaultTableModel(customer_Columns, 0));
        management_Table.getTableHeader().setReorderingAllowed(false);
        management_TableModel = (DefaultTableModel)management_Table.getModel();      
        management_ScrollPane = new JScrollPane(management_Table);
        table_Panel.add(management_ScrollPane, BorderLayout.CENTER);

        management_TableModel.setColumnIdentifiers(employee_Columns);
        
        /************************* TABLE PANEL - END ******************************/
        
        /************************ BUTTON PANEL - START ****************************/
        
        button1_Panel = new JPanel();
        button1_Panel.setLayout(null);
        button1_Panel.setLocation(25, 20);
        button1_Panel.setSize(145, 398);
        management_Panel.add(button1_Panel);
        
        customer_Button = new JButton("Customer");
        customer_Button.setLocation(0, 0);
        customer_Button.setSize(145, 40);
        customer_Button.setHorizontalAlignment(0);
        button1_Panel.add(customer_Button);
        
        stock_Button = new JButton("Stock");
        stock_Button.setLocation(0, 60);
        stock_Button.setSize(145, 40);
        stock_Button.setHorizontalAlignment(0);
        button1_Panel.add(stock_Button);
        
        employee_Button = new JButton("Employee");
        employee_Button.setLocation(0, 120);
        employee_Button.setSize(145, 40);
        employee_Button.setHorizontalAlignment(0);
        button1_Panel.add(employee_Button);
        
        viewTransaction_Button = new JButton("Transaction");
        viewTransaction_Button.setLocation(0, 180);
        viewTransaction_Button.setSize(145, 40);
        viewTransaction_Button.setHorizontalAlignment(0);
        button1_Panel.add(viewTransaction_Button);
        
        generateReport_Button = new JButton("Report");
        generateReport_Button.setLocation(0, 240);
        generateReport_Button.setSize(145, 40);
        generateReport_Button.setHorizontalAlignment(0);
        button1_Panel.add(generateReport_Button);
        
        exit_Button = new JButton("Exit");
        exit_Button.setLocation(0, 358);
        exit_Button.setSize(145, 40);
        exit_Button.setHorizontalAlignment(0);
        button1_Panel.add(exit_Button);
        
        button2_Panel = new JPanel();
        button2_Panel.setLayout(null);
        button2_Panel.setLocation(300, 430);
        button2_Panel.setSize(290, 25);
        management_Panel.add(button2_Panel);
        
        add_Button = new JButton("Add");
        add_Button.setLocation(0, 0);
        add_Button.setSize(140, 25);
        add_Button.setHorizontalAlignment(0);
        button2_Panel.add(add_Button);
        
        edit_Button = new JButton("Edit");
        edit_Button.setLocation(150, 0);
        edit_Button.setSize(140, 25);
        edit_Button.setHorizontalAlignment(0);
        button2_Panel.add(edit_Button);
        
        /************************ BUTTON PANEL - END ******************************/
        
        /************************* CONTAINER - START ******************************/
        
        management_Container = this.getContentPane();
        management_Container.add(management_Panel);
        
		/************************* CONTAINER - END ********************************/
		  
		setBounds(335,130,700,500);
		setResizable(false);
		setVisible(true);
	}
	  
	/**
	 * Customer button listener
	 * 
	 */
	/** @Requires({listenCustomerButton != null}) **/
	public void Listener_CustomerManage(ActionListener listenCustomerButton)
	{
		customer_Button.addActionListener(listenCustomerButton);
	}
	
	/**
	 * Stock button listener
	 * 
	 */
	/** @Requires({listenStockButton != null}) **/
	public void Listener_StockManage(ActionListener listenStockButton)
	{
		stock_Button.addActionListener(listenStockButton);
	}
	
	/**
	 * Employee button listener
	 * 
	 */
	/** @Requires({listenEmployeeButton != null}) **/
	public void Listener_EmployeeManage(ActionListener listenEmployeeButton)
	{
		employee_Button.addActionListener(listenEmployeeButton);
	}
	
	/**
	 * Transaction button listener
	 * 
	 */
	/** @Requires({listenTransButton != null}) **/
	public void Listener_TransactionManage(ActionListener listenTransButton)
	{
		viewTransaction_Button.addActionListener(listenTransButton);
	}
	
	/**
	 * Generate Report button listener
	 * 
	 */
	/** @Requires({listenGenerateReport != null}) **/
	public void Listener_GenerateReport(ActionListener listenGenerateReport)
	{
		generateReport_Button.addActionListener(listenGenerateReport);
	}
	
	/**
	 * Add button listener
	 * 
	 */
	/** @Requires({listenAddButton != null}) **/
	public void Listener_AddManage(ActionListener listenAddButton)
	{
		add_Button.addActionListener(listenAddButton);
	}
	
	/**
	 * Edit button listener
	 * 
	 */
	/** @Requires({listenEditButton != null}) **/
	public void Listener_EditManage(ActionListener listenEditButton)
	{
		edit_Button.addActionListener(listenEditButton);
	}
	
	/**
	 * Exit button listener
	 * 
	 */
	/** @Requires({listenExitButton != null}) **/
	public void Listener_ExitManage(ActionListener listenExitButton)
	{
		exit_Button.addActionListener(listenExitButton);
	}
}
