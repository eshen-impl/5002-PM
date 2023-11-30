package pm.model;



public class Item {
	protected int itemId;
	protected String itemName;
	protected int maxStackSize;
	protected int vendorPrice;
	protected boolean canBeSold;
	
	

	public Item(int itemId, String itemName, int maxStackSize, int vendorPrice, boolean canBeSold) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.maxStackSize = maxStackSize;
		this.vendorPrice = vendorPrice;
		this.canBeSold = canBeSold;
	}
	
	
	
	

	public Item(int itemId) {
		this.itemId = itemId;
	}



	public Item(String itemName, int maxStackSize, int vendorPrice, boolean canBeSold) {
		this.itemName = itemName;
		this.maxStackSize = maxStackSize;
		this.vendorPrice = vendorPrice;
		this.canBeSold = canBeSold;
	}





	public int getItemId() {
		return itemId;
	}





	public void setItemId(int itemId) {
		this.itemId = itemId;
	}





	public String getItemName() {
		return itemName;
	}





	public void setItemName(String itemName) {
		this.itemName = itemName;
	}





	public int getMaxStackSize() {
		return maxStackSize;
	}





	public void setMaxStackSize(int maxStackSize) {
		this.maxStackSize = maxStackSize;
	}





	public int getVendorPrice() {
		return vendorPrice;
	}





	public void setVendorPrice(int vendorPrice) {
		this.vendorPrice = vendorPrice;
	}





	public boolean getCanBeSold() {
		return canBeSold;
	}





	public void setCanBeSold(boolean canBeSold) {
		this.canBeSold = canBeSold;
	}



	
}
