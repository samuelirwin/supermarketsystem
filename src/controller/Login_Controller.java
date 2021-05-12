/**
 * @author Jun Han LIAW
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;



import model.Employee;
import model.EmployeeSQL;
import model.HibernateDB_Connector;

import org.hibernate.Session;

import controller.Main_Controller.StaffLogin;
import view.Checkout_View;
import view.Login_View;

public class Login_Controller {
	
	private Login_View Login_View; 
	private EmployeeSQL EmployeeSQL;
	private Employee EmployeeObject;
	private StaffLogin StaffLogin;
	
	/** @Requires({login_View != null && staffLogin != null}) **/
	public Login_Controller(Login_View login_View, StaffLogin staffLogin)
	{
		this.StaffLogin = staffLogin;
		this.Login_View = login_View;
		this.Login_View.Listener_Login(new LoginListener());
		this.Login_View.Listener_CancelLogin(new CancelListener());
		
		this.EmployeeSQL = new EmployeeSQL();
	}	
	

//******************************************
// LOGIN VIEW LISTENERS
//******************************************	
	/**
	  * Listens the the login button
	  * 
	  */
	 class LoginListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			String id = Login_View.userId_TextField.getText();
			String password = Login_View.password_PasswordField.getText(); //getPassword()
			
			if(!verifyEmptyLogin(id, password)){return;}
			
			Session session = HibernateDB_Connector.GetOrCreateSession();
			
			EmployeeObject = EmployeeSQL.VerifyLogin(id, password, session);
			
			if(EmployeeObject!=null){
				
				//If Employee is active
				if(EmployeeObject.getEmpActive()== true){
					
					Login_View.setVisible(false);
					JOptionPane.showMessageDialog(null,"Hello, "+EmployeeObject.getFirstName()+
													" "+EmployeeObject.getLastName()+"!","Welcome!",1);
					
					resetLogin();
					StaffLogin.InitializeStaffCheckoutView(EmployeeObject);
				}
				else{
					JOptionPane.showMessageDialog(null, "User is no longer active", "Error", 0);
				}		
			}
			
			else{
				JOptionPane.showMessageDialog(null, "Username or Password are incorrect.", "Error", 0);
			}
		}
	 }
			
	 
	 /**
	  * Listens the exit button
	  * 
	  */
	 class CancelListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Login_View.setVisible(false);
			resetLogin();
		}	 
	 }
//*************************************
//   LOGIN LISTENER END
//*************************************	 
	
	 public void resetLogin(){
		 Login_View.userId_TextField.setText("");
		 Login_View.password_PasswordField.setText("");
	 }
	 
	 public boolean verifyEmptyLogin(String id, String password) {
		 if(id.isEmpty()){
				JOptionPane.showMessageDialog(null,"Please enter your User ID","Error",0);
				return false;
			}
			
		 else if(password.isEmpty()){
				JOptionPane.showMessageDialog(null,"Please enter your Password","Error",0);
				return false;
			}
		 return true;
	 }

}
