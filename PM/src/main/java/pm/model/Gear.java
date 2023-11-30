package Project.model;



import java.math.BigDecimal;



/**
 * Gear is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Equippable}).
 */
public class Gear extends Equippable {
	protected double defenseRating;
	protected double magicDefenseRating;
	
	
	public Gear(Integer itemID, String itemName, Integer maxStackSize, BigDecimal vendorPrice, Boolean canBeSold, 
			Integer itemLevel, SlotType slotType, Integer requiredJobLevel,
			double defenseRating, double magicDefenseRating) {
		super(itemID, itemName, maxStackSize, vendorPrice, canBeSold,
			  itemLevel, slotType, requiredJobLevel);
		this.defenseRating = defenseRating;
		this.magicDefenseRating = magicDefenseRating;
	}

	public Gear(Integer itemID,double defenseRating, double magicDefenseRating) {
		super(itemID);
		this.defenseRating = defenseRating;
		this.magicDefenseRating = magicDefenseRating;
	}
	
	public Gear(Integer itemID) {
		super(itemID);
	}

	/** Getters and setters. */
	
	public double getDefenseRating() {
		return defenseRating;
	}


	public void setDefenseRating(double defenseRating) {
		this.defenseRating = defenseRating;
	}


	public double getMagicDefenseRating() {
		return magicDefenseRating;
	}


	public void setMagicDefenseRating(double magicDefenseRating) {
		this.magicDefenseRating = magicDefenseRating;
	}
}
	