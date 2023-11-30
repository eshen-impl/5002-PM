package pm.model;

import java.math.BigDecimal;



/**
 * Equippable is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Item}).
 */
public class Equippable extends Item {
	protected Integer itemLevel;
	protected SlotType slotType;
	protected Integer requiredJobLevel;

	public enum SlotType {
		Mainhand, head, body, hands, legs, feet, offHand, earring, Wrist, ring
	}

	public Equippable(Integer itemID, String itemName, Integer maxStackSize, BigDecimal vendorPrice, Boolean canBeSold, 
			Integer itemLevel, SlotType slotType, Integer requiredJobLevel) {
		super(itemID, itemName, maxStackSize, vendorPrice, canBeSold );
		this.itemLevel = itemLevel;
		this.slotType = slotType;
		this.requiredJobLevel = requiredJobLevel;
	}

	public Equippable(String itemName, Integer maxStackSize, BigDecimal vendorPrice, Boolean canBeSold, 
			Integer itemLevel, SlotType slotType, Integer requiredJobLevel) {
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

	public SlotType getSlotType() {
		return slotType;
	}

	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;
	}

	public Integer getRequiredJobLevel() {
		return requiredJobLevel;
	}

	public void setRequiredJobLevel(Integer requiredJobLevel) {
		this.requiredJobLevel = requiredJobLevel;
	}	
}