package pm.model;


public class Consumable extends Item {
	protected int itemLevel;
	protected String description;
	
	
	public Consumable(int itemId, String itemName, int maxStackSize, int vendorPrice, boolean canBeSold, int itemLevel,
			String description) {
		super(itemId, itemName, maxStackSize, vendorPrice, canBeSold);
		this.itemLevel = itemLevel;
		this.description = description;
	}


	public Consumable(int itemId) {
		super(itemId);
	}


	public int getItemLevel() {
		return itemLevel;
	}


	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
