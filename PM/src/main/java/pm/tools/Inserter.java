package Project.tools;

import Project.dal.*;
import Project.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;



public class Inserter {

	public static void main(String[] args) throws SQLException {
		
		ItemDao itemDao = ItemDao.getInstance();
		EquippableDao equippableDao = EquippableDao.getInstance();
		WeaponDao weaponDao = WeaponDao.getInstance();
		GearDao gearDao = GearDao.getInstance();
		EquippableBonusDao equippableBonusDao = EquippableBonusDao.getInstance();
		
		// INSERT objects from models.
		// 1. Insert data into "Equippable"
		Equippable equi1 = new Equippable(11, "Sword", 5,BigDecimal.ZERO,false, 1, Equippable.SlotType.Mainhand, 1); 
        equi1 = equippableDao.create(equi1);
		
        Equippable equi2 = new Equippable(12, "Bow", 5,BigDecimal.valueOf(110.0),true, 2, Equippable.SlotType.hands, 1); 
        equi2 = equippableDao.create(equi2);
		
        Equippable equi3 = new Equippable(13, "War Hammer", 5, BigDecimal.ZERO, false, 2, Equippable.SlotType.hands, 1); 
        equi3 = equippableDao.create(equi3);
        
        Equippable equi4 = new Equippable(14, "Staff", 5, BigDecimal.valueOf(112), true, 1, Equippable.SlotType.Mainhand, 1); 
        equi4 = equippableDao.create(equi4);
        
        Equippable equi5 = new Equippable(15, "Dagger", 5, BigDecimal.valueOf(113), true, 3, Equippable.SlotType.Mainhand, 2); 
        equi5 = equippableDao.create(equi5);
        
        Equippable equi6 = new Equippable(16, "Plate Armor", 5, BigDecimal.valueOf(114), true, 2, Equippable.SlotType.body, 1); 
        equi6 = equippableDao.create(equi6);
        
        Equippable equi7 = new Equippable(17, "Cloak", 5, BigDecimal.valueOf(115), true, 1, Equippable.SlotType.body, 1); 
        equi7 = equippableDao.create(equi7);
        
        Equippable equi8 = new Equippable(18, "Amulet",5,BigDecimal.valueOf(116),true, 3, Equippable.SlotType.hands, 3); 
        equi8 = equippableDao.create(equi8);
        
        Equippable equi9 = new Equippable(19, "Leather Boots", 5, BigDecimal.valueOf(117), true, 2, Equippable.SlotType.feet, 2); 
        equi9 = equippableDao.create(equi9);
        
        Equippable equi10 = new Equippable(20, "Helmet", 5, BigDecimal.valueOf(118), true, 2, Equippable.SlotType.head, 2); 
        equi10 = equippableDao.create(equi10);
        
		// 2. Insert data into "Weapon"
        
        Weapon weapon1 = new Weapon(equi1.getItemID(),100, 10, 1,job1.getJobID()); 
        weapon1 = weaponDao.create(weapon1);
		
        Weapon weapon2 = new Weapon(equi2.getItemID(),100,5,2,job1.getJobID()); 
        weapon2 = weaponDao.create(weapon2);
		
        Weapon weapon3 = new Weapon(equi3.getItemID(),100,5,3,job2.getJobID()); 
        weapon3 = weaponDao.create(weapon3);
        
        Weapon weapon4 = new Weapon(equi4.getItemID(), 100, 10,3,job3.getJobID()); 
        weapon4 = weaponDao.create(weapon4);
        
        Weapon weapon5 = new Weapon(equi5.getItemID(),100,10,4, job6.getJobID()); 
        weapon5 = weaponDao.create(weapon5);
		
		//3. Insert data into " Gear"
        Gear gear1 = new Gear(equi6.getItemID(), 100.0, 50.0);
        gear1 = gearDao.create(gear1);
        
        Gear gear2 = new Gear(equi7.getItemID(),100.0,50.0);  
        gear2 = gearDao.create(gear2);
        
        Gear gear3 = new Gear(equi8.getItemID(),100.0,50.0); 
        gear3 = gearDao.create(gear3);
        
        Gear gear4 = new Gear(equi9.getItemID(),100.0,50.0);  
        gear4 = gearDao.create(gear4);
        
        Gear gear5 = new Gear(equi10.getItemID(),100.0,50.0); 
        gear5 = gearDao.create(gear5);
		
		// 4. Insert data into "EquippableBonus"
        EquippableBonus equiBonus1 = new EquippableBonus(equi1,EquippableBonus.Attribute.strength,10); 
        equiBonus1 = equippableBonusDao.create(equiBonus1);
        
        EquippableBonus equiBonus2 = new EquippableBonus(equi2,EquippableBonus.Attribute.defense,100); 
        equiBonus2 = equippableBonusDao.create(equiBonus2);
		
        EquippableBonus equiBonus3 = new EquippableBonus(equi3,EquippableBonus.Attribute.attackPower,50); 
        equiBonus3 = equippableBonusDao.create(equiBonus3);
		
        EquippableBonus equiBonus4 = new EquippableBonus(equi4,EquippableBonus.Attribute.strength,100); 
        equiBonus4 = equippableBonusDao.create(equiBonus4);
		
        EquippableBonus equiBonus5 = new EquippableBonus(equi5,EquippableBonus.Attribute.defense,100); 
        equiBonus5 = equippableBonusDao.create(equiBonus5);
        
		// READ
		//1. Get Equippable By ItemID
        Equippable e1 = equippableDao.getEquippableByItemID(11);
		
		//1.2 Get Equippable List By ItemLevel
        List<Equippable> eList1 = equippableDao.getEquippableByItemLevel(1);
        System.out.println("Reading equippable items:");
        for (Equippable e : eList1) {
            System.out.format("ID:%d Name:%s MaxStackSize:%d VendorPrice:%s CanBeSold:%s ItemLevel:%d SlotType:%s RequiredJobLevel:%d\n",
                    e.getItemID(), e.getItemName(), e.getMaxStackSize(), e.getVendorPrice(), e.getCanBeSold(),
                    e.getItemLevel(), e.getSlotType(), e.getRequiredJobLevel());
        }
        
        System.out.println("Reading equippables:");
        for (Equippable e : eList1) {
            System.out.format("ID:%d Name:%s MaxStackSize:%d VendorPrice:%s CanBeSold:%s ItemLevel:%d SlotType:%s RequiredJobLevel:%d\n",
                    e.getItemID(), e.getItemName(), e.getMaxStackSize(), e.getVendorPrice(), e.getCanBeSold(),
                    e.getItemLevel(), e.getSlotType(), e.getRequiredJobLevel());
        }
		
		//2. get Weapon By ItemID
		Weapon w1 = weaponDao.getWeaponByItemID(weapon1.getItemID());
		
		//3. Get Gear By ItemID
	    Gear g1 = gearDao.getGearByItemID(equi1.getItemID());
		
		//4. Get EquippableBonus By ItemID And Attribute
	    EquippableBonus eq1 = equippableBonusDao.getEquippableBonusByItemIDAndAttribute(equi1,EquippableBonus.Attribute.strength);
		
		//5. get EquippableBonus By ItemID
	    List<EquippableBonus> ebList1 = equippableBonusDao.getEquippableBonusByItemID(equi1);
	    System.out.println("Reading equippable bonus:");
	    for (EquippableBonus eb : ebList1) {
	        System.out.format("ID:%d Attribute:%s BonusValue:%f\n",
	                eb.getItemID(), eb.getAttribute());
	    }

	    System.out.println("Reading equippable item bonus:");
	    for (EquippableBonus eb : ebList1) {
	        System.out.format("ID:%d Attribute:%s BonusValue:%f\n",
	                eb.getItemID(), eb.getAttribute(), eb.getBonusValue());
	    }
		
		// Delete
		//1. Delete(Gear itemID)
		try {
		    Gear result = gearDao.delete(gear1);
		    if (result == null) {
		        System.out.println("Gear successfully deleted: " + gear1.getItemID());
		    }
		} catch (SQLException e) {
		    System.out.println("Error occurred during deletion: " + e.getMessage());
		}
	}
}