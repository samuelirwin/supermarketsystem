/**
 * @author Sharma Irwin
 */
package view;

import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Report_View extends JFrame
{
	/**
	 * Declaration of GUI variables and values.
	 */

	private Container report_Container;
	private JPanel report_Panel, button_Panel;
	private JButton chart_Button, pdf_Button, HTML_Button, exit_Button;
	private JMenuBar report_MenuBar;
	private JMenu file_Menu;
	private JMenuItem print_MenuItem;
	
	public Report_View()
	{
		super("Report Option Screen");
		
		report_MenuBar = new JMenuBar();
		file_Menu = new JMenu("File");		
		print_MenuItem = new JMenuItem("Print");
		file_Menu.add(print_MenuItem);
		report_MenuBar.add(file_Menu);
		
		report_Panel = new JPanel();
		report_Panel.setLayout(null);
		report_Panel.setLocation(25, 20);
		report_Panel.setSize(145, 398);
		
		button_Panel = new JPanel();
		button_Panel.setLayout(null);
		button_Panel.setLocation(25, 20);
		button_Panel.setSize(145, 398);
		report_Panel.add(button_Panel);
		               
		chart_Button = new JButton("Chart");
		chart_Button.setLocation(0, 0);
		chart_Button.setSize(145, 40);
		chart_Button.setHorizontalAlignment(0);
		button_Panel.add(chart_Button);
        
		pdf_Button = new JButton("PDF");
		pdf_Button.setLocation(0, 60);
		pdf_Button.setSize(145, 40);
		pdf_Button.setHorizontalAlignment(0);
		button_Panel.add(pdf_Button);
        
		HTML_Button = new JButton("HTML");
		HTML_Button.setLocation(0, 120);
		HTML_Button.setSize(145, 40);
		HTML_Button.setHorizontalAlignment(0);
		button_Panel.add(HTML_Button);
		
		exit_Button = new JButton("Exit");
        exit_Button.setLocation(0, 358);
        exit_Button.setSize(145, 40);
        exit_Button.setHorizontalAlignment(0);
        button_Panel.add(exit_Button);
		
		report_Container = this.getContentPane();
		report_Container.add(report_Panel);
		
		setBounds(335,130,700,500);
		setJMenuBar(report_MenuBar);
		setResizable(false);
		setVisible(true);		
	}
		
	/**
	 * Add Chart Button listener
	 * 
	 */
	/** @Requires({listenChartButton != null}) **/
	public void Listener_Chart(ActionListener listenChartButton)
	{
		chart_Button.addActionListener(listenChartButton);
	}
	
	/**
	 * Add PDF Button listener
	 * 
	 */
	/** @Requires({listenPDFButton != null}) **/
	public void Listener_PDF(ActionListener listenPDFButton)
	{
		pdf_Button.addActionListener(listenPDFButton);
	}
	
	/**
	 * Add HTML Button listener
	 * 
	 */
	/** @Requires({listenHTMLButton != null}) **/
	public void Listener_HTML(ActionListener listenHTMLButton)
	{
		chart_Button.addActionListener(listenHTMLButton);
	}
	
	/**
	 * Add Exit Button listener
	 * 
	 */
	/** @Requires({listenExitButton != null}) **/
	public void Listener_Exit(ActionListener listenExitButton)
	{
		exit_Button.addActionListener(listenExitButton);
	}
}