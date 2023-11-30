package Project.model;


public class EquippableBonus {
	protected Item itemID;
	protected Attribute attribute;
	protected double bonusValue;
	
	public enum Attribute {
		maxHP, strength, dexterity, vitality, intelligence, mind, criticalHit, determination, directHitRate, defense,
		magicDefense, attackPower, skillSpeed, attackMagicPotency, healingMagicPotency, spellSpeed, averageItemLevel,
		tenacity, piety
	}
	
	public EquippableBonus(Item itemID, Attribute attribute, double bonusValue) {
		this.itemID = itemID;
		this.attribute = attribute;
		this.bonusValue = bonusValue;
	}
	
	public EquippableBonus(Item itemID) {
		this.itemID = itemID;
	}
	
	public EquippableBonus(Item itemID, Attribute attribute) {
		this.itemID = itemID;
		this.attribute = attribute;
	}
	
	public EquippableBonus(Attribute attribute) {
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

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public double getBonusValue() {
		return bonusValue;
	}

	public void setBonusValue(double bonusValue) {
		this.bonusValue = bonusValue;
	}		
}
	