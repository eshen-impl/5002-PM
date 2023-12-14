package pm.model;


public class EquippableBonus {
	protected Item itemID;
	protected String attribute;
	protected int bonusValue;



	public EquippableBonus(Item itemID, String attribute, int bonusValue) {
		this.itemID = itemID;
		this.attribute = attribute;
		this.bonusValue = bonusValue;
	}

	public EquippableBonus(Item itemID) {
		this.itemID = itemID;
	}

	public EquippableBonus(Item itemID, String attribute) {
		this.itemID = itemID;
		this.attribute = attribute;
	}

	public EquippableBonus(String attribute) {
		this.attribute = attribute;
	}

	public EquippableBonus(int bonusValue) {
		this.bonusValue = bonusValue;
	}

	/** Getters and setters. */
	public Item getItemID() {
		return itemID;
	}

	public void setItemID(Item itemID) {
		this.itemID = itemID;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public int getBonusValue() {
		return bonusValue;
	}

	public void setBonusValue(int bonusValue) {
		this.bonusValue = bonusValue;
	}		
}