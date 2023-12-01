package pm.model;


public class EquippableBonus {
	protected Item itemID;
	protected String attribute;
	protected double bonusValue;



	public EquippableBonus(Item itemID, String attribute, double bonusValue) {
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

	public EquippableBonus(double bonusValue) {
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

	public double getBonusValue() {
		return bonusValue;
	}

	public void setBonusValue(double bonusValue) {
		this.bonusValue = bonusValue;
	}		
}