package pm.model;


public class Miscellaneous extends Item {
	protected String description;
	


	public Miscellaneous(int itemId, String itemName, int maxStackSize, int vendorPrice, boolean canBeSold,
			String description) {
		super(itemId, itemName, maxStackSize, vendorPrice, canBeSold);
		this.description = description;
	}


	


	public Miscellaneous(int itemId) {
		super(itemId);
	}





	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
