package pm.model;

import java.math.BigDecimal;



/**
 * Equippable is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Item}).
 */
public class Equippable extends Item {
	protected Integer itemLevel;
	protected String slotType;
	protected Integer requiredJobLevel;



	public Equippable(int itemID, String itemName, int maxStackSize, int vendorPrice, Boolean canBeSold, 
			Integer itemLevel, String slotType, Integer requiredJobLevel) {
		super(itemID, itemName, maxStackSize, vendorPrice, canBeSold );
		this.itemLevel = itemLevel;
		this.slotType = slotType;
		this.requiredJobLevel = requiredJobLevel;
	}

	public Equippable(String itemName, int maxStackSize, int vendorPrice, Boolean canBeSold, 
			Integer itemLevel, String slotType, Integer requiredJobLevel) {
		super(itemName, maxStackSize, vendorPrice, canBeSold );
		this.itemLevel = itemLevel;
		this.slotType = slotType;
		this.requiredJobLevel = requiredJobLevel;
	}

	public Equippable(Integer itemID) {
		super(itemID);
	}

	/** Getters and setters. */

	public Integer getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(Integer itemLevel) {
		this.itemLevel = itemLevel;
	}

	public String getSlotType() {
		return slotType;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public Integer getRequiredJobLevel() {
		return requiredJobLevel;
	}

	public void setRequiredJobLevel(Integer requiredJobLevel) {
		this.requiredJobLevel = requiredJobLevel;
	}

	@Override
	public String toString() {
		return "Equippable [slotType=" + slotType + ", itemId=" + itemId + ", itemName=" + itemName + "]";
	}
	
	
}