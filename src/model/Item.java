/**
 * @author Eng Sun CHEW
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table (name = "Item")
public class Item {

	private int Item_ID;
	private String Item_Name;
	private String Category;
	private int Quantity;
	private double Price;
	private boolean Item_Active;
	
	public Item() {	
		
	}
	
	public Item(int Item_ID, String Item_Name, String Category, double Price, int Quantity, boolean status) {
		this.Item_ID = Item_ID;
		this.Item_Name = Item_Name;
		this.Category = Category;
		this.Quantity = Quantity;
		this.Price = Price;
		this.Item_Active = status;
		
	}

	/** @Ensures({Item_ID != null}) **/
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "Item_ID", unique = true, nullable = false)
	public int getItemID() {
		return this.Item_ID;
	}

	/** @Requires({itemID != null}) **/
	public void setItemID(int itemID) {
		this.Item_ID = itemID;
	}
	
	/** @Ensures({Item_Name != null}) **/
	@Column (name = "Item_Name")
	public String getItemName() {
		return Item_Name;
	}

	/** @Requires({itemname != null}) **/
	public void setItemName(String itemname) {
		this.Item_Name = itemname;
	}

	/** @Ensures({Category != null}) **/
	@Column (name = "Item_Category")
	public String getCategory() {
		return Category;
	}

	/** @Requires({category != null}) **/
	public void setCategory(String category) {
		this.Category = category;
	}

	/** @Ensures({Quantity != null}) **/
	@Column (name = "Item_Quantity")
	public int getQuantity() {
		return this.Quantity;
	}

	/** @Requires({quantity != null}) **/
	public void setQuantity(int quantity) {
		this.Quantity = quantity;
	}

	/** @Ensures({Price != null}) **/
	@Column (name = "Item_Price")
	public double getPrice() {
		return this.Price;
	}

	/** @Requires({price != null}) **/
	public void setPrice(double price) {
		this.Price = price;
	}

	/** @Ensures({Item_Active != null}) **/
	@Column (name = "Item_Active")
	@Type(type = "numeric_boolean")
	public boolean getItemActive() {
		return this.Item_Active;
	}

	/** @Requires({status != null}) **/
	public void setItemActive(boolean status) {
		this.Item_Active = status;
	}

}
