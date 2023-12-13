package pm.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pm.model.*;
import pm.model.Character;

public class InventoryDao {
    
    protected ConnectionManager connectionManager;
    private static InventoryDao instance = null;
    protected InventoryDao() {
        connectionManager = new ConnectionManager();
    }
    public static InventoryDao getInstance() {
        if(instance == null) {
            instance = new InventoryDao();
        }
        return instance;
    }

    // Create method for inserting a new record
    public Inventory create(Inventory inventory) throws SQLException {
        String insert = "INSERT INTO Inventory (characterId, slotId, itemId, quantity,  customizationId) " +
                "VALUES (?, ?, ?, ?, ?)";
        String insertNoCst = "INSERT INTO Inventory (characterId, slotId, itemId, quantity) " +
                "VALUES (?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionManager.getConnection();
			if (inventory.getCustomization()!=null) {
				pstmt = connection.prepareStatement(insert);
				pstmt.setInt(5, inventory.getCustomization().getCustomizationId());
			} else {
				pstmt = connection.prepareStatement(insertNoCst);
			}
			
            pstmt.setInt(1, inventory.getCharacter().getCharacterId());
            pstmt.setInt(2, inventory.getSlotId());
            pstmt.setInt(3, inventory.getItem().getItemId());
            pstmt.setInt(4, inventory.getQuantity());
            pstmt.executeUpdate();
           

			return inventory;
		} catch (SQLException e) {
			        e.printStackTrace();
			        throw e;
			
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}

		}
  
    }

    // Method for fetching records based on search keys
    public List<Inventory> getInventoryByCharacterId(int characterId) throws SQLException {
        List<Inventory> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM Inventory WHERE characterId = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectQuery);
			selectStmt.setInt(1, characterId);
			results = selectStmt.executeQuery();
			while(results.next()) {
                Inventory inventory = mapResultSetToInventory(results);
                result.add(inventory);
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

        return result;
    }

    // Method for updating an attribute for a single record
    public Inventory updateQuantity(Inventory inventory, int newQuantity) throws SQLException {
        String updateQuery = "UPDATE Inventory SET quantity = ? WHERE characterId = ? AND slotId = ?";

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			 pstmt = connection.prepareStatement(updateQuery);
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, inventory.getCharacter().getCharacterId());
            pstmt.setInt(3, inventory.getSlotId());
            pstmt.executeUpdate();
            inventory.setQuantity(newQuantity);
			return inventory;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		}
        
    }

    // Method for deleting a record
    public void delete(Inventory inventory) throws SQLException {
        String deleteQuery = "DELETE FROM Inventory WHERE characterId = ? AND slotId = ?";

        
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			pstmt = connection.prepareStatement(deleteQuery);
            pstmt.setInt(1, inventory.getCharacter().getCharacterId());
            pstmt.setInt(2, inventory.getSlotId());
            pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		}
        
        
    }

    // Helper method to map ResultSet to Inventory object
    private Inventory mapResultSetToInventory(ResultSet resultSet) throws SQLException {
        Inventory inventory = new Inventory();
        CharacterDao characterDao = CharacterDao.getInstance();
        CustomizationDao customizationDao = CustomizationDao.getInstance();
        ItemDao itemDao = ItemDao.getInstance();
        inventory.setCharacter(characterDao.getCharacterById(resultSet.getInt("characterId")));
        inventory.setSlotId(resultSet.getInt("slotId"));
        inventory.setCustomization(customizationDao.getCustomizationById(resultSet.getInt("customizationId")));
        inventory.setItem(itemDao.getItemById(resultSet.getInt("itemId")));
        inventory.setQuantity(resultSet.getInt("quantity"));

        return inventory;
    }
}