package pm.model;


import java.math.BigDecimal;



/**
 * Weapon is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Equippabl}).
 */
public class Weapon extends Equippable {
	protected double damageDone;
	protected double autoAttack;
	protected double attackDelay;
	protected Job associatedJob;

	public Weapon(int itemID, String itemName, int maxStackSize, int vendorPrice, Boolean canBeSold, 
			Integer itemLevel, String slotType, Integer requiredJobLevel,
			double damageDone, double autoAttack, double attackDelay, Job associatedJob) {
		super(itemID, itemName, maxStackSize, vendorPrice, canBeSold,
			  itemLevel, slotType, requiredJobLevel);
		this.damageDone = damageDone;
		this.autoAttack = autoAttack;
		this.attackDelay = attackDelay;
		this.associatedJob = associatedJob;
	}

	public Weapon(Integer itemID,double damageDone, double autoAttack, double attackDelay, Job associatedJob) {
		super(itemID);
		this.damageDone = damageDone;
		this.autoAttack = autoAttack;
		this.attackDelay = attackDelay;
		this.associatedJob = associatedJob;
	}

	public Weapon(Integer itemID) {
		super(itemID);
	}

	/** Getters and setters. */

	public double getDamageDone() {
		return damageDone;
	}

	public void setDamageDone(double damageDone) {
		this.damageDone = damageDone;
	}

	public double getAutoAttack() {
		return autoAttack;
	}

	public void setAutoAttack(double autoAttack) {
		this.autoAttack = autoAttack;
	}

	public double getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(double attackDelay) {
		this.attackDelay = attackDelay;
	}

	public Job getAssociatedJob() {
		return associatedJob;
	}

	public void setAssociatedJob(Job associatedJob) {
		this.associatedJob = associatedJob;
	}	
}