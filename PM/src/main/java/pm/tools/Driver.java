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
		GearJobDao gearJobDao = GearJobDao.getInstance();
		CharacterAttributeDao characterAttributeDao = CharacterAttributeDao.getInstance();
		CustomizationDao customizationDao = CustomizationDao.getInstance();
		
		
		// test ItemDao
		Item item = new Item("TestItem", 10, 100, true);
		item = itemDao.create(item);
		Item item1 = itemDao.getItemById(1);
		System.out.format("Item : id: %s name:%s mss:%s vp:%s cbs:%s \n",item1.getItemId(),item1.getItemName(),item1.getMaxStackSize(),item1.getVendorPrice(), item1.getCanBeSold() );
		item1= itemDao.updateItemName(item1, "newName");
		System.out.format("Item : id: %s name:%s mss:%s vp:%s cbs:%s \n",item1.getItemId(),item1.getItemName(),item1.getMaxStackSize(),item1.getVendorPrice(), item1.getCanBeSold() );
		itemDao.delete(item);
		List<Item> items = itemDao.getItemsByMaxStackSize(10);
		for (Item i: items) {
			System.out.format("items with maxStackSize=10: name:%s mss:%s vp:%s cbs:%s \n",
					i.getItemName(),i.getMaxStackSize(),i.getVendorPrice(), i.getCanBeSold());
		};

		// test ConsumableDao
		Consumable consumable1 = new Consumable("consumable1", 5, 50, true, 1, "Test Description");
		Consumable consumable2 = new Consumable("consumable2", 5, 100, true, 1, "Test Description1");
		consumable1 = consumableDao.create(consumable1);
		consumable2 = consumableDao.create(consumable2);
		Consumable consumable_ = consumableDao.getConsumablesById(2);
		System.out.format("cons : id:%s name:%s level:%s dp:%s \n", consumable_.getItemId(),consumable_.getItemName(),consumable_.getItemLevel(),consumable_.getDescription());
		consumableDao.updateItemLevel(consumable_, 24);
		System.out.format("cons(new level): id:%s name:%s level:%s dp:%s \n", consumable_.getItemId(),consumable_.getItemName(),consumable_.getItemLevel(),consumable_.getDescription());
		consumableDao.delete(consumable2);
		List<Consumable> consumables = consumableDao.getConsumableByItemLevel(1);
		for (Consumable cons:consumables) {
			System.out.format("consList(itemlevel=1): id:%s name:%s level:%s dp:%s \n", cons.getItemId(),cons.getItemName(),cons.getItemLevel(),cons.getDescription());
		}

		// test Miscellaneous
		Miscellaneous miscellaneous1 = new Miscellaneous(4,"Miscellaneous1", 5, 50, true, "Description1");
		Miscellaneous miscellaneous2 = new Miscellaneous(5,"Miscellaneous2", 5, 75, true, "Description2");
		Miscellaneous miscellaneous3 = new Miscellaneous(6,"Miscellaneous3", 5, 100, true, "Description1");
		miscellaneous1 = miscellaneousDao.create(miscellaneous1);
		miscellaneous2 = miscellaneousDao.create(miscellaneous2);
		miscellaneous3 = miscellaneousDao.create(miscellaneous3);

		Miscellaneous miscellaneous = miscellaneousDao.getMiscellaneoussById(6);
		System.out.format("mis: id:%s name:%s dp:%s \n",miscellaneous.getItemId(),miscellaneous.getItemName(),miscellaneous.getDescription());
		miscellaneousDao.updateDescription(miscellaneous2, "updated");
		System.out.format("mis: id:%s name:%s dp:%s \n",miscellaneous2.getItemId(),miscellaneous.getItemName(),miscellaneous.getDescription());
		miscellaneousDao.delete(miscellaneous3);
		
		List<Miscellaneous> miscellaneousList = miscellaneousDao.getMiscellaneousByDescription("Description1");
		for (Miscellaneous mis:miscellaneousList) {
			System.out.format("misList : id:%s name:%s dp:%s \n", mis.getItemId(),mis.getItemName(),mis.getDescription());
		}

		// test ConsumableBonus
	
		Consumable consumable3 = new Consumable("N", 5, 50, true, 1, "Test Description1");
		consumable3 = consumableDao.create(consumable3);
		ConsumableBonus consumableBonus = new ConsumableBonus(consumable3, "strength", BigDecimal.valueOf(0.1), 100);
		ConsumableBonus consumableBonus2 = new ConsumableBonus(consumable3, "attackPower", BigDecimal.valueOf(0.5), 100);

		consumableBonus = consumableBonusDao.create(consumableBonus);
		consumableBonus = consumableBonusDao.create(consumableBonus2);
		ConsumableBonus consumableBonus_ = consumableBonusDao.getConsumableBonusByItemAttribute(consumable3, "strength");
		System.out.format("bonus: id:%s attr:%s percentage:%s \n",consumableBonus_.getItem().getItemId(),consumableBonus_.getAttribute(),consumableBonus_.getBonusPercentage());
		consumableBonusDao.updateBonusPercentage(consumableBonus_, new BigDecimal(2));
		System.out.format("bonus: id:%s attr:%s percentage:%s \n",consumableBonus_.getItem().getItemId(),consumableBonus_.getAttribute(),consumableBonus_.getBonusPercentage());
		consumableBonusDao.delete(consumableBonus_);
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

		
		// INSERT objects from models.
		// 1. Insert data into "Equippable"
		Equippable equi1 = new Equippable( "Sword", 5,0,false, 1, "Main hand", 1); 
	    equi1 = equippableDao.create(equi1);
	
	    Equippable equi2 = new Equippable("Bow", 5,110,true, 2, "Main hand", 1); 
	    equi2 = equippableDao.create(equi2);
	
	    Equippable equi3 = new Equippable("War Hammer", 5, 0, false, 2, "Main hand", 1); 
	    equi3 = equippableDao.create(equi3);
	
	    Equippable equi4 = new Equippable("Staff", 5, 112, true, 1, "Main hand", 1); 
	    equi4 = equippableDao.create(equi4);

	
	    Equippable equi5 = new Equippable( "Bow2", 5, 118, true, 2, "Main hand", 2); 
	    equi5 = equippableDao.create(equi5);
	
		// 2. Insert data into "Weapon"
	
	    
	    Weapon weapon1 = new Weapon("Sword1", 5,0,false, 1, "Main hand", 1, 100, 10, 1,newJob); 
	    weapon1 = weaponDao.create(weapon1);
	
	    Weapon weapon2 = new Weapon("Bow3", 5,110,true, 2, "Main hand", 1,100,5,2,newJob); 
	    weapon2 = weaponDao.create(weapon2);
	
	    Weapon weapon3 = new Weapon("War Hammer", 5, 0, false, 2, "Main hand", 1,100,5,3,newJob); 
	    weapon3 = weaponDao.create(weapon3);
	
	    Weapon weapon4 = new Weapon("Staff", 5, 112, true, 1, "Main hand", 1, 100, 10,3,newJob); 
	    weapon4 = weaponDao.create(weapon4);
	
	    Weapon weapon5 = new Weapon("Bow2", 5, 118, true, 2, "Main hand", 2,100,10,4, newJob); 
	    weapon5 = weaponDao.create(weapon5);

		//3. Insert data into " Gear"
	    Gear gear1 = new Gear("Dagger", 5, 113, true, 3, "head", 2, 100.0, 50.0);
	    gear1 = gearDao.create(gear1);
	
	    Gear gear2 = new Gear("Plate Armor", 5, 114, true, 2, "body", 1,100.0,50.0);  
	    gear2 = gearDao.create(gear2);
	
	    Gear gear3 = new Gear("Cloak", 5, 115, true, 1, "body", 1,100.0,50.0); 
	    gear3 = gearDao.create(gear3);
	
	    Gear gear4 = new Gear("Amulet",5,116,true, 3, "hands", 3,100.0,50.0);  
	    gear4 = gearDao.create(gear4);
	
	    Gear gear5 = new Gear("Leather Boots", 5, 117, true, 2, "feet", 2,100.0,50.0); 
	    gear5 = gearDao.create(gear5);
	
		// 4. Insert data into "EquippableBonus"
	    EquippableBonus equiBonus1 = new EquippableBonus(equi1,"strength",10); 
	    equiBonus1 = equippableBonusDao.create(equiBonus1);
	
	    EquippableBonus equiBonus2 = new EquippableBonus(equi2,"defense",100); 
	    equiBonus2 = equippableBonusDao.create(equiBonus2);
	
	    EquippableBonus equiBonus3 = new EquippableBonus(equi3,"attackPower",50); 
	    equiBonus3 = equippableBonusDao.create(equiBonus3);
	
	    EquippableBonus equiBonus4 = new EquippableBonus(equi4,"strength",100); 
	    equiBonus4 = equippableBonusDao.create(equiBonus4);
	
	    EquippableBonus equiBonus5 = new EquippableBonus(equi5,"defense",100); 
	    equiBonus5 = equippableBonusDao.create(equiBonus5);
	
	  //insert data into GearJob
	    GearJob gearJob1 = new GearJob(gear1,newJob);
	    gearJob1 = gearJobDao.create(gearJob1);
	    
	    GearJob gearJob2 = new GearJob(gear2,newJob);
	    gearJob2 = gearJobDao.create(gearJob2);
	    
	    GearJob gearJob3 = new GearJob(gear3,newJob);
	    gearJob3 = gearJobDao.create(gearJob3);
	    
	    GearJob gearJob4 = new GearJob(gear4,newJob);
	    gearJob4 = gearJobDao.create(gearJob4);
	    
	    GearJob gearJob5 = new GearJob(gear5,newJob);
	    gearJob5 = gearJobDao.create(gearJob5);
	    
	    GearJob gj = gearJobDao.getGearJobByItemIdJobId(39, 7);
	    
	    System.out.println("Reading GearJob: " + gj);
	    gearJobDao.deleteGearJob(gearJob5);
	    
	    //insert data into CharacterAttribute
	    CharacterAttribute characterAttribute1 = new CharacterAttribute(newCharacter,"strength",10);
	    characterAttribute1 = characterAttributeDao.create(characterAttribute1);
	    		
	    CharacterAttribute characterAttribute2 = new CharacterAttribute(newCharacter,"intelligence",21);
	    characterAttribute2 = characterAttributeDao.create(characterAttribute2);
	    
	    CharacterAttribute characterAttribute3 = new CharacterAttribute(newCharacter,"magicDefense",5);
	    characterAttribute3 = characterAttributeDao.create(characterAttribute3);
	    
	    CharacterAttribute ca = characterAttributeDao.getCharacterAttributeByIds(1001, "intelligence");
	    System.out.println("Reading CharacterAttribute: " + ca);
	    characterAttributeDao.delete(characterAttribute3);

	    // insert into Customization
	    
	    Customization customization1 = new Customization(equi3,"Red",0.88f,"High",newCharacter);
	    customization1 = customizationDao.create(customization1) ;
	    
	    Customization customization2 = new Customization(equi3,"Red",0.50f,"High",newCharacter);
	    customization1 = customizationDao.create(customization2) ;
	    
	    Customization customization3 = new Customization(equi2,"Yellow",0.2f,"Normal",newCharacter);
	    customization1 = customizationDao.create(customization3) ;
	    
	    Customization customization4 = new Customization(gear2,"Yellow",0.9f,"Normal",newCharacter);
	    customization4 = customizationDao.create(customization4) ;
	    
	    Customization customization5 = new Customization(gear2,"Green",1.0f,"Normal",newCharacter);
	    customization5 = customizationDao.create(customization5) ;
	    
	    Customization cst = customizationDao.getCustomizationById(1);
	    System.out.println("Reading Customization: " + cst);
	    customizationDao.delete(customization5);
	    
	    
		// READ
		//1. Get Equippable By ItemID
	    Equippable e1 = equippableDao.getEquippableByItemID(11);

	    System.out.println("Reading equippable:");
	    System.out.format("ID:%d Name:%s MaxStackSize:%d VendorPrice:%s CanBeSold:%s ItemLevel:%d SlotType:%s RequiredJobLevel:%d\n",
                e1.getItemId(), e1.getItemName(), e1.getMaxStackSize(), e1.getVendorPrice(), e1.getCanBeSold(),
                e1.getItemLevel(), e1.getSlotType(), e1.getRequiredJobLevel());
    
		//1.2 Get Equippable List By ItemLevel
	    List<Equippable> eList1 = equippableDao.getEquippableByItemLevel(1);
	    System.out.println("Reading equippable items:");
	    for (Equippable e : eList1) {
	        System.out.format("ID:%d Name:%s MaxStackSize:%d VendorPrice:%s CanBeSold:%s ItemLevel:%d SlotType:%s RequiredJobLevel:%d\n",
	                e.getItemId(), e.getItemName(), e.getMaxStackSize(), e.getVendorPrice(), e.getCanBeSold(),
	                e.getItemLevel(), e.getSlotType(), e.getRequiredJobLevel());
	    }
	
	   
		//2. get Weapon By ItemID
		Weapon w1 = weaponDao.getWeaponByItemID(weapon1.getItemId());
		System.out.println("Reading weapon: " + w1 );
	    
	
		//3. Get Gear By ItemID
	    Gear g1 = gearDao.getGearByItemID(equi1.getItemId());
	
		//4. Get EquippableBonus By ItemID And Attribute
	    EquippableBonus eq1 = equippableBonusDao.getEquippableBonusByItemIDAndAttribute(equi1,"strength");
	    System.out.format("ID:%d Attribute:%s BonusValue:%.2f\n",
                eq1.getItemID().getItemId(), eq1.getAttribute(), eq1.getBonusValue());
	
		//5. get EquippableBonus By ItemID
	    List<EquippableBonus> ebList1 = equippableBonusDao.getEquippableBonusByItemID(itemDao.getItemById(11));
	
	    System.out.println("Reading equippable item bonus:");
	    for (EquippableBonus eb : ebList1) {
	        System.out.format("ID:%d Attribute:%s BonusValue:%.2f\n",
	                eb.getItemID().getItemId(), eb.getAttribute(), eb.getBonusValue());
	    }
	
		// Delete
		//1. Delete(Gear itemID)
		try {
		    Gear result = gearDao.delete(gear1);
		    if (result == null) {
		        System.out.println("Gear successfully deleted weapon id = : " + gear1.getItemId());
		    }
		} catch (SQLException e) {
		    System.out.println("Error occurred during deletion: " + e.getMessage());
		}
		
		
		
	}
	
	
	
	
	
		


}
	
	

