package pm.tools;

import pm.dal.*;
import pm.model.*;
import pm.model.Character;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.util.Date;

import java.util.List;





public class Driver {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		PlayerDao playerDao = PlayerDao.getInstance();
		CharacterDao characterDao = CharacterDao.getInstance();
		JobDao jobDao = JobDao.getInstance();
		CurrencyDao currencyDao = CurrencyDao.getInstance();
		ItemDao itemDao = ItemDao.getInstance();
		EquippableDao equippableDao = EquippableDao.getInstance();
		WeaponDao weaponDao = WeaponDao.getInstance();
		GearDao gearDao = GearDao.getInstance();
		EquippableBonusDao equippableBonusDao = EquippableBonusDao.getInstance();
		ConsumableDao consumableDao = ConsumableDao.getInstance();
		MiscellaneousDao miscellaneousDao = MiscellaneousDao.getInstance();
		ConsumableBonusDao consumableBonusDao = ConsumableBonusDao.getInstance();

		// test ItemDao
		Item item = new Item("TestItem", 10, 100, true);
		item = itemDao.create(item);
		Item item1 = itemDao.getItemById(1);
		System.out.format("Item : id: %s name:%s mss:%s vp:%s cbs:%s \n",item1.getItemId().item1.getItemName(),item1.getMaxStackSize(),item1.getVendorPrice(), item1.getCanBeSold() );
		List<Item> items = itemDao.getItemsByMaxStackSize(10);
		for (Item i: items) {
			System.out.format("items: name:%s mss:%s vp:%s cbs:%s \n",
					i.getItemName(),i.getMaxStackSize(),i.getVendorPrice(), i.getCanBeSold());
		};

		// test ConsumableDao
		Consumable consumable1 = new Consumable(2,"consumable1", 5, 50, true, 1, "Test Description");
		Consumable consumable2 = new Consumable(3,"consumable2", 5, 100, true, 1, "Test Description1");
		consumable1 = consumableDao.create(consumable1);
		consumable2 = consumableDao.create(consumable2);
		Consumable consumable_ = consumableDao.getConsumablesById(2);
		System.out.format("cons : id:%s name:%s level:%s dp:%s \n", consumable_.getItemId(),consumable_.getItemName(),consumable_.getItemLevel(),consumable_.getDescription());
		List<Consumable> consumables = consumableDao.getConsumableByItemLevel(1);
		for (Consumable cons:consumables) {
			System.out.format("consList : id:%s name:%s level:%s dp:%s \n", cons.getItemId(),cons.getItemName(),cons.getItemLevel(),cons.getDescription());
		}

		// test Miscellaneous
		Miscellaneous miscellaneous1 = new Miscellaneous(4,"Miscellaneous1", 5, 50, true, "Description1");
		Miscellaneous miscellaneous2 = new Miscellaneous(5,"Miscellaneous2", 5, 75, true, "Description2");
		Miscellaneous miscellaneous3 = new Miscellaneous(6,"Miscellaneous3", 5, 100, true, "Description1");
		miscellaneous1 = miscellaneousDao.create(miscellaneous1);
		miscellaneous2 = miscellaneousDao.create(miscellaneous2);
		miscellaneous3 = miscellaneousDao.create(miscellaneous3);

		Miscellaneous miscellaneous = miscellaneousDao.getMiscellaneoussById(5);
		System.out.format("mis: id:%s name:%s dp:%s \n",miscellaneous.getItemId(),miscellaneous.getItemName(),miscellaneous.getDescription());
		List<Miscellaneous> miscellaneousList = miscellaneousDao.getMiscellaneousByDescription("Description1");
		for (Miscellaneous mis:miscellaneousList) {
			System.out.format("misList : id:%s name:%s dp:%s \n", mis.getItemId(),mis.getItemName(),mis.getDescription());
		}

		// test ConsumableBonus
		Item item_ = new Item("N", 5, 50, true);
		item_ = itemDao.create(item_);
		ConsumableBonus consumableBonus = new ConsumableBonus(item_, "Strength", BigDecimal.valueOf(0.1), 100);
		consumableBonus = consumableBonusDao.create(consumableBonus);
		ConsumableBonus consumableBonus_ = consumableBonusDao.getConsumableBonusByItemAttribute(item_, "Strength");
		System.out.format("bonus: id:%s attr:%s percentage:%s \n",consumableBonus_.getItem().getItemId(),consumableBonus_.getAttribute(),consumableBonus_.getBonusPercentage());
		List<ConsumableBonus> consumableBonuses = consumableBonusDao.getConsumableBonusesByBonusCap(100);
		for (ConsumableBonus consBonus: consumableBonuses) {
			System.out.format("bonuses: id:%s attr:%s percentage:%s \n", consBonus.getItem().getItemId(), consBonus.getAttribute(), consBonus.getBonusPercentage());
		}

		// test PlayerDao
		Player newPlayer = new Player( "katy", "katy@sina.com", true);
		newPlayer = playerDao.create(newPlayer);
		int exPlayerId = 33;
		Player exPlayer = playerDao.getPlayerByID(exPlayerId);
		System.out.println("Reading player: " + exPlayer);
		
		// test CharacterDao
		Character newCharacter = new Character(newPlayer, "Patti", "Lulu");
		newCharacter = characterDao.create(newCharacter);
		int exCharacterId = 4;
		Character exCharacter = characterDao.getCharacterById(exCharacterId);
		System.out.println("Reading character: " + exCharacter);
		List<Character> characters = characterDao.getCharactersForPlayer(exPlayer);
		System.out.println("Looping characters:  " );
		for(Character c : characters) {
			System.out.println(c);
		}
		characterDao.delete(exCharacter);
		
		
		// test JobDao
		Job newJob = new Job( "Archer", 1, 0, 1200);
		newJob = jobDao.create(newJob);
		int exJobId = 6;
		Job exJob = jobDao.getJobByID(exJobId);
		System.out.println("Reading job: " + exJob);
		
		//test CurrencyDao
		Currency newCurrency = new Currency("Voucher", 1000000, 50000, false);
		newCurrency = currencyDao.create(newCurrency);
		String exCurrencyName = "Diamonds";
		Currency exCurrency = currencyDao.getCurrencyById(exCurrencyName);
		System.out.println("Reading currency: " + exCurrency);
		int newWeeklyCap = 240000;
		exCurrency = currencyDao.updateWeeklyCap(exCurrency, newWeeklyCap);
		exCurrency = currencyDao.getCurrencyById(exCurrencyName);
		System.out.println("Reading updated currency: " + exCurrency);
	}
	
	
	
	
	
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
	
	

