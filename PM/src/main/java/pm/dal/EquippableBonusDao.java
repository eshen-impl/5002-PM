package pm.dal;

import pm.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EquippableBonusDao {
	protected ConnectionManager connectionManager;

	private static EquippableBonusDao instance = null;
	protected EquippableBonusDao() {
		connectionManager = new ConnectionManager();
	}
	public static EquippableBonusDao getInstance() {
		if(instance == null) {
			instance = new EquippableBonusDao();
		}
		return instance;
	}

	/**
	 * 1. public EquippableBonus create(EquippableBonus equippableBonus)
	 */
	public EquippableBonus create(EquippableBonus equippableBonus) throws SQLException {
		String insertEquippableBonus =
			"INSERT INTO EquippableBonus(itemID, attribute, bonusValue) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEquippableBonus);
			insertStmt.setInt(1, equippableBonus.getItemID().getItemId());
			insertStmt.setString(2, equippableBonus.getAttribute());
			insertStmt.setInt(3, equippableBonus.getBonusValue());
			insertStmt.executeUpdate();
			
			return equippableBonus;
		}  catch (SQLException e) {
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
	 * 2. public EquippableBonus getEquippableBonusByItemIDAndAttribute(ItemID itemID, Attribute attribute)   
	 */
	public EquippableBonus getEquippableBonusByItemIDAndAttribute(Item item, String attribute) throws SQLException {
		String selectEquippableBonus =
			"SELECT EquippableBonus.itemID AS ItemID, itemName, maxStackSize, vendorPrice, canBeSold, itemLevel, slotType, requiredJobLevel,attribute,bonusValue " +
			"FROM EquippableBonus INNER JOIN Equippable ON EquippableBonus.itemID = Equippable.itemID INNER JOIN Item ON EquippableBonus.itemID = Item.itemID WHERE EquippableBonus.itemID = ? AND EquippableBonus.attribute = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEquippableBonus);
			selectStmt.setInt(1, item.getItemId());
			selectStmt.setString(2, attribute);
			
			results = selectStmt.executeQuery();
			
			ItemDao itemDao = ItemDao.getInstance();
			
			if(results.next()) {
				Integer resultitemID = results.getInt("itemID");
				String resultattribute = results.getString("attribute");
				Integer bonusValue = results.getInt("bonusValue");
				
                Item fetchedItem = itemDao.getItemById(resultitemID);
                
               
                EquippableBonus equippableBonus = new EquippableBonus(fetchedItem,resultattribute, bonusValue);
				return equippableBonus;
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
	 * 3.public List< EquippableBonus > getEquippableBonusByItemID(Item item)
	 */
	public List<EquippableBonus> getEquippableBonusByItemID(Item item) throws SQLException {
		List<EquippableBonus> equippableBonusList = new ArrayList< >();
		String selectEquippableBonus =
				"SELECT EquippableBonus.itemID AS ItemID, itemName, maxStackSize, vendorPrice, canBeSold, itemLevel, slotType, requiredJobLevel,attribute,bonusValue " +
				"FROM EquippableBonus INNER JOIN Equippable ON EquippableBonus.itemID = Equippable.itemID INNER JOIN Item ON EquippableBonus.itemID = Item.itemID WHERE EquippableBonus.itemID = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEquippableBonus);
			selectStmt.setInt(1, item.getItemId());
			results = selectStmt.executeQuery();
			
			ItemDao itemDao = ItemDao.getInstance();

			while(results.next()) {
				Integer resultitemID = results.getInt("itemID");
				String resultattribute = results.getString("attribute");
				Integer bonusValue = results.getInt("bonusValue");
				
                Item fetchedItem = itemDao.getItemById(resultitemID);
				
                EquippableBonus equippableBonus = new EquippableBonus(fetchedItem, resultattribute, bonusValue);
                equippableBonusList.add(equippableBonus);
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
		return equippableBonusList;
	}
}