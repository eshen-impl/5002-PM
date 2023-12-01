package pm.dal;

import pm.model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * This is used to store {@link Equippable} into your MySQL instance and 
 * retrieve {@link Equippable} from MySQL instance.
 */

public class EquippableDao extends ItemDao {
	// Single pattern: instantiation is limited to one object.
	private static EquippableDao instance = null;
	
	protected EquippableDao() {
		super();
	}
	
	public static EquippableDao getInstance() {
		if(instance == null) {
			instance = new EquippableDao();
		}
		return instance;
	}
	
/**
 * 1. public Equippable create(Equippable equippable)
 */

	public Equippable create(Equippable equippable) throws SQLException {
		// Insert into the superclass table first.
		Item item = create((Item) equippable);
		
		
		String insertEquippable = "INSERT INTO Equippable(itemID,itemLevel,slotType,requiredJobLevel) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEquippable);
			insertStmt.setInt(1, item.getItemId());
			insertStmt.setInt(2, equippable.getItemLevel());
			insertStmt.setString(3, equippable.getSlotType());
			insertStmt.setInt(4, equippable.getRequiredJobLevel());
			
			insertStmt.executeUpdate();
			return equippable;
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
	 * 2.public Equippable getEquippableByItemID(int itemID)   
	 */
	public Equippable getEquippableByItemID(Integer itemID) throws SQLException {
		
		String selectEquippable =
			"SELECT Equippable.itemID AS ItemID,itemName,maxStackSize,vendorPrice,canBeSold,itemLevel,slotType,requiredJobLevel " +
			"FROM Equippable INNER JOIN Item ON Equippable.itemID = Item.itemID WHERE Equippable.itemID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEquippable);
			selectStmt.setInt(1, itemID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer resultitemID = results.getInt("itemID");
				String itemName = results.getString("itemName");
				Integer maxStackSize = results.getInt("maxStackSize");
				Integer vendorPrice = results.getInt("vendorPrice");
				Boolean canBeSold = results.getBoolean("canBeSold");
		        Integer itemLevel = results.getInt("itemLevel");
		        String slotType = results.getString("slotType");
				Integer requiredJobLevel = results.getInt("requiredJobLevel");
				
				Equippable equippable = new Equippable(resultitemID,itemName,maxStackSize, vendorPrice,canBeSold,
						itemLevel, slotType,requiredJobLevel);
				return equippable;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
   
	/**
	 * 3. public List< Equippable > getEquippableByItemLevel(Int itemLevel)
	 */
	public List<Equippable> getEquippableByItemLevel(Integer itemLevel)
			throws SQLException {
		List<Equippable> equippable = new ArrayList<Equippable>();
		String selectEquippable =
			"SELECT Equippable.itemID AS ItemID,itemName,maxStackSize,vendorPrice,canBeSold,itemLevel,slotType,requiredJobLevel " +
			"FROM Equippable INNER JOIN Item ON Equippable.itemID = Item.itemID WHERE Equippable.itemLevel=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEquippable);
			selectStmt.setInt(1, itemLevel);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Integer itemID = results.getInt("itemID");
				String itemName = results.getString("itemName");
				Integer maxStackSize = results.getInt("maxStackSize");
				Integer vendorPrice = results.getInt("vendorPrice");
				Boolean canBeSold = results.getBoolean("canBeSold");
		        Integer resultitemLevel = results.getInt("itemLevel");
		        String slotType = results.getString("slotType");
				Integer requiredJobLevel = results.getInt("requiredJobLevel");
				
				Equippable newEquippable = new Equippable(itemID,itemName,maxStackSize, vendorPrice,canBeSold,
						resultitemLevel, slotType,requiredJobLevel);
				equippable.add(newEquippable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return equippable;
	}	
}