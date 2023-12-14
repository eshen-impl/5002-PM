package pm.dal;
import pm.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterSlotDao {
    protected ConnectionManager connectionManager;
    private static CharacterSlotDao instance = null;
    protected CharacterSlotDao() {
        connectionManager = new ConnectionManager();
    }
    public static CharacterSlotDao getInstance() {
        if(instance == null) {
            instance = new CharacterSlotDao();
        }
        return instance;
    }


    // Create method for inserting a new record
    public CharacterSlot create(CharacterSlot characterSlot) throws SQLException {
        
        String insert = "INSERT INTO CharacterSlot (characterId, slotType, equippedItem, customization) " +
                "VALUES (?, ?, ?, ?)";
        String insertNoCst = "INSERT INTO CharacterSlot (characterId, slotType, equippedItem) " +
                "VALUES (?, ?, ?)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionManager.getConnection();
			if (characterSlot.getCustomization()!=null) {
				pstmt = connection.prepareStatement(insert);
				pstmt.setInt(4, characterSlot.getCustomization().getCustomizationId());
			} else {
				pstmt = connection.prepareStatement(insertNoCst);
			}
			
	           pstmt.setInt(1, characterSlot.getCharacter().getCharacterId());
	            pstmt.setString(2, characterSlot.getSlotType());
	            pstmt.setInt(3, characterSlot.getEquippedItem().getItemId());

            pstmt.executeUpdate();
           

			return characterSlot;
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
    public List<CharacterSlot> getCharacterSlotsByCharacterId(int characterId) throws SQLException {
        List<CharacterSlot> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM CharacterSlot WHERE characterId = ?";
    	Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectQuery);
			selectStmt.setInt(1, characterId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				CharacterSlot characterSlot = mapResultSetToCharacterSlot(results);
                result.add(characterSlot);
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
    
    public CharacterSlot getCharacterSlotByCharacterIdandSlotType(int characterId, String slot) throws SQLException {
        
        String selectQuery = "SELECT * FROM CharacterSlot WHERE characterId = ? and slotType = ?";
    	Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectQuery);
			selectStmt.setInt(1, characterId);
			selectStmt.setString(2, slot);
			results = selectStmt.executeQuery();
			if(results.next()) {
				CharacterSlot characterSlot = mapResultSetToCharacterSlot(results);
				return characterSlot;
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

    // Method for updating an attribute for a single record
    public CharacterSlot updateEquippedItem(CharacterSlot characterSlot, Equippable newEquippedItem) throws SQLException {
        String updateQuery = "UPDATE CharacterSlot SET equippedItem = ? WHERE characterId = ? AND slotType = ?";

        
        Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			 pstmt = connection.prepareStatement(updateQuery);
	            pstmt.setInt(1, newEquippedItem.getItemId());
	            pstmt.setInt(2, characterSlot.getCharacter().getCharacterId());
	            pstmt.setString(3, characterSlot.getSlotType());
            pstmt.executeUpdate();
            characterSlot.setEquippedItem(newEquippedItem);;
			return characterSlot;
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
    public void delete(CharacterSlot characterSlot) throws SQLException {
        String deleteQuery = "DELETE FROM CharacterSlot WHERE characterId = ? AND slotType = ?";

        
        Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			pstmt = connection.prepareStatement(deleteQuery);
            pstmt.setInt(1, characterSlot.getCharacter().getCharacterId());
            pstmt.setString(2, characterSlot.getSlotType());
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

    // Helper method to map ResultSet to CharacterSlot object
    private CharacterSlot mapResultSetToCharacterSlot(ResultSet resultSet) throws SQLException {
        CharacterSlot characterSlot = new CharacterSlot();
        
        CharacterDao characterDao = CharacterDao.getInstance();
        CustomizationDao customizationDao = CustomizationDao.getInstance();
        WeaponDao weaponDao = WeaponDao.getInstance();
        GearDao gearDao = GearDao.getInstance();
        
        characterSlot.setCharacter(characterDao.getCharacterById(resultSet.getInt("characterId")));
        String slotType = resultSet.getString("slotType");
        characterSlot.setSlotType(slotType);
        if (slotType.equals("Main hand")) {
        	characterSlot.setEquippedItem(weaponDao.getWeaponByItemID(resultSet.getInt("equippedItem")));
        } else {
        	characterSlot.setEquippedItem(gearDao.getGearByItemID(resultSet.getInt("equippedItem")));
        }
        
        characterSlot.setCustomization(customizationDao.getCustomizationById(resultSet.getInt("customization")));
        return characterSlot;

    }
}