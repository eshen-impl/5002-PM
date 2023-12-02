-- Create new schema
DROP SCHEMA IF EXISTS CS5200Project;
CREATE SCHEMA CS5200Project;
USE CS5200Project;

-- Create tables
-- The 'Player' table stores player information.
CREATE TABLE Player (
  accountId INT AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  emailAddress VARCHAR(255) NOT NULL,
  isActive BOOL DEFAULT TRUE,
  CONSTRAINT pk_Player_accountId PRIMARY KEY (accountId)
);

-- The 'Character' table stores character information for each player, and a player can have one or more characters.
CREATE TABLE `Character` (
  characterId INT AUTO_INCREMENT,
  accountId INT NOT NULL,
  characterFirstName VARCHAR(255) NOT NULL,
  characterLastName VARCHAR(255) NOT NULL,
  CONSTRAINT pk_Character_characterId PRIMARY KEY (characterId),
  CONSTRAINT fk_Character_accountId FOREIGN KEY (accountId)
    REFERENCES Player(accountId)
	ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT uq_Character_characterFirstName_characterLastName UNIQUE (characterFirstName, characterLastName)
);

-- The 'Job' table contains all job-related information.
CREATE TABLE Job (
  jobId INT AUTO_INCREMENT,
  jobName VARCHAR(50) NOT NULL,
  jobLevel SMALLINT NOT NULL,
  minLevelExp BIGINT NOT NULL,
  maxLevelExp BIGINT NOT NULL,
  CONSTRAINT pk_Job_jobId PRIMARY KEY (jobId)
);

-- The 'Currency' table holds data on various currencies, with the 'discontinued' column indicating whether a specific currency is still in use.
CREATE TABLE Currency (
  currencyName VARCHAR(30),
  totalCap BIGINT NOT NULL,
  weeklyCap INT NOT NULL,
  discontinued BOOL DEFAULT FALSE,
  CONSTRAINT pk_Currency_currencyName PRIMARY KEY (currencyName)
);

-- The 'Attributes' table stores various attribute types for characters, such as Strength and Dexterity.
CREATE TABLE Attributes (
  attribute VARCHAR(50) NOT NULL,
  CONSTRAINT pk_Attributes_attribute PRIMARY KEY (attribute)
);

-- The 'Item table' serves as the parent table and includes common properties for four subtypes: gear, weapons, consumables, and miscellaneous.
CREATE TABLE Item(
  itemId INT AUTO_INCREMENT,
  itemName VARCHAR(255) NOT NULL,
  maxStackSize INT NOT NULL,
  vendorPrice INT,
  canBeSold BOOLEAN DEFAULT TRUE,
  CONSTRAINT pk_Item_itemId PRIMARY KEY (itemId)
);

-- The 'Consumable' table is dedicated to storing consumable items.
CREATE TABLE Consumable(
  itemId INT NOT NULL,
  itemLevel INT NOT NULL,
  `description` TEXT NOT NULL,
  CONSTRAINT pk_Consumable_itemId PRIMARY KEY (itemId),
  CONSTRAINT fk_Consumable_itemId FOREIGN KEY (itemId) 
    REFERENCES Item (itemId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

-- The 'Miscellaneous' table is reserved for miscellaneous items.
CREATE TABLE Miscellaneous(
  itemId INT NOT NULL,
  `description` TEXT NOT NULL,
  CONSTRAINT pk_Miscellaneous_itemId PRIMARY KEY (itemId),
  CONSTRAINT fk_Miscellaneous_itemId FOREIGN KEY (itemId) 
    REFERENCES Item (itemId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

-- The 'ConsumableBonus' table contains information about attribute bonuses for consumable items, identifying them with various attributes.
CREATE TABLE ConsumableBonus(
  itemId INT NOT NULL,
  attribute VARCHAR(50) NOT NULL,
  bonusPercentage DECIMAL NOT NULL,
  bonusCap BIGINT NOT NULL,
  CONSTRAINT pk_ConsumableBonus_itemId_attribute PRIMARY KEY (itemId,attribute),
  CONSTRAINT fk_ConsumableBonus_itemId FOREIGN KEY (itemId)
    REFERENCES Consumable(itemId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_ConsumableBonus_attribute FOREIGN KEY (attribute)
    REFERENCES Attributes(attribute)
    ON UPDATE CASCADE ON DELETE CASCADE
);

-- The 'SlotTypes' table stores information about equipment slots. Instead of using enumerations, we will incorporate the required slots into the assignment as table data.
CREATE TABLE SlotTypes (
  slotType VARCHAR(255) NOT NULL,
  CONSTRAINT pk_SlotTypes_slotType PRIMARY KEY (slotType)
);

-- The 'Equippable' table acts as a parent table, encompassing both Gear and Weapon tables, which represent items that can be equipped in various slots.
CREATE TABLE Equippable (
  itemId INT NOT NULL,
  itemLevel INT NOT NULL,
  slotType VARCHAR(255) NOT NULL,
  requiredJobLevel INT,
  CONSTRAINT pk_Equippable_itemId PRIMARY KEY (itemId),
  CONSTRAINT fk_Equippable_itemId FOREIGN KEY (itemId)
    REFERENCES Item (itemId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Equippable_slotType FOREIGN KEY (slotType)
    REFERENCES SlotTypes (slotType)
    ON UPDATE CASCADE ON DELETE CASCADE
);

-- The 'Weapon' table contains information about weapon items.
CREATE TABLE Weapon (
  itemId INT NOT NULL,
  damageDone FLOAT NOT NULL,
  autoAttack FLOAT NOT NULL,
  attackDelay FLOAT NOT NULL,
  associatedJob INT NOT NULL,
  CONSTRAINT pk_Weapon_itemId PRIMARY KEY (itemId),
  CONSTRAINT fk_Weapon_itemId FOREIGN KEY (itemId)
    REFERENCES Equippable (itemId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Weapon_associatedJob FOREIGN KEY (associatedJob)
    REFERENCES Job (jobId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

-- The 'Gear' table contains information about gear items.
CREATE TABLE Gear (
  itemId INT NOT NULL,
  defenseRating FLOAT,
  magicDefenseRating FLOAT,
  CONSTRAINT pk_Gear_itemId PRIMARY KEY (itemId),
  CONSTRAINT fk_Gear_itemId FOREIGN KEY (itemId)
    REFERENCES Equippable (itemId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

-- The 'EquippableBonus' table stores attribute bonuses for Gear and Weapon items, enabling the identification of attributes associated with each item.
CREATE TABLE EquippableBonus (
  itemId INT NOT NULL,
  attribute VARCHAR(50) NOT NULL,
  bonusValue FLOAT NOT NULL,
  CONSTRAINT pk_EquippableBonus_itemId_attribute PRIMARY KEY (itemId,attribute),
  CONSTRAINT fk_EquippableBonus_itemId FOREIGN KEY (itemId)
    REFERENCES Equippable (itemId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_EquippableBonus_attribute FOREIGN KEY (attribute)
	REFERENCES Attributes (attribute)
	ON UPDATE CASCADE ON DELETE CASCADE
);

-- The 'GearJob' table contains information about which Jobs can equip Gear items.
CREATE TABLE GearJob(
  itemId INT NOT NULL,
  jobId INT NOT NULL,
  CONSTRAINT pk_GearJob_itemId_jobId PRIMARY KEY (itemId,jobId),
  CONSTRAINT fk_Gearjob_itemId FOREIGN KEY (itemId)
	REFERENCES  Gear(itemId)
	ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Gearjob_jobId FOREIGN KEY (jobId)
	REFERENCES Job(jobId)
	ON UPDATE CASCADE ON DELETE CASCADE
 );
 
 -- The 'CharacterAttribute' table stores attribute information for each character.
CREATE TABLE CharacterAttribute(
  characterId INT NOT NULL,
  attributeName VARCHAR(50) NOT NULL,
  attributeValue BIGINT NOT NULL,
  CONSTRAINT pk_CharacterAttribute_characterId_attributeName PRIMARY KEY (characterId,attributeName),
  CONSTRAINT fk_CharacterAttribute_characterId FOREIGN KEY (characterId)
	REFERENCES `Character`(characterId)
	ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_CharacterAttribute_attributeName FOREIGN KEY (attributeName)
	REFERENCES Attributes(attribute)
	ON UPDATE CASCADE ON DELETE CASCADE
 );
 
 -- The 'Colors' table stores the colors that can be customized in various equippable items by different characters.
  CREATE TABLE Colors(
    color VARCHAR(255) NOT NULL,
    CONSTRAINT pk_Colors_color PRIMARY KEY(color)
  );
  
-- 'Customization' records customized equippable items by different characters.
CREATE TABLE Customization(
  customizationId INT AUTO_INCREMENT,
  itemId INT NOT NULL,
  dyeColor VARCHAR(255),
  isHighQuality ENUM('High','Normal') NOT NULL,
  `condition` FLOAT NOT NULL,
  madeBy INT,
  CONSTRAINT pk_Customization_customizationId PRIMARY KEY(customizationId),
  CONSTRAINT fk_Customization_itemId FOREIGN KEY (itemId)
	REFERENCES Equippable(itemId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Customization_dyeColor FOREIGN KEY (dyeColor)
	REFERENCES Colors (color)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Customization_madeBy FOREIGN KEY (madeBy)
	REFERENCES `Character` (characterId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

-- The 'Inventory' table holds collections of items for each character, with each character having their dedicated slotId for item information.
CREATE TABLE Inventory (
  characterId INT NOT NULL,
  slotId INT NOT NULL,
  itemId INT NOT NULL,
  customizationId INT,
  quantity INT NOT NULL,
  CONSTRAINT pk_Inventory_characterId_slotId PRIMARY KEY (characterId, slotId),
  CONSTRAINT fk_Inventory_characterId FOREIGN KEY (characterId) 
    REFERENCES `Character` (characterId) 
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Inventory__itemId FOREIGN KEY (itemId) 
    REFERENCES Item(itemId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Inventory__customizationId FOREIGN KEY (customizationId)
    REFERENCES Customization (customizationId) 
    ON UPDATE CASCADE ON DELETE SET NULL
);

-- The 'CharacterJob' table contains data about various jobs available to each character, with 'isUnlocked' and 'isCurrentJob' used to indicate unlocked and current jobs, respectively.
CREATE TABLE CharacterJob (
  characterId INT NOT NULL,
  jobId INT NOT NULL,
  currentExp BIGINT NOT NULL,
  isUnlocked BOOLEAN DEFAULT FALSE,
  isCurrentJob BOOLEAN DEFAULT FALSE,
  CONSTRAINT pk_CharacterJob_characterId_jobId PRIMARY KEY (characterId, jobId),
  CONSTRAINT fk_CharacterJob_characterId FOREIGN KEY (characterId) 
    REFERENCES `Character` (characterId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_CharacterJob_JobId FOREIGN KEY (JobId) 
    REFERENCES Job (JobId) 
    ON UPDATE CASCADE ON DELETE CASCADE
);

-- The 'CharacterCurrency' table stores currency data for each character.
CREATE TABLE CharacterCurrency (
  characterId INT NOT NULL,
  currencyName VARCHAR(30) NOT NULL,
  amountOwned BIGINT NOT NULL,
  weeklyAmountOwned INT NOT NULL,
  CONSTRAINT pk_CharacterCurrency_characterId_currencyName PRIMARY KEY (characterId, currencyName), 
  CONSTRAINT fk_CharacterCurrency_characterId FOREIGN KEY (characterId) 
    REFERENCES `Character` (characterId) 
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_CharacterCurrency_currencyName FOREIGN KEY (currencyName) 
    REFERENCES Currency(currencyName) 
    ON UPDATE CASCADE ON DELETE CASCADE
);

-- The 'CharacterSlot' table records equipped items in different slots for each character.
CREATE TABLE CharacterSlot (
    characterId INT NOT NULL,
    slotType VARCHAR(255) NOT NULL,
    equippedItem INT NOT NULL,
    customization INT,
	CONSTRAINT pk_CharacterSlot_characterId_slotType PRIMARY KEY (characterId, slotType), 
    CONSTRAINT fk_CharacterSlot_characterId FOREIGN KEY (characterId)
      REFERENCES `Character`(characterId)
      ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_CharacterSlot_slotType FOREIGN KEY (slotType)
      REFERENCES slotTypes (slotType)
      ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_Inventory_equippedItem FOREIGN KEY (equippedItem) 
      REFERENCES Equippable(itemId) 
      ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT pk_CharacterSlot_customization  FOREIGN KEY (customization) 
      REFERENCES Customization(customizationId) 
      ON UPDATE CASCADE ON DELETE SET NULL
);


-- Insert some sample data
-- Insert data into the table 'Player'
INSERT INTO Player (accountId, `name`, emailAddress, isActive) 
  VALUES (1,	'Joe', 'joe@gamil.com', TRUE),
         (2,	'Bob', 'Bb123@gmail.com', FALSE),
         (33, 'Sherry', 'Sherry@northeastern.edu', TRUE),
         (90, 'Hello', 'Hhh@sina.com', TRUE),
         (529, 'Cool', 'coolcool@coo.com', FALSE),
         (1000, 'Kaden', 'Kaden112233@gmail.com', TRUE);
    
-- Insert data into the table 'Character'
INSERT INTO `Character` (characterId, accountId, characterFirstName, characterLastName) 
  VALUES (1,	33, 'Dragon', 'Li'),
         (2,	1, 'Fury', 'Zhang'),
         (4, 90, 'Iron', 'Chen'),
         (8, 33, 'Shadow', 'Li'),
         (100, 1000, 'Golden', 'Kong'),
         (200, 90, 'Panda', 'Wang'),
         (1000, 33, 'Tiger', 'Wang');
    
-- Insert data into the table 'Job'
INSERT INTO Job (jobName, jobLevel, MinLevelExp, MaxLevelExp) 
  VALUES ('King', 1, 0, 2000),
         ('King', 2, 2000, 5000),
         ('Knight', 1, 0, 1000),
         ('Civilian', 1, 0, 1000),
         ('Civilian', 2, 1000, 5000),
         ('Mage', 1, 0, 8000);

-- Insert data into the table 'Currency'
INSERT INTO Currency (currencyName, totalCap, weeklyCap, discontinued) 
  VALUES ('Gold', 1000000, 50000, FALSE),
         ('Silver', 2000000, 2000, FALSE),
         ('Bronze', 300000, 300, TRUE),
         ('Diamonds', 5000000, 100000, FALSE),
         ('Platinum', 1000000, 50000, FALSE);

-- Insert data into the table 'Attributesy'
INSERT INTO Attributes (attribute) 
  VALUES ('maxHP'),
         ('strength'),
         ('dexterity'),
         ('vitality'),
         ('intelligence'),
         ('mind'),
         ('criticalHit'),
		 ('determination'),
         ('directHitRate'),
         ('defense'),
         ('magicDefense'),
         ('attackPower'),
         ('SkillSpeed'),
         ('attackMagicPotency'),
         ('HealingMagicPotency'),
         ('spellSpeed'),
         ('averageItemLevel'),
         ('tenacity'),
         ('piety');
         
-- Insert data into the table 'Attributesy'
INSERT INTO Item (itemId,itemName,maxStackSize,vendorPrice,canbeSold)
  VALUES (1, 'Health Potion', 5, 90, TRUE),
		 (2, 'Energy Elixir', 10, 100, TRUE),
         (3, 'Speed Boost', 8, 101, TRUE),
         (4, 'Fire Scroll', 5, 102, TRUE),
         (5, 'Invisibility Potion', 10, 103, TRUE),
         (6, 'Treasure Map', 5, 104, TRUE),
         (7, 'Lockpick Set', 3, 105, TRUE),
         (8, 'Lucky Charm',3, 0, FALSE),
         (9, 'Ancient Relic', 5, 107, TRUE),
         (10, 'Enchanted Amulet', 5, 108, TRUE),
         (11, 'Sword', 5, 0, FALSE),
		 (12, 'Bow', 5, 110, TRUE),
         (13, 'War Hammer', 5, 0, FALSE),
         (14, 'Staff', 5, 112, TRUE),
         (15, 'Dagger', 5, 113, TRUE),
         (16, 'Plate Armor', 5, 114, TRUE),
         (17, 'Cloak', 5, 115, TRUE),
         (18, 'Amulet',5,116,TRUE),
         (19, 'Leather Boots', 5, 117, TRUE),
         (20, 'Helmet', 5, 118, TRUE);
         
-- Insert data into the table 'Consumable'
INSERT INTO Consumable (itemId,itemLevel,description)
  VALUE (1,1,'Restores 50 health points.'),
        (2,3,'Replenishes 30 energy.'),
        (3,2,'Increases movement speed.'),
        (4,5,'Casts a fireball spell.'),
        (5,1,'Makes you invisible.');

-- Insert data into the table 'Miscellaneous'
INSERT INTO Miscellaneous (itemId,description)
  VALUE (6,'Leads to hidden riches.'),
        (7,'Used to pick locks.'),
        (8,'Grants good luck for a short time.'),
        (9,'An artifact with unknown powers.'),
        (10,'Enhances your magical abilities.');
        
-- Insert data into the table 'ConsumableBonus'
INSERT INTO ConsumableBonus (itemId,attribute,bonusPercentage,bonusCap)
  VALUE (2,'SkillSpeed',8,100),
		(3,'attackPower',10,100),
        (2,'defense',10,100),
        (3,'SkillSpeed',30,300),
        (1,'tenacity',10,1000);

-- Insert sample data into table 'SlotTypes'
INSERT INTO SlotTypes (slotType)
  VALUE ('Main hand'),
		('head'),
		('hands'),
		('body'),
		('feet');

-- Insert sample data into table 'Equippable'
INSERT INTO Equippable (itemId,itemLevel,slotType,requiredJobLevel)
  VALUE (11,1,'Main hand', 1),
		(12,2,'hands',1),
		(13,2,'hands',1),
		(14,1,'Main hand',1),
		(15,3,'Main hand',2),
		(16,2,'body',1),
		(17,1,'body',1),
		(18,3,'hands',3),
		(19,2,'feet',2),
		(20,2,'head',2);

-- Insert sample data into table 'Weapon'
INSERT INTO Weapon (itemId,damageDone,autoAttack,attackDelay,associatedJob)
  VALUE (11,100,10,1,1),
		(12,100,5,2,1),
		(13,100,5,3,2),
		(14,100,10,3,3),
		(15,100,10,4,6);

-- Insert sample data into table 'Gear'
INSERT INTO Gear (itemId,defenseRating,magicDefenseRating)
  VALUE (16,100,50),
		(17,100,50),
		(18,100,50),
		(19,100,50),
		(20,100,50);

-- Insert sample data into table 'EquippableBonus'
INSERT INTO EquippableBonus (itemId,attribute,bonusValue)
  VALUE (11, 'strength',10),
		(12,'defense',100),
		(11,'attackPower',50),
		(12,'strength',100),
		(16,'defense',100);
        
-- Insert sample data into table 'GearJob'
INSERT INTO GearJob(itemId, jobId)
 VALUES (16,1),
	    (17,1),
	    (18,3),
	    (19,5),
	    (20,6);
 
-- Insert sample data into table 'CharacterAttribut'
INSERT INTO CharacterAttribute(characterId, attributeName,attributeValue)
  VALUES (1,'maxHP',100),
		 (2,'strength',100),
         (4,'dexterity',10),
         (4,'vitality',50),
         (8,'intelligence',10);

-- Insert sample data into table 'Colors'
INSERT INTO Colors(color)
  VALUES ('Red'),
		 ('Blue'),
         ('Yellow'),
         ('Purple'),
         ('Green');

-- Insert sample data into table 'Customization'
INSERT INTO Customization(customizationId,itemId,dyeColor,isHighQuality,`condition`,madeBy)
  VALUES (1,11,'Red','High',50,1),
		 (2,12,'Blue','Normal',20,4),
         (3,14,'Red','Normal',10,100),
         (4,17,'Red','Normal',20,1),
         (5,17,'Blue','High',20,1);


-- Insert sample data into table 'Inventory'
INSERT INTO Inventory (characterId, slotId, itemId, customizationId, quantity) 
  VALUES (1, 1, 11, 1, 5),
         (1, 2, 12, 1, 3),
         (100, 3, 13, 2, 2),
         (4, 4, 13, 3, 1),
         (8, 5, 13, 1, 1);
         
-- Insert sample data into table 'CharacterSlot'
INSERT INTO CharacterSlot (characterId, slotType, equippedItem, customization) 
  VALUES (1, 'Main hand', 11, 1),
         (2, 'hands', 12, NULL),
         (4, 'feet', 19, NULL),
         (1, 'hands', 17, 4),
         (100, 'head', 20, NULL);
	
-- Insert sample data into table 'CharacterJob'
INSERT INTO CharacterJob (characterId, jobId, currentExp, isUnlocked, isCurrentJob ) 
  VALUES (1, 1, 100,TRUE, TRUE),
         (2, 1, 100,TRUE, TRUE),
         (4, 4, 100,TRUE, TRUE),
         (1, 4, 100,FALSE, FALSE),
         (4, 3, 100,FALSE, FALSE);
         
-- Insert sample data into table 'CharacterCurrency'
INSERT INTO CharacterCurrency (characterId, currencyName, amountOwned, weeklyAmountOwned) 
  VALUES (1, 'Gold', 1000, 500),
         (2, 'Gold', 2000, 500),
         (1, 'Bronze', 3000, 500),
         (2, 'Bronze', 3000, 500),
         (4, 'Gold', 1000, 0);
   
