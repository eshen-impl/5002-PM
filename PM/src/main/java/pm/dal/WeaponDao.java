package pm.dal;

import pm.model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




/**
 * This is used to store {@link Weapon} into your MySQL instance and 
 * retrieve {@link Weapon} from MySQL instance.
 */

public class WeaponDao extends EquippableDao {
	// Single pattern: instantiation is limited to one object.
	private static WeaponDao instance = null;

	protected WeaponDao() {
		super();
	}

	public static WeaponDao getInstance() {
		if(instance == null) {
			instance = new WeaponDao();
		}
		return instance;
	}

/**
 * 1. public Weapon create(Weapon weapon)
 */

	public Weapon create(Weapon weapon) throws SQLException {
		// Insert into the superclass table first.
		
		Equippable equippable = create((Equippable) weapon);


		String insertWeapon = "INSERT INTO Weapon(itemID,damageDone,autoAttack,attackDelay,associatedJob) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertWeapon);
            insertStmt.setInt(1, equippable.getItemId());
            insertStmt.setDouble(2, weapon.getDamageDone());
            insertStmt.setDouble(3, weapon.getAutoAttack());
            insertStmt.setDouble(4, weapon.getAttackDelay());
         // Check if associatedJob is not null before accessing properties
            insertStmt.setInt(5, (weapon.getAssociatedJob() != null) ? weapon.getAssociatedJob().getJobId() : null);

            insertStmt.executeUpdate();
            return weapon;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * 2.public Weapon getWeaponByItemID(int itemID)   
	 */

	public Weapon getWeaponByItemID(Integer itemID) throws SQLException {
	    String selectWeapon =
	            "SELECT Weapon.itemID AS ItemID, itemName, maxStackSize, vendorPrice, canBeSold, itemLevel, slotType, requiredJobLevel, damageDone, autoAttack, attackDelay, associatedJob " +
	            "FROM Weapon INNER JOIN Equippable ON Weapon.itemID = Equippable.itemID INNER JOIN Item ON Equippable.itemID = Item.itemID WHERE Weapon.itemID = ?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectWeapon);
	        selectStmt.setInt(1, itemID);
	        results = selectStmt.executeQuery();
	        if (results.next()) {
	            Integer resultItemID = results.getInt("ItemID");
	            String itemName = results.getString("itemName");
	            Integer maxStackSize = results.getInt("maxStackSize");
	            Integer vendorPrice = results.getInt("vendorPrice");
	            Boolean canBeSold = results.getBoolean("canBeSold");
	            Integer itemLevel = results.getInt("itemLevel");
	            String slotType = results.getString("slotType");
	            Integer requiredJobLevel = results.getInt("requiredJobLevel");
	            Double damageDone = results.getDouble("damageDone");
	            Double autoAttack = results.getDouble("autoAttack");
	            Double attackDelay = results.getDouble("attackDelay");
	            JobDao jobDao = new JobDao();
	            Job associatedJob = jobDao.getJobByID(results.getInt("associatedJob"));

	            Weapon weapon = new Weapon(resultItemID, itemName, maxStackSize, vendorPrice, canBeSold,
	                    itemLevel, slotType, requiredJobLevel, damageDone, autoAttack, attackDelay, associatedJob);
	            return weapon;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (connection != null) {
	            connection.close();
	        }
	        if (selectStmt != null) {
	            selectStmt.close();
	        }
	        if (results != null) {
	            results.close();
	        }
	    }
	    return null;
	}
}