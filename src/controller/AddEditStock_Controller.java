/**
 * @author Eng Sun CHEW
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;






import model.Item;
import model.ItemSQL;
import view.AddEditStock_View;
import controller.Management_Controller.AddManageListener;
import controller.Management_Controller.EditManageListener;

public class AddEditStock_Controller {
	
	private AddEditStock_View AddEditStock_View; 
	private AddManageListener AddManageListener;
	private EditManageListener EditManageListener;
	private ItemSQL ItemSQL;
	private Item Item;
	
	/** @Requires({addeditStock != null && amclass != null}) **/
	public AddEditStock_Controller(AddEditStock_View addeditStock, AddManageListener amclass)
	{
		this.AddEditStock_View = addeditStock;
		this.AddManageListener = amclass;
		this.AddEditStock_View.Listener_AddItem(new AddItem());
		this.AddEditStock_View.Listener_CancelItem(new CancelItem());
		
		this.ItemSQL = new ItemSQL();
	}
	
	/** @Requires({addeditStock != null && emclass != null}) **/
	public AddEditStock_Controller(AddEditStock_View addeditStock, EditManageListener emclass)
	{
		this.AddEditStock_View = addeditStock;
		this.EditManageListener = emclass;
		this.AddEditStock_View.Listener_SaveItem(new SaveItem());
		this.AddEditStock_View.Listener_CancelItem(new CancelItem());
		
		this.ItemSQL = new ItemSQL();
	}	
	

//******************************************
// Add Item Listener START
//******************************************	
	/**
	  * Listens to the add button
	  * 
	  */
	 class AddItem implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			String itemName = AddEditStock_View.itemName_TextField.getText();
			String category = (String) AddEditStock_View.category_ComboBox.getSelectedItem();
			String priceString = AddEditStock_View.price_TextField.getText();
			String quantityString = AddEditStock_View.quantity_TextField.getText();
			String active = (String) AddEditStock_View.active_ComboBox.getSelectedItem();
			
			if(!verifyEmptyField(itemName, priceString, quantityString))
				return;
			
			double price = Double.parseDouble(priceString);
			int quantity = Integer.parseInt(quantityString);
			boolean status = convertActiveToBoolean(active);
			
			if(ItemSQL.AddItem(itemName, category, price, quantity, status))
				JOptionPane.showMessageDialog(null,"Item "+itemName+" is added to database.","Item Added",1);
			else
				JOptionPane.showMessageDialog(null, "Item failed to add into database, something went wrong.", "Error", 0);
			
			AddEditStock_View.setVisible(false);
			resetAddItemView();
			AddManageListener.reloadItemTable();
		}
			
	 }
	 
	 /**
	  * Listens to the save button
	  * 
	  */
	 class SaveItem implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			String id = AddEditStock_View.itemId_TextField.getText();
			String itemName = AddEditStock_View.itemName_TextField.getText();
			String category = (String) AddEditStock_View.category_ComboBox.getSelectedItem();
			String priceString = AddEditStock_View.price_TextField.getText();
			String quantityString = AddEditStock_View.quantity_TextField.getText();
			String active = (String) AddEditStock_View.active_ComboBox.getSelectedItem();
			
			if(!verifyEmptyField(itemName, priceString, quantityString))
				return;
			
			double price = Double.parseDouble(priceString);
			int quantity = Integer.parseInt(quantityString);
			boolean status = convertActiveToBoolean(active);
			
			if(ItemSQL.UpdateItem(id, itemName, category, price, quantity, status))
				JOptionPane.showMessageDialog(null,"Edited item "+itemName+" is saved to database.","Item Added",1);
			else
				JOptionPane.showMessageDialog(null, "Item failed to save into database, something went wrong.", "Error", 0);
			
			AddEditStock_View.setVisible(false);
			resetAddItemView();
			EditManageListener.reloadItemTable();
		}
			
	 }
			
	 
	 /**
	  * Listens the cancel button
	  * 
	  */
	 class CancelItem implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			AddEditStock_View.setVisible(false);
			resetAddItemView();
		}	 
	 }
//*************************************
//   Add Customer Listener END
//*************************************	 

//*************************************
//   Non Listeners functions
//*************************************
	 
	
	 public void resetAddItemView(){
		 AddEditStock_View.itemName_TextField.setText("");
		 AddEditStock_View.price_TextField.setText("");
		 AddEditStock_View.quantity_TextField.setText("");
	 }
	 
	 public boolean convertActiveToBoolean(String active){
		 if(active.equals("True"))
			 return true;
		 else
			 return false;
	 }
	 
	 public boolean verifyEmptyField(String itemName, String price, String quantity) {
		 	
		 	if(itemName.isEmpty()|| quantity.contains(" ")){
				JOptionPane.showMessageDialog(null, "Item name cannot be empty", "Error", 0);
				return false;
			}
			
		 	if(price.isEmpty()|| quantity.contains(" ")){
				JOptionPane.showMessageDialog(null, "Price cannot be empty", "Error", 0);
				return false;
			}
			
			if(quantity.isEmpty() || quantity.contains(" ")){
				JOptionPane.showMessageDialog(null, "Quantity cannot be empty", "Error", 0);
				return false;
			}
			return true;
	 }
	 
}
