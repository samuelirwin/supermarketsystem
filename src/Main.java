import javax.swing.UIManager;

import controller.Main_Controller;
import view.*;


public class Main {

	public static void main(String[] args)
	{
		
		try {
	         UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	      } catch (Exception e) {}
		
		Checkout_View checkoutView = new Checkout_View();
		Main_Controller mainController = new Main_Controller(checkoutView);
		
	}
	
}
