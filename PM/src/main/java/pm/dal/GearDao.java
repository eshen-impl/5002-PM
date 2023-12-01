package pm.dal;

import pm.model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is used to store {@link Gear} into your MySQL instance and 
 * retrieve {@link Gear} from MySQL instance.
 */

public class GearDao extends EquippableDao {
	// Single pattern: instantiation is limited to one object.
	private static GearDao instance = null;

	protected GearDao() {
		super();
	}

	public static GearDao getInstance() {
		if(instance == null) {
			instance = new GearDao();
		}
		return instance;
	}

/**
 * 1. public Gear create(Gear gear)
 */

	public Gear create(Gear gear) throws SQLException {
		// Insert into the superclass table first.
		
		
		Equippable equippable = create((Equippable) gear);


		String insertGear = "INSERT INTO Gear(itemID,defenseRating,magicDefenseRating) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertGear);
            insertStmt.setInt(1, equippable.getItemId());
            insertStmt.setDouble(2, gear.getDefenseRating());
            insertStmt.setDouble(3, gear.getMagicDefenseRating());

            insertStmt.executeUpdate();
            return gear;

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
	 * 2.public Gear getGearByItemID(int itemID)     
	 */

	public Gear getGearByItemID(Integer itemID) throws SQLException {
	    String selectGear =
	            "SELECT Gear.itemId AS itemId, itemName, maxStackSize, vendorPrice, canBeSold, itemLevel, slotType, requiredJobLevel,defenseRating,magicDefenseRating " +
	            "FROM Gear INNER JOIN Equippable ON Gear.itemId = Equippable.itemId INNER JOIN Item ON Equippable.itemId = Item.itemId WHERE Gear.itemId = ?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectGear);
	        selectStmt.setInt(1, itemID);
	        results = selectStmt.executeQuery();
	        if (results.next()) {
	            Integer resultItemID = results.getInt("itemId");
	            String itemName = results.getString("itemName");
	            Integer maxStackSize = results.getInt("maxStackSize");
	            Integer vendorPrice = results.getInt("vendorPrice");
	            Boolean canBeSold = results.getBoolean("canBeSold");
	            Integer itemLevel = results.getInt("itemLevel");
	            String slotType = results.getString("slotType");
	            Integer requiredJobLevel = results.getInt("requiredJobLevel");
	            Double defenseRating = results.getDouble("defenseRating");
	            Double magicDefenseRating = results.getDouble("magicDefenseRating");


	            Gear gear = new Gear(resultItemID, itemName, maxStackSize, vendorPrice, canBeSold,
	                    itemLevel, slotType, requiredJobLevel, defenseRating,magicDefenseRating);
	            return gear;
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

	/**
	 * 3. public void delete(Gear itemID)
	 */

	public Gear delete(Gear gear) throws SQLException {
		String deleteGear = "DELETE FROM Gear WHERE itemID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGear);
			deleteStmt.setInt(1, gear.getItemId());
			Integer affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for itemID=" + gear.getItemId());
			}	
			//I want to delete only the Gear records for a given Gear.

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}